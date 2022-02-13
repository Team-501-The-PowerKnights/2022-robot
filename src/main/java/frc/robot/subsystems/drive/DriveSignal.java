/*-----------------------------------------------------------------------*/
/* Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by other FRC teams  */
/* under the terms of the Team501 license. The code must be accompanied  */
/* by the Team 501 - The PowerKnights license file in the root directory */
/* of this project.                                                      */
/*-----------------------------------------------------------------------*/

package frc.robot.subsystems.drive;


import java.lang.StringBuilder;

import riolog.PKLogger;
import riolog.RioLogger;


/**
 * A drivetrain command consisting of the left, right motor settings and whether
 * the brake mode is enabled.
 */
class DriveSignal {

    /** Our classes' logger **/
    @SuppressWarnings("unused")
    private static final PKLogger logger = RioLogger.getLogger(DriveSignal.class.getName());

    protected double mLeftMotor;
    protected double mRightMotor;
    protected boolean mBrakeMode;

    public DriveSignal(double left, double right) {
        this(left, right, false);
    }

    public DriveSignal(double left, double right, boolean brakeMode) {
        mLeftMotor = left;
        mRightMotor = right;
        mBrakeMode = brakeMode;
    }

    public static DriveSignal NEUTRAL = new DriveSignal(0, 0);
    public static DriveSignal BRAKE = new DriveSignal(0, 0, true);

    public void setLeft(double left) {
        mLeftMotor = left;
    }

    public void setRight(double right) {
        mRightMotor = right;
    }

    public void setBrakeMode(boolean brakeMode) {
        mBrakeMode = brakeMode;
    }

    public double getLeft() {
        return mLeftMotor;
    }

    public double getRight() {
        return mRightMotor;
    }

    public boolean getBrakeMode() {
        return mBrakeMode;
    }

    @Override
    public String toString() {
        return new StringBuilder().append("L: ").append(mLeftMotor).append(", R: ").append(mRightMotor)
                .append((mBrakeMode ? ", BRAKE" : "")).toString();
    }

}
