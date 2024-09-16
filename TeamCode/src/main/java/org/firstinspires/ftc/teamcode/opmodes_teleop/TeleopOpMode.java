package org.firstinspires.ftc.teamcode.opmodes_teleop;


import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.roadrunner.Pose2d;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.command.button.Button;
import com.arcrobotics.ftclib.command.button.GamepadButton;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.commands.drive.AlignToNote;
import org.firstinspires.ftc.teamcode.commands.drive.JogDrive;
import org.firstinspires.ftc.teamcode.subsystems.LimelightSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.MecanumDriveSubsystem;


@TeleOp
public class TeleopOpMode extends CommandOpMode {

    protected MecanumDriveSubsystem drive;
    protected LimelightSubsystem limelight;
    FtcDashboard dashboard;
    GamepadEx driver;
    GamepadEx coDriver;
    private AlignToNote m_alighToNote;

    private Button alignbutton;

    @Override
    public void initialize() {

        driver = new GamepadEx(gamepad1);

        coDriver = new GamepadEx(gamepad2);

        drive = new MecanumDriveSubsystem(this, new Pose2d(0, 0, 0));

        limelight = new LimelightSubsystem(this);

        m_alighToNote = new AlignToNote(drive, limelight, driver, limelight.redPipeline, this);
        //DEFAULT COMMANDS

        alignbutton = (new GamepadButton(driver, GamepadKeys.Button.LEFT_BUMPER));

        drive.setDefaultCommand(new JogDrive(this.drive, driver, false, this));

        register(drive, limelight);

    }


    public void runOpMode() throws InterruptedException {
        initialize();
        waitForStart();
        while (!isStopRequested() && opModeIsActive()) {
            CommandScheduler.getInstance().run();
            checkTriggers();
        }
        reset();
    }

    void checkTriggers() {
        driver.readButtons();
        if (driver.getTrigger(GamepadKeys.Trigger.LEFT_TRIGGER) == 1) {
            new JogDrive(drive, driver, true, this).execute();
        }

        if (driver.getTrigger(GamepadKeys.Trigger.RIGHT_TRIGGER) == 1) {
            drive.resetEncoders();
        }
        alignbutton.whenHeld(m_alighToNote);


    }


}