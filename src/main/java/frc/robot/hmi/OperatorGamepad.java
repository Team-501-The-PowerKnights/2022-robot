/*-----------------------------------------------------------------------*/
/* Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by other FRC teams  */
/* under the terms of the Team501 license. The code must be accompanied  */
/* by the Team 501 - The PowerKnights license file in the root directory */
/* of this project.                                                      */
/*-----------------------------------------------------------------------*/

package frc.robot.hmi;


import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.telemetry.TelemetryNames;

import riolog.PKLogger;
import riolog.RioLogger;


/**
 * This class implements the Operator's gamepad.
 * <p>
 * See <code>control_mode.md</code> for documentation of how configured and used.
 */
public class OperatorGamepad extends BaseGamepad {
    
    /** Our classes' logger **/
    private static final PKLogger logger = RioLogger.getLogger(OperatorGamepad.class.getName());

    
    public OperatorGamepad() 
    {
        super(1);
        logger.info("constructing {}");

        logger.info("constructed");
    }


    @Override
    public void configureButtonBindings() {
        // TODO Auto-generated method stub
        logger.info("configure");
        
        logger.info("configured");
    }


    @Override
    public void updateTelemetry() {
        // TODO Auto-generated method stub
        
    }

}
