// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Turret;
/**
 * This command allows the turret to spin at a certain
 * speed for a certain time.
 * 
 * This is only used in autonomous.
 */
public class TurnTurretForTime extends CommandBase {
  private final Turret turret;
    private final double speed;

    private int counter = 0;
    private int target = 0;
  /** Creates a new TurnTurretForTime. */
  public TurnTurretForTime(Turret turret, double speed, double seconds) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.turret = turret;
        this.speed = speed;

        target = (int)(seconds * 50);

        addRequirements(turret);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {if(counter < target)
    counter++;

turret.setRotatorSpeed(speed);}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {turret.setRotatorSpeed(0);}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return counter >= target;
  }
}
