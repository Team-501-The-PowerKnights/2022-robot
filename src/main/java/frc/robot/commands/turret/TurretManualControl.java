/*-----------------------------------------------------------------------*/
/* Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by other FRC teams  */
/* under the terms of the Team501 license. The code must be accompanied  */
/* by the Team 501 - The PowerKnights license file in the root directory */
/* of this project.                                                      */
/*-----------------------------------------------------------------------*/

package frc.robot.commands.turret;


import riolog.PKLogger;
import riolog.RioLogger;


public class TurretManualControl extends TurretOICommandBase {

    /** Our classes' logger **/
    private static final PKLogger logger = RioLogger.getLogger(TurretManualControl.class.getName());

    /**
     * Creates a new TurretManualControlJog.
     */
    public TurretManualControl() {
        logger.info("constructing {}", getName());

        logger.info("constructed");
    }

    // FIXME: This is a hack; use commands right
    boolean started = true;

    @Override
    public void execute() {
        super.execute();

        double speed = oi.getTurretJog();
        turret.setSpeed(20, speed);
    }

}
