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
import riolog.PKLogger;
import riolog.RioLogger;

public class PCMModule extends BasePCMModule {

    /** Our classes' logger **/
    private static final PKLogger logger = RioLogger.getLogger(PCMModule.class.getName());

    private final PneumaticsControlModule module;

    // TODO - are there only two solenoids? or two per subsystem using them?
    private final int intakeSolenoidChannel;
    private final int climberSolenoidChannel;

    private final Solenoid intakeSolenoid;
    private final Solenoid climberSolenoid;

    public PCMModule() {
        logger.info("constructing");

        module = new PneumaticsControlModule(10); // TODO - is this the right way to access?
        // TODO - what is the type of compressor?

        intakeSolenoidChannel = 0; // TODO - Figure out real channel
        climberSolenoidChannel = 1; // TODO - Figure out real channel

        intakeSolenoid = module.makeSolenoid(intakeSolenoidChannel);
        climberSolenoid = module.makeSolenoid(climberSolenoidChannel);

        logger.info("constructed");
    }

    @Override
    public void updatePreferences() {
        // Real doesn't implement this

    }

    // TODO - should we also have an enable method to enable the compressor?

    @Override
    public void disable() {
        module.disableCompressor();
        // TODO - should we set a solenoid state here?

    }

    @Override
    public void updateTelemetry() {
        // TODO - what states do we want to collect?
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
    public void extendClimber() {
        climberSolenoid.set(true);
    }

    @Override
    public void retractClimber() {
        climberSolenoid.set(false);
    }

}
