package org.firstinspires.ftc.teamcode.drive;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

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

    @Override
    public void runOpMode(){
        expansionHub = hardwareMap.get(ExpansionHubEx.class, "Expansion Hub 173");
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        webcam = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Webcam 1"), cameraMonitorViewId);
        pipeline = new CameraEx.SkystoneDeterminationPipeline();
        webcam.setPipeline(pipeline);
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        webcam.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
            @Override
            public void onOpened() {
                //TODO: change rotation of camera????
                webcam.startStreaming(640, 480, OpenCvCameraRotation.UPRIGHT); //keep it at 480p
            }
        });
        drive.grabber.setPosition(0.4); //open

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
        }
        else{
            noRings();

        }

    }
    public void oneRing() {
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        Pose2d startPose = new Pose2d(-65, 49);
        drive.setPoseEstimate(startPose);
        Trajectory firstOne0 = drive.trajectoryBuilder(startPose)
                .lineTo(new Vector2d(20, 36))
                .build();
        Trajectory secondOne0 = drive.trajectoryBuilder(firstOne0.end())
                .lineTo(new Vector2d(20, 36))
                .build();
        Trajectory thirdOne0 = drive.trajectoryBuilder(secondOne0.end())
                .back(40)
                .build();
        Trajectory fourthOne0 = drive.trajectoryBuilder(thirdOne0.end())
                .back(5)
                .build();

        drive.grabNFlip.setPosition(.9); //pickup wobble
        sleep(750);
        drive.grabber.setPosition(0);
        sleep(10);
        drive.grabNFlip.setPosition(.8);//raise wobble above field
        drive.followTrajectory(firstOne0); //move to wobble drop off
        drive.followTrajectory(secondOne0); //go to launch zone
        drive.grabber.setPosition(0.4); //drop wobble
        drive.grabNFlip.setPosition(.25); //flip arm in
        drive.setMotorPowers(1, 1, -1, -1);//rotate?
        sleep(250);
        drive.setMotorPowers(0, 0,0,0);
        drive.fasterSpinnyThing.setVelocity(400); //start shooting
        drive.followTrajectory(thirdOne0); //go to launch zone
        drive.ring_kicker.setPosition(1);//shoot ring 1
        sleep(200);
        drive.ring_kicker.setPosition(.9);
        sleep(500);
        drive.ring_kicker.setPosition(1); //shoot ring 2
        sleep(200);
        drive.ring_kicker.setPosition(.9);
        sleep(200);
        drive.spinnyThing.setPower(1); //move last ring up to shooter
        drive.ring_sander.setVelocity(-1500);
        drive.followTrajectory(thirdOne0); //go to launch zone
        sleep(750);
        drive.ring_kicker.setPosition(1); //shoot ring 3
        drive.ring_kicker.setPosition(.9);
        drive.spinnyThing.setVelocity(0);
        drive.fasterSpinnyThing.setVelocity(0);
        drive.ring_sander.setVelocity(0);
        drive.setMotorPowers(1, 1, 1, 1);
        sleep(300);
        drive.setMotorPowers(0,0,0,0);
    }


    public void noRings(){
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        Pose2d startPose = new Pose2d(-65, 49);
        drive.setPoseEstimate(startPose);
        Trajectory firstOne0 = drive.trajectoryBuilder(startPose)
                .lineTo(new Vector2d(0, 59))
                .build();
        Trajectory secondOne0 = drive.trajectoryBuilder(firstOne0.end())
                .strafeLeft(1)
                .build();
        Trajectory thirdOne0 = drive.trajectoryBuilder(secondOne0.end())
                .forward(10)
                .build();

        drive.grabNFlip.setPosition(.9); //pickup wobble
        sleep(750);
        drive.grabber.setPosition(0);
        sleep(10);
        drive.grabNFlip.setPosition(.8);//raise wobble above field
        drive.followTrajectory(firstOne0); //move to wobble drop off
        drive.grabber.setPosition(0.4); //drop wobble
        drive.grabNFlip.setPosition(.25); //flip arm in
        drive.setMotorPowers(1, 1, -1, -1);//rotate?
        sleep(250);
        drive.setMotorPowers(0, 0,0,0);
        drive.fasterSpinnyThing.setVelocity(400); //start shooting
        drive.followTrajectory(secondOne0); //go to launch zone
        drive.ring_kicker.setPosition(1);//shoot ring 1
        sleep(200);
        drive.ring_kicker.setPosition(.9);
        sleep(500);
        drive.ring_kicker.setPosition(1); //shoot ring 2
        sleep(200);
        drive.ring_kicker.setPosition(.9);
        sleep(200);
        drive.spinnyThing.setPower(1); //move last ring up to shooter
        drive.ring_sander.setVelocity(-750);
        sleep(750);
        drive.ring_kicker.setPosition(1); //shoot ring 3
        drive.ring_kicker.setPosition(.9);
        drive.spinnyThing.setVelocity(0);
        drive.fasterSpinnyThing.setVelocity(0);
        drive.ring_sander.setVelocity(0);
        drive.followTrajectory(thirdOne0);
    }
    public void fourRings(){
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        Pose2d startPose = new Pose2d(-65, 49);
        drive.setPoseEstimate(startPose);
        Trajectory firstOne4 = drive.trajectoryBuilder(startPose)
                .strafeRight(40)
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
        sleep(750);
        drive.grabber.setPosition(0);
        drive.grabNFlip.setPosition(.75);
        drive.followTrajectory(firstOne4); //strafe to left of ring stack
        drive.followTrajectory(secondOne4); //go forward
        drive.grabber.setPosition(0.4); //drop wobble
        drive.grabNFlip.setPosition(.25); //flip arm in
        drive.fasterSpinnyThing.setVelocity(400); //start shooting
        drive.followTrajectory(thirdOne4);//go diagonally to launch area
        drive.ring_kicker.setPosition(1);//shoot ring 1
        sleep(250);
        drive.ring_kicker.setPosition(.9);
        sleep(250);
        drive.ring_kicker.setPosition(1); //shoot ring 2
        sleep(250);
        drive.ring_kicker.setPosition(.9);
        drive.spinnyThing.setPower(1); //move last ring up to shooter
        drive.ring_sander.setVelocity(-1500);
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
