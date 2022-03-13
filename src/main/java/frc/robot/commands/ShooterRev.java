// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Limelight;
import frc.robot.utils.Logger;
import edu.wpi.first.wpilibj2.command.CommandBase;


public class ShooterRev extends CommandBase {

  private final Shooter shooter;
  private final Limelight limelight;

  private double targetRPM = 0;

  public ShooterRev(Shooter shooter, Limelight limelight){
    this.shooter = shooter;
    this.limelight = limelight;

    addRequirements(shooter, limelight);
}

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
      double distance = limelight.getDistanceFromTarget() + 0.5;

      if (distance <= 30)
          targetRPM = (distance * 34.1) + 2505;
      else
          targetRPM = 3750;

      shooter.setFlywheelRPM(targetRPM);

      Logger.info("Started revving shooter to " + targetRPM + "RPM");
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    Logger.info("Finished revving shooter");
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return shooter.isStable();
  }
}
