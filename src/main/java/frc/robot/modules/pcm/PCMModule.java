/*-----------------------------------------------------------------------*/
/* Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by other FRC teams  */
/* under the terms of the Team501 license. The code must be accompanied  */
/* by the Team 501 - The PowerKnights license file in the root directory */
/* of this project.                                                      */
/*-----------------------------------------------------------------------*/

package frc.robot.modules.pcm;

import edu.wpi.first.wpilibj.PneumaticsControlModule;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.telemetry.TelemetryNames;

import riolog.PKLogger;
import riolog.RioLogger;

class PCMModule extends BasePCMModule {

    /** Our classes' logger **/
    private static final PKLogger logger = RioLogger.getLogger(PCMModule.class.getName());

    /** My module */
    private final PneumaticsControlModule module;

    private static final int climberSolenoidChannel = 1; // TODO - unsure what this will be
    private static final int intakeSolenoidChannel = 3;

    private final Solenoid intakeSolenoid;
    private final Solenoid climberSolenoid;

    public PCMModule() {
        logger.info("constructing");

        module = new PneumaticsControlModule(0);
        enable();

        intakeSolenoid = module.makeSolenoid(intakeSolenoidChannel);
        intakeSolenoid.set(false);

        climberSolenoid = module.makeSolenoid(climberSolenoidChannel);
        // TODO - This isn't implemented mechanically yet

        logger.info("constructed");
    }

    @Override
    public void updateTelemetry() {
        setTlmPressureGood(module.getPressureSwitch());
        super.updateTelemetry();

        SmartDashboard.putBoolean(TelemetryNames.PCM.intakeExtended, isIntakeExtended());
    }

    @Override
    public void updatePreferences() {
        // Nothing here
    }

    @Override
    public void disable() {
        module.disableCompressor();
        setTlmEnabled(false);
    }

    @Override
    public void enable() {
        module.enableCompressorDigital();
        setTlmEnabled(true);
    }

    @Override
    public void extendIntake() {
        // Not testing - just do it (could change)
        intakeSolenoid.set(true);
    }

    @Override
    public void retractIntake() {
        // Not testing - just do it (could change)
        intakeSolenoid.set(false);
    }

    private boolean isIntakeExtended() {
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
