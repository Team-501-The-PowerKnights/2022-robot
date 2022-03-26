/*-----------------------------------------------------------------------*/
/* Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by other FRC teams  */
/* under the terms of the Team501 license. The code must be accompanied  */
/* by the Team 501 - The PowerKnights license file in the root directory */
/* of this project.                                                      */
/*-----------------------------------------------------------------------*/

// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;


import java.util.ArrayList;
import java.util.List;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.WaitCommand;

import frc.robot.commands.modes.AutoDoNothing;
import frc.robot.commands.PKParallelCommandGroup;
import frc.robot.commands.PKSequentialCommandGroup;
import frc.robot.commands.climber.ClimberStateMachine;
import frc.robot.commands.drive.DriveForwardTimed;
import frc.robot.commands.drive.DriveTrajectory;
import frc.robot.commands.intake.IntakeIngestTimed;
import frc.robot.commands.poses.FirePoseVision;
import frc.robot.commands.turret.TurretVisionAlign;
import frc.robot.modules.IModule;
import frc.robot.modules.ModulesFactory;
import frc.robot.preferences.PreferencesManager;
import frc.robot.properties.PropertiesManager;
import frc.robot.sensors.ISensor;
import frc.robot.sensors.SensorsFactory;
import frc.robot.telemetry.SchedulerProvider;
import frc.robot.telemetry.TelemetryManager;
import frc.robot.telemetry.TelemetryNames;
import frc.robot.subsystems.ISubsystem;
import frc.robot.subsystems.SubsystemsFactory;

