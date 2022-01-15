package frc.robot;

import edu.wpi.first.wpilibj.XboxController.Button;

public final class Constants {
        public static class Controls{
            //Drive Controls 
             public final class GenericHID{
                 public final int DRIVE = Button.kLeftStick.value;
             }
             public static final int RUN_INTAKE_FWD = Button.kRightBumper.value;
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