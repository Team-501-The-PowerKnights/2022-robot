/*-----------------------------------------------------------------------*/
/* Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by other FRC teams  */
/* under the terms of the Team501 license. The code must be accompanied  */
/* by the Team 501 - The PowerKnights license file in the root directory */
/* of this project.                                                      */
/*-----------------------------------------------------------------------*/

package frc.robot.subsystems.climber;


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
public final class ClimberPreferences {

    /** Our classes' logger **/
    private static final PKLogger logger = RioLogger.getLogger(ClimberPreferences.class.getName());

    static private final String name = SubsystemNames.climberName;
    static final String ramp = name + ".ramp";

    private ClimberPreferences() {}

    public static void initialize()
    {
        if (!Preferences.containsKey(ramp)) {
            logger.warn("{} doesn't exist; creating with default", ramp);
            Preferences.setDouble(ramp, 0.0);
        }
    }

}
