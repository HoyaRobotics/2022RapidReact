// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;
import frc.robot.utils.Logger;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
public class ShooterRevManual extends CommandBase {

  private final Shooter shooter;

  public ShooterRevManual(Shooter shooter){
    this.shooter = shooter;

    addRequirements(shooter);
}

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    double rpm = SmartDashboard.getNumber("Target Shooter RPM",1000);
      shooter.setFlywheelRPM(rpm);

      Logger.info("Started revving shooter to " + rpm + "RPM");
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