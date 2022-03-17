/*-----------------------------------------------------------------------*/
/* Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by other FRC teams  */
/* under the terms of the Team501 license. The code must be accompanied  */
/* by the Team 501 - The PowerKnights license file in the root directory */
/* of this project.                                                      */
/*-----------------------------------------------------------------------*/

package frc.robot.sensors.elevator;


import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.sensors.BaseSensor;
import frc.robot.sensors.SensorNames;
import frc.robot.telemetry.TelemetryNames;

import riolog.PKLogger;
import riolog.RioLogger;


abstract class BaseElevatorLoadedSensor extends BaseSensor implements IElevatorLoadedSensor {

    /** Our classes' logger **/
    private static final PKLogger logger = RioLogger.getLogger(BaseElevatorLoadedSensor.class.getName());

    BaseElevatorLoadedSensor() {
        super(SensorNames.elevatorLoadedName);
        logger.info("constructing");

        logger.info("constructed");
    }

    @Override
    public void updateTelemetry() {
        SmartDashboard.putBoolean(TelemetryNames.Elevator.full, get());
    }

}
