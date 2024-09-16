package com.example.meepmeeptesting;


import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;

public final class FieldConstantsRed {


    //field values


    public static final double basketSideStartAngle = Math.toRadians(90);
    public static final double specimenSideStartAngle = Math.toRadians(90);
    public static final double observationSideStartAngle = Math.toRadians(0);


    //specimen values
    public static final double observationZoneCenterPose = Math.toRadians(90);
    static final double distanceBetweenSpikeMarks = 10;


    static final double specimenLength = 3.5;
    static final double specimenSide = 1.5;

    public static final double spikeMarkYCenter = -24 - Constants.FieldConstants.tileTeeth - specimenLength / 2;


    public static Vector2d wallSideYellowSample = new Vector2d(-60, spikeMarkYCenter);
    public static Vector2d centerYellowSample = new Vector2d(-50, spikeMarkYCenter);
    public static Vector2d middleYellowSample = new Vector2d(-40, spikeMarkYCenter);


    public static Vector2d alSideRedSample = new Vector2d(55, spikeMarkYCenter);
    public static Vector2d centerRedSample = new Vector2d(55, spikeMarkYCenter);
    public static Vector2d middleRedSample = new Vector2d(55, spikeMarkYCenter);


    public static Pose2d basketPose = new Pose2d(-55, -55, Math.toRadians(-135));
    public static Pose2d parkPose = new Pose2d(48, -50, Math.toRadians(45));


    //auto start locationa
    public static Pose2d basketSideStartPose = new Pose2d(-30, -55, basketSideStartAngle);
    public static Pose2d observationSideStartPose = new Pose2d(-55, 60, observationSideStartAngle);
    public static Pose2d specimenStartPose = new Pose2d(0, -48, specimenSideStartAngle);
    public static Pose2d specimenDropPose = new Pose2d(0, -30, specimenSideStartAngle);
    public static Pose2d specimenDropBackupPose = new Pose2d(0, -48, specimenSideStartAngle);


//


}






