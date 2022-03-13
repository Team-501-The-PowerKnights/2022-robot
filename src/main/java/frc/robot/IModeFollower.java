/*-----------------------------------------------------------------------*/
/* Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by other FRC teams  */
/* under the terms of the Team501 license. The code must be accompanied  */
/* by the Team 501 - The PowerKnights license file in the root directory */
/* of this project.                                                      */
/*-----------------------------------------------------------------------*/


package frc.robot;


/**
 * An interface that allows things like sensors, modules and subsystems
 * track the mode transistions of the robot as defined by the
 * <code>Iterative</code> flavor we use
 * {@link edu.wpi.first.wpilibj.IterativeRobotBase robot}.
 */
public interface IModeFollower {

    /**
     * Initialization code for disabled mode should go here.
     * <p>
     * Users should override this method for initialization code which
     * will be called each time the robot enters disabled mode.
     */
    default public void disabledInit() {};

    /**
     * Exit code for disabled mode should go here.
     * <p>
     * Users should override this method for code which will be called
     * each time the robot exits disabled mode.
     */
    default public void disabledExit() {};

    /**
     * Initialization code for autonomous mode should go here.
     * <p>
     * Users should override this method for initialization code which
     * will be called each time the robot enters autonomous mode.
     */
    default public void autonomousInit() {};

    /**
     * Exit code for autonomous mode should go here.
     * <p>
     * Users should override this method for code which will be called
     * each time the robot exits autonomous mode.
     */
    default public void autonomousExit() {};

    /**
     * Initialization code for teleop mode should go here.
     * <p>
     * Users should override this method for initialization code which
     * will be called each time the robot enters teleop mode.
     */
    default public void teleopInit() {};

    /**
     * Exit code for teleop mode should go here.
     * <p>
     * Users should override this method for code which will be called
     * each time the robot exits teleop mode.
     */
    default public void teleopExit() {};

    /**
     * Initialization code for test mode should go here.
     * <p>
     * Users should override this method for initialization code which
     * will be called each time the robot enters test mode.
     */
    default public void testInit() {};

    /**
     * Exit code for test mode should go here.
     * <p>
     * Users should override this method for code which will be called
     * each time the robot exits test mode.
     */
    default public void testExit() {};

}
