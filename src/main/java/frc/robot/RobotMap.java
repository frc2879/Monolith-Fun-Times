/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

    public enum DriveModes {
        STICK,
        MECANUM
    }
    public static DriveModes driveMode = DriveModes.MECANUM;

    public static final int frw = 4;
    public static final int flw = 2;
    public static final int brw = 1;
    public static final int blw = 3;

    public static final int joystick = 0;

    public static final double lowpower = 0.4;
    public static final double highpower = 0.7;

    public static final double xDead = 0.2;
    public static final double yDead = 0.2;
    public static final double aDead = 0.2;

}
