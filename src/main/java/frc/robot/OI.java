/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.Peck;
import frc.robot.commands.Bite;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

private static Joystick stick = new Joystick(RobotMap.joystick);
public static JoystickButton pecking = new JoystickButton(stick, 1);
public static JoystickButton biting = new JoystickButton(stick, 2);

//// CREATING BUTTONS
  // One type of button is a joystick button which is any button on a
  //// joystick.
  // You create one by telling it which joystick it's on and which button
  // number it is.
  // Joystick stick = new Joystick(port);
  // Button button = new JoystickButton(stick, buttonNumber);

  // There are a few additional built in buttons you can use. Additionally,
  // by subclassing Button you can create custom triggers and bind those to
  // commands the same as any other Button.

  //// TRIGGERING COMMANDS WITH BUTTONS
  // Once you have a button, it's trivial to bind it to a button in one of
  // three ways:

  // Start the command when the button is pressed and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenPressed(new ExampleCommand());

  // Run the command while the button is being held down and interrupt it once
  // the button is released.
  // button.whileHeld(new ExampleCommand());

  // Start the command when the button is released and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenReleased(new ExampleCommand());

  

  public Joystick getJoystick()
  {
    return stick;
  }

  public double getStickX()
  {
    double x = (stick.getX()*Math.abs(stick.getX()));
    if(Math.abs(x)<= RobotMap.xDead)
    {
      x = 0;
    }
      return x;
  }
  
  public double getStickY()
  {
    double y = (stick.getY()*Math.abs(stick.getY()));
    if(Math.abs(y)<= RobotMap.yDead)
    {
      y = 0;
    }
      return y;
  }
  
  public double getStickAngle()
  {
    double a = (stick.getTwist()*Math.abs(stick.getTwist()));
    if(Math.abs(a)<= RobotMap.aDead)
    {
      a = 0;
    }
      return a;
  }

  public OI()
  {
    pecking.toggleWhenPressed(new Peck(true));
    biting.whileHeld(new Bite(true));
  }

}
