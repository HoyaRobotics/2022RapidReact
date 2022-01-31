package frc.robot.commands;


import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

import java.util.function.DoubleSupplier;

import frc.robot.Constants;
import frc.robot.subsystems.DriveBase;
import frc.robot.utils.utils;

/**
 * This class allows for control of the drivetrain
 * using a joystick. It runs the drivetrain in
 * arcade drive mode.
 * (the joystick's y-axis controls throttle and
 * x-axis controls rotation)
 */
public class DriveWithJoystick extends CommandBase{
    
    private final DriveBase driveBase;
    private final DoubleSupplier throttle;
    private final DoubleSupplier rotation;

    public DriveWithJoystick(DriveBase driveBase, DoubleSupplier throttle, DoubleSupplier rotation){
        this.driveBase = driveBase;
        this.throttle = throttle;
        this.rotation = rotation;
        
        addRequirements(driveBase);
    }

    @Override
    public void execute() {
        driveBase.arcadeDrive(
            utils.applydeadband(throttle.getAsDouble(), Constants.CONTROL_DEADBAND),
            utils.applydeadband(rotation.getAsDouble(), Constants.CONTROL_DEADBAND)
        );
        SmartDashboard.putNumber("test", 1);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted){
        driveBase.arcadeDrive(0, 0);
    }
}