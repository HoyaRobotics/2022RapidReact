// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import static frc.robot.Constants.*;
import frc.robot.subsystems.Turret;
import frc.robot.subsystems.Limelight;

public class AlignTurret extends CommandBase {
  private final Turret turret;
  private final Limelight limielight;

  private boolean useTDM = false;
  private int tdmCount = 0
  private int tdmThreshold = 100;


  public AlignTurret(Turret turret, Limelight limelight) {
    this(turret, limelight, false);
  }

  public AlignTurret(Turret turret, Limelight limelight, boolean useTDM){
    this.turret = turret;
    this.limelight = limelight;
    this.useTDM = useTDM;
    
    addRequirements(turret, limelight);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    Logger.info("Beginning turret alignment");
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double speed = (limelight.getXOffset() + 2) * TURRET_P;

    if(speed > 0.1)
      speed = 0.1;
    else if(speed < 0.1)
      speed = -0.1;

    turret.setRotatorSpeed(speed);

    tdmCount++;
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
        turret.setRotatorSpeed(0);
        Logger.info("Finished turret alignment");
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
     if(useTDM && tdmCount >= tdmThreshold){
         Logger.warn("Turret disaster mitigation triggered");
         return true;
     }
     return Math.abs((limelight.getXOffset() + 2)) < TURRET_SENSITIVITY_DEGREES;
  }
}


//Evin was here
