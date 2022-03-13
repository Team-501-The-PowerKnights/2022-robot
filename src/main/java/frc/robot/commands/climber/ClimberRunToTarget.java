/*-----------------------------------------------------------------------*/
/* Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by other FRC teams  */
/* under the terms of the Team501 license. The code must be accompanied  */
/* by the Team 501 - The PowerKnights license file in the root directory */
/* of this project.                                                      */
/*-----------------------------------------------------------------------*/

package frc.robot.commands.climber;


import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.telemetry.TelemetryNames;

import riolog.PKLogger;
import riolog.RioLogger;


public class ClimberRunToTarget extends ClimberCommandBase {

    /** Our classes' logger **/
    private static final PKLogger logger = RioLogger.getLogger(ClimberRunToTarget.class.getName());

    //
    private final double target;

    public ClimberRunToTarget(double target) {
        logger.info("constructing {} for {}", getName(), target);

        this.target = target;
        SmartDashboard.putNumber(TelemetryNames.Climber.targetPos, target);

        logger.info("constructed");
    }

    @Override
    public void initialize() {
        super.initialize();

        climber.zeroPosition();
        SmartDashboard.putBoolean(TelemetryNames.Climber.atTarget, false);
    }

    @Override
    public void execute() {
        super.execute();
    }

    @Override
    protected void firstExecution() {
        logger.trace("climber.climb() called in firstExecution()");
        climber.climb();
    }

    @Override
    public boolean isFinished() {
        return climber.getPosition() >= target;
    }

    @Override
    public void end(boolean interrupted) {
        super.end(interrupted);

        climber.stop();

        SmartDashboard.putBoolean(TelemetryNames.Climber.atTarget, true);
    }

}
