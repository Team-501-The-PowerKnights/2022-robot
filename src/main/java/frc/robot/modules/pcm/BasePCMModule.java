/*-----------------------------------------------------------------------*/
/* Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by other FRC teams  */
/* under the terms of the Team501 license. The code must be accompanied  */
/* by the Team 501 - The PowerKnights license file in the root directory */
/* of this project.                                                      */
/*-----------------------------------------------------------------------*/

package frc.robot.modules.pcm;


import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.modules.BaseModule;
import frc.robot.telemetry.TelemetryNames;

import riolog.PKLogger;
import riolog.RioLogger;


abstract class BasePCMModule extends BaseModule implements IPCMModule {

    /** Our classes' logger **/
    private static final PKLogger logger = RioLogger.getLogger(BasePCMModule.class.getName());

    BasePCMModule() {
        super(TelemetryNames.PCM.name);
        logger.info("constructing");

        logger.info("constructed");
    }

    // Module state
    protected boolean tlmEnabled = false;
    // Enough pressure? Compressor should be running if not ...
    protected boolean tlmPressure = false;

    protected void setTlmEnabled(boolean enabled) {
        tlmEnabled = enabled;
    }

    protected void setTlmPressureGood(boolean pressure) {
        tlmPressure = pressure;
    }

    @Override
    public void updateTelemetry() {
        SmartDashboard.putBoolean(TelemetryNames.PCM.enabled, tlmEnabled);
        SmartDashboard.putBoolean(TelemetryNames.PCM.pressureGood, tlmPressure);
    }

}
