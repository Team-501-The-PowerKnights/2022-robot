/*-----------------------------------------------------------------------*/
/* Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by other FRC teams  */
/* under the terms of the Team501 license. The code must be accompanied  */
/* by the Team 501 - The PowerKnights license file in the root directory */
/* of this project.                                                      */
/*-----------------------------------------------------------------------*/

package frc.robot.modules.pcm;


import edu.wpi.first.wpilibj.PneumaticsControlModule;
import edu.wpi.first.wpilibj.PneumaticsModuleType;

import riolog.PKLogger;
import riolog.RioLogger;


class SuitcasePCMModule extends BasePCMModule {
 
    /** Our classes' logger **/
    private static final PKLogger logger = RioLogger.getLogger(SuitcasePCMModule.class.getName());
    
    private final PneumaticsControlModule module;

    public SuitcasePCMModule() {
        logger.info("constructing");

        module = new PneumaticsControlModule(0);
        module.enableCompressorDigital();

        logger.info("constructed");
    }

    @Override
    public void updateTelemetry() {
        super.updateTelemetry();

        // TODO Auto-generated method stub
        
    }

    @Override
    public void updatePreferences() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void disable() {
        // module.disableCompressor();
        setTlmEnabled(false);
        // TODO Auto-generated method stub

    }

    @Override
    public void enable() {
        // module.enableCompressorDigital();
        setTlmEnabled(true);
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
