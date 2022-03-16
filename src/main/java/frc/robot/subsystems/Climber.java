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

public class Climber extends SubsystemBase {
  /** Creates a new Climber. */
  private CANSparkMax LeftMotor;
  private CANSparkMax RightMotor;
  private final Solenoid LeftSolenoid  = new Solenoid(PneumaticsModuleType.REVPH, Constants.CLIMBER_LEFT_PCM);
  private final Solenoid RightSolenoid  = new Solenoid(PneumaticsModuleType.REVPH, Constants.CLIMBER_RIGHT_PCM);
  private boolean angled = true;//flag to ensure intake starts raised.
  
  
  public Climber() {
    this.LeftMotor = new CANSparkMax(Constants.CLIMBER_VERTICAL_L, MotorType.kBrushless);
    this.RightMotor = new CANSparkMax(Constants.CLIMBER_VERTICAL_R, MotorType.kBrushless);
    
//    this.LeftMotor.setSmartCurrentLimit(35);
 //   this.RightMotor.setSmartCurrentLimit(35);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
  
  public void setClimberMotor(double speed){
    
    this.LeftMotor.set(-speed);
    this.RightMotor.set(speed);
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
