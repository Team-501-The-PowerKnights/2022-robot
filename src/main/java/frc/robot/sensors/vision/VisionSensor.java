/*-----------------------------------------------------------------------*/
/* Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by other FRC teams  */
/* under the terms of the Team501 license. The code must be accompanied  */
/* by the Team 501 - The PowerKnights license file in the root directory */
/* of this project.                                                      */
/*-----------------------------------------------------------------------*/

package frc.robot.sensors.vision;

import java.util.LinkedList;

import riolog.PKLogger;
import riolog.RioLogger;

/**
 * Provides implementation of <code>IVisionSensor</code> for the
 * <i>Real-Bot</i>.
 */
class VisionSensor extends BaseVisionSensor {

    /** Our classes' logger **/
    private static final PKLogger logger = RioLogger.getLogger(VisionSensor.class.getName());

    private LimelightVision mySensor;

    VisionSensor() {
        logger.info("constructing");

        mySensor = new LimelightVision();
        mySensor.disable();

        initialize();

        logger.info("constructed");
    }

    @Override
    public void updateTelemetry() {
        super.updateTelemetry();
    }

    @Override
    public void enable() {
        super.enable();

        empty();

        mySensor.enable();
    }

    @Override
    public void disable() {
        super.disable();

        empty();

        mySensor.disable();
    }

    @Override
    public double getError() {
        // return mySensor.getError();
        return next(mySensor.getError());
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

    private LinkedList<Double> list;
    private double sum;
    int size;

    private void initialize() {
        sum = 0.0;
        size = 10;
        list = new LinkedList<>();
    }

    private double next(double value) {
        sum += value;
        list.offer(value);
        if (list.size() <= size) {
            return sum / list.size();
        }
        else {
            sum -= list.poll();
            return sum / size;
        }
    }

    private void empty() {
        list.clear();
    }

}
