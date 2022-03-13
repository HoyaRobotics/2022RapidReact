// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intakev2;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.Storage;

public class PoopBall extends CommandBase {
  private final Intakev2 intake;
  private boolean running = false;
  private double speed = 0;
  private int counter = 0;
  //private final Indexer indexer;
  /** Creates a new IntakeBall. */
  public PoopBall(Intakev2 intake) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.intake = intake;
    addRequirements(this.intake);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    this.intake.setIntakeRoller(-1.0);
    //SmartDashboard.putNumber("McT",counter);
    counter++;
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    //storage.setIndexerRoller(0.0);
    //this.intake.setIntakeRoller(0.0);
  }

  // Returns true when the command should end.
  /*@Override
  public boolean isFinished() {
    return false;
  }*/
}
