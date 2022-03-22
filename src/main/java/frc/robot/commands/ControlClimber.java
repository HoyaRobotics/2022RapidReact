// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.DoubleSupplier;
import java.util.function.DoubleSupplier;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Climber;

public class ControlClimber extends CommandBase {
    private final Climber climber;
    private final DoubleSupplier commander;
    /** Creates a new ControlClimber. */
  public ControlClimber(Climber climber, DoubleSupplier commander) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.climber = climber;
    this.commander = commander;
    addRequirements(climber);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    int command = (int) commander.getAsDouble();
    // Elevator control:
        // Up
        if(command == 0)
            climber.setClimberMotor(0.5);
        // Down
        else if(command == 180)
            climber.setClimberMotor(-0.5);
        else
            climber.setClimberMotor(0);

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
