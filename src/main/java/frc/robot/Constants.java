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
        public static final int FRONT_LEFT_DRIVE = 23; 
        public static final int FRONT_RIGHT_DRIVE = 22;
        public static final int REAR_LEFT_DRIVE = 25;
        public static final int REAR_RIGHT_DRIVE = 24;
        public static final int TURRET_ROTATOR = 4;
        public static final int INTAKE_INTERNAL_ROLLER_LEADER = 5;
        public static final int INTAKE_INTERNAL_ROLLER_FOLLOWER = 6;
        public static final int INTAKE_EXTERNAL_ROLLER = 7;
        public static final int SHOOTER_LEFT = 8;
        public static final int SHOOTER_RIGHT = 9;
        public static final int CLIMBER_VERTICAL_1 = 10;
        public static final int CLIMBER_VERTICAL_2 = 11;
        public static final int CLIMBER_HORIZONTAL = 12;

        //Intake

    
        //Drivebase
        public static final double CONTROL_DEADBAND = 0.10;
        public static final int SENSOR_UNITS_PER_ROTATION = 2048;
        public static final int ENCODER_UNITS_PER_ROTATION = 24140;
        public static final int ACCEL_UNITS = 473000;
        public static final int DECEL_UNITS = ACCEL_UNITS;
        
        //Turret
        public static final double TURRET_P = 0.05;
        public static final double TURRET_SENSITIVITY_DEGREES = 0.5;
        public static final double TURRET_SENSITIVITY_VELOCITY = 0;
    }