package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.controller.wpilibcontroller.ProfiledPIDController;
import com.arcrobotics.ftclib.hardware.motors.CRServo;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.trajectory.TrapezoidProfile;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.commands.Arm.PositionHoldArm;

public class ArmSubsystem extends SubsystemBase {


    private final CommandOpMode myOpMode;
    private final FtcDashboard dashboard;
    public Motor armMotor;
    public Motor.Encoder armEncoder;
    public CRServo intakeRoller;
    public double targetInches;
    public int armPositionIndex;
    public double power;
    public ProfiledPIDController profController = null;
    public int holdCtr;
    public boolean show;
    ElapsedTime et;
    private int armDeliverLevel;
    private Telemetry telemetry;
    private double scanTime;

    public ArmSubsystem(CommandOpMode opMode) {

        myOpMode = opMode;

        armMotor = new Motor(myOpMode.hardwareMap, "arm motor", Motor.GoBILDA.RPM_435);

        armMotor.setInverted(true);

        armMotor.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);

        intakeRoller = opMode.hardwareMap.get(CRServo.class, "intakeRoller");

        intakeRoller.setInverted((false));

        armEncoder = armMotor.encoder;

        armEncoder.setDirection(Motor.Direction.FORWARD);

        armEncoder.setDistancePerPulse(1 / Constants.ArmConstants.ENCODER_COUNTS_PER_INCH);

        profController = new ProfiledPIDController(
                Constants.ArmConstants.kP, Constants.ArmConstants.kI, Constants.ArmConstants.kD,
                new TrapezoidProfile.Constraints(Constants.ArmConstants.MAX_VEL, Constants.ArmConstants.MAX_ACCEL));

        profController.setTolerance(Constants.ArmConstants.POSITION_TOLERANCE_INCHES);

        profController.reset(0);

        resetEncoder();


        setDefaultCommand(new PositionHoldArm(this));

        setTargetInches(.5);

        dashboard = FtcDashboard.getInstance();

        opMode.telemetry = new MultipleTelemetry(opMode.telemetry, dashboard.getTelemetry());


        et = new ElapsedTime();
    }


    @Override

    public void periodic() {

        if (show) {
            showTelemetry();
        }
        if (holdCtr >= 100) {
            scanTime = et.milliseconds() / holdCtr;
            holdCtr = 0;
            et.reset();
        }
    }

    public void setTargetInches(double inches) {
        targetInches = inches;
        profController.setGoal(targetInches);
    }

    public void setArmDeliverLevel(int n) {
        if (n > Constants.ArmConstants.armPositionInches.length - 1)
            n = Constants.ArmConstants.armPositionInches.length - 1;
        if (n < 0)
            n = 0;
        // if (n != armDeliverLevel) {
        armDeliverLevel = n;
        //   profController.setGoal(Constants.ArmConstants.armPositionInches[armDeliverLevel]);
        setTargetInches(Constants.ArmConstants.armPositionInches[armDeliverLevel]);
    }

    public void incArmDeliveryLevel() {
        int n = armDeliverLevel;
        setArmDeliverLevel(n + 1);
    }

    public void decArmDeliveryLeve() {
        int n = armDeliverLevel;
        setArmDeliverLevel(n - 1);
    }

    public void resetEncoder() {
        armEncoder.reset();
    }

    public double getPositionInches() {
        return armEncoder.getDistance();
    }

    public boolean inPosition() {
        return profController.atSetpoint();
    }

    public double getGoalPosition() {
        return profController.getGoal().position;
    }

    public double getPositionKp() {
        return profController.getP();
    }

    public void setPositionKp(double kp) {
        profController.setP(kp);
    }

    public double getPositionKi() {
        return profController.getD();
    }

    public void setPositionKi(double ki) {
        profController.setI(ki);
    }

    public double getPositionKd() {
        return profController.getD();
    }

    public void setPositionKd(double kd) {
        profController.setD(kd);
    }

    public void setTrapConstraints(double vel, double acc) {
        profController.setConstraints(new TrapezoidProfile.Constraints(vel, acc));
    }

    public double getPower() {
        return armMotor.get();
    }

    public void setPower(double power) {
        armMotor.set(power);
    }

    public void showTelemetry() {
        telemetry.addData("Arm", show);
//        telemetry.addData("EncCtsPerInch", Constants.ArmConstants.ENCODER_COUNTS_PER_INCH);
        telemetry.addData("MaxIPS", Constants.ArmConstants.MAX_INCHES_PER_SECOND);
        telemetry.addData("HoldRng", holdCtr);
        telemetry.addData("Scantime", scanTime);

        telemetry.addData("ArmInches", getPositionInches());
        telemetry.addData("GoalInches", getGoalPosition());
        telemetry.addData("TargetInches", targetInches);
        telemetry.addData("ArmPower", armMotor.get());


        telemetry.update();

    }
}
