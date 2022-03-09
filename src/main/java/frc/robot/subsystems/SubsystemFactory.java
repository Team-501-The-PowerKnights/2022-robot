/*-----------------------------------------------------------------------*/
/* Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by other FRC teams  */
/* under the terms of the Team501 license. The code must be accompanied  */
/* by the Team 501 - The PowerKnights license file in the root directory */
/* of this project.                                                      */
/*-----------------------------------------------------------------------*/

package frc.robot.subsystems;

import java.util.ArrayList;
import java.util.List;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.climber.ClimberFactory;
import frc.robot.subsystems.drive.DriveFactory;
import frc.robot.subsystems.elevator.ElevatorFactory;
import frc.robot.subsystems.incrementer.IncrementerFactory;
import frc.robot.subsystems.intake.IntakeFactory;
import frc.robot.subsystems.shooter.ShooterFactory;
import frc.robot.subsystems.turret.TurretFactory;
import frc.robot.telemetry.TelemetryNames;
import frc.robot.telemetry.TelemetryManager;
import frc.robot.utils.PKStatus;

import riolog.PKLogger;
import riolog.RioLogger;

/**
 * 
 **/
public class SubsystemFactory {

    /** Our classes' logger **/
    private static final PKLogger logger = RioLogger.getLogger(SubsystemFactory.class.getName());

    public static List<ISubsystem> constructSubsystems() {
        logger.info("constructing");

        ArrayList<ISubsystem> subsystems = new ArrayList<ISubsystem>();

        TelemetryManager tlmMgr = TelemetryManager.getInstance();

        // ** Drive **
        SmartDashboard.putNumber(TelemetryNames.Drive.status, PKStatus.unknown.tlmValue);
        {
            DriveFactory.constructInstance();
            ISubsystem ss = DriveFactory.getInstance();
            tlmMgr.addProvider(ss);
            subsystems.add(ss);
        }

        // ** Intake **
        SmartDashboard.putNumber(TelemetryNames.Intake.status, PKStatus.unknown.tlmValue);
        {
            IntakeFactory.constructInstance();
            ISubsystem ss = IntakeFactory.getInstance();
            tlmMgr.addProvider(ss);
            subsystems.add(ss);
        }

        // ** Elevator **
        SmartDashboard.putNumber(TelemetryNames.Elevator.status, PKStatus.unknown.tlmValue);
        {
            ElevatorFactory.constructInstance();
            ISubsystem ss = ElevatorFactory.getInstance();
            tlmMgr.addProvider(ss);
            subsystems.add(ss);
        }

        // ** Incrementer **
        SmartDashboard.putNumber(TelemetryNames.Incrementer.status, PKStatus.unknown.tlmValue);
        {
            IncrementerFactory.constructInstance();
            ISubsystem ss = IncrementerFactory.getInstance();
            tlmMgr.addProvider(ss);
            subsystems.add(ss);
        }

        // ** Shooter **
        SmartDashboard.putNumber(TelemetryNames.Shooter.status, PKStatus.unknown.tlmValue);
        {
            ShooterFactory.constructInstance();
            ISubsystem ss = ShooterFactory.getInstance();
            tlmMgr.addProvider(ss);
            subsystems.add(ss);
        }

        // ** Turret **
        SmartDashboard.putNumber(TelemetryNames.Turret.status, PKStatus.unknown.tlmValue);
        {
            TurretFactory.constructInstance();
            ISubsystem ss = TurretFactory.getInstance();
            tlmMgr.addProvider(ss);
            subsystems.add(ss);
        }

        // ** Climber **
        SmartDashboard.putNumber(TelemetryNames.Climber.status, PKStatus.unknown.tlmValue);
        {
            ClimberFactory.constructInstance();
            ISubsystem ss = ClimberFactory.getInstance();
            tlmMgr.addProvider(ss);
            subsystems.add(ss);
        }

        // Load and update the preferences now that all the subsystems are created
        for (ISubsystem ss : subsystems) {
            ss.updatePreferences();
        }

        // Load the default commands now that all subsystems are created
        for (ISubsystem ss : subsystems) {
            ss.loadDefaultCommands();
        }

        logger.info("constructed");
        return subsystems;
    }

}
