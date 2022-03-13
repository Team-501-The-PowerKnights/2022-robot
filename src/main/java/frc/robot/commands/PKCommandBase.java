/*-----------------------------------------------------------------------*/
/* Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by other FRC teams  */
/* under the terms of the Team501 license. The code must be accompanied  */
/* by the Team 501 - The PowerKnights license file in the root directory */
/* of this project.                                                      */
/*-----------------------------------------------------------------------*/

package frc.robot.commands;


import java.util.HashSet;

import edu.wpi.first.wpilibj2.command.CommandBase;

import riolog.PKLogger;
import riolog.RioLogger;


/**
 * 
 */
public abstract class PKCommandBase extends CommandBase {

    /* Our classes logger */
    private static final PKLogger logger = RioLogger.getLogger(PKCommandBase.class.getName());

    // FIXME - Use handle to CommandTracker class (TBW)

    //
    private static final HashSet<PKCommandBase> activeCommands;
    //
    private static PKCommandBase[] activeCommandsList = new PKCommandBase[0];

    static {
        activeCommands = new HashSet<PKCommandBase>();
    }

    private static void add(PKCommandBase c) {
        activeCommands.add(c);
        activeCommandsList = activeCommands.toArray(new PKCommandBase[0]);
    }

    private static void remove(PKCommandBase c) {
        activeCommands.remove(c);
        activeCommandsList = activeCommands.toArray(new PKCommandBase[0]);
    }

    public static PKCommandBase[] getActiveCommands() {
        return activeCommandsList;
    }

    protected PKCommandBase() {
        logger.info("constructing for {}", getName());

        logger.info("constructed");
    }
    
    // Flag for whether the first execution has happened
    private boolean executedOnce;

    @Override
    public void initialize() {
        logger.debug("initializing {}", getName());

        executedOnce = false;
    }

    @Override
    public void execute() {
        if (!executedOnce) {
            logger.debug("first execution of {}", getName());

            executedOnce = true;
    
            add(this);

            firstExecution();
        }
    }

    /**
     * Provides hook to have something happen on the first time the
     * <code>execute</code> method is called. Typicall for subsystem
     * commands that are stateful and don't need to be called each
     * time.
     */
    protected void firstExecution() {};

    @Override
    public void end(boolean interrupted) {
        logger.debug("ending {} interrupted={}", getName(), interrupted);

        remove(this);
    }

}
