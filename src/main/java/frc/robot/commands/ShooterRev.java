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
  double offsetRev;
  double offsetLimelightY;
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
    offsetRev = SmartDashboard.getNumber("Offset Rev", 0);
    offsetLimelightY = SmartDashboard.getNumber("Offset LimelightY", 0);
    //distance = limelight.getDistanceFromTarget() + 0.5;
    //check if target visible
    //if visible, use table of values and offset to set RPM
    //if not visible0 - use rpm based on touching the hangar
    if(limelight.getTV()  == 0){
      targetRPM = 1250;
      SmartDashboard.putNumber("TS TV", 0);
    }else{
      SmartDashboard.putNumber("TS TV", distance);
      //if based on March 30 shot testing.
      if(distance < -0.06){
        //y = -139.65x + 3072.2
        targetRPM = -139.65*distance + 3072.2 + SmartDashboard.getNumber("Offset Rev Long", 0); //add longer offset
//        targetRPM = 3085;
      }
      if(distance < 0.22){
        targetRPM = 3050 + SmartDashboard.getNumber("Offset Rev Long", 0); //add longer offset
      }
      if(distance < 0.41){
        targetRPM = 3025 + SmartDashboard.getNumber("Offset Rev Long", 0);
      }
      else if(distance < 0.66){
        targetRPM = 2950 + SmartDashboard.getNumber("Offset Rev Long", 0);
      }
      else if(distance < 1.51){
        targetRPM = 2860;
      }
      else if(distance < 1.73){
        targetRPM = 2830;
      }
      else if(distance < 1.94){
        targetRPM = 2810;
      }
      else if(distance < 3.42){
        targetRPM = 2810;
      }
      else if(distance < 3.80){
        targetRPM = 2802;
      }
      else if(distance < 4.46){
        targetRPM = 2798;
      }
      else if(distance < 5.40){
        targetRPM = 2785;
      }
      else if(distance < 6.132811546){
        targetRPM = 2785;
      }else if(distance <  7.560172080993652){
        targetRPM = 2750;
      }else if(distance < 8.343558311){
        targetRPM = 2550;
      }else if(distance <  9.360091209411621){
        targetRPM = 2708;
      }else if(distance <  9.831542015075684){
        targetRPM = 2500;
      }else if(distance < 10.75){
        targetRPM = 2500;
      
      }else if(distance < 11.81616879){
        targetRPM = 2400;
      }else if(distance < 12.61730099){
        targetRPM = 2250;
      }else if(distance < 17.94713402){
        targetRPM = 2350;
      }else if(distance < 23.27815819){
        targetRPM = 2300;
      }else{
        targetRPM = 2300;
      }
    //  targetRPM = -139.65*distance + 3072.2;
    }

    if(SmartDashboard.getBoolean("low shot", false))
    {
      targetRPM = 1250;
      shooter.setFlywheelRPM(targetRPM);
    }else{
    shooter.setFlywheelRPM(targetRPM+SmartDashboard.getNumber("Offset Rev", 0));
    }
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
  public void execute() {

  }

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
/*March 29 if statement:
//table of values
      if(distance < -0.552796)
      {
        targetRPM = 3200;

      }
      else if(distance <=2 ){
        //targetRPM = 3000;
        x1 = -0.552796;
       y1 = 3200;
       x2 = 2;
      y2 = 3100;
      targetRPM = (y2-y1)/(x2-x1)*distance+(y2-((y2-y1)/(x2-x1))*x2);
      }
      else if(distance <=2.88 ){
        //targetRPM = 3000;
        x1 = 2;
       y1 = 3100;
       x2 = 2.88;
      y2 = 2900;//was 3000
      targetRPM = (y2-y1)/(x2-x1)*distance+(y2-((y2-y1)/(x2-x1))*x2);
      }
      else if(distance <= 2.99)
      {
       // targetRPM = 2875;
       x1 = 2;
       y1 = 3000;
       x2 = 2.99;
      y2 = 2875;
      targetRPM = (y2-y1)/(x2-x1)*distance+(y2-((y2-y1)/(x2-x1))*x2);

      }else if(distance <= 3.64){
        //targetRPM = 2800
        x1 = 2.99;
        x2 = 3.64;
        y1 = 2875;
        y2 = 2800;
        targetRPM = (y2-y1)/(x2-x1)*distance+(y2-((y2-y1)/(x2-x1))*x2);

      
    }else if(distance <= 3.99){
      //targetRPM = 2800
      x1 = 3.64;
      x2 = 3.99;
      y1 = 2850;
      y2 = 2800;
      targetRPM = (y2-y1)/(x2-x1)*distance+(y2-((y2-y1)/(x2-x1))*x2);
    }else if(distance <= 4.20){
      //targetRPM = 2800
      x1 = 3.99;
      x2 = 4.2;
      y1 = 3000;
      y2 = 2850;
      targetRPM = (y2-y1)/(x2-x1)*distance+(y2-((y2-y1)/(x2-x1))*x2);
    }
      else if(distance <= 4.9){
        //targetRPM = 2875;
        x1 = 4.2;
        y1 = 2875;
        x2 = 4.9;
       y2 = 3000;
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
      }else if(distance <= 9.65){
        //targetRPM = 2600;
        x1 = 8.5;
        y1 = 2600;
        x2 = 8.5;
       y2 = 2350;
       targetRPM = (y2-y1)/(x2-x1)*distance+(y2-((y2-y1)/(x2-x1))*x2);
      }
        else if(distance <=13.1)
        {
        //targetRPM = 2283;
        x1 = 8.5;
        y1 = 2400;//was 2350
        x2 = 13.1;
       y2 = 2283;//was 2283
       targetRPM = (y2-y1)/(x2-x1)*distance+(y2-((y2-y1)/(x2-x1))*x2);  
      }
        else if(distance <= 23.1){
        //targetRPM = 2283;
        x1 = 13.1;
        y1 = 2450;
        x2 = 23.1;
       y2 = 2350;//was 2283
       targetRPM = (y2-y1)/(x2-x1)*distance+(y2-((y2-y1)/(x2-x1))*x2);
        }
        else
        targetRPM = 2283;
    }*/