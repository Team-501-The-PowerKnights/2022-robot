/*-----------------------------------------------------------------------*/
/* Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by other FRC teams  */
/* under the terms of the Team501 license. The code must be accompanied  */
/* by the Team 501 - The PowerKnights license file in the root directory */
/* of this project.                                                      */
/*-----------------------------------------------------------------------*/

package frc.robot.sensors;


import java.util.ArrayList;
import java.util.List;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.sensors.elevator.ElevatorSensorFactory;
import frc.robot.sensors.gyro.GyroFactory;
import frc.robot.sensors.incrementer.IncrementerSensorFactory;
import frc.robot.sensors.turretlocation.TurretLocationFactory;
import frc.robot.sensors.vision.VisionFactory;
import frc.robot.sensors.walldistance.WallDistanceSensorFactory;
import frc.robot.telemetry.TelemetryManager;
import frc.robot.telemetry.TelemetryNames;
import frc.robot.utils.PKStatus;

import riolog.PKLogger;
import riolog.RioLogger;


/**
 * Add your docs here.
 */
public class SensorFactory {

    /** Our classes' logger **/
    private static final PKLogger logger = RioLogger.getLogger(SensorFactory.class.getName());

    public static List<ISensor> constructSensors() {
        logger.info("constructing");

        ArrayList<ISensor> sensors = new ArrayList<ISensor>();

        TelemetryManager tlmMgr = TelemetryManager.getInstance();

        SmartDashboard.putNumber(TelemetryNames.Gyro.status, PKStatus.unknown.tlmValue);
        {
            GyroFactory.constructInstance();
            ISensor s = GyroFactory.getInstance();
            tlmMgr.addProvider(s);
            sensors.add(s);
        }

        SmartDashboard.putNumber(TelemetryNames.IncrementerSensor.status, PKStatus.unknown.tlmValue);
        {
            IncrementerSensorFactory.constructInstance();
            ISensor s = IncrementerSensorFactory.getInstance();
            tlmMgr.addProvider(s);
            sensors.add(s);
        }

        SmartDashboard.putNumber(TelemetryNames.ElevatorSensor.status, PKStatus.unknown.tlmValue);
        {
            ElevatorSensorFactory.constructInstance();
            ISensor s = ElevatorSensorFactory.getInstance();
            tlmMgr.addProvider(s);
            sensors.add(s);
        }

        SmartDashboard.putNumber(TelemetryNames.TurretLocation.status, PKStatus.unknown.tlmValue);
        {
            TurretLocationFactory.constructInstance();
            ISensor s = TurretLocationFactory.getInstance();
            tlmMgr.addProvider(s);
            sensors.add(s);
        }

        SmartDashboard.putNumber(TelemetryNames.WallDistance.status, PKStatus.unknown.tlmValue);
        {
            WallDistanceSensorFactory.constructInstance();
            ISensor s = WallDistanceSensorFactory.getInstance();
            tlmMgr.addProvider(s);
            sensors.add(s);
        }
        
        SmartDashboard.putNumber(TelemetryNames.Vision.status, PKStatus.unknown.tlmValue);
        {
            VisionFactory.constructInstance();
            ISensor s = VisionFactory.getInstance();
            tlmMgr.addProvider(s);
            sensors.add(s);
        }

        logger.info("constructed");
        return sensors;
    }

}
