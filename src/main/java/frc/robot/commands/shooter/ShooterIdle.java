/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.shooter;

import org.slf4j.Logger;

import riolog.RioLogger;

/**
 * Add your docs here.
 */
public class ShooterIdle extends ShooterCommandBase {

  /** Our classes' logger **/
  private static final Logger logger = RioLogger.getLogger(ShooterIdle.class.getName());

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

    // FIXME: Add a 'full speed' command
    shooter.setSpeed(29, speed);
  }

}