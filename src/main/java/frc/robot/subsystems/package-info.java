/*-----------------------------------------------------------------------*/
/* Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by other FRC teams  */
/* under the terms of the Team501 license. The code must be accompanied  */
/* by the Team 501 - The PowerKnights license file in the root directory */
/* of this project.                                                      */
/*-----------------------------------------------------------------------*/

/**
 * This package contains the implementation of the framework for subsystems
 * (which is not quite the same as the WPILib <code>Preferences</code> 
 * implementation of the same name.
 * <p>
 * More detail ...
 * 
 * (1) Subsystems Needed
 * <code>subsystems/Xxxxxx<code>
 * <code>IXXxxxxxSubsystem</code>
 * <code>BaseXXxxxxxSubsystem</code>
 * <code>StubXXxxxxxSubsystem</code>
 * <code>XxxxxxFactory</code>
 * <code>XxxxxxPreferences</code>
 * <code>XxxxxxProperties</code>
 * 
 * (2) Commands Needed
 * <code>commands/Xxxxxx<code>
 * <code>XxxxxxCommandBase</code>  command
 * <code>XxxxxxDoNothing</code> command
 * 
 * (3) Add Subsystem to other parts
 * <code>SubsystemNames</code>
 * <code>TelemetryNames</code>
 * 
 * (4) Edit <code>PreferencesInitializer</code> to add the 
 * <code>initialize()<code> call to the 
 * subsystem's <code>Preferences<code> class.
 * </p>
 * 
 * (5) Edit <code>SubsystemsFactory</code> to add the
 * code to create the <code>XxxxxxFactory</code>.
 * 
 * (6) Edit the properties file(s) to add the new subystem. At a
 * minimum it needs the section, className of 
 *
 * @since 2022.0.0
 * @author first.stu
 * @version 2022.0.0
 **/

package frc.robot.subsystems;
