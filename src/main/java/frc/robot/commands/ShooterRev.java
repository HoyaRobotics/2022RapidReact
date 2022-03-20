// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import javax.lang.model.util.ElementScanner6;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Limelight;
import frc.robot.utils.Logger;
import edu.wpi.first.wpilibj2.command.CommandBase;


public class ShooterRev extends CommandBase {

  private final Shooter shooter;
  private final Limelight limelight;

  private double targetRPM = 0;
  double distance = 0;
  public ShooterRev(Shooter shooter, Limelight limelight){
    this.shooter = shooter;
    this.limelight = limelight;

    addRequirements(shooter, limelight);
}

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    SmartDashboard.putBoolean("In Rev March 20", true);
    distance = limelight.getYOffset();
    SmartDashboard.putNumber("March distance", distance);
    //distance = limelight.getDistanceFromTarget() + 0.5;
    //check if target visible
    //if visible, use table of values and offset to set RPM
    //if not visible0 - use rpm based on touching the hangar
    if(limelight.getTV()  == 0){
      targetRPM = 1250;
      SmartDashboard.putNumber("TS TV", 0);
    }else{
      SmartDashboard.putNumber("TS TV", distance);
      //table of values
      if(distance <2 )
        targetRPM = 3000;
      else if(distance <= 2.99)
        targetRPM = 2875;
      else if(distance <= 4.9)
        targetRPM = 2875;
      else if(distance < 7.25)
        targetRPM = 2500;
      else if(distance <= 8.5)
        targetRPM = 2350;
        else if(distance <=13.1)
        targetRPM = 2283;
        else if(distance <= 23.1)
        targetRPM = 2283;
        else
        targetRPM = 2283;
    }

    shooter.setFlywheelRPM(targetRPM);

    // double distance = limelight.getDistanceFromTarget() + 0.5;

    //   if (distance <= 30)
    //       targetRPM = (distance * 34.1) + 2505;
    //   else
    //       targetRPM = 3750;

    //   shooter.setFlywheelRPM(targetRPM);

    //   Logger.info("Started revving shooter to " + targetRPM + "RPM");
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    Logger.info("Finished revving shooter");
    SmartDashboard.putBoolean("In Rev March 20", false);

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return shooter.isStable();
  }
}
