// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intakev2;
import frc.robot.subsystems.ColorSensor;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.Storage;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class StopIntake extends CommandBase {
  private final Intakev2 intake;
  private final Storage storage;
  /** Creates a new StopIntake. */
  public StopIntake(Intakev2 intake,  Storage storage) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.intake = intake;
    this.storage = storage;
    addRequirements(this.intake);
    addRequirements(this.storage);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    this.intake.setIntakeRoller(0.0);
    this.storage.setIndexerRoller(0.0);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
