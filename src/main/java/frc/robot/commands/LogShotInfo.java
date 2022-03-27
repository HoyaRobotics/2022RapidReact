// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.DoubleSupplier;
import java.util.function.DoubleSupplier;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.utils.Logger;


public class LogShotInfo extends CommandBase {
  private int distance;
  public LogShotInfo(int distance) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.distance = distance;
    addRequirements();
  }
  
  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    String s=("Sachin").append(" Tendulkar).toString();  
    Logger.info(new StringBuilder()).append("Shot result: ").append(this.distance.toString());
    end(false);                           
  }
  
  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
