/*-----------------------------------------------------------------------*/
/* Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by other FRC teams  */
/* under the terms of the Team501 license. The code must be accompanied  */
/* by the Team 501 - The PowerKnights license file in the root directory */
/* of this project.                                                      */
/*-----------------------------------------------------------------------*/

package frc.robot.properties;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.*;
import static java.util.Map.Entry.*;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.properties.PropertyNames.Robot;
import frc.robot.telemetry.TelemetryNames;
import frc.robot.utils.PKStatus;

import riolog.PKLogger;
import riolog.RioLogger;


/**
 * Add your docs here.
 */
public class PropertiesManager {

    /** Our classes' logger **/
    private static final PKLogger logger = RioLogger.getLogger(PropertiesManager.class.getName());

    /* Default fully qualified file name */
    public static final String defaultFileName = "/home/lvuser/501robot.props";

    private static PropertiesManager ourInstance;

    private static String myName = "Props";

    /** Name of the robot */
    private static String robotName;
    /** Name of the implementation of robot */
    private static String robotImpl;


    public static void constructInstance() {
        constructInstance(defaultFileName);
    }

    public static void constructInstance(String fileName) {
        SmartDashboard.putNumber(TelemetryNames.Properties.status, PKStatus.unknown.tlmValue);

        if (ourInstance != null) {
            throw new IllegalStateException(myName + " already constructed");
        }

        SmartDashboard.putNumber(TelemetryNames.Properties.status, PKStatus.inProgress.tlmValue);

        ourInstance = new PropertiesManager(fileName);

        // Put robot info into dashboard (tests successful access)
        PKProperties props = ourInstance.getProperties(PropertyNames.Robot.name);
        robotName = props.getString(Robot.robotName);
        SmartDashboard.putString(TelemetryNames.Misc.robotName, robotName);
        robotImpl = props.getString(Robot.implementation);
        SmartDashboard.putString(TelemetryNames.Misc.robotImpl, robotImpl);

        // Check to see if file exists and mark as failed if not
        if ( !new File(fileName).exists()) {
            logger.error("Properties file doesn't exist {}", fileName);
            SmartDashboard.putNumber(TelemetryNames.Properties.status, PKStatus.failed.tlmValue);
        }
        // Check to see if the robot info exists and mark as suspect if not
        else if ( robotName.isEmpty() || robotImpl.isEmpty() ) {
            logger.warn("Properties file {} exists but missing key info", fileName);
            SmartDashboard.putNumber(TelemetryNames.Properties.status, PKStatus.unknown.tlmValue);
        }
        else {
            SmartDashboard.putNumber(TelemetryNames.Properties.status, PKStatus.success.tlmValue);
        }
     }

    public static PropertiesManager getInstance() {
        if (ourInstance == null) {
            throw new IllegalStateException(myName + " not constructed yet");
        }
        return ourInstance;
    }

    private final Map<String, Map<String, String>> ownerProperties;

    private PropertiesManager(String fileName) {
        logger.info("constructing");

        logger.debug("file: {}", fileName);

        ownerProperties = new HashMap<String, Map<String, String>>();

        try {
            // Reader for properties file
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            // Use Properties class for conviencence to read & parse file
            Properties props = new Properties();
            props.load(reader);
            /* logger.trace("properties as read: {}", props); */

            Map<String, String> rawProperties = props.entrySet().stream()
                    .collect(Collectors.toMap(e -> e.getKey().toString(), e -> e.getValue().toString()));
            Map<String, String> sortedProperties = rawProperties.entrySet().stream().sorted(comparingByKey())
                    .collect(toMap(e -> e.getKey(), e -> e.getValue(), (e1, e2) -> e2, LinkedHashMap::new));
            logger.info("properties as read:\n{}", sortedProperties);

            props.forEach((k, v) -> sort(k, v));
        } catch (IOException ex) {
            logger.error("Can't load properties from file {}", fileName, ex);
            // Handled above ...
        }

        if (ownerProperties.isEmpty()) {
            logger.error("No properties parsed from file {}", fileName);
        }

        logger.info("constructed");
    }

    // Expression to parse "owner.property"
    private final Pattern pattern = Pattern.compile("\\.");

    /**
     * 
     * @param arg0 - key of set
     * @param arg1 - value of set (don't care but need for method)
     */
    private void sort(Object arg0, Object arg1) {
        // Cast arguments into strings
        String key = (String) arg0;
        String value = (String) arg1;
        // Splits "argument" into its subsystem and property parts
        String[] keys = pattern.split(key);
        // Assigns subsystem and property keys
        String ownerKey = keys[0];
        String propKey = keys[1];
        // Check if the subsystem-organized array already contains an entry for the
        // subsystem
        if (!(ownerProperties.containsKey(ownerKey))) {
            // If it doesn't, create one
            ownerProperties.put(ownerKey, new HashMap<String, String>());
        }
        // Now there has to be a subsystem entry, so we assign the values into that
        // subsystem's corresponding PKProperties Object
        ownerProperties.get(ownerKey).put(propKey, value);
    }

    public String getRobotName() {
        return robotName;
    }

    public String getImpl() {
        return robotImpl;
    }

    public PKProperties getProperties(String owner) {
        if (ownerProperties.containsKey(owner)) {
            return new PKProperties(owner, ownerProperties.get(owner));
        } else {
            logger.error("Properties for owner {} don't exist", owner);
            return new PKProperties(owner, new HashMap<String, String>());
        }
    }

    public String listProperties() {
        StringBuilder buf = new StringBuilder();
        buf.append(" properties:");
        for (String owner : ownerProperties.keySet()) {
            buf.append(getProperties(owner).listProperties());
        }
        return buf.toString();
    }

    public void logProperties(PKLogger logger) {
        StringBuilder buf = new StringBuilder();
        buf.append(" properties:");
        for (String owner : ownerProperties.keySet()) {
            buf.append("\n..."); // logger gobbles up leading spaces
            buf.append(getProperties(owner).listProperties());
        }
        logger.info(buf.toString());
    }

}
