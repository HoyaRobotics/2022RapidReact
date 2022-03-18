package frc.robot;

import static frc.robot.Constants.*;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.subsystems.*;
import frc.autos.MultiBallAuto;
import frc.robot.commands.*;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;


/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...

  private final XboxController driver = new XboxController(DRIVER);
  private final XboxController operator = new XboxController(OPERATOR);

  private final DriveBase driveBase = new DriveBase();
  private final Turret turret = new Turret();
  private final Intakev2 intake = new Intakev2();
  private final Storage storage = new Storage();
  private final Shooter shooter = new Shooter();
  private final IntakeCamera intakeCamera = new IntakeCamera();
  private final Limelight limelight = new Limelight();
  private final Climber climber = new Climber();
  private final ColorSensor colorSensor = new ColorSensor();
  private final MultiBallAuto multiBallAuto = new MultiBallAuto( intake,  storage,  shooter,  driveBase, colorSensor);
  //private final Intakev2 intakev2 = new Intakev2();


  

   // A chooser for autonomous commands
   SendableChooser<Command> m_chooser= new SendableChooser<>();
   SendableChooser<Command> HowToGetRPM = new SendableChooser<>();
   ShootBallManually shootBallManually = new ShootBallManually(intake, storage, shooter);
   DriveForTime driveForTime = new DriveForTime(driveBase, 0.5, 2);
   Auto2 auto2;


  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    storage.setDefaultCommand(new ControlStorage(storage, () -> driver.getPOV()));
    driveBase.setDefaultCommand(new DriveWithJoystick(driveBase, () -> driver.getLeftY(), () -> driver.getLeftX()));
    turret.setDefaultCommand(new RotateWithJoystick(turret, () -> driver.getRightX()));
    // Configure the button bindings
    configureButtonBindings();

//    auto2= new Auto2(driveBase,  intake,  storage);

    // Add commands to the autonomous command chooser
m_chooser.setDefaultOption("Auto1 - Taxi", driveForTime);
m_chooser.addOption("Multi auto", multiBallAuto);
//m_chooser.addOption("Auto2", auto2);

HowToGetRPM.setDefaultOption("From Dashboard", shootBallManually);
// Put the chooser on the dashboard
SmartDashboard.putData(m_chooser);
SmartDashboard.putData(HowToGetRPM);

    JoystickButton releaseClimberBtn = new JoystickButton(operator, Controls.RELEASE_CLIMBER);
    releaseClimberBtn.whileHeld(new ReleaseClimber(climber));

    JoystickButton climbToNextRungBtn = new JoystickButton(operator, Controls.CLIMB_TO_NEXT_RUNG);
    climbToNextRungBtn.whenPressed(new ClimbToNextRung(climber));

    JoystickButton runIntakeBkd = new JoystickButton(driver, Controls.RUN_INTAKE_RVS);
    runIntakeBkd.whileHeld(new PoopBall(intake));
    JoystickButton runIntakeFwd = new JoystickButton(driver,Controls.RUN_INTAKE_FWD);
    /*Mct - intake, this is the old way to call it*/
    runIntakeFwd.whileHeld(new IntakeBall(intake, colorSensor, storage));

    JoystickButton changeCameraViewBtn = new JoystickButton(driver, Controls.TOGGLE_CAMERA_VIEW);
    changeCameraViewBtn.whenPressed(new ChangeCameraView());

    JoystickButton shootBallBtn = new JoystickButton(driver, Controls.SHOOT_BALL);
    shootBallBtn.whenPressed(HowToGetRPM.getSelected());
    shootBallBtn.whenReleased(new StopShooter(shooter));
    //McT check this out
    //checked - moved to ChangeCameraView
    /*@Override
    public void teleopPeriodic() {
      if (joy1.getTriggerPressed()) {
        System.out.println("Setting camera 2");
        cameraSelection.setString(camera2.getName());
      } else if (joy1.getTriggerReleased()) { 
        System.out.println("Settingcamera 1");
        cameraSelection.setString(camera1.getName());
      }
    }
 */ 
    /*Mct - intake, this is the old way to call it*/
    runIntakeFwd.whenReleased(new StopIntake(intake, storage));
    runIntakeBkd.whenReleased(new StopIntake(intake, storage));
    /*
    
    /*
    runIntakeFwd.whileHeld(new InstantCommand(() -> {
      intake.setIntakeRoller(intake.getSpeed(),true);
    }
    ));//*/
    /*McT new intake
    runIntakeFwd.whileHeld(new IntakeBall(intakev2, colorSensor));
/*McT - old intake*/
  /*  runIntakeFwd.whenReleased(new InstantCommand(() ->{
      intake.setIntakeRoller(0,true);
    }
    ));*/

    /*JoystickButton runIntakeRvs = new JoystickButton(driver,Controls.RUN_INTAKE_RVS);
    runIntakeRvs.whileHeld(new InstantCommand(() -> {
      intake.setIntakeRoller(intake.getSpeed()*-1,false);
    }
    ));

    runIntakeRvs.whenReleased(new InstantCommand(() ->{
      intake.setIntakeRoller(0,false);
    }
    ));*/

    JoystickButton toggleIntakeRaised = new JoystickButton(driver, Controls.TOGGLE_INTAKE_RAISED);
    toggleIntakeRaised.whenPressed(new InstantCommand(() -> {intake.toggleRaised();}));
//end old intake*/
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {}

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return m_chooser.getSelected();
  }
}
