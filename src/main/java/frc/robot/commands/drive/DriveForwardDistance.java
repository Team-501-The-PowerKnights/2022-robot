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

    // Distance to drive (from current position) in clicks
    private double distanceClicks;
    // Target position in clicks (current + distance)
    private double targetClicks;

    public DriveForwardDistance(double distanceInFeet) {
        logger.info("constructing {} for {}", getName(), distanceInFeet);

        distanceClicks = distanceInFeet * 6.849; // motor revolutions per foot
        // FIXME: this seems better than 2x's the distance
        distanceClicks /= 2;
        SmartDashboard.putNumber(TelemetryNames.Drive.distanceClicks, distanceClicks);

        logger.info("constructed");
    }

    @Override
    public void initialize() {
        super.initialize();

        targetClicks = drive.getEncoderClicks() - distanceClicks;
        SmartDashboard.putNumber(TelemetryNames.Drive.targetClicks, targetClicks);
    }

    @Override
    public void execute() {
        super.execute();
    }

    @Override
    protected void firstExecution() {
        logger.trace("drive.drive() called in firstExecution()");

        double speed = 0.20;
        double turn = 0.0;

        drive.drive(speed, turn);
    }

    @Override
    public boolean isFinished() {
        return (drive.getEncoderClicks() <= targetClicks);
    }

    @Override
    public void end(boolean interrupted) {
        super.end(interrupted);

        // Stop the drive
        drive.stop();
    }

}
