// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intakev2;
import frc.robot.subsystems.Storage;

/**
 * This command runs the intake at a certain speed
 * for a certain time.
 * 
 * There is also a distinction made between the intake 
 * and storage.
 * The intake is the rollers outside the frame.
 * Storage control the cargo in the robot.
 * 
 * This is used for automatic ball control during shooting,
 * in teleop and autonomous.
 */
public class TimedIntake extends CommandBase {

  public enum IntakeMode {
    INTAKE, STORAGE, BOTH
}

  private final Intakev2 intake;
  private final Storage storage;
  private final double speed;
  private final IntakeMode mode;
  private final int calls;

  private int callCounter = 0;

  public TimedIntake(Intakev2 intake, Storage storage, double speed, double seconds, IntakeMode mode) {
    this.intake = intake;
    this.storage = storage;
    this.speed = speed;
    this.mode = mode;

    calls = (int)(seconds * 50);
    addRequirements(this.intake);
    addRequirements(this.storage);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    if(mode == IntakeMode.INTAKE || mode == IntakeMode.BOTH)
        intake.setIntakeRoller(speed);
    if(mode == IntakeMode.STORAGE || mode == IntakeMode.BOTH)
        storage.setIndexerRoller(speed);

    callCounter = 1;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    callCounter++;
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    intake.setIntakeRoller(0);
    storage.setIndexerRoller(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return callCounter >= calls;
  }
}
