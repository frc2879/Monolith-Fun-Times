/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

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
  MecanumDrive mecanum_drive;
  //DifferentialDrive m_drive;
  
  private static final double spinWheelWeight = .45;
  
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
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new Stickdrive(1.0));
    //.out.println("you're initializing the default command for stickdrive");
  }

  public void stickdrive(double power)
  {
    //change inputs to stick vals 
    double speed=Robot.m_oi.getJoystick().getStickY();
    double angle =Robot.m_oi.getStickAngle();

    /*
    frw.setNeutralMode(NeutralMode.Brake);
    flw.setNeutralMode(NeutralMode.Brake);
    brw.setNeutralMode(NeutralMode.Brake);
    blw.setNeutralMode(NeutralMode.Brake);
    */
    double rs=(speed-angle)*power;
    double ls=(speed+angle)*power;
    double WWSG = -angle*spinWheelWeight; //Wheel Speed When Spinning in Place. They will be spinning at 45 percent speed.
    
    if(angle>0.1){ls=((ls+1.0))/2.0;rs=0.0;
    }if(angle<-0.1){ rs=(rs+1.0)/2.0;ls=0.0;
    }if(Math.abs(speed)<.2){if(angle>0.0){flw.set(WWSG);blw.set(WWSG);frw.set(WWSG);brw.set(WWSG);return;
    }if(angle<0.0){frw.set(WWSG);brw.set(WWSG);flw.set(WWSG);blw.set(WWSG);return;}}    
    //System.out.println("you're calling stickdrive()! congratulations.");
    //System.out.println("speed/angle: "+speed+" , "+angle);
    System.out.println("s: "+speed);
    System.out.println("r: "+rs);
    System.out.println("l: "+ls);
    frw.set(-rs);
    flw.set(ls);
    brw.set(-rs);
    blw.set(ls);
    //System.out.println("motor speeds are being set to "+(angle-speed)+" and "+(speed+angle));
  } 
 
  public void mecanum_drive(double power)
  {
    double yspeed=Robot.m_oi.getJoystick().getStickY();
    double xspeed=Robot.m_oi.getJoystick().getStickX();
    double angle =Robot.m_oi.getStickAngle();
    mecanum_drive.driveCartesian(yspeed,xspeed,angle);
    }
}
