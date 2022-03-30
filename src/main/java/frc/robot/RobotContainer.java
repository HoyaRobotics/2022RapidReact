package frc.robot;

import static frc.robot.Constants.*;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
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
  
  public enum OperatorMode{OPERATE, LOG};
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
  private final Compressor compressor = new Compressor(13,PneumaticsModuleType.CTREPCM);
  //private final Intakev2 intakev2 = new Intakev2();
  private final MarchAuto marchAuto = new MarchAuto(driveBase, shooter, storage, intake);

  

   // A chooser for autonomous commands
   SendableChooser<Command> m_chooser= new SendableChooser<>();
   SendableChooser<Command> HowToGetRPM = new SendableChooser<>();
   SendableChooser<OperatorMode> OperatorControls = new SendableChooser<>();
  
   ShootBallManually shootBallManually = new ShootBallManually(intake, storage, shooter, limelight);
   DriveForTime driveForTime = new DriveForTime(driveBase, 0.5, 2);
   Auto2 auto2;


  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Climber control
    climber.setDefaultCommand(new ControlClimber(climber, () -> operator.getPOV()));
    storage.setDefaultCommand(new ControlStorage(storage, () -> driver.getPOV()));
    driveBase.setDefaultCommand(new DriveWithJoystick(driveBase, () -> driver.getLeftY(), () -> driver.getLeftX()));
    turret.setDefaultCommand(new RotateWithJoystick(turret, () -> operator.getRightX()));

    compressor.enableDigital();

    // Configure the button bindings
    configureButtonBindings();

//    auto2= new Auto2(driveBase,  intake,  storage);

    // Add commands to the autonomous command chooser
m_chooser.setDefaultOption("March auto", marchAuto);
m_chooser.addOption("Multi auto", multiBallAuto);
m_chooser.addOption("Auto1 - Taxi", driveForTime);
//m_chooser.addOption("Auto2", auto2);

HowToGetRPM.setDefaultOption("From Dashboard", shootBallManually);
    OperatorControls.setDefaultOption("Operator",OperatorMode.OPERATE);
    OperatorControls.addOption("Log Data",OperatorMode.LOG);
    
// Put the chooser on the dashboard
SmartDashboard.putData(m_chooser);
SmartDashboard.putData(HowToGetRPM);
SmartDashboard.putData(OperatorControls);

//Defining our buttons
 //Operator commands
  //Climber commands
JoystickButton releaseClimberBtn = new JoystickButton(operator, Controls.RELEASE_CLIMBER);
JoystickButton climbToNextRungBtn = new JoystickButton(operator, Controls.CLIMB_TO_NEXT_RUNG);
JoystickButton angleClimberBtn = new JoystickButton(operator, Controls.ANGLE_CLIMBER);
  //Shooting commands
JoystickButton changeTargetGoal = new JoystickButton(operator, Controls.CHANGE_TARGET_GOAL);//Note: Functionality not programmed yet.

//JoystickButton increaseRPMBtn = new JoystickButton(operator, Controls.INC_RPM_OFFSET); //Note: functionality disabled after York competiton.
// JoystickButton decreaseRPMBtn = new JoystickButton(operator, Controls.DEC_RPM_OFFSET);

//Driver commands
  //Intake commands
JoystickButton toggleIntakeRaised = new JoystickButton(driver, Controls.TOGGLE_INTAKE_RAISED);
JoystickButton runIntakeBkd = new JoystickButton(driver, Controls.RUN_INTAKE_RVS);
JoystickButton runIntakeFwd = new JoystickButton(driver,Controls.RUN_INTAKE_FWD);
  //Camera commands
JoystickButton changeCameraViewBtn = new JoystickButton(driver, Controls.TOGGLE_CAMERA_VIEW);
  //Shooting commands
JoystickButton shootBallBtn = new JoystickButton(driver, Controls.SHOOT_BALL);

//OperatorMode based commands
System.out.println("***********************************\n\rV2 Operator Mode");
System.out.println(OperatorMode.OPERATE.ordinal());
System.out.println("NEW ONE!@!!");
System.out.println(OperatorControls.getSelected());
System.out.println((int)(OperatorMode.LOG.ordinal()));
System.out.println((int)(SmartDashboard.getNumber("OperatorControls",0)));

    if(SmartDashboard.getBoolean("NormalMode", true)){//If we are in normal mode
      
      climbToNextRungBtn.whenPressed(new ClimbToNextRung(climber));//A
      angleClimberBtn.whenPressed(new InstantCommand(() ->{climber.toggleAngled();}));//B
      //Change target goal functionality hasn't been added, when it is, define that command here. -Ethan.M
      //changeTargetGoal.whenPressed(new InstantCommand(() ->{**SUBSYTEM.CHANGE TARGET GOAL COMMAND();})); //Y

    }else{
      
      changeTargetGoal.whenPressed(new LogShotInfo(1));//Y, Shot went past the target, RPM is too high.
      angleClimberBtn.whenPressed(new LogShotInfo(0));//B, Shot went in, good RPM
      climbToNextRungBtn.whenPressed(new LogShotInfo(-1)); //A, Shot went short of target, too low RPM

    }
    
    //Not OperatorMode dependant commands - These are the controls we want to use regardless of what mode we have the robot in.

    releaseClimberBtn.whileHeld(new ReleaseClimber(climber));

    toggleIntakeRaised.whenPressed(new InstantCommand(() -> {intake.toggleRaised();}));
    runIntakeBkd.whileHeld(new PoopBall(intake));
    runIntakeFwd.whileHeld(new IntakeBall(intake, colorSensor, storage));
    /*Mct - intake, this is the old way to call it*/
    runIntakeFwd.whenReleased(new StopIntake(intake, storage));
    runIntakeBkd.whenReleased(new StopIntake(intake, storage));
      
    changeCameraViewBtn.whenPressed(new ChangeCameraView());

    shootBallBtn.whenPressed(HowToGetRPM.getSelected());
    shootBallBtn.whenReleased(new StopShooter(shooter));
    
    //increaseRPMBtn.whenPressed(new InstantCommand(() -> shooter.incrementRPMOffset(0)));
    //this might have been our problem with overshooting
    // decreaseRPMBtn.whenPressed(new InstantCommand(() -> shooter.decrementRPMOffset(100)));
    
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
