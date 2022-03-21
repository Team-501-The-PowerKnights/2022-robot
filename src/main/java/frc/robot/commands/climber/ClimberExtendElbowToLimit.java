/*-----------------------------------------------------------------------*/
/* Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by other FRC teams  */
/* under the terms of the Team501 license. The code must be accompanied  */
/* by the Team 501 - The PowerKnights license file in the root directory */
/* of this project.                                                      */
/*-----------------------------------------------------------------------*/

package frc.robot.commands.climber;

import riolog.PKLogger;
import riolog.RioLogger;

public class ClimberExtendElbowToLimit extends ClimberCommandBase {

    /** Our classes' logger **/
    private static final PKLogger logger = RioLogger.getLogger(ClimberExtendElbowToLimit.class.getName());

    private double setPoint;

    public ClimberExtendElbowToLimit(double setPoint) {
        logger.info("constructing {}", getName());

        this.setPoint = setPoint;

        logger.info("constructed");
    }

    @Override
    protected void firstExecution() {
        super.firstExecution();

        // climber.elbowGoToSetPoint(setPoint); // PID control
        climber.runElbow(0.2);
    }

    @Override
    public boolean isFinished() {
        return climber.getElbowPosition() >= setPoint;
    }

}
