// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;
/*
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.TimedIntake.IntakeMode;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Turret;
//import frc.robot.util.Logger;
import frc.robot.subsystems.Limelight;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class ShootBall extends SequentialCommandGroup {
  public ShootBall(Shooter shooter, Intake intake, Turret turret, Limelight limelight) {
    // Use addRequirements() here to declare subsystem dependencies.

    addCommands(
      new TimedIntake(intake, -1.0, 0.1, IntakeMode.INTERNAL),
      new WaitCommand(0.1),
      new InstantCommand(() -> shooter.openBallGate()),
      new AlignTurret(turret, limelight),
      new ShooterRev(shooter, limelight),
      new AlignTurret(turret, limelight),
      new TimedIntake(intake, 1.0, 0.12, IntakeMode.INTERNAL),
      new AlignTurret(turret, limelight),
      new ShooterRev(shooter, limelight),
      new AlignTurret(turret, limelight),
      new TimedIntake(intake, 1.0, 0.12, IntakeMode.INTERNAL),
      new AlignTurret(turret, limelight),
      new ShooterRev(shooter, limelight),
      new AlignTurret(turret, limelight),
      new TimedIntake(intake, 1.0, 0.12, IntakeMode.INTERNAL),
      new AlignTurret(turret, limelight),
      new ShooterRev(shooter, limelight),
      new AlignTurret(turret, limelight),
      new TimedIntake(intake, 1.0, 0.12, IntakeMode.INTERNAL),
      new AlignTurret(turret, limelight),
      new ShooterRev(shooter, limelight),
      new AlignTurret(turret, limelight),
      new TimedIntake(intake, 1.0, 0.12, IntakeMode.INTERNAL)
    );

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
*/