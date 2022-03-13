// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.TimedIntake.IntakeMode;
import frc.robot.subsystems.Intakev2;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Turret;
import frc.robot.utils.Logger;
import frc.robot.subsystems.Limelight;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class ShootBall extends SequentialCommandGroup {
  public ShootBall(Shooter shooter, Intakev2 intake, Turret turret, Limelight limelight) {
    // Use addRequirements() here to declare subsystem dependencies.

    addCommands(
      new AlignTurret(turret, limelight),
      new ShooterRev(shooter, limelight),
      new AlignTurret(turret, limelight),
      new TimedIntake(intake, storage, 1.0, 0.12, IntakeMode.STORAGE),
      new AlignTurret(turret, limelight),
      new ShooterRev(shooter, limelight),
      new AlignTurret(turret, limelight),
      new TimedIntake(intake, storage, 1.0, 0.12, IntakeMode.STORAGE)
    );
  }
}
