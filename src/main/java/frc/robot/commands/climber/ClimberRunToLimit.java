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

public class ClimberRunToLimit extends ClimberCommandBase {

    /** Our classes' logger **/
    private static final PKLogger logger = RioLogger.getLogger(ClimberRunToLimit.class.getName());

    private double limit = 0;

    public ClimberRunToLimit(double limit) {
        logger.info("constructing {}", getName());

        this.limit = limit;

        logger.info("constructed");
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        super.execute();

        climber.climb();
    }

    @Override
    public boolean isFinished() {
        return climber.getPosition() == limit;
    }

    // Called once when either the Command finishes normally, or when it
    // is interrupted/canceled.
    @Override
    public void end(boolean interrupted) {
        super.end(interrupted);

        climber.stop();
    }

}
