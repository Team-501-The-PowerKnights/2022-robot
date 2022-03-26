/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.shooter;


import riolog.PKLogger;
import riolog.RioLogger;


public class ShooterDisable extends ShooterCommandBase {

    /** Our classes' logger **/
    private static final PKLogger logger = RioLogger.getLogger(ShooterDisable.class.getName());

    public ShooterDisable() {
        logger.info("constructing {}", getName());

        logger.info("constructed");
    }

    @Override
    public void execute() {
      super.execute();

      shooter.stop();
      shooter.setDefaultCommand(new ShooterDoNothing());
    }
    
    @Override
    public boolean isFinished() {
        return true;
    }
    
}