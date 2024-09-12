package org.firstinspires.ftc.teamcode.opmodes_teleop;


import com.acmerobotics.roadrunner.Pose2d;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.commands.drive.JogDrive;
import org.firstinspires.ftc.teamcode.subsystems.MecanumDriveSubsystem;


@TeleOp
public class TeleopOpMode extends CommandOpMode {

    protected MecanumDriveSubsystem drive;


    Pose2d poseEstimate;
    GamepadEx driver;
    GamepadEx coDriver;

    int ctr;

    private int teleSwitch;

    @Override
    public void initialize() {

        driver = new GamepadEx(gamepad1);

        coDriver = new GamepadEx(gamepad2);

        drive = new MecanumDriveSubsystem(hardwareMap, new Pose2d(0, 0, 0));


        //DEFAULT COMMANDS

        drive.setDefaultCommand(new JogDrive(this.drive, driver, false));

    }


    public void runOpMode() throws InterruptedException {

        initialize();

        waitForStart();

        while (!isStopRequested() && opModeIsActive()) {

            run();

            checkTriggers();

            showTelemetry();


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


        public void showTelemetry () {

            //  poseEstimate = drive.drive.getPoseEstimate();
            if (teleSwitch > 4) teleSwitch = 0;
            if (teleSwitch < 0) teleSwitch = 4;


            switch (teleSwitch) {

                case 0:
                    drive.showTelemetry(telemetry);
                    break;
                case 1:
                    drive.showTelemetry(telemetry);
                    break;
                case 2:

                    break;
                case 3:

                    break;
                case 4:

                    break;
                default:
                    break;

            }
        }
    }

