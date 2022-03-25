/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.turret;

import riolog.PKLogger;
import riolog.RioLogger;

/**
 * Add your docs here.
 */
public class TurretHome extends TurretCommandBase {

    /** Our classes' logger **/
    private static final PKLogger logger = RioLogger.getLogger(TurretHome.class.getName());

    public TurretHome() {
        logger.info("constructing {}", getName());

        logger.info("constructed");
    }

    @Override
    public void execute() {
        super.execute();
    }

    @Override
    protected void firstExecution() {
        logger.trace("turret.home() called in firstExecution()");
        
        // turret.home();
        turret.setTurretAngle(0); // This works for now; 0 and 180 will be roughly facing straight ahead or back
    }

    @Override
    public boolean isFinished() {
        // FIXME: Should this be something else? Timed? Check on angle?
        return true;
    }

}
