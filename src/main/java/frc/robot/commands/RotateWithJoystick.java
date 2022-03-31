// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

import java.util.function.DoubleSupplier;

import frc.robot.Constants;
import frc.robot.subsystems.Turret;
import frc.robot.utils.utils;
import frc.robot.utils.Logger;

public class RotateWithJoystick extends CommandBase {

    private final Turret turret;
    private final DoubleSupplier input;

    public RotateWithJoystick(Turret turret, DoubleSupplier input){
        this.turret = turret;
        this.input = input;

        addRequirements(turret);
    }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute(){
      //double speed = utils.applydeadband(input.getAsDouble(), Constants.CONTROL_DEADBAND) / 2;
      //                                                                                    ^
      //                                  divide by two to allow for much finer turret control
      double speed = utils.applydeadband(input.getAsDouble(), Constants.CONTROL_DEADBAND) / 3.5;
      // changed to 3 to slow down the turret.
      turret.setRotatorSpeed(speed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
      turret.setRotatorSpeed(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
