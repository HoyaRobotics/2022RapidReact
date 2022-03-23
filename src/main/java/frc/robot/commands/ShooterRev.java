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
  double x1, x2, y1, y2;
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
      {
       // targetRPM = 2875;
       x1 = 2;
       y1 = 3000;
       x2 = 2.99;
      y2 = 2875;
      targetRPM = (y2-y1)/(x2-x1)*distance+(y2-((y2-y1)/(x2-x1))*x2);

      }
        
      else if(distance <= 4.9){
        //targetRPM = 2875;
        x1 = 2.99;
        y1 = 2875;
        x2 = 4.9;
       y2 = 2875;
       targetRPM = (y2-y1)/(x2-x1)*distance+(y2-((y2-y1)/(x2-x1))*x2);
      }
      else if(distance < 7.25)
        {//targetRPM = 2500;
          x1 = 4.9;
          y1 = 2875;
          x2 = 7.25;
         y2 = 2500;
         targetRPM = (y2-y1)/(x2-x1)*distance+(y2-((y2-y1)/(x2-x1))*x2);
        }
      else if(distance <= 8.5)
      {
        //targetRPM = 2350;
        x1 = 7.25;
        y1 = 2500;
        x2 = 8.5;
       y2 = 2350;
       targetRPM = (y2-y1)/(x2-x1)*distance+(y2-((y2-y1)/(x2-x1))*x2);
      }
        else if(distance <=13.1)
        {
        //targetRPM = 2283;
        x1 = 8.5;
        y1 = 2350;
        x2 = 13.1;
       y2 = 2283;
       targetRPM = (y2-y1)/(x2-x1)*distance+(y2-((y2-y1)/(x2-x1))*x2);  
      }
        else if(distance <= 23.1){
        //targetRPM = 2283;
        x1 = 13.1;
        y1 = 2350;
        x2 = 23.1;
       y2 = 2283;
       targetRPM = (y2-y1)/(x2-x1)*distance+(y2-((y2-y1)/(x2-x1))*x2);
        }
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
