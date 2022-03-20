// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.autos;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.commands.DriveForTime;
import frc.robot.commands.IntakeBall;
import frc.robot.commands.ShooterRevManual;
import frc.robot.commands.StopIntake;
import frc.robot.commands.StopShooter;
import frc.robot.commands.TimedIntake;
import frc.robot.commands.TimedIntake.IntakeMode;
import frc.robot.subsystems.ColorSensor;
import frc.robot.subsystems.DriveBase;
import frc.robot.subsystems.Intakev2;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Storage;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class MultiBallAuto extends SequentialCommandGroup {
  /** Creates a new MultiBallAuto. */
  public MultiBallAuto(Intakev2 intake, Storage storage, Shooter shooter, DriveBase driveBase, ColorSensor colorSensor) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      //new ParallelCommandGroup(
      new ShooterRevManual(shooter, 1250),
        //new setRaised(true)
      new TimedIntake(intake, storage, -1.0, 0.5, IntakeMode.STORAGE),
      new StopShooter(shooter),
      new ParallelCommandGroup(
        new DriveForTime(driveBase, 0.5, 2),
        new InstantCommand(() -> {intake.toggleRaised();}),
        new TimedIntake(intake, storage, -1.0, 0.5, IntakeMode.INTAKE)
        //new IntakeBall(intake, colorSensor, storage)
        ),
        new ShooterRevManual(shooter, 1550),
        //new setRaised(true)
        //new TimedIntake(intake, storage, -1.0, 0.5, IntakeMode.STORAGE),
        new StopShooter(shooter),
        new StopIntake(intake, storage)
    );
  }
}
