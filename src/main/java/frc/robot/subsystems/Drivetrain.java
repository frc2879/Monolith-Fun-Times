/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.MecanumDrive;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import frc.robot.commands.DriveMecanum;
import frc.robot.commands.Stickdrive;

import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.RobotMap.DriveModes;

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
  MecanumDrive mecanum_drive;
  //DifferentialDrive mh_drive;
  
  public Drivetrain()
  {
    frw.setNeutralMode(NeutralMode.Coast);
    flw.setNeutralMode(NeutralMode.Coast);
    brw.setNeutralMode(NeutralMode.Coast);
    
    blw.setNeutralMode(NeutralMode.Coast);
    //DifferentialDrive m_drive = new DifferentialDrive(flw, frw);
    mecanum_drive = new  MecanumDrive(flw,blw,frw,brw);

  }
  
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    
    if(RobotMap.driveMode == DriveModes.MECANUM){
      setDefaultCommand(new Stickdrive(1.0));
    }
    if(RobotMap.driveMode == DriveModes.STICK){
      setDefaultCommand(new DriveMecanum(1.0));
    }

  }

  public void stickdrive(double power)
  {

    brw.setInverted(true);
    frw.setInverted(true);

    double t =Robot.m_oi.getJoystick().getThrottle();
    t=RobotMap.lowpower+(RobotMap.highpower-RobotMap.lowpower)*((t+1)/2);

    double speed=Robot.m_oi.getStickY();
    double angle =Robot.m_oi.getStickAngle();

    frw.setNeutralMode(NeutralMode.Brake);
    flw.setNeutralMode(NeutralMode.Brake);
    brw.setNeutralMode(NeutralMode.Brake);
    blw.setNeutralMode(NeutralMode.Brake);

    double rs=(speed+angle)*power*t;
    double ls=(speed-angle)*power*t;

    frw.set(rs);
    flw.set(ls);
    brw.set(rs);
    blw.set(ls);
    System.out.println("stickdrive input: r="+rs+" l="+ls);
  } 
 
  public void mecanum_drive(double power)
  {
    brw.setInverted(false);
    frw.setInverted(false);


    double t =Robot.m_oi.getJoystick().getThrottle();
    t=RobotMap.lowpower+(RobotMap.highpower-RobotMap.lowpower)*((t+1)/2);


    double yspeed=Robot.m_oi.getStickY();
    double xspeed=Robot.m_oi.getStickX();
    double angle =Robot.m_oi.getStickAngle();
    mecanum_drive.driveCartesian(-xspeed*t*0.75,yspeed*t,-angle*t);
    System.out.println("mecanum input: x="+(-xspeed*t*0.75)+" y="+(yspeed*t)+" a="+(-angle*t));
    }
 }
