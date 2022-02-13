/*-----------------------------------------------------------------------*/
/* Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by other FRC teams  */
/* under the terms of the Team501 license. The code must be accompanied  */
/* by the Team 501 - The PowerKnights license file in the root directory */
/* of this project.                                                      */
/*-----------------------------------------------------------------------*/

package frc.robot.subsystems.drive;


import edu.wpi.first.wpilibj.Preferences;

import frc.robot.subsystems.SubsystemNames;

import riolog.PKLogger;
import riolog.RioLogger;


/**
 * Defines the names and values of properties for this package.
 * <p>
 * The name uses dot notation for the hierarchy. The first part is
 * the name of the subsystem and the second is the name of the
 * preference retreivable from the
 * {@link edu.wpi.first.wpilibj.Preferences}.
 *
 * @see edu.wpi.first.networktables.NetworkTable
 */
public final class DrivePreferences {

    /** Our classes' logger **/
    private static final PKLogger logger = RioLogger.getLogger(DrivePreferences.class.getName());

    static private final String name = SubsystemNames.driveName;
    static final String pid_P = name + ".P";
    static final String pid_I = name + ".I";
    static final String pid_D = name + ".D";
    static final String pid_F = name + ".F";

    private DrivePreferences() {}

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
    }

}
