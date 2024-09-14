package org.firstinspires.ftc.teamcode.commands.drive;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.controller.PIDController;
import com.arcrobotics.ftclib.gamepad.GamepadEx;

import org.firstinspires.ftc.teamcode.subsystems.LimelightSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.MecanumDriveSubsystem;


public class AlignToNote extends CommandBase {
    public static double kP = .001;
    public static double kD = .001;
    public static double kI = .001;
    private final MecanumDriveSubsystem drive;
    private LimelightSubsystem limelight;
    private final PIDController alignpid = new PIDController(kP, kI, kD);
    private final GamepadEx gamepad;
    private final int pipeline;


    public AlignToNote(MecanumDriveSubsystem drive, LimelightSubsystem limelight, GamepadEx gamepad, int pipeline) {
        this.drive = drive;
        this.gamepad = gamepad;
        this.pipeline = pipeline;
        addRequirements(this.drive);
    }


    public void initialize() {
        limelight.setPipeline(pipeline);
    }


    public void execute() {

        double piderror = alignpid.calculate(limelight.getTX(), 0);


    }


    public void end(boolean interrupted) {
        drive.slowMode = 0;
    }

    public boolean isFinished() {
        return false;
    }


}
