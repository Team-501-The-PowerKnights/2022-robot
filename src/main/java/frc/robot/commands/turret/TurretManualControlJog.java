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

public class TurretManualControlJog extends TurretOICommandBase {

    /** Our classes' logger **/
    private static final PKLogger logger = RioLogger.getLogger(TurretManualControlJog.class.getName());

    /**
     * Creates a new TurretManualControlJog.
     */
    public TurretManualControlJog() {
        logger.info("constructing {}", getName());

        logger.info("constructed");
    }

    // FIXME: This is a hack; use commands right
    boolean started = true;

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        super.execute();

        double speed = oi.getTurretJog();
        // if ( speed == 0 ) {
        // turret.stop();
        // started = false;
        // }
        // else if ( !started ) {
        // if ( speed > 0 )
        // {
        // turret.jogCCW();
        // }
        // else if ( speed < 0 )
        // {
        // turret.jogCW();
        // }
        // started = true;
        // }
        turret.setSpeed(20, speed);
    }

}
