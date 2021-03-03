package org.firstinspires.ftc.teamcode.drive;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;


import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

public class RingNumMoves {
    SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

    public void oneRing() {
        Pose2d startPose = new Pose2d(-65, 35);
        drive.setPoseEstimate(startPose);
        Trajectory firstOne1 = drive.trajectoryBuilder(startPose)
                .strafeLeft(5)
                .build();
        Trajectory secondOne1 = drive.trajectoryBuilder(firstOne1.end())
                .forward(90)
                .build();
        Trajectory thirdOne1 = drive.trajectoryBuilder(secondOne1.end())
                .strafeRight(22)
                .build();
        Trajectory fourthOne1 = drive.trajectoryBuilder(thirdOne1.end())
                .back(35)
                .build();
        Trajectory fifthOne1 = drive.trajectoryBuilder(fourthOne1.end())
                .back(5)
                .build();
        Trajectory lastOne1 = drive.trajectoryBuilder(fifthOne1.end())
                .forward(25)
                .build();
        /* start motion here*/
        drive.grabNFlip.setPosition(1); //pickup wobble
        drive.grabber.setPosition(.4);
        drive.followTrajectory(secondOne1); //go forward-ish
        drive.followTrajectory(thirdOne1);//strafe right
        drive.grabber.setPosition(0); //drop wobble
        drive.grabNFlip.setPosition(.25);
        drive.followTrajectory(fourthOne1); //go into launch zone
        drive.fasterSpinnyThing.setVelocity(1000); //start shooting

    }


    public void noRings(){
        Pose2d startPose = new Pose2d(-65, 35);
        drive.setPoseEstimate(startPose);
        Trajectory firstOne0 = drive.trajectoryBuilder(startPose)
                .splineTo(new Vector2d(0, 59), 0)
                .build();
        Trajectory secondOne0 = drive.trajectoryBuilder(firstOne0.end())
                .strafeRight(12)
                .build();
        Trajectory thirdOne0 = drive.trajectoryBuilder(secondOne0.end())
                .forward(10)
                .build();
         drive.followTrajectory(firstOne0);
    }
    public void fourRings(){
        Pose2d startPose = new Pose2d(-65, 49);
        drive.setPoseEstimate(startPose);
    }

}
