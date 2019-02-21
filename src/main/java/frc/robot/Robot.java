/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

//updated 2-21-19

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.Cone;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Pecker;
import frc.robot.RobotMap;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  

  private String m_autoSelected;
/*private WPI_TalonSRX frwheel;
  private WPI_TalonSRX flwheel;
  private WPI_TalonSRX brwheel;
  private WPI_TalonSRX blwheel;
  */
  private  WPI_TalonSRX lift;
  public static final Pecker p_subsystem= new Pecker();
  public static final Cone c_subsystem = new Cone();
  public static final Drivetrain d_subsystem = new Drivetrain();
  //public static Drivetrain d_subsystem = new Drivetrain(blwheel, blwheel, blwheel, blwheel);
  public static OI m_oi;

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
   @Override
  public void robotInit() {
    m_oi = new OI();
    /*
    frwheel = new WPI_TalonSRX(RobotMap.frw);
    flwheel = new WPI_TalonSRX(RobotMap.flw);
    brwheel = new WPI_TalonSRX(RobotMap.brw);
    blwheel = new WPI_TalonSRX(RobotMap.blw);
    */
    lift = new WPI_TalonSRX(RobotMap.lift);
    
    
    
  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   * You can use it to reset any subsystem information you want to clear when
   * the robot is disabled.
   */
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();


  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString code to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional commands to the
   * chooser code above (like the commented example) or additional comparisons
   * to the switch structure below with additional strings & commands.
   */
 /* @Override
  public void autonomousInit() {
    m_autonomousCommand = m_chooser.getSelected();

    /*
     * String autoSelected = SmartDashboard.getString("Auto Selector",
     * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
     * = new MyAutoCommand(); break; case "Default Auto": default:
     * autonomousCommand = new ExampleCommand(); break; }
     */

    // schedule the autonomous command (example)
   /* if (m_autonomousCommand != null) {
      m_autonomousCommand.start();
    }
  }

  /**
   * This function is called periodically during aut
   * onomous.
   */
  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  /*@Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
   /* if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
    //d_subsystem.stickdrive(m_oi.getStickY(),m_oi.getStickAngle());
    //System.out.println("WOW YOU'RE CALLING STICKDRIVE YAY");
    //System.out.println("stick y and angle are "+m_oi.getStickY()+" , "+m_oi.getStickAngle());
    
    //this part *should* be implemented as a command class but it was causing problems so I'm just bypassing it

    //get joystick doubles
    double x = m_oi.getJoystick().getX();
    double y = m_oi.getJoystick().getY();
    double a = m_oi.getJoystick().getTwist();
    double t = m_oi.getJoystick().getThrottle();
    //modify joystick doubles
    t*=-1;
    if(Math.abs(x)<0.2){
      x = 0;
    }
    if(Math.abs(y)<0.2){
      y = 0;
    }
    if(Math.abs(a)<0.5){
      a = 0;
    }
    //drive
    d_subsystem.stickdrive(y,a*2,(1.25+t)*.75);
  }
  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
