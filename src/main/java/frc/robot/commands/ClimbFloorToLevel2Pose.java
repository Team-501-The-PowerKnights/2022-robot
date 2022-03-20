/*-----------------------------------------------------------------------*/
/* Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by other FRC teams  */
/* under the terms of the Team501 license. The code must be accompanied  */
/* by the Team 501 - The PowerKnights license file in the root directory */
/* of this project.                                                      */
/*-----------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.sensors.gyro.GyroFactory;
import frc.robot.sensors.gyro.IGyroSensor;
import frc.robot.subsystems.climber.ClimberFactory;
import frc.robot.subsystems.climber.IClimberSubsystem;
import riolog.PKLogger;
import riolog.RioLogger;

/**
 * Pose to climb from floor to Level 2.
 * <p>
 * Assumes that the climber hook is set and the robot is correctly
 * positioned under the bar.
 */
public class ClimbFloorToLevel2Pose extends PKSequentialCommandGroup {

    /** Our classes' logger **/
    private static final PKLogger logger = RioLogger.getLogger(ClimbFloorToLevel2Pose.class.getName());

    private final IClimberSubsystem climber;
    private final IGyroSensor gyro;

    public ClimbFloorToLevel2Pose() {
        logger.info("constructing");

        climber = ClimberFactory.getInstance();
        gyro = GyroFactory.getInstance();

        logger.info("constructed");

        super.addCommands(commands);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void end(boolean interrupted) {

    }
}
