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

    private TelemetryNames() {
    }

    public final class Misc {
        private static final String name = "Misc";

        public static final String programmer = name + ".programmer";
        public static final String codeVersion = name + ".codeVersion";

        public static final String robotName = name + ".robotName";
        public static final String robotImpl = name + ".robotImpl";

        public static final String fmsConnected = name + ".fmsConnected";

        public static final String realAuto = name + ".realAuto";

        public static final String initStatus = name + ".initStatus";
    }

    /***************
     * Managers
     ***************/

    public final class Telemetry {
        // FIXME: Need to not be public (but needs higher grouping)
        public static final String name = "Telemetry";

        public static final String status = name + ".status";
    }

    public final class Preferences {
        private static final String name = "Preferences";

        public static final String status = name + ".status";
    }

    public final class Properties {
        private static final String name = "Properties";

        public static final String status = name + ".status";
    }

    public static class Scheduler {
        // FIXME: Need to not be public (but needs higher grouping)
        public static final String name = "Scheduler";

        public static final String status = name + ".status";
        // The current commands running on the robot
        public static final String currentCommands = name + ".currentCommands";
    }

    /***************
     * OI
     ***************/

    public final class OI {
        // FIXME: Need to not be public (but needs higher grouping)
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

        public static final String firePose = name + ".firePose";
        public static final String visionTargetting = name + ".visionTargetting";
        public static final String revShooter = name + ".revShooter";
        public static final String homeTurret = name + ".homeTurret";
        public static final String elevatorSpeed = name + ".elevatorSpeed";
        public static final String turretJog = name + ".turretJog";
    }

    /***************
     * Modules
     ***************/

    public final class PDP {
        private static final String name = ModuleNames.pdpName;

        public static final String status = name + ".status";
        public static final String implClass = name + ".implClass";
        public static final String defCommand = name + "defCommand";

        public static final String busVoltage = name + ".busVoltage";
        public static final String totalCurrent = name + ".totalCurrent";
        public static final String totalEnergy = name + ".totalEnergy";
    }

    public final class PCM {
        private static final String name = ModuleNames.pcmName;

        public static final String status = name + ".status";
        public static final String implClass = name + ".implClass";
        public static final String defCommand = name + "defCommand";

        public static final String enabled = name + ".enabled";
        public static final String pressureGood = name + ".pressureGood";
        public static final String intakeExtended = name + ".intakeExtended";
    }

    /***************
     * Sensors
     ***************/

    public final class Gyro {
        private static final String name = SensorNames.gyroName;

        public static final String status = name + ".status";
        public static final String implClass = name + ".implClass";

        public static final String roll = name + ".roll";
        public static final String pitch = name + ".pitch";
        public static final String yaw = name + ".yaw";
        public static final String angle = name + ".angle";
        public static final String heading = name + ".heading";
    }

    public final class IncrementorLoadedSensor {
        private static final String name = SensorNames.incrementorLoadedName;

        public static final String status = name + ".status";
        public static final String implClass = name + ".implClass";
    }

    public final class ElevatorLoadedSensor {
        private static final String name = SensorNames.elevatorLoadedName;

        public static final String status = name + ".status";
        public static final String implClass = name + ".implClass";
    }

    public final class TurretLocation {
        private static final String name = SensorNames.turretLocationName;

        public static final String status = name + ".status";
        public static final String implClass = name + ".implClass";

        public static final String isFound = name + ".isFound";
    }
    
    public final class WallDistance {
        private static final String name = SensorNames.wallDistanceName;

        public static final String status = name + ".status";
        public static final String implClass = name + ".implClass";

        public static final String distance = name +".distance";
        public static final String valid = name +".valid";
    }

    public final class Vision {
        private static final String name = SensorNames.visionName;

        public static final String status = name + ".status";
        public static final String implClass = name + ".implClass";

        public static final String enabled = name + ".enabled";
        public static final String active = name + ".active";
        public static final String locked = name + ".locked";
    }

    /***************
     * Subsystems
     ***************/

    public final class Drive {
        private static final String name = SubsystemNames.driveName;

        public static final String status = name + ".status";
        public static final String implClass = name + ".implClass";
        public static final String autoCommand = name + ".autoCommand";
        public static final String teleCommand = name + ".teleCommand";

        public static final String encoderClicks = name + ".encoderClicks";
        public static final String distanceClicks = name + ".distanceClicks";
        public static final String targetClicks = name + ".targetClicks";

        public static final String leftSpeed = name + ".leftSpeed";
        public static final String rightSpeed = name + ".rightSpeed";
    }

    public final class Intake {
        private static final String name = SubsystemNames.intakeName;

        public static final String status = name + ".status";
        public static final String implClass = name + ".implClass";
        public static final String autoCommand = name + ".autoCommand";
        public static final String teleCommand = name + ".teleCommand";

        public static final String speed = name + ".speed";
        public static final String stopped = name + ".stopped";
        public static final String pullingIn = name + ".pullingIn";
        public static final String pushingOut = name + ".pushingOut";
        public static final String extended = name + ".extended";
        public static final String retracted = name + "retracted";

    }

    public final class Elevator {
        private static final String name = SubsystemNames.elevatorName;

        public static final String status = name + ".status";
        public static final String implClass = name + ".implClass";
        public static final String autoCommand = name + ".autoCommand";
        public static final String teleCommand = name + ".teleCommand";

        public static final String speed = name + ".speed";
        public static final String atLimit = name + ".atLimit";
        public static final String stopped = name + ".stopped";
        public static final String lifting = name + ".lifting";
        public static final String lowering = name + ".lowering";
        public static final String full = name + ".full";
    }

    public final class Incrementer {
        private static final String name = SubsystemNames.incrementorName;

        public static final String status = name + ".status";
        public static final String implClass = name + ".implClass";
        public static final String autoCommand = name + ".autoCommand";
        public static final String teleCommand = name + ".teleCommand";

        public static final String speed = name + ".speed";
        public static final String atLimit = name + ".atLimit";
        public static final String stopped = name + ".stopped";
        public static final String lifting = name + ".lifting";
        public static final String lowering = name + ".lowering";
        public static final String full = name + ".full";
    }

    public final class Shooter {
        private static final String name = SubsystemNames.shooterName;

        public static final String status = name + ".status";
        public static final String implClass = name + ".implClass";
        public static final String autoCommand = name + ".autoCommand";
        public static final String teleCommand = name + ".teleCommand";

        public static final String isActive = name + ".isActive";
        public static final String rpm = name + ".rpm";
        public static final String targetRpm = name + ".targetRpm";
        public static final String atTarget = name + ".atTarget";

        public static final String setSpeed = name + ".setSpeed";
        public static final String speed = name + ".speed";
    }

    public final class Turret {
        private static final String name = SubsystemNames.turretName;

        public static final String status = name + ".status";
        public static final String implClass = name + ".implClass";
        public static final String autoCommand = name + ".autoCommand";
        public static final String teleCommand = name + ".teleCommand";

        public static final String angle = name + ".angle";
        public static final String position = name + ".rawPosition";
        public static final String isHomed = name + ".isHomed";
        public static final String visionPIDOutput = name + ".visionPIDOutput";

        public static final String setSpeed = name + ".setSpeed";
        public static final String speed = name + ".speed";
    }

    public final class Climber {
        private static final String name = SubsystemNames.climberName;

        public static final String status = name + ".status";
        public static final String implClass = name + ".implClass";
        public static final String autoCommand = name + ".autoCommand";
        public static final String teleCommand = name + ".teleCommand";

        public static final String topLimit = name + ".topLimit";
        public static final String bottomLimit = name + ".bottomLimit";
        public static final String speed = name + ".speed";
        public static final String climbing = name + ".climbing";
        public static final String retracting = name + ".retracting";

        public static final String position = name + ".position";
        public static final String targetPos = name + ".targetPosition";
        public static final String atTarget = name + ".atTarget";
    }

}