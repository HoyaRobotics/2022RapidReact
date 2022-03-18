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
  /** Creates a new ClimbToNextRung. */
  public ClimbToNextRung(Climber climber) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.climber = climber;
    target = (int)(0.25*50);
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
      this.climber.setClimberMotor(0.15);
    //release solenoid
    if(counter == target)
      this.climber.setAngled(true);
    //stop motor so it extends fully for time
    if(counter >=(target+10))
      this.climber.setClimberMotor(0);
    //set solenoid
      if(counter >=(target+30))
      this.climber.setAngled(false);
    
    
    counter++;
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(counter >=(target+30))
      return true;
    return false;
  }
}
