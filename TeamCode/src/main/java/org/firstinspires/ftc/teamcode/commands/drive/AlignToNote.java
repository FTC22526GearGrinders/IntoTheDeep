package org.firstinspires.ftc.teamcode.commands.drive;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.controller.PIDController;
import com.arcrobotics.ftclib.gamepad.GamepadEx;

import org.firstinspires.ftc.teamcode.subsystems.LimelightSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.MecanumDriveSubsystem;


public class AlignToNote extends CommandBase {
    public static double kP = .0009;
    public static double kD = .001;
    public static double kI = .001;
    private final MecanumDriveSubsystem drive;
    private final PIDController alignpid = new PIDController(kP, kI, kD);
    private final GamepadEx gamepad;
    private final int pipeline;
    private final LimelightSubsystem limelight;
    private final CommandOpMode myOpmode;


    public AlignToNote(MecanumDriveSubsystem drive, LimelightSubsystem limelight, GamepadEx gamepad, int pipeline, CommandOpMode opMode) {
        this.drive = drive;
        this.limelight = limelight;
        this.gamepad = gamepad;
        this.pipeline = pipeline;
        myOpmode = opMode;
        addRequirements(this.drive);
    }

    @Override
    public void initialize() {
        limelight.setPipeline(pipeline);
        limelight.test = limelight.test + 1;
        drive.show = false;
        limelight.show = false;
        FtcDashboard dashboard = FtcDashboard.getInstance();
        myOpmode.telemetry = new MultipleTelemetry(myOpmode.telemetry, dashboard.getTelemetry());
    }

    @Override
    public void execute() {

        double rx = this.gamepad.getRightX();

        if (limelight.getLatestResult().isValid()) {
            rx = alignpid.calculate(limelight.getTX(), 0);
        }

        double y = this.gamepad.getLeftY();
        double x = this.gamepad.getLeftX();
        drive.jog(y, x, rx);

        myOpmode.telemetry.addData("LLISValid", limelight.getLatestResult().isValid());
        myOpmode.telemetry.addData("TX", limelight.getTX());
        myOpmode.telemetry.addData("TY", limelight.getTY());
        myOpmode.telemetry.addData("Area", limelight.getTA());
        myOpmode.telemetry.addData("PIError", rx);
        myOpmode.telemetry.update();

    }

    @Override
    public void end(boolean interrupted) {
        // drive.show = true;
        limelight.show = true;
        drive.jog(0, 0, 0);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

}