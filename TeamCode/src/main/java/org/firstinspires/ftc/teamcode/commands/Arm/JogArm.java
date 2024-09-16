package org.firstinspires.ftc.teamcode.commands.Arm;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.gamepad.GamepadEx;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.subsystems.ArmSubsystem;


public class JogArm extends CommandBase {

    private ArmSubsystem arm;

    private final GamepadEx gamepad;


    public JogArm(ArmSubsystem arm, GamepadEx gamepad) {
        this.arm = arm;
        this.gamepad = gamepad;
        addRequirements(this.arm);
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {

        arm.power = gamepad.getRightY() / 2;

        if (arm.power > 0 && arm.getPositionInches() < Constants.ArmConstants.UPPER_POSITION_LIMIT

                || arm.power < 0 && arm.getPositionInches() > Constants.ArmConstants.LOWER_POSITION_LIMIT) {

        } else
            arm.power = 0;

        arm.armMotor.set(arm.power);

        arm.setTargetInches(arm.getPositionInches());
    }

    @Override
    public void end(boolean interrupted) {
        arm.armMotor.set(0);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
