/*-----------------------------------------------------------------------*/
/* Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by other FRC teams  */
/* under the terms of the Team501 license. The code must be accompanied  */
/* by the Team 501 - The PowerKnights license file in the root directory */
/* of this project.                                                      */
/*-----------------------------------------------------------------------*/

package frc.robot.telemetry;


import frc.robot.modules.ModuleNames;
import frc.robot.sensors.SensorNames;
import frc.robot.subsystems.SubsystemNames;


/**
 * Provides a standard way of defining names for the <i>Telemetry</i> used in
 * the program. No code should define or use a hard-coded string outside of the
 * ones defined in this class.
 **/
public final class TelemetryNames {

    private TelemetryNames() {}

    public final class Misc {
        public static final String name = "Misc";

        public static final String programmer = name + ".programmer";
        public static final String codeVersion = name + ".codeVersion";

        public static final String robotName = name + ".robotName";
        public static final String robotImpl = name + ".robotImpl";

        public static final String fmsConnected = name + ".fmsConnected";

        public static final String realAuto = name + ".realAuto";
    }

    /***************
     * Managers
     ***************/

    public final class Telemetry {
        public static final String name = "Telemetry";

        public static final String status = name + ".status";
    }

    public final class Preferences {
        public static final String name = "Preferences";

        public static final String status = name + ".status";
    }

    public final class Properties {
        public static final String name = "Properties";

        public static final String status = name + ".status";
    }

    public static class Scheduler {
        public static final String name = "Scheduler";

        public static final String status = name + ".status";
        // The current commands running on the robot
        public static final String currentCommands = name + ".currentCommands";
    }

    /***************
     * OI
     ***************/

    public final class OI {
        public static final String name = "OI";

        public static final String status = name + ".status";
    }

    public static class HMI {
        private static final String name = SubsystemNames.hmiName;

        public static final String rawSpeed = name + ".rawSpeed";
        public static final String rawTurn = name + ".rawTurn";
        public static final String turbo = name + ".turbo";
        public static final String crawl = name + ".crawl";
        public static final String reversed = name + ".reversed";
        public static final String oiSpeed = name + ".oiSpeed";
        public static final String oiTurn = name + ".oiTurn";

        public static final String speed = name + ".speed";
        public static final String turn = name + ".turn";

        public static final String intakeSpeed = name + ".intakeSpeed";
    }

    /***************
     * Modules
     ***************/

    public final class PDP {
        public static final String name = ModuleNames.pdpName;

        public static final String status = name + ".status";
        public static final String implClass = name + ".implClass";
        
        public static final String busVoltage = name + ".busVoltage";
        public static final String totalCurrent = name + ".totalCurrent";
        public static final String totalEnergy = name + ".totalEnergy";
    }

    /***************
     * Sensors
     ***************/

    public final class Gyro {
        public static final String name = SensorNames.gyroName;

        public static final String status = name + ".status";
        public static final String implClass = name + ".implClass";

        public static final String roll = name + ".roll";
        public static final String pitch = name + ".pitch";
        public static final String yaw = name + ".yaw";
        public static final String angle = name + ".angle";
        public static final String heading = name + ".heading";
    }

    /***************
     * Subsystems
     ***************/

    public final class Drive {
        public static final String name = SubsystemNames.driveName;

        public static final String status = name + ".status";
        public static final String implClass = name + ".implClass";

        public static final String encoderClicks = name + ".encoderClicks";
        public static final String distanceClicks = name + ".distanceClicks";
        public static final String targetClicks = name + ".targetClicks";
    }

    public final class Intake {
        public static final String name = SubsystemNames.intakeName;

        public static final String status = name + ".status";
        public static final String implClass = name + ".implClass";
    }

    public final class Elevator {
        public static final String name = SubsystemNames.elevatorName;

        public static final String status = name + ".status";
        public static final String implClass = name + ".implClass";
    }

    public final class Shooter {
        public static final String name = SubsystemNames.shooterName;

        public static final String status = name + ".status";
        public static final String implClass = name + ".implClass";
    }

    public final class Turret {
        public static final String name = SubsystemNames.turretName;

        public static final String status = name + ".status";
        public static final String implClass = name + ".implClass";
    }

    public final class Climber {
        public static final String name = SubsystemNames.climberName;

        public static final String status = name + ".status";
        public static final String implClass = name + ".implClass";
    }

}