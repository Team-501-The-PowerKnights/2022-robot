/*-----------------------------------------------------------------------*/
/* Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by other FRC teams  */
/* under the terms of the Team501 license. The code must be accompanied  */
/* by the Team 501 - The PowerKnights license file in the root directory */
/* of this project.                                                      */
/*-----------------------------------------------------------------------*/

package frc.robot.modules;


import java.util.ArrayList;
import java.util.List;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.modules.pcm.PCMFactory;
import frc.robot.modules.pdp.PDPFactory;
import frc.robot.telemetry.TelemetryManager;
import frc.robot.telemetry.TelemetryNames;
import frc.robot.utils.PKStatus;

import riolog.PKLogger;
import riolog.RioLogger;


/**
 * Add your docs here.
 */
public class ModulesFactory {

    /** Our classes' logger **/
    @SuppressWarnings("unused")
    private static final PKLogger logger = RioLogger.getLogger(ModulesFactory.class.getName());

    public static List<IModule> constructModules() {

        ArrayList<IModule> modules = new ArrayList<IModule>();

        TelemetryManager tlmMgr = TelemetryManager.getInstance();

        SmartDashboard.putNumber(TelemetryNames.PDP.status, PKStatus.unknown.tlmValue);
        {
            PDPFactory.constructInstance();
            IModule m = PDPFactory.getInstance();
            tlmMgr.addProvider(m);
            modules.add(m);
        }

        SmartDashboard.putNumber(TelemetryNames.PCM.status, PKStatus.unknown.tlmValue);
        {
            PCMFactory.constructInstance();
            IModule m = PCMFactory.getInstance();
            tlmMgr.addProvider(m);
            modules.add(m);
        }

        return modules;
    }

}
