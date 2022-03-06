// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveBase;

public class DriveForDistance extends CommandBase {

  private final DriveBase driveBase;
  private final double speed;
  private final double distance;

  private double distTarget = 0;
  
  public DriveForDistance(DriveBase driveBase, double speed, double distance) {
    this.driveBase = driveBase;
    this.speed = speed;
    this.distance = distance;

    distTarget = (int)(distance*341 / 10.71);
    //341 is the encoder value for one inch of rotation, 10.71 is the gear ratio found at https://andymark-weblinc.netdna-ssl.com/media/W1siZiIsIjIwMjIvMDEvMDYvMDkvNDYvMTQvMzEyNTA3ZGYtZDFlOS00MWYzLWJiODEtYzZmMDJjYTFjM2YwL0FNMTRVNV9Vc2VyR3VpZGVfMjAyMi5wZGYiXV0/AM14U5_UserGuide_2022.pdf?sha=778f29ff5a4ccf8d
    addRequirements(driveBase);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    this.driveBase.zeroEncoders();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    SmartDashboard.putNumber("left encoder", this.driveBase.getLeftEncoder());
    SmartDashboard.putNumber("right encoder", this.driveBase.getRightEncoder());

    while(Math.abs(this.driveBase.getLeftEncoder()) < distTarget)
    {
      driveBase.arcadeDrive(-speed, 0);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    driveBase.arcadeDrive(0, 0);
    
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return (this.driveBase.getLeftEncoder() >= distTarget);
  }
}
