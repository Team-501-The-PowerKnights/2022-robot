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

public class ClimberExtendLevel4 extends ClimberCommandBase {

    /** Our classes' logger **/
    private static final PKLogger logger = RioLogger.getLogger(ClimberExtendLevel4.class.getName());

    private double targetElbowCounts;
    private double targetShoulderCounts;

    public ClimberExtendLevel4() {
        logger.info("constructing {}", getName());

        logger.info("constructed");
    }

    @Override
    public void initialize() {
        super.initialize();

        targetElbowCounts = 0; // TODO - make this the actual value
        targetShoulderCounts = 0; // TODO - make this the actual value
    }

    @Override
    protected void firstExecution() {
        logger.trace(" called in firstExecution()");
        if (climber.getElbowPosition() < targetElbowCounts) {
            climber.runElbow(0.2); // TODO - make this the actual value
        } else {
            climber.runElbow(0);
        }
        if (climber.getShoulderPosition() < targetShoulderCounts) {
            climber.runShoulder(0.2); // TODO - make this the actual value
        } else {
            climber.runShoulder(0);
        }
    }

    @Override
    public boolean isFinished() {
        return (climber.getShoulderPosition() >= targetShoulderCounts
                && climber.getElbowPosition() >= targetElbowCounts);
    }

    @Override
    public void end(boolean interrupted) {
        super.end(interrupted);

        climber.stop();
    }

}
