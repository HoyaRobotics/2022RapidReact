// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Limelight extends SubsystemBase {
  private final NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");

  private final NetworkTableEntry pipeline = table.getEntry("pipeline");
  private final NetworkTableEntry xOffset = table.getEntry("tx");
  private final NetworkTableEntry yOffset = table.getEntry("ty");
  private final NetworkTableEntry tv = table.getEntry("tv");
  private final NetworkTableEntry ta = table.getEntry("ta");
  private int currentPipeline = 0;
  
  @Override
  public void periodic() {
    // This method will be called once per scheduler 
    table.getEntry("ledmode").setNumber(3);
  }

  public void toggleZoom(){
    if(currentPipeline == 0)
      currentPipeline = 1;
    else if(currentPipeline == 1)
      currentPipeline = 0;

      setPipeline(currentPipeline);
  }

/**
 * Calculate distance to target.  Refer to Limelight docs.
 */
  public double getDistanceFromTarget() {
    return Constants.LL_SHOT_HEIGHT/Math.abs(Math.toRadians(getYOffset()));
    
  }
  public double getXOffset(){
    return xOffset.getDouble(0);
  }

  public double getArea(){
    return ta.getDouble(0);
  }

  public double getYOffset(){
    return yOffset.getDouble(0);
  }

  public double getTV(){
    return tv.getDouble(0);
  }

  public void setPipeline(int id){
    pipeline.setNumber(id);
  }
}
