// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import frc.robot.Constants;
import frc.robot.Constants.*;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Intakev2 extends SubsystemBase {
  //solenoids and motors intake uses
  private final Solenoid raiser = new Solenoid(PneumaticsModuleType.REVPH, Constants.INTAKE_RAISER);
  private final WPI_TalonSRX IntakeRoller = new WPI_TalonSRX(Constants.INTAKE_ROLLER);
  private boolean raised = true;//flag to ensure intake starts raised.

  /** Creates a new Intakev2. */
  public Intakev2() {
    //add code to current limit
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  /*Used to raise or lower the intake*/
  public void setRaised(boolean raised){
    if(this.raised != raised)
      this.raised = raised;

    raiser.set(!this.raised);
  }

  /*Changes the intake status (raises if lowered, lowers if raised)*/
  public void toggleRaised(){
    setRaised(!raised);
  }

  public double getSpeed(){
//    SmartDashboard.putNumber("speed gotten?", SmartDashboard.getNumber("intake speed", 0));
    
    return SmartDashboard.getNumber("intake speed", 0);
  }
  public void setIntakeRoller(double speed){
    SmartDashboard.putNumber("speed gotten?", 99);
    IntakeRoller.set(ControlMode.PercentOutput, speed);
  }
}
