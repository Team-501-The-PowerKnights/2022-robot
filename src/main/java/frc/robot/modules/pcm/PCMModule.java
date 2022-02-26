/*-----------------------------------------------------------------------*/
/* Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by other FRC teams  */
/* under the terms of the Team501 license. The code must be accompanied  */
/* by the Team 501 - The PowerKnights license file in the root directory */
/* of this project.                                                      */
/*-----------------------------------------------------------------------*/

package frc.robot.modules.pcm;


import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.telemetry.TelemetryNames;

import riolog.PKLogger;
import riolog.RioLogger;


class PCMModule extends BasePCMModule {

    /** Our classes' logger **/
    private static final PKLogger logger = RioLogger.getLogger(PCMModule.class.getName());

    private final Compressor module;

    // TODO - are there only two solenoids? or two per subsystem using them?
    private final int intakeSolenoidChannel;
    private final int climberSolenoidChannel;

    private final Solenoid intakeSolenoid;
    private final Solenoid climberSolenoid;

    public PCMModule() {
        logger.info("constructing");

        module = new Compressor(0, PneumaticsModuleType.CTREPCM);
        module.enableDigital();

        intakeSolenoidChannel = 3;
        climberSolenoidChannel = 1; // TODO - This isn't implemented mechanically yet

        intakeSolenoid = new Solenoid(PneumaticsModuleType.CTREPCM, intakeSolenoidChannel);
        intakeSolenoid.set(false);

        climberSolenoid = new Solenoid(PneumaticsModuleType.CTREPCM, climberSolenoidChannel);

        logger.info("constructed");
    }

    @Override
    public void updateTelemetry() {
        SmartDashboard.putBoolean(TelemetryNames.PCM.compressorEnabled, module.enabled());
        SmartDashboard.putBoolean(TelemetryNames.PCM.pressureGood, module.getPressureSwitchValue());
        SmartDashboard.putBoolean(TelemetryNames.PCM.intakeExtended, isIntakeExtended());
    }

    @Override
    public void updatePreferences() {
        // Nothing here
    }

    // TODO - should we also have an enable method to enable the compressor?

    @Override
    public void disable() {
        module.disable();

    }

    @Override
    public void enable() {
        module.enableDigital();
    }

    @Override
    public void extendIntake() {
        intakeSolenoid.set(true);
    }

    @Override
    public void retractIntake() {
        intakeSolenoid.set(false);
    }

    @Override
    public boolean isIntakeExtended() {
        return intakeSolenoid.get();
    }

    @Override
    public void extendClimber() {
        climberSolenoid.set(true);
    }

    @Override
    public void retractClimber() {
        climberSolenoid.set(false);
    }

}
