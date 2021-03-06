/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.shooter;

import org.slf4j.Logger;

import frc.robot.Robot;
import riolog.RioLogger;

/**
 * Add your docs here.
 */
public class ShooterIdle extends ShooterCommandBase {

  /** Our classes' logger **/
  private static final Logger logger = RioLogger.getLogger(ShooterIdle.class.getName());

  /** Speed to run shooter at */
  private double speed;

  public ShooterIdle() {
    logger.info("constructing {}", getName());

    this.speed = 0.50;

    logger.info("constructed");
  }

  public ShooterIdle(double speed) {
    logger.info("constructing {} w/ speed = ", getName(), speed);

    this.speed = speed;

    logger.info("constructed");
  }

  @Override
  public void execute() {
    super.execute();
  }

  @Override
  protected void firstExecution() {
    logger.trace("shooter.setSpeed() called in firstExecution()");
    if (Robot.isFieldConnected()) {
      // FIXME: Add a 'full speed' command
      shooter.setSpeed(29, Robot.shooterSetSpeed);
    } else {
      shooter.setSpeed(29, 0);
    }
  }

}