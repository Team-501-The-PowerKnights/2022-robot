/*-----------------------------------------------------------------------*/
/* Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by other FRC teams  */
/* under the terms of the Team501 license. The code must be accompanied  */
/* by the Team 501 - The PowerKnights license file in the root directory */
/* of this project.                                                      */
/*-----------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.sensors.gyro.GyroFactory;
import frc.robot.sensors.gyro.IGyroSensor;
import frc.robot.subsystems.climber.ClimberFactory;
import frc.robot.subsystems.climber.IClimberSubsystem;
import riolog.PKLogger;
import riolog.RioLogger;

/**
 * Pose to climb from Level 3 to Level 4.
 * <p>
 * Assumes that the hanging hook is set and the robot is on the
 * Level 3 bar.
 */
public class ClimbLevel3ToLevel4Pose extends PKSequentialCommandGroup {

    /** Our classes' logger **/
    private static final PKLogger logger = RioLogger.getLogger(ClimbLevel3ToLevel4Pose.class.getName());

    private final IClimberSubsystem climber;
    private final IGyroSensor gyro;

    public ClimbLevel3ToLevel4Pose() {
        logger.info("constructing");

        climber = ClimberFactory.getInstance();
        gyro = GyroFactory.getInstance();

        addCommands();

        logger.info("constructed");
    }

    public ClimbLevel3ToLevel4Pose(double delay) {
        logger.info("constructing");

        climber = ClimberFactory.getInstance();
        gyro = GyroFactory.getInstance();

        addCommands(new WaitCommand(delay));

        logger.info("constructed");
    }

    @Override
    public void end(boolean interrupted) {
        // ClimberStateMachine.getInstance().finish(interrupted);
    }

}