import riolog.PKLogger;
import riolog.RioLogger;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

    /* Our classes logger */
    private static final PKLogger logger = RioLogger.getLogger(Robot.class.getName());

    private OI oi;

    // Handle to telemetry manager
    private TelemetryManager tlmMgr;
    // Periodic runnable to do the reporting off main loop
    private Runnable telemetryReporter = new Runnable() {

        @Override
        public void run() {
            // Update the telemetry
            tlmMgr.sendTelemetry();
        }

    };

    //
    private List<IModule> modules;
    //
    private List<ISensor> sensors;
    //
    private List<ISubsystem> subsystems;

    //
    private List<IModeFollower> followers;

    // Flag for started/running autonomous part of match
    @SuppressWarnings("unused")
    private boolean autonomousRunning;
    // Flag for having run first autonomous loop
    private boolean autonomousFirstRun;
    // Flag for having completed autonomous part of match
    private static boolean autonomousComplete;

    // Flag for having started/running teleop part of match
    private boolean teleopRunning;
    // Flag for having run first teleop loop
    private boolean teleopFirstRun;
    // Flag for having completed teleop part of match
    private static boolean teleopComplete;

    // Flag for in end game of match
    private static boolean endGameStarted;
    // Periodic runnable to do the determining off main loop
    private Runnable endGameDeterminer = new Runnable() {

        @Override
        public void run() {
            // Have to have field connected, otherwise remaining seconds counts up
            // Have to be running teleop
            // Have to not have triggered the end game start yet
            if (isFieldConnected() && teleopRunning && !endGameStarted) {
                double remainingSeconds = DriverStation.getMatchTime();
                if (remainingSeconds <= 40) {
                    endGameStarted = true;
                    SmartDashboard.putBoolean(TelemetryNames.Misc.endGameStarted, endGameStarted);
                }
            }
        }

    };

    // Handle to climber state machine
    private ClimberStateMachine climberSM;

    // Chooser for autonomous command from Dashboard
    private SendableChooser<Command> autoChooser;
    // Command that was selected
    private Command autoCommand;

    // Chooser for overriding field connection in pit
    private static SendableChooser<Boolean> fmsOverrideChooser;

    // Capture the period at start (shouldn't ever change)
    private static double loopPeriod;

    public static double shooterSetSpeed;

    /**
     * Constucts an instance of the robot to play the match.
     */
    public Robot() {
        loopPeriod = getPeriod();
    }

    /**
     * This function is run when the robot is first started up and should be used
     * for any initialization code.
     */
    @Override
    public void robotInit() {
        logger.info("initializing");

        shooterSetSpeed = 0.4665;

        // Wait until we get the configuration data from driver station
        waitForDriverStationData();

        // Get the loop period set for robot
        loopPeriod = getPeriod();

        // Initialize the dashboard to false for status
        SmartDashboard.putBoolean(TelemetryNames.Misc.initStatus, false);

        // Make sure Preferences are initialized
        intializePreferences();

        // Make sure Properties file exists and can be parsed
        initializeProperties();

        // Create telemetry manager
        TelemetryManager.constructInstance();
        tlmMgr = TelemetryManager.getInstance();

        // Create command manager
        SchedulerProvider.constructInstance();
        tlmMgr.addProvider(SchedulerProvider.getInstance());

        // Creat the OI "subsystem"
        OI.constructInstance();
        tlmMgr.addProvider(OI.getInstance());
        
        // Add all the mode followers (need to be in order of creation)
        followers = new ArrayList<>();

        // Create all the modules
        modules = ModulesFactory.constructModules();
        followers.addAll(modules);
        // Create all the sensors
        sensors = SensorsFactory.constructSensors();
        followers.addAll(sensors);
        // Create all the subsystems
        subsystems = SubsystemsFactory.constructSubsystems();
        followers.addAll(subsystems);

        // Add the OI to mode followers
        followers.add(OI.getInstance());

        // TODO: Put in the Climber subsystem before default commands
        // Construct the instance of climber state machine
        ClimberStateMachine.constructInstance();
        climberSM = ClimberStateMachine.getInstance();

        // Create the chooser for autonomous command
        createAutoChooser();

        // Initialize state variables
        autonomousRunning = false;
        autonomousFirstRun = false;
        autonomousComplete = false;
        teleopRunning = false;
        teleopFirstRun = false;
        teleopComplete = false;

        // Create the chooser for FMS connected override
        createFmsOverrideChooser();

        // Put indication of initialization status on dash
        determineInitStatus();

        // Set up end game determiner
        endGameStarted = false;
        SmartDashboard.putBoolean(TelemetryNames.Misc.endGameStarted, endGameStarted);
        addPeriodic(endGameDeterminer, 2.0);

        // Set up the telemetry reporter
        addPeriodic(telemetryReporter, 5 * getLoopPeriod());

        logger.info("initialized");
    }

    /**
     * Holds the constructor until we receive at least one update of the control
     * data, which holds the run-spinTime configuration.
     **/
    private void waitForDriverStationData() {
        long count = 0;
        while (!DriverStation.isNewControlData()) {
            if ((count % 100) == 0) {
                logger.trace("Waiting ...");
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                logger.error("exception for sleep: ", ex);
            }
            count++;
        }
    }

    private void intializePreferences() {
        // Reads and initializes all subsystems preferences
        PreferencesManager.constructInstance();

        logger.info("Preferences as initialized:");
        PreferencesManager.getInstance().logPreferences(logger);
    }

    private void initializeProperties() {
        // Reads and stores all the properties
        PropertiesManager.constructInstance();

        logger.info("Properties as initialized:");
        PropertiesManager.getInstance().logProperties(logger);
    }

    private void createAutoChooser() {
        autoChooser = new SendableChooser<>();

        //
        autoChooser.setDefaultOption("Do Nothing", new AutoDoNothing());

        // LinearTest
        autoChooser.addOption("General 2 Ball", 
            new PKParallelCommandGroup(new TurretVisionAlign(),
                                       new PKSequentialCommandGroup(new PKParallelCommandGroup(new IntakeIngestTimed(5.0),
                                                                                               new PKSequentialCommandGroup(new WaitCommand(1.0), new DriveTrajectory("LinearTest")),
                                                                                               new FirePoseVision()
                                                                                              )
                                                                   )
                                      )
                             );

        // HangerSideFirstBall
        autoChooser.addOption("Hangar Side 2 Ball", 
            new PKParallelCommandGroup(new TurretVisionAlign(),
                                       new PKSequentialCommandGroup(new PKParallelCommandGroup(new IntakeIngestTimed(5.0),
                                                                                               new PKSequentialCommandGroup(new WaitCommand(1.0), new DriveTrajectory("HangarSideFirstBall")),
                                                                                               new FirePoseVision()
                                                                                               )
                                                                   )
                                      )
                             );

         // WallSideFirstBall
         autoChooser.addOption("Wall Side 2 Ball", 
         new PKParallelCommandGroup(new TurretVisionAlign(),
                                    new PKSequentialCommandGroup(new PKParallelCommandGroup(new IntakeIngestTimed(5.0),
                                                                                            new PKSequentialCommandGroup(new WaitCommand(1.0), new DriveTrajectory("WallSideFirstBall")),
                                                                                            new FirePoseVision()
                                                                                            )
                                                                )
                                   )
                          );

        // autoChooser.addOption("Wall Side 2 Ball Then Turn", new PKParallelCommandGroup(new TurretVisionAlign(),
        //                         new PKSequentialCommandGroup(new PKParallelCommandGroup(new IntakeIngestTimed(5.0),
        //                                 new PKSequentialCommandGroup(new WaitCommand(1.0), new DriveTrajectory("WallSideFirstBallThenTurn")),
        //                                 new FirePoseVision()))));

        autoChooser.addOption("Full Auto (Driving Forward Delay)",
            new PKParallelCommandGroup(new TurretVisionAlign(),
                                        new PKSequentialCommandGroup(new PKParallelCommandGroup(new IntakeIngestTimed(4.0),
                                                                                                new PKSequentialCommandGroup(new WaitCommand(1.0), new DriveForwardTimed(3.0)),
                                                                                                new FirePoseVision()
                                                                                               )
                                                                    )
                                      )
                             );

        // FIXME: This only works because default shooter command is idle

        // FIXME: Make parameterized like distance
        // autoChooser.addOption("Drive Forward (4 sec)", new DriveForwardTimed(4.0));
        // autoChooser.addOption("Drive Forward (3 feet)", new DriveForwardDistance(3.0));
        // autoChooser.addOption("Shoot and Drive Forward (3 feet)",
        //         new PKParallelCommandGroup(new ElevatorLift(), new DriveForwardDistance(3)));
        // autoChooser.addOption("Shoot and Drive Forward (4 sec)",
        //         new PKParallelCommandGroup(new ElevatorLift(), new DriveForwardTimed(4.0)));

        // autoChooser.addOption("Drive Backward (4 sec)", new DriveBackwardTimed(4.0));
        // autoChooser.addOption("Drive Backward (3 feet)", new DriveBackwardDistance(3));
        // autoChooser.addOption("Shoot and Drive Backward (3 feet)",
        //         new PKParallelCommandGroup(new ElevatorLift(), new DriveBackwardDistance(3)));
        // autoChooser.addOption("Shoot and Drive Backward (4 sec)",
        //         new PKParallelCommandGroup(new ElevatorLift(), new DriveBackwardTimed(4.0)));

        // autoChooser.addOption("Drive Straight Trajectory", new DriveTrajectory("StraightLine"));
        // autoChooser.addOption("Linear Test Trajectory", new DriveTrajectory("LinearTest"));

        SmartDashboard.putData("Auto Mode", autoChooser);
    }

    private void determineInitStatus() {
        // TODO: Make tri-color status when implemented
        long warnCount = logger.getWarnCount();
        long errorCount = logger.getErrorCount();
        logger.info("init status: errorCount={}, warnCount={}", errorCount, warnCount);
        // red for bad, green for good (so reverse sense)
        boolean status = !((errorCount != 0) || (warnCount != 0));
        SmartDashboard.putBoolean(TelemetryNames.Misc.initStatus, status);

        // TODO: Parse network tables for all status and do a roll-up
    }

    /**
     * This function is called every robot packet, no matter the mode. Use this for
     * items like diagnostics that you want ran during disabled, autonomous,
     * teleoperated and test.6+++++6
     *
     * <p>
     * This runs after the mode specific periodic functions, but before LiveWindow
     * and SmartDashboard integrated updating.
     */
    @Override
    public void robotPeriodic() {
        // Runs the Scheduler. This is responsible for polling buttons, adding
        // newly-scheduled commands, running already-scheduled commands, removing
        // finished or interrupted commands, and running subsystem periodic()
        // methods. This must be called from the robot's periodic block in order
        // for anything in the Command-based framework to work.
        CommandScheduler.getInstance().run();
    }

    /**
     * This function is called once each time the robot enters Disabled mode.
     */
    @Override
    public void disabledInit() {
        logger.info("disabling");

        for (IModeFollower f : followers) {
            f.disabledInit();
        }

        if (isMatchComplete()) {
            logger.info("match complete");

            logFinalVisionData();

            logFinalPreferences();

            logMatchData();

            logErrorCounts();

            for (IModule m : modules) {
                m.disable();
            }
            for (ISensor s : sensors) {
                s.disable();
            }
            for (ISubsystem s : subsystems) {
                s.disable();
            }
        }

        // (Re-)initialize end game state
        endGameStarted = false;
        SmartDashboard.putBoolean(TelemetryNames.Misc.endGameStarted, endGameStarted);

        logger.info("disabled");
    }

    /**
     * Log the data associated with the vision to the tail of the log file.
     **/
    private void logFinalVisionData() {
        logger.info("vision data:");
    }

    /**
     * Log the data associated with the preferences to the tail of the log file.
     **/
    private void logFinalPreferences() {
        logger.info("preferences:");
        PreferencesManager.getInstance().logPreferences(logger);
    }

    /**
     * Log the data associated with the match to the tail of the log file. This
     * allows us to easily determine whether it is a real match, and what match it
     * was.
     **/
    private void logMatchData() {
        logger.info("EventName:     {}", DriverStation.getEventName());
        logger.info("MatchType:     {}", DriverStation.getMatchType());
        logger.info("MatchNumber:   {}", DriverStation.getMatchNumber());
        logger.info("ReplayNumber:  {}", DriverStation.getReplayNumber());
        logger.info("Alliance:      {}", DriverStation.getAlliance());
        logger.info("Location:      {}", DriverStation.getLocation());
    }

    /**
     * Log the count of errors and warnings from the logger to the tail of the
     * log file.
     */
    private void logErrorCounts() {
        long warnCount = logger.getWarnCount();
        long errorCount = logger.getErrorCount();
        logger.info("error counts: errorCount={}, warnCount={}", errorCount, warnCount);
    }

    /**
     * This function is called periodically during Disabled mode.
     */
    @Override
    public void disabledPeriodic() {
        // Add an indicator about what auto command is current selected
        String doNothingName = AutoDoNothing.class.getSimpleName();
        SmartDashboard.putBoolean(TelemetryNames.Misc.realAuto,
                !autoChooser.getSelected().getName().equalsIgnoreCase(doNothingName));
    }

    /**
     * This function is called once each time the robot exits Disabled mode.
     */
    @Override
    public void disabledExit() {
        for (IModeFollower f : followers) {
            f.disabledExit();
        }
    }

    /**
     * This autonomous runs the autonomous command selected by your
     * frc.robot.RobotContainer class.
     */
    @Override
    public void autonomousInit() {
        logger.info("initializing autonomous");
        autonomousRunning = true;
        autonomousFirstRun = false;
        autonomousComplete = false;
        
        // Cancel any running commands so they exit the scheduler
        CommandScheduler.getInstance().cancelAll();

        // Initialize autonomous everywhere (which sets default commands)
        for (IModeFollower f : followers) {
            f.autonomousInit();
        }

        autoCommand = autoChooser.getSelected();
        logger.info("auto command is {}", autoCommand.getName());
        if (autoCommand != null) {
            CommandScheduler.getInstance().schedule(true, autoCommand);
        }

        logger.info("initialized autonomous");
    }

    /**
     * This function is called periodically during autonomous.
     */
    @Override
    public void autonomousPeriodic() {
        if (!autonomousFirstRun) {
            autonomousFirstRun = true;
            logger.info("first run of autonomous periodic");
        }
    }

    /**
     * This function is called once each time the robot exits Autonomous mode.
     */
    @Override
    public void autonomousExit() {
        logger.info("exiting autonomous");

        autonomousRunning = false;
        autonomousComplete = true;

        for (IModeFollower f : followers) {
            f.autonomousExit();
        }

        logger.info("exited autonomous");
    }

    /**
     * This function is called once each time the robot enters Teleop mode.
     */
    @Override
    public void teleopInit() {
        logger.info("initializing teleop");
        teleopRunning = true;
        teleopFirstRun = false;
        teleopComplete = false;

        // This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autoCommand != null) {
            autoCommand.cancel();
        }
                
        // Cancel any running commands so they exit the scheduler
        // (this includes the auto command)
        CommandScheduler.getInstance().cancelAll();

        // Initialize autonomous everywhere (which sets default commands)
        for (IModeFollower f : followers) {
            f.teleopInit();
        }

        // Initialize the climber state manager (only valid in teleop)
        climberSM.initialize();

        logger.info("initialized teleop");
    }

    /**
     * This function is called periodically during operator control.
     */
    @Override
    public void teleopPeriodic() {
        if (!teleopFirstRun) {
            teleopFirstRun = true;
            logger.info("first run of teleop periodic");
        }

        if (endGameStarted && !climberSM.isClimberEnabled()) {
            climberSM.enableClimberSequencing();
        }
    }

    /**
     * This function is called once each time the robot exits Teleop mode.
     */
    @Override
    public void teleopExit() {
        logger.info("exiting teleop");

        teleopRunning = false;
        teleopComplete = true;

        for (IModeFollower f : followers) {
            f.teleopExit();
        }

        // Disable the climber sequencing as we're done
        climberSM.disableClimberSequencing();

        logger.info("exited teleop");
    }

    /**
     * This function is called once each time the robot enters Test mode.
     */
    @Override
    public void testInit() {
        logger.info("initializing test");

        // Cancels all running commands at the start of test mode.
        CommandScheduler.getInstance().cancelAll();

        for (IModeFollower f : followers) {
            f.testInit();
        }

        logger.info("initialized test");
    }

    /**
     * This function is called periodically during Test mode.
     */
    @Override
    public void testPeriodic() {
    }

    /**
     * This function is called once each time the robot exits Test mode.
     */
    @Override
    public void testExit() {
        logger.info("exiting test");

        for (IModeFollower f : followers) {
            f.testExit();
        }

        logger.info("exited test");
    }

    private static void createFmsOverrideChooser() {
        fmsOverrideChooser = new SendableChooser<>();

        fmsOverrideChooser.setDefaultOption("Use Real FMS Connect", Boolean.FALSE);
        fmsOverrideChooser.addOption("Override FMS Connect", Boolean.TRUE);

        SmartDashboard.putData("FMS Override", fmsOverrideChooser);
    }

    static public boolean isFieldConnected() {
        if (DriverStation.isFMSAttached()) {
            return true;
        } else {
            return fmsOverrideChooser.getSelected();
        }
    }

    static public double getLoopPeriod() {
        return loopPeriod;
    }

    static public boolean isEndGameStarted() {
        return endGameStarted;
    }

    static public boolean isMatchComplete() {
        return (autonomousComplete && teleopComplete);
    }

}
