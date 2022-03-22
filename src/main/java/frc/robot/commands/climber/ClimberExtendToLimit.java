/*-----------------------------------------------------------------------*/
/* Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by other FRC teams  */
/* under the terms of the Team501 license. The code must be accompanied  */
/* by the Team 501 - The PowerKnights license file in the root directory */
/* of this project.                                                      */
/*-----------------------------------------------------------------------*/

package frc.robot.commands.climber;

import frc.robot.commands.PKCommandBase;
import frc.robot.subsystems.climber.ClimberFactory;
import frc.robot.subsystems.climber.IClimberSubsystem;
import riolog.PKLogger;
import riolog.RioLogger;

/**
 * Extends the shoulder to an encoder position. NOTE: does not extend the base
 * command or require the climber subsystem because it is used only as a
 * component command of larger command groups.
 */
public class ClimberExtendToLimit extends PKCommandBase {

    /** Our classes' logger **/
    private static final PKLogger logger = RioLogger.getLogger(ClimberExtendToLimit.class.getName());

    private double setPoint;

    // Handle to the climber
    private final IClimberSubsystem climber;

    public ClimberExtendToLimit(double setPoint) {
        logger.info("constructing {}", getName());

        climber = ClimberFactory.getInstance();

        this.setPoint = setPoint;

        logger.info("constructed");
    }

    @Override
    protected void firstExecution() {
        super.firstExecution();

        // climber.shoulderGoToSetPoint(setPoint); // PID control
        climber.run(0.2);
    }

    @Override
    public boolean isFinished() {
        return climber.getAveragePosition() >= setPoint;
    }

}
