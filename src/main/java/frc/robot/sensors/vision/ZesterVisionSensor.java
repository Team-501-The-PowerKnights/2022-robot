/*-----------------------------------------------------------------------*/
/* Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by other FRC teams  */
/* under the terms of the Team501 license. The code must be accompanied  */
/* by the Team 501 - The PowerKnights license file in the root directory */
/* of this project.                                                      */
/*-----------------------------------------------------------------------*/

package frc.robot.sensors.vision;


import riolog.PKLogger;
import riolog.RioLogger;


/**
 * Provides implementation of <code>IVisionSensor</code> for the
 * <i>Zester-Bot</i>.
 */
class ZesterVisionSensor extends BaseVisionSensor {

    /** Our classes' logger **/
    private static final PKLogger logger = RioLogger.getLogger(ZesterVisionSensor.class.getName());

    private LimelightVision mySensor;

    ZesterVisionSensor() {
        logger.info("constructing");

        mySensor = new LimelightVision();

        logger.info("constructed");
    }

    @Override
    public void updateTelemetry() {
        super.updateTelemetry();
    }

    @Override
    public void enable() {
        super.enable();

        mySensor.enable();
    }

    @Override
    public void disable() {
        super.disable();

        mySensor.disable();
    }

    @Override
    public double getError() {
        return mySensor.getError();
    }

    @Override
    public double getY() {
        return mySensor.getY();
    }

    @Override
    public boolean isLocked() {
        boolean locked = mySensor.isLocked();
        setTlmLocked(locked);
        return locked;
    }

}
