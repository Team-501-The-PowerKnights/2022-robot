/*-----------------------------------------------------------------------*/
/* Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by other FRC teams  */
/* under the terms of the Team501 license. The code must be accompanied  */
/* by the Team 501 - The PowerKnights license file in the root directory */
/* of this project.                                                      */
/*-----------------------------------------------------------------------*/

package frc.robot.subsystems.elevator;


import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.commands.elevator.ElevatorDoNothing;
import frc.robot.subsystems.BaseSubsystem;
import frc.robot.subsystems.SubsystemNames;
import frc.robot.telemetry.TelemetryNames;

import riolog.PKLogger;
import riolog.RioLogger;


/**
 * Add your docs here.
 */
abstract class BaseElevatorSubsystem extends BaseSubsystem implements IElevatorSubsystem {

    /** Our classes' logger **/
    private static final PKLogger logger = RioLogger.getLogger(BaseElevatorSubsystem.class.getName());

    BaseElevatorSubsystem() {
        super(SubsystemNames.elevatorName);
        logger.info("constructing");

        logger.info("constructed");
    }

    @Override
    public void loadDefaultCommands() {
        loadDefaultCommands(ElevatorDoNothing.class);
        SmartDashboard.putString(TelemetryNames.Elevator.autoCommand, defaultAutoCommand.getClass().getSimpleName());
        SmartDashboard.putString(TelemetryNames.Elevator.teleCommand, defaultTeleCommand.getClass().getSimpleName());
    }

    protected void loadPreferences() {
        @SuppressWarnings("unused")
        double v;

        logger.info("new preferences for {}:", myName);
    }

    private double tlmSpeed = 0.0;
    private boolean tlmStopped = false;
    private boolean tlmLifting = false;
    private boolean tlmLowering = false;

    @Override
    public void updateTelemetry() {
        SmartDashboard.putNumber(TelemetryNames.Elevator.speed, tlmSpeed);
        SmartDashboard.putBoolean(TelemetryNames.Elevator.stopped, tlmStopped);
        SmartDashboard.putBoolean(TelemetryNames.Elevator.lifting, tlmLifting);
        SmartDashboard.putBoolean(TelemetryNames.Elevator.lowering, tlmLowering);
    }

    protected void setTlmSpeed(double speed) {
        tlmSpeed = speed;
    }

    // FIXME: Make these methods to just set telemetry so not calling "super."

    @Override
    public void stop() {
        tlmStopped = true;
        tlmLifting = false;
        tlmLowering = false;
    }

    @Override
    public void lift() {
        tlmStopped = false;
        tlmLifting = true;
        tlmLowering = false;
    }

    @Override
    public void lower() {
        tlmStopped = false;
        tlmLifting = false;
        tlmLowering = true;
    }

    @Override
    public void liftToLimit() {
        tlmStopped = false;
        tlmLifting = true;
        tlmLowering = false;
    }

    @Override
    public void updatePreferences() {
        loadPreferences();
    }

}
