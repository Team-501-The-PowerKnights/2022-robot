/*-----------------------------------------------------------------------*/
/* Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by other FRC teams  */
/* under the terms of the Team501 license. The code must be accompanied  */
/* by the Team 501 - The PowerKnights license file in the root directory */
/* of this project.                                                      */
/*-----------------------------------------------------------------------*/

package frc.robot.commands.drive;

import java.io.IOException;
import java.nio.file.Path;

import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryUtil;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.commands.PKSequentialCommandGroup;
import frc.robot.subsystems.drive.DriveFactory;
import frc.robot.subsystems.drive.IDriveSubsystem;

import riolog.PKLogger;
import riolog.RioLogger;

/**
 * Add your docs here.
 */
public class DriveTrajectory extends PKSequentialCommandGroup {

    /** Our classes' logger **/
    private static final PKLogger logger = RioLogger.getLogger(DriveTrajectory.class.getName());

    private final IDriveSubsystem drive;

    public DriveTrajectory(String pathName) {
        logger.info("constructing {} for {}", getName(), pathName);

        drive = DriveFactory.getInstance();

        addRequirements(drive);

        String trajectoryJSON = "output/" + pathName + ".wpilib.json";
        Trajectory trajectory;
        try {
            Path trajectoryPath = Filesystem.getDeployDirectory().toPath().resolve(trajectoryJSON);
            trajectory = TrajectoryUtil.fromPathweaverJson(trajectoryPath);
        } catch (IOException ex) {
            DriverStation.reportError("Unable to open trajectory " + trajectoryJSON, ex.getStackTrace());
            return;
        }

        addCommands(drive.followTrajectory(trajectory), new InstantCommand(() -> drive.stop(), drive));

        logger.info("constructed");
    }

}
