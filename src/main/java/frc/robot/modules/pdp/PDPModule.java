/*-----------------------------------------------------------------------*/
/* Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by other FRC teams  */
/* under the terms of the Team501 license. The code must be accompanied  */
/* by the Team 501 - The PowerKnights license file in the root directory */
/* of this project.                                                      */
/*-----------------------------------------------------------------------*/

package frc.robot.modules.pdp;


import edu.wpi.first.wpilibj.PowerDistribution;
import edu.wpi.first.wpilibj.PowerDistribution.ModuleType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.telemetry.TelemetryNames;

import riolog.PKLogger;
import riolog.RioLogger;


class PDPModule extends BasePDPModule {

    /** Our classes' logger **/
    private static final PKLogger logger = RioLogger.getLogger(PDPModule.class.getName());

    private final PowerDistribution pdp;

    public PDPModule() {
        logger.info("constructing");

        pdp = new PowerDistribution(0, ModuleType.kCTRE);

        logger.info("constructed");
    }

    @Override
    public void updateTelemetry() {
        SmartDashboard.putNumber(TelemetryNames.PDP.busVoltage, getBusVoltage());
        SmartDashboard.putNumber(TelemetryNames.PDP.totalCurrent, getTotalEnergy());
        SmartDashboard.putNumber(TelemetryNames.PDP.totalEnergy, getTotalEnergy());
    }

    @Override
    public void updatePreferences() {
        // No preferences for this module
    }

    @Override
    public void disable() {
        // Nothing active so nothing to disable
    }

    @Override
    public double getBusVoltage() {
        return pdp.getVoltage();
    }

    @Override
    public double getTotalCurrent() {
        return pdp.getTotalCurrent();
    }

    @Override
    public double getTotalEnergy() {
        return pdp.getTotalEnergy();
    }

    @Override
    public double getCurrent(int deviceID) {
        return pdp.getCurrent(deviceID);
    }

}
