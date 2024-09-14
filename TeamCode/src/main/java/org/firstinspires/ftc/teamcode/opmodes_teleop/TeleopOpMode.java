package org.firstinspires.ftc.teamcode.opmodes_teleop;


import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.roadrunner.Pose2d;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.commands.drive.JogDrive;
import org.firstinspires.ftc.teamcode.subsystems.LimelightSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.MecanumDriveSubsystem;


@TeleOp
public class TeleopOpMode extends CommandOpMode {

    public static int CTR;
    protected MecanumDriveSubsystem drive;
    protected LimelightSubsystem limelight;
    FtcDashboard dashboard;
    Pose2d poseEstimate;
    GamepadEx driver;
    GamepadEx coDriver;



    @Override
    public void initialize() {

        driver = new GamepadEx(gamepad1);

        coDriver = new GamepadEx(gamepad2);

        drive = new MecanumDriveSubsystem(this, new Pose2d(0, 0, 0));

        limelight = new LimelightSubsystem(this);
        //DEFAULT COMMANDS

        drive.setDefaultCommand(new JogDrive(this.drive, driver, false));

    }


    public void runOpMode() throws InterruptedException {

        initialize();

        waitForStart();

        while (!isStopRequested() && opModeIsActive()) {

            run();

            checkTriggers();


        }
        reset();

    }

    void checkTriggers() {

        if (driver.getTrigger(GamepadKeys.Trigger.LEFT_TRIGGER) == 1) {
            new JogDrive(drive, driver, true).execute();
        }

        if (driver.getTrigger(GamepadKeys.Trigger.RIGHT_TRIGGER) == 1) {
            drive.resetEncoders();
        }
        telemetry.update();
    }


}

