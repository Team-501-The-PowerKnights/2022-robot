/*-----------------------------------------------------------------------*/
/* Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by other FRC teams  */
/* under the terms of the Team501 license. The code must be accompanied  */
/* by the Team 501 - The PowerKnights license file in the root directory */
/* of this project.                                                      */
/*-----------------------------------------------------------------------*/

package frc.robot.commands;


import frc.robot.commands.climber.ClimberStateMachine;
import frc.robot.sensors.gyro.GyroFactory;
import frc.robot.sensors.gyro.IGyroSensor;
import frc.robot.subsystems.climber.ClimberFactory;
import frc.robot.subsystems.climber.IClimberSubsystem;

import riolog.PKLogger;
import riolog.RioLogger;


/**
 * Class to act as the base of all the <code>poses<code> for
 * the climber.
 */
public class ClimbBasePose extends PKSequentialCommandGroup {
    
    /** Our classes' logger **/
    private static final PKLogger logger = RioLogger.getLogger(ClimbBasePose.class.getName());

    // Handle to the climber subsystem
    protected final IClimberSubsystem climber;
    // Handle to the gyro sensor
    protected final IGyroSensor gyro;

    // Handle to the climber state machine for sequencing
    protected ClimberStateMachine csm;

    public ClimbBasePose() {
        logger.info("constructing");

        climber = ClimberFactory.getInstance();
        // Make this latch to the <code>Climber</code> subsystem
        addRequirements(climber);

        gyro = GyroFactory.getInstance();

        csm = ClimberStateMachine.getInstance();

        logger.info("constructed");
    }

}
