// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;
/*
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class TimedIntake extends CommandBase {

  public enum IntakeMode {
    INTERNAL, EXTERNAL, BOTH
}

  private final Intake intake;
  private final double speed;
  private final IntakeMode mode;
  private final int calls;

  private int callCounter = 0;

  public TimedIntake(Intake intake, double speed, double seconds, IntakeMode mode) {
    this.intake = intake;
    this.speed = speed;
    this.mode = mode;

    calls = (int)(seconds * 50);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    if(mode == IntakeMode.INTERNAL || mode == IntakeMode.BOTH)
        intake.setInternalRoller(speed);
    if(mode == IntakeMode.EXTERNAL || mode == IntakeMode.BOTH)
        intake.setExternalRoller(speed);

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
    intake.setInternalRoller(0);
    intake.setExternalRoller(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return callCounter >= calls;
  }
}
*/