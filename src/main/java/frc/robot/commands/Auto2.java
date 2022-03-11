// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveBase;
import frc.robot.commands.DriveForTime;
import frc.robot.commands.TimedIntake;
import frc.robot.commands.TimedIntake.IntakeMode;
import frc.robot.subsystems.Storage;
import frc.robot.subsystems.Intakev2;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class Auto2 extends SequentialCommandGroup {
  DriveBase driveBase;
  DriveForTime driveForTime;
  TimedIntake timedIntake;
  Intakev2 intake;
  Storage storage;
  /** Creates a new Auto2. */
  public Auto2(DriveBase drivebase, Intakev2 intake, Storage storage) {
    addCommands(
      new SequentialCommandGroup(
        new DriveForTime(driveBase, 0.5, 2),
        new TimedIntake(intake, storage, 0.5, 1.0, IntakeMode.INTAKE)
      )
    );
  }

  
}
