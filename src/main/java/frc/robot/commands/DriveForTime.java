// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveBase;

public class DriveForTime extends CommandBase {
  
  private final DriveBase driveBase;
  private final double speed;

  private int counter = 0;
  private int target = 0;

  public DriveForTime(DriveBase driveBase, double speed, double seconds) {

    this.driveBase = driveBase;
    this.speed = speed;

    target = (int)(seconds*50);

    addRequirements(driveBase);
  }

  // Called when the command is initially scheduled.
  @Override
  public void execute() {
    if(counter < target)
      counter++;

      driveBase.arcadeDrive(-speed, 0);
  }

  // Called once the command ends or is interrupted.

  @Override
  public boolean isFinished() {
    return counter >= target;
  }

  @Override
  public void end(boolean interrupted) {
    driveBase.arcadeDrive(0, 0);
  }

}
