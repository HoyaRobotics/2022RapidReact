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
// import edu.wpi.first.wpilibj.PneumaticsModuleType;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;



public class Storage extends SubsystemBase {
  /** Creates a new Storage. */

  private CANSparkMax indexMotor;
  
  public Storage() {
    indexMotor = new CANSparkMax(Constants.INDEXER, MotorType.kBrushless);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
  public void setIndexerRoller(double speed){
    //SmartDashboard.putBoolean("Forward",direction);
    indexMotor.set(0.3) ;
  }
}
