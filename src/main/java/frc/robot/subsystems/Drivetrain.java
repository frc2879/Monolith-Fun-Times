/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import frc.robot.commands.Stickdrive;
import frc.robot.Robot;
import frc.robot.RobotMap;
import com.ctre.phoenix.motorcontrol.NeutralMode;


/**
 * Add your docs here.
 */
public class Drivetrain extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  WPI_TalonSRX frw = new WPI_TalonSRX(RobotMap.frw);
  WPI_TalonSRX flw = new WPI_TalonSRX(RobotMap.flw);
  WPI_TalonSRX brw = new WPI_TalonSRX(RobotMap.brw);
  WPI_TalonSRX blw = new WPI_TalonSRX(RobotMap.blw);
  

  


  
  public Drivetrain()
  {
    frw.setNeutralMode(NeutralMode.Brake);
    flw.setNeutralMode(NeutralMode.Brake);
    brw.setNeutralMode(NeutralMode.Brake);
    blw.setNeutralMode(NeutralMode.Brake);
  }
  

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    //setDefaultCommand(new Stickdrive(Robot.m_oi.getStickX(),Robot.m_oi.getStickAngle()));
    //.out.println("you're initializing the default command for stickdrive");
  }

  public void stickdrive(double speed, double angle,double power)
  {
    /*
    frw.setNeutralMode(NeutralMode.Brake);
    flw.setNeutralMode(NeutralMode.Brake);
    brw.setNeutralMode(NeutralMode.Brake);
    blw.setNeutralMode(NeutralMode.Brake);
    */
    //System.out.println("you're calling stickdrive()! congratulations.");
    //System.out.println("speed/angle: "+speed+" , "+angle);
    frw.set(-(angle+speed)*power);
    flw.set((speed-angle)*power);
    brw.set(-(angle+speed)*power);
    blw.set((speed-angle)*power);
    //System.out.println("motor speeds are being set to "+(angle-speed)+" and "+(speed+angle));

  }

  

}
