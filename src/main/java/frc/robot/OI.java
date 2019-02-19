/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.NoidCommander;
import frc.robot.commands.NoidCommander;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
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

  private Joystick stick;

  public Joystick getJoystick()
  {
    return stick;
  }

  public double getStickX()
  {
    double xDed = .05;
    double in = stick.getX();
    double x = in*in;

    if(x <= xDed)
    {
      x = 0;
    }

    else
    {
      x = (x - (x-xDed)/(1-xDed));
    }

    if(in<0)
    {
      x = -x;
    }
      return x;
  }

  public double getStickY()
  {
    double yDed = .05;
    double in = stick.getY();
    double y = in*in;

    if(y<= yDed)
    {
      y = 0;
    }

    else
    {
      y = (y - (y-yDed)/(1-yDed));
    }
    
    if(y<0)
    {
      y = -y;
    }
    return y;
  }

  public OI()
  {
    stick = new Joystick(RobotMap.joystick);
    new JoystickButton(stick, 0).toggleWhenPressed(new NoidCommander(true));
    //new JoystickButton(stick, 1).toggleWhenPressed(NoidCommander(true));
  }

}
