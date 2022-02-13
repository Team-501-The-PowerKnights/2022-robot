/*-----------------------------------------------------------------------*/
/* Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by other FRC teams  */
/* under the terms of the Team501 license. The code must be accompanied  */
/* by the Team 501 - The PowerKnights license file in the root directory */
/* of this project.                                                      */
/*-----------------------------------------------------------------------*/

package frc.robot.properties;


import riolog.PKLogger;
import riolog.RioLogger;


/**
 * Add your docs here.
 */
public final class PropertyNames {

    /** Our classes' logger **/
    @SuppressWarnings("unused")
    private static final PKLogger logger = RioLogger.getLogger(PropertyNames.class.getName());

    private PropertyNames(){}

    public final class Robot {
        public static final String name = "Robot";
        public static final String robotName = "robotName";
        public static final String implementation = "implementation";
    }

}
