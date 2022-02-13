/*-----------------------------------------------------------------------*/
/* Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by other FRC teams  */
/* under the terms of the Team501 license. The code must be accompanied  */
/* by the Team 501 - The PowerKnights license file in the root directory */
/* of this project.                                                      */
/*-----------------------------------------------------------------------*/

package frc.robot.config;

/**
 * This class provides a versioning string that can be used to determine the
 * build pedigree of a robot as loaded on the roboRIO.
 **/
public class VersionInfo {

    // Provides a version string that gets incorporated into build
    public static final String version = 
        new StringBuilder("").
        append("501-Robot").append(" ").
        append(CodeVersionInfo.version).append(" ").
        append(BuildVersionInfo.programmer).append(" ").
        append(BuildVersionInfo.timeStamp).append(" ").
        append(BuildVersionInfo.commitSHA).toString();
}
