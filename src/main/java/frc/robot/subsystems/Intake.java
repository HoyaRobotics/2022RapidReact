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

public class Intake extends SubsystemBase {
  private final Solenoid raiser = new Solenoid(PneumaticsModuleType.CTREPCM, Constants.INTAKE_RAISER);

  private final WPI_TalonSRX IntakeRoller = new WPI_TalonSRX(Constants.INTAKE_ROLLER);
  //private final CANSparkMax sparkMax = new CANSparkMax(Constants.storageRoller, MotorType.kBrushless)

  private boolean raised = false;

  public Intake() {
    IntakeRoller.configOpenloopRamp(0.50);
    IntakeRoller.configContinuousCurrentLimit(25);    
  }

  public void setRaised(boolean raised){
    if(this.raised != raised)
      this.raised = raised;

    raiser.set(!this.raised);
  }

  public void toggleRaised(){
    setRaised(!raised);
  }
  public double getSpeed(){
//    SmartDashboard.putNumber("speed gotten?", SmartDashboard.getNumber("intake speed", 0));
    return SmartDashboard.getNumber("Intake Speed McT",0);
  }
  public void setIntakeRoller(double speed, boolean direction){
    SmartDashboard.putBoolean("Forward",direction);
    IntakeRoller.set(ControlMode.PercentOutput, speed);
  }
}
