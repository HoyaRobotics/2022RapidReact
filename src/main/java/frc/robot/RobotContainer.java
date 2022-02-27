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
  private final Intake intake = new Intake();


  private final ColorSensor colorSensor = new ColorSensor();

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    driveBase.setDefaultCommand(new DriveWithJoystick(driveBase, () -> driver.getLeftY(), () -> driver.getLeftX()));
    // Configure the button bindings
    configureButtonBindings();

    

    JoystickButton runIntakeFwd = new JoystickButton(driver,Controls.RUN_INTAKE_FWD);
    runIntakeFwd.whenPressed(new InstantCommand(() -> {
      intake.setInternalRoller(1.0);
    }
    ));

    runIntakeFwd.whenReleased(new InstantCommand(() ->{
      intake.setInternalRoller(0);
    }
    ));

    JoystickButton runIntakeRvs = new JoystickButton(driver,Controls.RUN_INTAKE_RVS);
    runIntakeFwd.whenPressed(new InstantCommand(() -> {
      intake.setInternalRoller(-1.0);
    }
    ));

    runIntakeRvs.whenReleased(new InstantCommand(() ->{
      intake.setInternalRoller(0);
    }
    ));

    JoystickButton toggleIntakeRaised = new JoystickButton(driver, Controls.TOGGLE_INTAKE_RAISED);
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
      new DriveForTime(driveBase, 0.6, 4) 
    );
  }
}
