// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.DoubleSupplier;


import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Storage;

public class ControlStorage extends CommandBase {

  private final Storage storage;
  private final DoubleSupplier commander;

  /** Creates a new ControlStorage. */
  public ControlStorage(Storage storage, DoubleSupplier commander) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.storage = storage;
    this.commander = commander;
  
    addRequirements(storage);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    int command = (int) commander.getAsDouble();

    if(command == 0)
      storage.setIndexerRoller(0.5);
    else if(command == 100)
      storage.setIndexerRoller(-0.5);
    else
      storage.setIndexerRoller(0);
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
