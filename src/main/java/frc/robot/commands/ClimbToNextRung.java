// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Climber;

public class ClimbToNextRung extends CommandBase {
  private Climber climber;
  private int counter = 0;
  private int target = 0;
  private int targetAngle = 0;
  private int targetFinish = 0;
  /** Creates a new ClimbToNextRung. */
  public ClimbToNextRung(Climber climber) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.climber = climber;
    target = (int)(50);
    targetAngle = 35;
    targetFinish = 80;
    addRequirements(this.climber);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //extend motor for time
    if(counter < target)
      this.climber.setClimberMotor(0.35);
    //release solenoid
    if(counter == target)
      this.climber.setAngled(false);
    //stop motor so it extends fully for time
    if(counter >=(target+targetAngle))
      this.climber.setClimberMotor(-0.35);
    //set solenoid
      if(counter >=(target+targetAngle+targetFinish-1)){
      this.climber.setAngled(true);
      this.climber.setClimberMotor(0);
      }
   
    
    
    counter++;
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    counter = 0;
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(counter >=(target+targetAngle+targetFinish))
      return true;
    return false;
  }
}
