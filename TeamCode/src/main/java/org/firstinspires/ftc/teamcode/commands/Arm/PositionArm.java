package org.firstinspires.ftc.teamcode.commands.Arm;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.Subsystems.ArmSubsystem;


public class PositionArm extends CommandBase {
    private final ArmSubsystem arm;

    private final double targetInches;

    int lpctr;

    public PositionArm(ArmSubsystem arm, double targetInches) {
        this.arm = arm;
        this.targetInches = targetInches;
        addRequirements(this.arm);
    }

    @Override
    public void initialize() {
        arm.setTargetInches(targetInches);
        lpctr = 0;
    }

    @Override
    public void execute() {
        lpctr++;
        double output = arm.profController.calculate(
                arm.getPositionInches());

        arm.armMotor.set(output + Constants.ArmConstants.POSITION_Kg);

    }


    @Override
    public void end(boolean interrupted) {
        arm.armMotor.set(0);
    }

    @Override
    public boolean isFinished() {
        return lpctr > 5 && arm.profController.atGoal();
    }
}
