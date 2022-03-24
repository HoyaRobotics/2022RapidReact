// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Climber extends SubsystemBase {
  /** Creates a new Climber. */
  private CANSparkMax LeftMotor;
  private CANSparkMax RightMotor;
  private final Solenoid LeftSolenoid  = new Solenoid(PneumaticsModuleType.REVPH, Constants.CLIMBER_LEFT_PCM);
  private final Solenoid RightSolenoid  = new Solenoid(PneumaticsModuleType.REVPH, Constants.CLIMBER_RIGHT_PCM);
  private boolean angled = true;//flag to ensure intake starts raised.
  private double leftEncoderRetracted=0;
  private double rightEncoderRetracted =0;
  private double leftEncoderExtended = 0;
  private double rightEncoderExtended = 0;
  
  public Climber() {
    this.LeftMotor = new CANSparkMax(Constants.CLIMBER_VERTICAL_L, MotorType.kBrushless);
    this.RightMotor = new CANSparkMax(Constants.CLIMBER_VERTICAL_R, MotorType.kBrushless);
    
//    this.LeftMotor.setSmartCurrentLimit(35);
  //  this.RightMotor.setSmartCurrentLimit(35);

    this.leftEncoderRetracted = this.LeftMotor.getEncoder().getPosition()-5;
    this.leftEncoderExtended = leftEncoderRetracted-70;
    this.rightEncoderRetracted = this.RightMotor.getEncoder().getPosition()+5;
    this.rightEncoderExtended = this.rightEncoderRetracted+70;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("ClimbLM", this.LeftMotor.getEncoder().getPosition());
    SmartDashboard.putNumber("ClimbRM", this.RightMotor.getEncoder().getPosition());
  }
  
  public void resetEncoderValues(){
    this.leftEncoderRetracted = this.LeftMotor.getEncoder().getPosition()-5;
    this.leftEncoderExtended = leftEncoderRetracted-70;
    this.rightEncoderRetracted = this.RightMotor.getEncoder().getPosition()+5;
    this.rightEncoderExtended = this.rightEncoderRetracted+70;
  }
  public void setClimberMotor(double speed){
    
    this.LeftMotor.set(-speed);
    this.RightMotor.set(speed);
  }
  public void setClimberMotorWithEncoder(double speed,boolean retract){
    if(retract){
      //right -, left +
      if(this.RightMotor.getEncoder().getPosition() > this.rightEncoderRetracted){
        this.RightMotor.set(speed);
      }else{
        this.RightMotor.set(0);
      }
      if(this.LeftMotor.getEncoder().getPosition() < this.leftEncoderRetracted){
        this.LeftMotor.set(-speed);
      }else{
        this.LeftMotor.set(0);
      }
    }else{
      //right -, left +
      if(this.RightMotor.getEncoder().getPosition() < this.rightEncoderExtended){
        this.RightMotor.set(-speed);
      }else{
        this.RightMotor.set(0);
      }
      if(this.LeftMotor.getEncoder().getPosition() > this.leftEncoderExtended){
        this.LeftMotor.set(speed);
      }else{
        this.LeftMotor.set(0);
      }
    }

  }
  
  /*Used to angle the arms*/
  public void setAngled(boolean angled){
    if(this.angled != angled)
      this.angled = angled;

    LeftSolenoid.set(!this.angled);
    RightSolenoid.set(!this.angled);
  }

  /*Changes the intake status (raises if lowered, lowers if raised)*/
  public void toggleAngled(){
    setAngled(!angled);
  }
}
