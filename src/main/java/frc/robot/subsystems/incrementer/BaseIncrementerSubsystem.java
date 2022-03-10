/*-----------------------------------------------------------------------*/
/* Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by other FRC teams  */
/* under the terms of the Team501 license. The code must be accompanied  */
/* by the Team 501 - The PowerKnights license file in the root directory */
/* of this project.                                                      */
/*-----------------------------------------------------------------------*/

package frc.robot.subsystems.incrementer;


import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.commands.incrementer.IncrementerDoNothing;
import frc.robot.subsystems.BaseSubsystem;
import frc.robot.subsystems.SubsystemNames;
import frc.robot.telemetry.TelemetryNames;

import riolog.PKLogger;
import riolog.RioLogger;


/**
 * Add your docs here.
 */
abstract class BaseIncrementerSubsystem extends BaseSubsystem implements IIncrementerSubsystem {

    /** Our classes' logger **/
    private static final PKLogger logger = RioLogger.getLogger(BaseIncrementerSubsystem.class.getName());

    BaseIncrementerSubsystem() {
        super(SubsystemNames.incrementerName);
        logger.info("constructing");

        logger.info("constructed");
    }

    @Override
    public void loadDefaultCommands() {
        loadDefaultCommands(IncrementerDoNothing.class);
    }

    private double tlmSpeed = 0.0;
    private boolean tlmStopped = false;
    private boolean tlmLifting = false;
    private boolean tlmLowering = false;

    @Override
    public void updateTelemetry() {
        SmartDashboard.putNumber(TelemetryNames.Incrementer.speed, tlmSpeed);
        SmartDashboard.putBoolean(TelemetryNames.Incrementer.stopped, tlmStopped);
        SmartDashboard.putBoolean(TelemetryNames.Incrementer.lifting, tlmLifting);
        SmartDashboard.putBoolean(TelemetryNames.Incrementer.lowering, tlmLowering);
    }

    protected void setTlmSpeed(double speed) {
        tlmSpeed = speed;
    }

    @Override
    public void stop() {
        tlmStopped = true;
        tlmLifting = false;
        tlmLowering = false;
    }

    @Override
    public void incrementToLimit() {
        tlmStopped = false;
        tlmLifting = true;
        tlmLowering = false;
    }

}
