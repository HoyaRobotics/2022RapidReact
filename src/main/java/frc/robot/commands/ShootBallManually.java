// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.*;
import frc.robot.subsystems.*;
import frc.robot.commands.TimedIntake.IntakeMode;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class ShootBallManually extends SequentialCommandGroup {
  /** Creates a new ShootBallManually. */
  public ShootBallManually(Intakev2 intake, Storage storage, Shooter shooter, Limelight limelight) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      /*new ShooterRevManual(shooter),*/
      new ShooterRev(shooter, limelight),
      new TimedIntake(intake, storage, -1.0, 0.5, IntakeMode.STORAGE)
      /*new ShooterRevManual(shooter),
      new TimedIntake(intake, storage, -1.0, 0.5, IntakeMode.STORAGE)*/
    );
  }
}
