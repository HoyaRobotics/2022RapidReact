// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Indexer;
import java.util.function.DoubleSupplier;
public class IndexWithJoystick extends CommandBase {
  /** Creates a new IndexWithJoystick. */

  private final Indexer indexer;
  private final DoubleSupplier speed;

  
  public IndexWithJoystick(Indexer indexer, DoubleSupplier speed) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.indexer = indexer;
    this.speed = speed;

    addRequirements(indexer);
  }
  

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //indexer.setFlywheelPercent(speed.getAsDouble());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    //indexer.setFlywheelPercent(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
