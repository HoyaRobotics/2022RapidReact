// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class ChangeCameraView extends CommandBase {
  NetworkTableEntry cameraSelection;
  /** Creates a new ChangeCameraView. */
  public ChangeCameraView() {
    // Use addRequirements() here to declare subsystem dependencies.
    cameraSelection = NetworkTableInstance.getDefault().getTable("").getEntry("CameraSelection");
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    if(cameraSelection.getString("limelight")=="limelight"){
      cameraSelection.setString("intakeCamera");
    }else{
      cameraSelection.setString("limelight");
    }
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
