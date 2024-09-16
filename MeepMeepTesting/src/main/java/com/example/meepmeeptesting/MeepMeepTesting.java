package com.example.meepmeeptesting;


import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTesting {


    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .setDimensions(Constants.RobotConstants.width, Constants.RobotConstants.length)

                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(FieldConstantsRed.specimenStartPose)
                                .lineToSplineHeading(FieldConstantsRed.specimenDropPose)
                                .waitSeconds((2))
                                .lineToSplineHeading(FieldConstantsRed.specimenDropBackupPose)
                                .lineToSplineHeading(new Pose2d(-30, -30, Math.toRadians(175)))
                                .lineToSplineHeading(new Pose2d(-40, -25, Math.toRadians(180)))
                                .lineToSplineHeading((FieldConstantsRed.basketPose))
                                .waitSeconds((2))
                                .lineToSplineHeading(new Pose2d(-36, -12, Math.toRadians(-150)))
                                .lineToSplineHeading(new Pose2d(-54, -20, Math.toRadians(-150)))
                                .lineToSplineHeading((FieldConstantsRed.basketPose))
                                .waitSeconds((2))
                                .lineToSplineHeading(new Pose2d(-46, -12, Math.toRadians(-150)))
                                .lineToSplineHeading(new Pose2d(-58, -20, Math.toRadians(-150)))
                                .lineToSplineHeading((FieldConstantsRed.basketPose))
                                .waitSeconds((2))
                                .build()
                );

        ShowField.showIt(meepMeep, myBot);
        myBot.getDrive().setPoseEstimate(FieldConstantsRed.specimenStartPose);

    }
}
