/*-----------------------------------------------------------------------*/
/* Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by other FRC teams  */
/* under the terms of the Team501 license. The code must be accompanied  */
/* by the Team 501 - The PowerKnights license file in the root directory */
/* of this project.                                                      */
/*-----------------------------------------------------------------------*/

package frc.robot.modules.pcm;


import riolog.PKLogger;
import riolog.RioLogger;


class SuitcasePCMModule extends BasePCMModule {
 
    /** Our classes' logger **/
    private static final PKLogger logger = RioLogger.getLogger(SuitcasePCMModule.class.getName());

    public SuitcasePCMModule() {
        logger.info("constructing");

        logger.info("constructed");
    }

    @Override
    public void updateTelemetry() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void updatePreferences() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void disable() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void enable() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void extendIntake() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void retractIntake() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public boolean isIntakeExtended() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void extendClimber() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void retractClimber() {
        // TODO Auto-generated method stub
        
    }

}
