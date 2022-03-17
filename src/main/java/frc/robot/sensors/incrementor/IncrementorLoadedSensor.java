/*-----------------------------------------------------------------------*/
/* Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by other FRC teams  */
/* under the terms of the Team501 license. The code must be accompanied  */
/* by the Team 501 - The PowerKnights license file in the root directory */
/* of this project.                                                      */
/*-----------------------------------------------------------------------*/

package frc.robot.sensors.incrementor;


import edu.wpi.first.wpilibj.DigitalInput;

import riolog.PKLogger;
import riolog.RioLogger;


/**
 * Provides implementation of {@link IIncrementorLoadedSensor} for the
 * <i>Real-Bot</i>.
 */
class IncrementorLoadedSensor extends BaseIncrementorLoadedSensor {

    /** Our classes' logger **/
    private static final PKLogger logger = RioLogger.getLogger(IncrementorLoadedSensor.class.getName());

    private final DigitalInput location;

    IncrementorLoadedSensor() {
        logger.info("constructing");

        location = new DigitalInput(0);

        logger.info("constructed");
    }

    @Override
    public boolean get() {
        return (location.get());
    }

}
