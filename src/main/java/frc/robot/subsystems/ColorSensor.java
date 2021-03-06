// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;

import com.revrobotics.ColorSensorV3;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorMatch;

public class ColorSensor extends SubsystemBase {
  private final I2C.Port i2cPort;
  private final ColorSensorV3 m_colorSensor;
 // private final ColorMatch m_colorMatcher = new ColorMatch();
  //private final Color kBlueTarget = new Color(0.143, 0.427, 0.429);
 // private final Color kRedTarget = new Color(0.561, 0.232, 0.114);
  private  int proximity = 0;
//  private final Color kBlueTarget = ColorMatch.makeColor(0.143, 0.427, 0.429);
 // private final Color kRedTarget = ColorMatch.makeColor(0.561, 0.232, 0.114);
  
  /** Creates a new ColorSensor. */
  public ColorSensor() {
    
    i2cPort = I2C.Port.kOnboard;
    m_colorSensor = new ColorSensorV3(i2cPort);
    //m_colorMatcher.addColorMatch(kBlueTarget);
    
   // m_colorMatcher.addColorMatch(kRedTarget);
  }

  @Override
  public void periodic() {
    //McT edit - March 7 - no longer getting colour.
    // This method will be called once per scheduler run
    /*Color detectedColor = m_colorSensor.getColor();
    String colorString;
    ColorMatchResult match = m_colorMatcher.matchClosestColor(detectedColor);*/


    //Attempting to measure proximity with the color sensor. -Ethan, 2022-03-05
    proximity = m_colorSensor.getProximity();//McT note - declared as class object

    //McT edit - March 7 - no longer getting colour.
    /*if (match.color == kBlueTarget) {
      colorString = "Blue";
    } else if (match.color == kRedTarget) {
      colorString = "Red";
    } else {
      colorString = "Unknown";
    }
    */



    /**
     * Open Smart Dashboard or Shuffleboard to see the color detected by the 
     * sensor.
     */
    //SmartDashboard.putNumber("Red", detectedColor.red);
    //SmartDashboard.putNumber("Blue", detectedColor.blue);
    //SmartDashboard.putNumber("Confidence", match.confidence);
    SmartDashboard.putNumber("Proximity", proximity);//Adding proximity values to the SmartDashboard.
//    SmartDashboard.putString("Detected Color", colorString);
    if(proximity > 100)
      SmartDashboard.putString("is ball in?", "yes");
    else
      SmartDashboard.putString("is ball in?", "no");

    
    /* NOTE: Storage roller is the internal roller. Needs to be clarified and properly defined. Confusion regarding which roller is the "internal". Waiting on clarification from programming lead/McTavish.
    while(proximity > (TBD - 10) ){//Small buffer window of 10, so a collision knocking the camera or robot wall to one another doesn't cause this to run. Might be uneeded? Not sure.
      intake.setStorageRoller(10);//Function needs to be defined, likely a copy of setIntakeRoller();. Additionally, is speed based on RPM? 
    }*/
  }
  public boolean ballInStorage(){
    
    if(proximity > 100){
    SmartDashboard.putBoolean("McT",true);
    return true;
    }
    else{
      SmartDashboard.putBoolean("McT",false);
      return false;
    }
  }


}
