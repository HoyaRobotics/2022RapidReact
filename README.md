# 2022RapidReact

March 10 - McT's Notes
Subsystems
Turret
TURRET_ROTATOR is declared in constants
Deleted local declaration
Line is now: 
    private final WPI_TalonSRX rotator = new WPI_TalonSRX(Constants.TURRET_ROTATOR);

Storage - looks good

Shooter
Replaced SHOOTER_LEFT with Constants.SHOOTER_LEFT
Same with right motor.
Added import frc.robot.Constants;
Noticed import static frc.robot.Constants.*;
This is wrong.  Deleted static
Error
C:\Users\Hoyar\Documents\2022RapidReact\2022RapidReact\src\main\java\frc\robot\subsystems\Shooter.java:28: warning: [removal] CANPIDController in com.revrobotics has been deprecated and marked for removal
  private final CANPIDController pid = right.getPIDController();
Checked documentation - use SparkMaxPIDController instead.
so I added
import com.revrobotics.SparkMaxPIDController;
then changed line
  private final CANPIDController pid = right.getPIDController();
 to
  private final SparkMaxPIDController pid = right.getPIDController();
Error: 
 C:\Users\Hoyar\Documents\2022RapidReact\2022RapidReact\src\main\java\frc\robot\subsystems\Shooter.java:30: warning: [removal] CANEncoder in com.revrobotics has been deprecated and marked for removal
  private final CANEncoder encoder = right.getEncoder();
documentation indicates replace with RelativeEncoder
Line 30 now reads: private final RelativeEncoder encoder = right.getEncoder();
added
import com.revrobotics.RelativeEncoder;
error C:\Users\Hoyar\Documents\2022RapidReact\2022RapidReact\src\main\java\frc\robot\subsystems\Shooter.java:51: error: cannot find symbol       
    pid.setFF(SHOOTER_FF);
Checked constants file, confirmed variable was there.  Changed line to
pid.setFF(Constants.SHOOTER_FF);
Made changes for other variables (Shooter_P)

Going to get RPM from Target Shooter RPM on Smart Dashboard

Limelight - was missing getDistanceFromTarget
- added the required Constants
Intake Camera
Ashley coded - working on checking.

https://docs.photonvision.org/en/latest/docs/programming/photonlib/adding-vendordep.html
Added to Programming 1 - need to add to Programming 2


Intakev2 - works

Indexer - not needed.
Gearbox - not needed
DriveBase - works
ColorSensor - works
Climber - need to code.
