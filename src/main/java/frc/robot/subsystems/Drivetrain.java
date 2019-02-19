/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

/**
 * Add your docs here.
 */
public class Drivetrain extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  WPI_TalonSRX frw;
  WPI_TalonSRX flw;
  WPI_TalonSRX brw;
  WPI_TalonSRX blw;

  public Drivetrain(WPI_TalonSRX frw, WPI_TalonSRX flw, WPI_TalonSRX brw, WPI_TalonSRX blw)
  {
    this.frw = frw;
    this.flw = flw;
    this.brw = brw;
    this.blw = blw;
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public void stickdrive(double speed, double angle)
  {
    frw.set((speed+angle)*-1);
    flw.set((speed+angle)*1);
    brw.set((speed+angle)*-1);
    blw.set((speed+angle)*1);

  }

  

}
