// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveBase;
import frc.robot.subsystems.Intakev2;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Storage;

public class MarchAuto extends CommandBase {
  private int counter = 0;
  private int target;
  private final DriveBase driveBase;
  private final Shooter shooter;
  private final Storage storage;
  private final Intakev2 intake;
  private int markFirstShot;
  private int markFedFirstShot;
  private int markReachedBall;
  private int markStartShot2;
  private int markFeedShot2;
  private int markEndShot2;
//turn on brake mode
//Rev shooter
//feed ball
//stop shooter
//stop storage
//drop intake
//start intake
//drive to ball
//Stop intake
//stop drivetrain

//rev shooter
//feed ball


//turn towards other ball
//start intake
//drive to ball
//stop intake
//stop drivetrain
//turn to goal
//rev shooter
//feed ball



  /** Creates a new MarchAuto. */
  public MarchAuto(DriveBase driveBase, Shooter shooter, Storage storage, Intakev2 intake) {
    this.driveBase = driveBase;
    this.shooter = shooter;
    this.storage = storage;
    this.intake = intake;



    // Convert time in seconds to robot cycles (50 cycles/s)
    target = (int)(12 * 50);
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(this.driveBase);
    addRequirements(this.shooter);
    addRequirements(this.storage);
    addRequirements(this.intake);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    this.markFirstShot = (int)(1*50);
    this.markFedFirstShot = (int)((2*50)+markFirstShot);
    this.markReachedBall = (int)((2*50)+markFedFirstShot);
    this.markStartShot2 =  (int)((1*50)+this.markReachedBall);
    this.markFeedShot2 = (int)((1*50)+this.markStartShot2);
    this.markEndShot2 = (int)((1*50)+this.markFeedShot2);    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(counter == 0){
      this.driveBase.setBrakeMode();
      this.shooter.setFlywheelRPM(1250);
    }
    //feed ball
    if(counter == markFirstShot){
      
      this.storage.setIndexerRoller(-1);
      this.intake.setRaised(false);
    }
    if(counter == markFedFirstShot){
      
      this.storage.setIndexerRoller(0);
      this.shooter.setFlywheelRPM(0);
      
      this.driveBase.arcadeDrive(-0.5,0);
      
    }
    if(counter == markFedFirstShot+1){
      this.intake.setIntakeRoller(1.0);
    }

    if(counter > markFirstShot && counter < markReachedBall){
      this.driveBase.arcadeDrive(-0.5,0);
    }
    if(counter == markReachedBall){
      this.driveBase.arcadeDrive(0,0);
      this.intake.setIntakeRoller(0);
      this.shooter.setFlywheelRPM(2000);
    }
    if(counter == markStartShot2){
      this.storage.setIndexerRoller(-1);
    }
    if(counter == markEndShot2){
      this.storage.setIndexerRoller(0);
      this.shooter.setFlywheelRPM(0);
    }

    counter++;
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    driveBase.setCoastMode();
    counter = 0;
    driveBase.arcadeDrive(0,0);
    shooter.stopMotors();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return counter >= target;
  }
}
