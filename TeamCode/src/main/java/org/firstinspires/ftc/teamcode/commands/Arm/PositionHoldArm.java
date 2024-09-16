package org.firstinspires.ftc.teamcode.commands.Arm;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.subsystems.ArmSubsystem;


public class PositionHoldArm extends CommandBase {
    private final ArmSubsystem arm;

    private double power;


    public PositionHoldArm(ArmSubsystem arm) {
        this.arm = arm;
        addRequirements(this.arm);
    }

    @Override
    public void initialize() {
        double temp = arm.getPositionInches();
        // if (temp < .25) temp = .25;
        // arm.profController.setGoal(temp);
        arm.setTargetInches(temp);
    }

    @Override
    public void execute() {
        arm.holdCtr++;
        double output = arm.profController.calculate(
                arm.getPositionInches());

        arm.armMotor.set(output + Constants.ArmConstants.POSITION_Kg);

    }


    @Override
    public void end(boolean interrupted) {
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
