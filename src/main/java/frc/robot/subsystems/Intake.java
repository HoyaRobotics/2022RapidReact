// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import frc.robot.Constants;
import frc.robot.Constants.*;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;

public class Intake extends SubsystemBase {
  private final Solenoid raiser = new Solenoid(PneumaticsModuleType.REVPH, Constants.INTAKE_RAISER);

  private final WPI_TalonSRX internalRoller = new WPI_TalonSRX(Constants.INTAKE_INTERNAL_ROLLER);

  private boolean raised = false;

  public Intake() {
  }

  public void setRaised(boolean raised){
    if(this.raised != raised)
      this.raised = raised;
  }

  public void toggleRaised(){
    setRaised(!raised);
  }

  public void setInternalRoller(double speed){
    internalRoller.set(ControlMode.PercentOutput, speed);
  }
}
