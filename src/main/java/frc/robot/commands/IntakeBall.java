// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intakev2;
import frc.robot.subsystems.ColorSensor;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.Storage;

public class IntakeBall extends CommandBase {
  private final Intakev2 intake;
  private final ColorSensor proximitySensor;
  private final Storage storage;
  private boolean running = false;
  private double speed = 0;
  private int counter = 0;
  //private final Indexer indexer;
  /** Creates a new IntakeBall. */
  public IntakeBall(Intakev2 intake, ColorSensor sensor, Storage storage) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.proximitySensor = sensor;
    this.intake = intake;
    this.storage = storage;
    addRequirements(this.intake);
    addRequirements(this.proximitySensor);
    addRequirements(this.storage);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    this.intake.setIntakeRoller(1.0);
    //SmartDashboard.putNumber("McT",counter);
    counter++;
    if(this.proximitySensor.ballInStorage()){
      //SmartDashboard.putString("McT","Storage in use");
      this.storage.setIndexerRoller(0.0);
      //add line to set storage roller to 0
    }else{
      //SmartDashboard.putString("McT","Nothing here");
     // this.storage.setIndexerRoller(-0.3);//April 6 commented out to test
      //add line to set storage roller to 0.3
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    //storage.setIndexerRoller(0.0);
    //this.intake.setIntakeRoller(0.0);
  }

  // Returns true when the command should end.
  /*@Override
  public boolean isFinished() {
    return false;
  }*/
}
