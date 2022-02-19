/*-----------------------------------------------------------------------*/
/* Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by other FRC teams  */
/* under the terms of the Team501 license. The code must be accompanied  */
/* by the Team 501 - The PowerKnights license file in the root directory */
/* of this project.                                                      */
/*-----------------------------------------------------------------------*/

package frc.robot.sensors.turretlocation;


import edu.wpi.first.wpilibj.DigitalInput;

import riolog.PKLogger;
import riolog.RioLogger;


/**
 * Provides implementation of <code>ITurretLocationSensor</code> for the
 * <i>Zester-Bot</i>.
 */
class ZesterTurretLocationSensor extends BaseTurretLocationSensor {

    /** Our classes' logger **/
    private static final PKLogger logger = RioLogger.getLogger(ZesterTurretLocationSensor.class.getName());

    private DigitalInput location;

    ZesterTurretLocationSensor() {
        logger.info("constructing");

        location = new DigitalInput(8);

        logger.info("constructed");
    }

    @Override
    public boolean get() {
        return location.get();
    }

}
