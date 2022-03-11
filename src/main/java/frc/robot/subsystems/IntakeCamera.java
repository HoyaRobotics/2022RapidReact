// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import org.photonvision.PhotonCamera;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IntakeCamera extends SubsystemBase {

  // Constants such as camera and target height stored. 
  //This needs to be adjusted, we may not need it!
  final double CAMERA_HEIGHT_METERS = Units.inchesToMeters(24);
  final double TARGET_HEIGHT_METERS = Units.feetToMeters(5);
  // Angle between horizontal and the camera.
  final double CAMERA_PITCH_RADIANS = Units.degreesToRadians(0);

  // How far from the target we want to be
  final double GOAL_RANGE_METERS = Units.feetToMeters(3);

  // Change this to match the name of your camera
  PhotonCamera intakeCamera = new PhotonCamera("photonvision");

  
  /** Creates a new IntakeCamera. */
  public IntakeCamera() {
    System.out.println("IntakeCamera constructor called");
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
