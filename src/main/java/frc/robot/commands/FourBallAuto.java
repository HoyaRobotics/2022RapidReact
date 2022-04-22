// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.DriveBase;
import frc.robot.subsystems.Intakev2;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Storage;
import frc.robot.commands.TurnByDegree;

public class FourBallAuto extends CommandBase {
  private int counter = 0;
  private int target;
  private final DriveBase driveBase;
  private final Shooter shooter;
  private final Storage storage;
  private final Intakev2 intake;
  private int shootFirstBall;
  private int lowerIntake;
  private int delayForIntake;
  private int shootSecondBall;
  private int raiseIntake;
  private int driveFromSecondPosition;
  private int endDrive;
  private int AprilleftEncoderValue;
  private int aimFirstBall;
  private int aimSecondBall;
  private int firstTurn;
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



  /** Creates a new FourBallAuto. */
  public FourBallAuto(DriveBase driveBase, Shooter shooter, Storage storage, Intakev2 intake) {
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
    this.AprilleftEncoderValue = 0;
    this.aimFirstBall = (int)(75);
    this.shootFirstBall = (int)(100)+aimFirstBall;
    this.lowerIntake = (int)((50)+shootFirstBall);
    this.delayForIntake = (int)((50)+lowerIntake);
    this.aimSecondBall = (int)((75)+delayForIntake);
    this.shootSecondBall = (int)((SmartDashboard.getNumber("shootSecondBall",150))+aimSecondBall);
    this.firstTurn = (int)((10)+this.shootSecondBall);
    this.driveFromSecondPosition =  (int)((100)+this.firstTurn);
    this.endDrive = (int)((150)+this.driveFromSecondPosition);

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(counter == 0){
      this.driveBase.setBrakeMode();
      this.driveBase.arcadeDrive(-0.5,0);
      SmartDashboard.putString("Line","94");
    }
    if(counter < aimFirstBall){
      this.driveBase.arcadeDrive(-0.5,0);
      SmartDashboard.putString("Line","98");
    }
    if(counter == aimFirstBall){
      this.driveBase.arcadeDrive(0,0);
      this.shooter.setFlywheelRPM(SmartDashboard.getNumber("Four Ball Auto First Shot", Constants.AUTO_SHOT2_RPM));
      SmartDashboard.putString("Line","103");
    }
    //feed ball

    if(counter == shootFirstBall){
      this.driveBase.arcadeDrive(0,0);
      this.storage.setIndexerRoller(-1);
      SmartDashboard.putString("Line","110");
    }
    if(counter == lowerIntake){
      this.storage.setIndexerRoller(0);
      this.shooter.setFlywheelRPM(0);
      this.intake.setRaised(false);
      this.intake.setIntakeRoller(1.0);
      SmartDashboard.putString("Line","118");
    }
    if(counter >= delayForIntake &&  counter < aimSecondBall){
      this.driveBase.arcadeDrive(-0.5,0);
      SmartDashboard.putString("Line","122");
    }
    if(counter == aimSecondBall){
      this.driveBase.arcadeDrive(0,0);
      this.shooter.setFlywheelRPM(SmartDashboard.getNumber("Four Ball Auto Second Shot", Constants.AUTO_SHOT2_RPM));
      SmartDashboard.putString("Line","127");
    }
    if(counter == shootSecondBall){
      this.driveBase.arcadeDrive(0,0);
      this.storage.setIndexerRoller(-1);
      SmartDashboard.putString("Line","132"); 
    }
    if(counter == firstTurn){
      this.storage.setIndexerRoller(0);
      this.intake.setIntakeRoller(0);
      this.shooter.setFlywheelRPM(0);
      this.driveBase.arcadeDrive(-0.5,0);
    }
    if(counter == driveFromSecondPosition){
      this.driveBase.arcadeDrive(-0.5,0);
      SmartDashboard.putString("Line","140");
    }
    if(counter >= driveFromSecondPosition && counter < endDrive){
      this.driveBase.arcadeDrive(-0.5,0);
      SmartDashboard.putString("Line","144");
    }
    if(counter == endDrive){
      this.intake.setRaised(true);
      this.driveBase.arcadeDrive(0,0);
      SmartDashboard.putString("Line","148");
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