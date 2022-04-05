package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.TalonSRXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import frc.robot.utils.utils;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.Constants.*;

/**
 * This subsystem encapsulates the robot's turret.
 */
public class Turret extends SubsystemBase {

    private final WPI_TalonSRX rotator = new WPI_TalonSRX(TURRET_ROTATOR);

    public Turret(){
      //System.out.println("Shooter constructor called");
        rotator.configSelectedFeedbackSensor(TalonSRXFeedbackDevice.Analog, 0, 10);
        rotator.setSelectedSensorPosition(0, 0, 10);
    }

    public void setRotatorSpeed(double speed){
        speed = utils.applydeadband(speed, 0);//was 0.25
        rotator.set(ControlMode.PercentOutput, speed);
    }
}