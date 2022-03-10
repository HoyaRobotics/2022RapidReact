package frc.robot;

import static frc.robot.Constants.*;
import frc.robot.commands.*;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.DriveWithJoystick;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;


/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...

  private final XboxController driver = new XboxController(DRIVER);

  private final DriveBase driveBase = new DriveBase();
  private final Turret turret = new Turret();
  private final Intakev2 intake = new Intakev2();
  private final Storage storage = new Storage();
  //private final Intakev2 intakev2 = new Intakev2();


  private final ColorSensor colorSensor = new ColorSensor();

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {

    driveBase.setDefaultCommand(new DriveWithJoystick(driveBase, () -> driver.getLeftY(), () -> driver.getLeftX()));
    // Configure the button bindings
    configureButtonBindings();

    
    JoystickButton runIntakeFwd = new JoystickButton(driver,Controls.RUN_INTAKE_FWD);
    /*Mct - intake, this is the old way to call it*/
    runIntakeFwd.whileHeld(new IntakeBall(intake, colorSensor, storage));
    
  
    /*Mct - intake, this is the old way to call it*/
    runIntakeFwd.whenReleased(new StopIntake(intake, storage));/*
    
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
    toggleIntakeRaised.whileHeld(new InstantCommand(() -> {intake.toggleRaised();}));
//end old intake*/
    //GenericHID d_pad = new GenericHID(driver, Controls.RUN_INDEXER_FWD);

    //SmartDashboard.putNumber("D-Pad value", d_pad.getRawAxis(0))
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
    return new SequentialCommandGroup(
      new DriveForTime(driveBase, 0.6, 4),
      new DriveForDistance(driveBase, 0.6, 15)
    );
  }
}
