/*-----------------------------------------------------------------------*/
/* Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by other FRC teams  */
/* under the terms of the Team501 license. The code must be accompanied  */
/* by the Team 501 - The PowerKnights license file in the root directory */
/* of this project.                                                      */
/*-----------------------------------------------------------------------*/

package frc.robot.commands.turret;


import frc.robot.OI;

import riolog.PKLogger;
import riolog.RioLogger;


/**
 * Add your docs here.
 */
public class TurretOICommandBase extends TurretCommandBase {
            
    /** Our classes' logger **/
    private static final PKLogger logger = RioLogger.getLogger(TurretOICommandBase.class.getName());

    // Handle to the OI
    protected OI oi;

    public TurretOICommandBase() {
        logger.info("constructing {}", getName());

        oi = OI.getInstance();

        logger.info("constructed");
    }

}
