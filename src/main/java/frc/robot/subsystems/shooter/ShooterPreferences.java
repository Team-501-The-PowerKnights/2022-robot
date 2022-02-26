/*-----------------------------------------------------------------------*/
/* Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by other FRC teams  */
/* under the terms of the Team501 license. The code must be accompanied  */
/* by the Team 501 - The PowerKnights license file in the root directory */
/* of this project.                                                      */
/*-----------------------------------------------------------------------*/

package frc.robot.subsystems.shooter;


import edu.wpi.first.wpilibj.Preferences;

import frc.robot.subsystems.SubsystemNames;

import riolog.PKLogger;
import riolog.RioLogger;


/**
 * Defines the names and a set of values of preferences for this subsystem.
 * If the required preferences aren't defined yet on the robot, it creates
 * an initial value.
 * <p>
 * The name uses dot notation for the hierarchy. The first part is
 * the name of the subsystem and the second is the name of the
 * preference retreivable from the
 * {@link edu.wpi.first.wpilibj.Preferences}.
 *
 * @see edu.wpi.first.networktables.NetworkTable
 */
public final class ShooterPreferences {

    /** Our classes' logger **/
    private static final PKLogger logger = RioLogger.getLogger(ShooterPreferences.class.getName());

    static private final String name = SubsystemNames.driveName;
    static final String pid_P = name + ".P";
    static final String pid_I = name + ".I";
    static final String pid_D = name + ".D";
    static final String pid_F = name + ".F";
    // Scale applied to shooter speed when in manual
    static final String scale = name + ".scale";

    private ShooterPreferences() {}

    public static void initialize()
    {
        if (!Preferences.containsKey(pid_P)) {
            logger.warn("{} doesn't exist; creating with default", pid_P);
            Preferences.setDouble(pid_P, 0.0);
        }

        if (!Preferences.containsKey(pid_I)) {
            logger.warn("{} doesn't exist; creating with default", pid_I);
            Preferences.setDouble(pid_I, 0.0);
        }

        if (!Preferences.containsKey(pid_D)) {
            logger.warn("{} doesn't exist; creating with default", pid_D);
            Preferences.setDouble(pid_D, 0.0);
        }

        if (!Preferences.containsKey(pid_F)) {
            logger.warn("{} doesn't exist; creating with default", pid_F);
            Preferences.setDouble(pid_F, 0.0);
        }

        if (!Preferences.containsKey(scale)) {
            logger.warn("{} doesn't exist; creating with default", scale);
            Preferences.setDouble(scale, 1.0);
        }
    }

}
