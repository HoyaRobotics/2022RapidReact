package frc.robot.subsystems;


import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static frc.robot.Constants.*;
/**
 *  This subsystem encapsulates the motors running the
 * robot's drivebase.
 * We use four Falcon 500s (two on each side).
 * They are set to coast mode and are current limited,
 * to prevent brownouts while pushing other robots.
 * 
 * It is used with the arcade drive control scheme,
 * where the joystick's y-axis controls throttle and 
 * the x-axis controls rotation.
 */

public class DriveBase extends SubsystemBase {
  /*
  private final WPI_TalonFX leftLeader = new WPI_TalonFX(FRONT_LEFT_DRIVE);
  private final WPI_TalonFX rightLeader = new WPI_TalonFX(FRONT_RIGHT_DRIVE);
  private final WPI_TalonFX leftFollower = new WPI_TalonFX(REAR_LEFT_DRIVE);
  private final WPI_TalonFX rightFollower = new WPI_TalonFX(REAR_RIGHT_DRIVE);
  */

  private final WPI_VictorSPX leftLeader = new WPI_VictorSPX(FRONT_LEFT_DRIVE);
  private final WPI_VictorSPX rightLeader = new WPI_VictorSPX(FRONT_RIGHT_DRIVE);
  private final WPI_VictorSPX leftFollower = new WPI_VictorSPX(REAR_LEFT_DRIVE);
  private final WPI_VictorSPX rightFollower = new WPI_VictorSPX(REAR_RIGHT_DRIVE);

  MotorControllerGroup m_left = new MotorControllerGroup(leftLeader,leftFollower);
  MotorControllerGroup m_right = new MotorControllerGroup(rightLeader,rightFollower);

  private final DifferentialDrive drive = new DifferentialDrive(m_left, m_right);

  /** Creates a new DriveBase. */
  public DriveBase() {
    leftLeader.configFactoryDefault();
    rightLeader.configFactoryDefault();
    leftFollower.configFactoryDefault();
    rightFollower.configFactoryDefault();

  leftLeader.setInverted(true);
  leftFollower.setInverted(true);

 //   SupplyCurrentLimitConfiguration supplyLimit = new SupplyCurrentLimitConfiguration(true, 30, 35, 1.0);
 //   leftLeader.configSupplyCurrentLimit(supplyLimit);
 //   rightLeader.configSupplyCurrentLimit(supplyLimit);
 //   leftFollower.configSupplyCurrentLimit(supplyLimit);
 //   rightFollower.configSupplyCurrentLimit(supplyLimit);

    leftLeader.setNeutralMode(NeutralMode.Coast);
    rightLeader.setNeutralMode(NeutralMode.Coast);
    leftFollower.setNeutralMode(NeutralMode.Coast);
    rightFollower.setNeutralMode(NeutralMode.Coast);

    leftFollower.follow(leftLeader);
    rightFollower.follow(rightLeader);

  }

  public void zeroEncoders(){
//    leftLeader.getSensorCollection().setIntegratedSensorPosition(0, 30);
//    rightLeader.getSensorCollection().setIntegratedSensorPosition(0,30);
//    leftFollower.getSensorCollection().setIntegratedSensorPosition(0, 30);
//    rightFollower.getSensorCollection().setIntegratedSensorPosition(0, 30);
  }

  public double getLeftEncoder(){
    return leftLeader.getSelectedSensorPosition();
  }

  public double getRightEncoder(){
    return rightLeader.getSelectedSensorPosition();
  }

  public void setRotation(double speed){
    leftLeader.set(ControlMode.PercentOutput, speed);
    rightLeader.set(ControlMode.PercentOutput, speed);
  }

  public double getPosition(){
    return 0;
    //return rightLeader.getSensorCollection().GetIntergratedSensorPosition();
  }

  public double getVelocity(){
    return 0;
    //return rightMaster.getSensorCollection().getIntergratedSensorVelocity();
  }

  public void setBrakeMode(){
    leftLeader.setNeutralMode(NeutralMode.Brake);
    leftFollower.setNeutralMode(NeutralMode.Brake);
    rightLeader.setNeutralMode(NeutralMode.Brake);
    rightFollower.setNeutralMode(NeutralMode.Brake);
  }

  public void setCoastMode(){
    leftLeader.setNeutralMode(NeutralMode.Coast);
    leftFollower.setNeutralMode(NeutralMode.Coast);
    rightLeader.setNeutralMode(NeutralMode.Coast);
    rightFollower.setNeutralMode(NeutralMode.Coast);
  }

  
    //Drives the robot with arcade controls, supplying throttle and rotation.
   
  public void arcadeDrive(double throttle, double rotation){
    rotation *= -1;
//    SmartDashboard.putNumber("LeftEncoder", leftLeader.getSelectedSensorPosition());
//    SmartDashboard.putNumber("RightEncoder", rightLeader.getSelectedSensorPosition());
    SmartDashboard.putNumber("THROTTLE rr", throttle);
    SmartDashboard.putNumber("Rotation rr", rotation);
    drive.arcadeDrive(throttle, rotation);
  }
}
