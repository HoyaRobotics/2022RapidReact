// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.autos;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.DriveForTime;
import frc.robot.commands.ShooterRevManual;
import frc.robot.commands.TimedIntake;
import frc.robot.commands.TimedIntake.IntakeMode;
import frc.robot.subsystems.DriveBase;
import frc.robot.subsystems.Intakev2;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Storage;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class MultiBallAuto extends SequentialCommandGroup {
  /** Creates a new MultiBallAuto. */
  public MultiBallAuto(Intakev2 intake, Storage storage, Shooter shooter, DriveBase driveBase) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new ShooterRevManual(shooter, 1250),
      new TimedIntake(intake, storage, -1.0, 0.5, IntakeMode.STORAGE),
      new DriveForTime(driveBase, 0.5, 2)
    );
  }
}
