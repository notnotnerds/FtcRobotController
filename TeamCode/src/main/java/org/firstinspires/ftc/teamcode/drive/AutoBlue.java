package org.firstinspires.ftc.teamcode.drive;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.vision.CameraEx;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.revextensions2.ExpansionHubEx;

/**
 ***************************************This code is the property of FTC team 12051 NotNotNerds***************************************
 **********************We do not guarantee that your robot will function correctly after you have used this code**********************
 **************************************************Please use some other team's code**************************************************
 */

@Autonomous
public class AutoBlue extends LinearOpMode {
    ExpansionHubEx expansionHub;
    OpenCvCamera webcam;
    CameraEx.SkystoneDeterminationPipeline pipeline;
    int rings=0;
    SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);


    @Override
    public void runOpMode(){
        expansionHub = hardwareMap.get(ExpansionHubEx.class, "Expansion Hub 173");
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        webcam = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Webcam 1"), cameraMonitorViewId);
        pipeline = new CameraEx.SkystoneDeterminationPipeline();
        webcam.setPipeline(pipeline);

        webcam.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
            @Override
            public void onOpened() {
                //TODO: change rotation of camera????
                webcam.startStreaming(640, 480, OpenCvCameraRotation.UPSIDE_DOWN); //keep it at 480p
            }
        });

        while (!opModeIsActive()) {
            telemetry.addData("Ring Count", CameraEx.ringCount);
            telemetry.update();
            rings=CameraEx.ringCount;
        }

        waitForStart();
        webcam.stopStreaming();

        if(rings == 4) {
            fourRings();
        }
        else if(rings == 1){
            oneRing();
            /*drive.grabNFlip.setPosition(1); //pickup wobble
            drive.grabber.setPosition(.4);
            drive.followTrajectory(ringNumMoves.oneRing(Trajectory firstOne); //strafe left

            drive.followTrajectory(secondOne1); //go forward-ish
            drive.followTrajectory(thirdOne1);//strafe right
            drive.grabber.setPosition(0); //drop wobble
            drive.grabNFlip.setPosition(.25);
            drive.followTrajectory(fourthOne1); //go into launch zone
            drive.fasterSpinnyThing.setVelocity(1000); //start shooting
            sleep(1000);
            drive.ring_kicker.setPosition(1);//shoot ring 1
            sleep(250);
            drive.ring_kicker.setPosition(.9);
            sleep(250);
            drive.ring_kicker.setPosition(1); //shoot ring 2
            sleep(250);
            drive.ring_kicker.setPosition(.9);
            drive.spinnyThing.setVelocity(250);
            drive.ring_sander.setVelocity(-250);
            sleep(250);
            drive.ring_kicker.setPosition(1);//shoot ring 3
            sleep(250);
            drive.ring_kicker.setPosition(.9);
            drive.spinnyThing.setVelocity(750);
            drive.ring_sander.setVelocity(-750);
            drive.followTrajectory(fifthOne1); //pickup ring 4
            sleep(250);
            drive.ring_kicker.setPosition(1);//shoot ring 4
            sleep(250);
            drive.ring_kicker.setPosition(.9);
            drive.followTrajectory(lastOne1); //go to park on line*/
        }
        else{
            noRings();

        }

    }
    public void oneRing() {
        Pose2d startPose = new Pose2d(-65, 35);
        drive.setPoseEstimate(startPose);
        Trajectory firstOne1 = drive.trajectoryBuilder(startPose)
                .strafeLeft(24)
                .build();
        Trajectory secondOne1 = drive.trajectoryBuilder(firstOne1.end())
                .forward(30)
                .build();
        Trajectory thirdOne1 = drive.trajectoryBuilder(secondOne1.end())
                .splineTo(new Vector2d(25, 37) ,0)
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
        /********** start motion here ********* */
        drive.grabNFlip.setPosition(1); //pickup wobble
        drive.grabber.setPosition(.4);
        drive.grabNFlip.setPosition(.9);
        drive.followTrajectory(firstOne1); //strafe to left of ring stack
        drive.followTrajectory(secondOne1); //go forward
        drive.followTrajectory(thirdOne1);//go diagonally to wobble drop-off point
        drive.grabber.setPosition(0); //drop wobble
        drive.grabNFlip.setPosition(.25); //flip arm in
        drive.fasterSpinnyThing.setVelocity(1000); //start shooting
        drive.followTrajectory(fourthOne1); //go into launch zone
        drive.ring_kicker.setPosition(1);//shoot ring 1
        sleep(250);
        drive.ring_kicker.setPosition(.9);
        sleep(250);
        drive.ring_kicker.setPosition(1); //shoot ring 2
        sleep(250);
        drive.ring_kicker.setPosition(.9);
        drive.spinnyThing.setVelocity(250); //move last ring up to shooter
        drive.ring_sander.setVelocity(-250);
        sleep(250);
        drive.ring_kicker.setPosition(1); //shoot ring 3
        sleep(250);
        drive.ring_kicker.setPosition(.9);
        drive.spinnyThing.setVelocity(750); //speed up intake
        drive.ring_sander.setVelocity(-750);
        drive.followTrajectory(fifthOne1); //pickup ring 4
        sleep(250);
        drive.ring_kicker.setPosition(1);//shoot ring 4
        sleep(250);
        drive.ring_kicker.setPosition(.9);
        drive.followTrajectory(lastOne1); //go to park on line
        drive.spinnyThing.setVelocity(0);
        drive.fasterSpinnyThing.setVelocity(0);
        drive.ring_sander.setVelocity(0);

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
        drive.grabNFlip.setPosition(1); //pickup wobble
        drive.grabber.setPosition(.4);
        drive.grabNFlip.setPosition(.9);
        drive.followTrajectory(firstOne0); //move to wobble drop off
        drive.grabber.setPosition(0); //drop wobble
        drive.grabNFlip.setPosition(.25); //flip arm in
        drive.fasterSpinnyThing.setVelocity(1000); //start shooting
        drive.followTrajectory(secondOne0); //go to launch zone
        drive.ring_kicker.setPosition(1);//shoot ring 1
        sleep(250);
        drive.ring_kicker.setPosition(.9);
        sleep(250);
        drive.ring_kicker.setPosition(1); //shoot ring 2
        sleep(250);
        drive.ring_kicker.setPosition(.9);
        drive.spinnyThing.setVelocity(250); //move last ring up to shooter
        drive.ring_sander.setVelocity(-250);
        sleep(250);
        drive.ring_kicker.setPosition(1); //shoot ring 3
        sleep(250);
        drive.ring_kicker.setPosition(.9);
        drive.followTrajectory(thirdOne0);
        drive.spinnyThing.setVelocity(0);
        drive.fasterSpinnyThing.setVelocity(0);
        drive.ring_sander.setVelocity(0);
    }
    public void fourRings(){
        Pose2d startPose = new Pose2d(-65, 35);
        drive.setPoseEstimate(startPose);
        Trajectory firstOne4 = drive.trajectoryBuilder(startPose)
                .strafeLeft(24)
                .build();
        Trajectory secondOne4 = drive.trajectoryBuilder(firstOne4.end())
                .forward(115)
                .build();
        Trajectory thirdOne4=drive.trajectoryBuilder(secondOne4.end())
                .lineTo(new Vector2d(0, 37))
                .build();
        Trajectory fourthOne4 = drive.trajectoryBuilder(thirdOne4.end())
                .back(15)
                .build();
        Trajectory fifthOne4 = drive.trajectoryBuilder(fourthOne4.end())
                .back(5)
                .build();
        Trajectory sixthOne4=drive.trajectoryBuilder(fifthOne4.end())
                .forward(15)
                .build();
        /********** start motion here ********* */
        drive.grabNFlip.setPosition(1); //pickup wobble
        drive.grabber.setPosition(.4);
        drive.grabNFlip.setPosition(.9);
        drive.followTrajectory(firstOne4); //strafe to left of ring stack
        drive.followTrajectory(secondOne4); //go forward
        drive.grabber.setPosition(0); //drop wobble
        drive.grabNFlip.setPosition(.25); //flip arm in
        drive.fasterSpinnyThing.setVelocity(1000); //start shooting
        drive.followTrajectory(thirdOne4);//go diagonally to launch area
        drive.ring_kicker.setPosition(1);//shoot ring 1
        sleep(250);
        drive.ring_kicker.setPosition(.9);
        sleep(250);
        drive.ring_kicker.setPosition(1); //shoot ring 2
        sleep(250);
        drive.ring_kicker.setPosition(.9);
        drive.spinnyThing.setVelocity(250); //move last ring up to shooter
        drive.ring_sander.setVelocity(-250);
        sleep(250);
        drive.ring_kicker.setPosition(1); //shoot ring 3
        sleep(250);
        drive.ring_kicker.setPosition(.9);
        drive.spinnyThing.setVelocity(750); //speed up intake
        drive.ring_sander.setVelocity(-750);
        drive.followTrajectory(fourthOne4); //pickup rings 4-7
        sleep(250);
        drive.ring_kicker.setPosition(1);//shoot ring 4
        sleep(250);
        drive.ring_kicker.setPosition(.9);
        sleep(250);
        drive.ring_kicker.setPosition(1);//shoot ring 5
        sleep(250);
        drive.ring_kicker.setPosition(.9);
        sleep(250);
        drive.followTrajectory(fourthOne4); //pickup rings 4-7
        drive.ring_kicker.setPosition(1);//shoot ring 6
        sleep(250);
        drive.ring_kicker.setPosition(.9);
        sleep(250);
        drive.ring_kicker.setPosition(1);//shoot ring 7
        sleep(250);
        drive.ring_kicker.setPosition(.9);
        drive.followTrajectory(sixthOne4); //go to park on line
        drive.spinnyThing.setVelocity(0);
        drive.fasterSpinnyThing.setVelocity(0);
        drive.ring_sander.setVelocity(0);
    }


}
