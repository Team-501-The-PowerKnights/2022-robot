/*-----------------------------------------------------------------------*/
/* Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by other FRC teams  */
/* under the terms of the Team501 license. The code must be accompanied  */
/* by the Team 501 - The PowerKnights license file in the root directory */
/* of this project.                                                      */
/*-----------------------------------------------------------------------*/

package frc.robot.commands.drive;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.telemetry.TelemetryNames;

import riolog.PKLogger;
import riolog.RioLogger;

/**
 * Add your docs here.
 */
public class DriveForwardDistance extends DriveCommandBase {

    /** Our classes' logger **/
    private static final PKLogger logger = RioLogger.getLogger(DriveForwardDistance.class.getName());

    private double distanceInFeet;

    // Distance to drive (from current position) in clicks
    private double distanceClicks;
    // Target position in clicks (current + distance)
    private double targetClicks;

    public DriveForwardDistance(double distanceInFeet) {
        logger.info("constructing {}", getName());

        this.distanceInFeet = distanceInFeet;

        logger.info("constructed");
    }

    @Override
    public void initialize() {
        super.initialize();

        distanceClicks = drive.convertInchesToEncoderClicks(distanceInFeet * 12);
        logger.info("distance clicks = {}", distanceClicks);
        SmartDashboard.putNumber(TelemetryNames.Drive.distanceClicks, distanceClicks);

        targetClicks = drive.getEncoderClicks() + distanceClicks;
        SmartDashboard.putNumber(TelemetryNames.Drive.targetClicks, targetClicks);
    }

    @Override
    public void execute() {
        super.execute();

        double speed = 0.20;
        double turn = 0.0;

        drive.drive(speed, turn);
    }

    @Override
    public boolean isFinished() {
        return (drive.getEncoderClicks() >= targetClicks);
    }

    @Override
    public void end(boolean interrupted) {
        // Stop the drive
        drive.stop();

        super.end(interrupted);
    }

}
