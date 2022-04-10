// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveBase;
public class TurnByDegree extends CommandBase {
  /** Creates a new TurnByDegree. */

  private final DriveBase driveBase;
    private  double speed;


    private int counter = 0;
    private double target = 0;
    private boolean zeroed = false;
    
  public TurnByDegree(DriveBase driveBase, double speed, double angle, int rotation) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.driveBase = driveBase;
    this.speed = speed;

    target = angle * 660;

    addRequirements(driveBase);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    
    double angle = SmartDashboard.getNumber("angle", 1);
    if(zeroed == false)
    {
      driveBase.zeroEncoders();
      zeroed = true;
    }
    
    double leftEncoder = Math.abs(driveBase.getLeftEncoder());
    double rightEncoder = Math.abs(driveBase.getRightEncoder());
    
    SmartDashboard.putNumber("Left encoder", leftEncoder);
    SmartDashboard.putNumber("Right encoder", rightEncoder);
    if(leftEncoder <= target && rightEncoder <= target)
    { 
      driveBase.setRotation(speed);
      SmartDashboard.putNumber("test", angle);
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
    return counter >= target;
  }
}