package frc.robot;

import edu.wpi.first.wpilibj.XboxController.Button;

public final class Constants {
        public static class Controls{
            //Driver Controls 
             public final class GenericHID{
                 public final int DRIVE = Button.kLeftStick.value;

             public class Hand {
                    public double getTriggerAxis(GenericHID.Hand hand){
                        return 0;

             
                         
                     }
                }
             }
             public static final int RUN_INTAKE_FWD = Button.kRightBumper.value;
             public static final int RUN_INTAKE_BWD = Button.kLeftBumper.value;
             public static final int TOGGLE_INTAKE_RAISED = Button.kX.value;
             public static final int TOGGLE_CAMERA_VIEW = Button.kY.value;
             public static final int SHOOT_BALL = Button.kA.value;


             // Operator Controls:
             public static final int ROTATE_TURRET = Button.kRightStick.value;
             public static final int TOGGLE_LIMELIGHT_ZOOM = Button.kB.value;
             public static final int INC_RPM_OFFSET = Button.kRightBumper.value;
             public static final int DEC_RPM_OFFSET = Button.kLeftBumper.value;
             public static final int CHANGE_TARGET_GOAL= Button.kY.value;
            
        }
        
        //USB ids
        public static final int DRIVER = 0;
        public static final int OPERATOR = 1;

    
        //CANbus ids
        public static final int FRONT_LEFT_DRIVE = 0; 
        public static final int FRONT_RIGHT_DRIVE = 1;
        public static final int REAR_LEFT_DRIVE = 2;
        public static final int REAR_RIGHT_DRIVE = 3;

        //Intake

    
        //Drivebase
        public static final double CONTROL_DEADBAND = 0.10;
        
    }