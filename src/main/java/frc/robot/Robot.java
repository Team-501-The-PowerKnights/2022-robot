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


import java.util.List;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.WaitCommand;

import frc.robot.commands.DoNothing;
import frc.robot.commands.FirePoseVision;
import frc.robot.commands.PKParallelCommandGroup;
import frc.robot.commands.PKSequentialCommandGroup;
import frc.robot.commands.drive.DriveBackwardDistance;
import frc.robot.commands.drive.DriveBackwardTimed;
import frc.robot.commands.drive.DriveForwardDistance;
import frc.robot.commands.drive.DriveForwardTimed;
import frc.robot.commands.elevator.ElevatorLift;
import frc.robot.commands.intake.IntakeIngest;
import frc.robot.commands.turret.TurretVisionAlign;
import frc.robot.modules.IModule;
import frc.robot.modules.ModuleFactory;
import frc.robot.preferences.PreferencesInitializer;
import frc.robot.properties.PropertiesManager;
import frc.robot.sensors.ISensor;
import frc.robot.sensors.SensorFactory;
import frc.robot.telemetry.SchedulerProvider;
import frc.robot.telemetry.TelemetryManager;
import frc.robot.telemetry.TelemetryNames;
import frc.robot.telemetry.TelemetryNames.Preferences;
import frc.robot.utils.PKStatus;
import frc.robot.subsystems.ISubsystem;
import frc.robot.subsystems.SubsystemFactory;

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

    private TelemetryManager tlmMgr;

    //
    private List<IModule> modules;
    //
    private List<ISensor> sensors;
    //
    private List<ISubsystem> subsystems;

    // Flag for started/running autonomous part of match
    @SuppressWarnings("unused")
    private boolean autonomousRunning;
    // Flag for having run first autonomous loop
    private boolean autonomousFirstRun;
    // Flag for having completed autonomous part of match
    private boolean autonomousComplete;

    // Flag for having started/running teleop part of match
    @SuppressWarnings("unused")
    private boolean teleopRunning;
    // Flag for having run first teleop loop
    private boolean teleopFirstRun;
    // Flag for having completed teleop part of match
    private boolean teleopComplete;

    // Chooser for autonomous command from Dashboard
    private SendableChooser<Command> autoChooser;
    // Command that was selected
    private Command autoCommand;

    // Chooser for overriding field connection in pit
    private static SendableChooser<Boolean> fmsOverrideChooser;

    /**
     * This function is run when the robot is first started up and should be used
     * for any initialization code.
     */
    @Override
    public void robotInit() {
        logger.info("initializing");

        // Wait until we get the configuration data from driver station
        waitForDriverStationData();

        // Make sure Preferences are initialized
        intializePreferences();

        // Make sure Properties file exists and can be parsed
        initializeProperties();

        // Create telemetry manager
        TelemetryManager.constructInstance();
        tlmMgr = TelemetryManager.getInstance();

        // Initialize the OI "subsystem"
        OI.constructInstance();
        oi = OI.getInstance();
        tlmMgr.addProvider(oi);

        // Create command manager
        SchedulerProvider.constructInstance();
        tlmMgr.addProvider(SchedulerProvider.getInstance());

        // Initialize all the modules
        modules = ModuleFactory.constructModules();
        // Initialize all the sensors
        sensors = SensorFactory.constructSensors();
        // Initialize all the subsystems
        subsystems = SubsystemFactory.constructSubsystems();

        // Configure all OI now that subsystems are complete
        oi.configureButtonBindings();

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
        // Needs to be here or conflict with class from WPILib? wth?
        SmartDashboard.putNumber(Preferences.status, PKStatus.inProgress.tlmValue);

        PreferencesInitializer.initialize();

        logger.info("Preferences as initialized:");
        PreferencesInitializer.logPreferences(logger);

        SmartDashboard.putNumber(Preferences.status, PKStatus.success.tlmValue);
    }

    private void initializeProperties() {
        // Reads and stores all the properties
        PropertiesManager.constructInstance();

        PropertiesManager.getInstance().logProperties(logger);
    }

    private void createAutoChooser() {
        autoChooser = new SendableChooser<>();

        autoChooser.setDefaultOption("Do Nothing", new DoNothing());

        // FIXME: This only works because default shooter command is idle

        // FIXME: Make parameterized like distance
        autoChooser.addOption("Drive Forward (4 sec)", new DriveForwardTimed());
        autoChooser.addOption("Drive Forward (3 feet)", new DriveForwardDistance(3));
        autoChooser.addOption("Shoot and Drive Forward (3 feet)",
                new PKParallelCommandGroup(new ElevatorLift(), new DriveForwardDistance(3)));
        autoChooser.addOption("Shoot and Drive Forward (4 sec)",
                new PKParallelCommandGroup(new ElevatorLift(), new DriveForwardTimed()));

        autoChooser.addOption("Drive Backward (4 sec)", new DriveBackwardTimed());
        autoChooser.addOption("Drive Backward (3 feet)", new DriveBackwardDistance(3));
        autoChooser.addOption("Shoot and Drive Backward (3 feet)",
                new PKParallelCommandGroup(new ElevatorLift(), new DriveBackwardDistance(3)));
        autoChooser.addOption("Shoot and Drive Backward (4 sec)",
                new PKParallelCommandGroup(new ElevatorLift(), new DriveBackwardTimed()));

        autoChooser.addOption("Full Auto (Driving Forward)",
                new PKParallelCommandGroup(new ElevatorLift(), new IntakeIngest(), new DriveForwardTimed()));
        // FIXME: This works because TurrentVisionAlign is going in parallel
        autoChooser.addOption("Full Auto (Driving Forward Delay)",
                new PKParallelCommandGroup(new IntakeIngest(), new TurretVisionAlign(),
                new PKSequentialCommandGroup(new WaitCommand(1.0), new DriveForwardTimed(), new FirePoseVision())));

        SmartDashboard.putData("Auto Mode", autoChooser);
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

        // Update the telemetry
        tlmMgr.sendTelemetry();

        // Add an indicator about what auto command is current selected
        SmartDashboard.putBoolean(TelemetryNames.Misc.realAuto,
                !autoChooser.getSelected().getName().equalsIgnoreCase("DoNothing"));
    }

    /**
     * This function is called once each time the robot enters Disabled mode.
     */
    @Override
    public void disabledInit() {
        logger.info("disabling");

        validateCalibrations();

        if (autonomousComplete && teleopComplete) {
            logger.info("match complete");

            logVisionData();

            logPreferences();

            logMatchData();

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

        logger.info("disabled");
    }

    /**
     * Calls each subsystem to validate their calibration and update the appropriate
     * telemetry points.
     */
    private void validateCalibrations() {
        for (ISubsystem s : subsystems) {
            s.validateCalibration();
        }
    }

    /**
     * Log the data associated with the vision to the tail of the log file.
     **/
    private void logVisionData() {
        logger.info("vision data:");
    }

    /**
     * Log the data associated with the preferences to the tail of the log file.
     **/
    private void logPreferences() {
        logger.info("preferences:");
        PreferencesInitializer.logPreferences(logger);
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
     * This function is called periodically during Disabled mode.
     */
    @Override
    public void disabledPeriodic() {
        // Runs the Scheduler. This is responsible for polling buttons, adding
        // newly-scheduled commands, running already-scheduled commands, removing
        // finished or interrupted commands, and running subsystem periodic()
        // methods. This must be called from the robot's periodic block in order
        // for anything in the Command-based framework to work.
        CommandScheduler.getInstance().run();
    }

    /**
     * This function is called once each time the robot exits Disabled mode.
     */
    @Override
    public void disabledExit() {
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

        // Update the preferences
        for (IModule m : modules) {
            m.updatePreferences();
        }
        for (ISensor s : sensors) {
            s.updatePreferences();
        }
        for (ISubsystem s : subsystems) {
            s.updatePreferences();
            s.loadDefaultAutoCommand();
        }

        // CommandScheduler.getInstance().schedule(true, new DriveForwardTimed());
        // CommandScheduler.getInstance().schedule(true, new AutoFull());
        autoCommand = autoChooser.getSelected();
        logger.info("auto command is {}", autoCommand.getName());
        if (autoCommand != null) {
            CommandScheduler.getInstance().schedule(true, autoCommand);
            // CommandScheduler.getInstance().schedule(true, new
            // PKSequentialCommandGroup(new TurretHome(), autoCommand));
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

        // Runs the Scheduler. This is responsible for polling buttons, adding
        // newly-scheduled commands, running already-scheduled commands, removing
        // finished or interrupted commands, and running subsystem periodic()
        // methods. This must be called from the robot's periodic block in order
        // for anything in the Command-based framework to work.
        CommandScheduler.getInstance().run();
    }

    /**
     * This function is called once each time the robot exits Autonomous mode.
     */
    @Override
    public void autonomousExit() {
        logger.info("exiting autonomous");

        autonomousRunning = false;
        autonomousComplete = true;

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

        // Update the preferences
        for (IModule m : modules) {
            m.updatePreferences();
        }
        for (ISensor s : sensors) {
            s.updatePreferences();
        }
        for (ISubsystem s : subsystems) {
            s.updatePreferences();
            s.loadDefaultTeleCommand();
        }

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

        // Runs the Scheduler. This is responsible for polling buttons, adding
        // newly-scheduled commands, running already-scheduled commands, removing
        // finished or interrupted commands, and running subsystem periodic()
        // methods. This must be called from the robot's periodic block in order
        // for anything in the Command-based framework to work.
        CommandScheduler.getInstance().run();
    }

    /**
     * This function is called once each time the robot exits Teleop mode.
     */
    @Override
    public void teleopExit() {
        logger.info("exiting teleop");

        teleopRunning = false;
        teleopComplete = true;

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

        // Update the preferences
        for (IModule m : modules) {
            m.updatePreferences();
        }
        for (ISensor s : sensors) {
            s.updatePreferences();
        }
        for (ISubsystem s : subsystems) {
            s.updatePreferences();
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

        logger.info("exited test");
    }

    private static void createFmsOverrideChooser() {
        fmsOverrideChooser = new SendableChooser<>();

        fmsOverrideChooser.setDefaultOption("Use Real FMS Connect", Boolean.FALSE);
        fmsOverrideChooser.addOption("Override FMS Connect", Boolean.TRUE);

        SmartDashboard.putData("FMS Override", fmsOverrideChooser);
    }
    
    static public boolean isFieldConnected() {
        if ( DriverStation.isFMSAttached()) {
            return true;
        }
        else {
            return fmsOverrideChooser.getSelected();
        }

        // // For Field Running
        // return DriverStation.isFMSAttached();

        // // For Pit Testing (be careful)
        // // return true; // FOR pit testing
    }

}
