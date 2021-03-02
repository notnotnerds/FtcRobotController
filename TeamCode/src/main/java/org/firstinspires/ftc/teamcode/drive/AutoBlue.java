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

@Autonomous
public class AutoBlue extends LinearOpMode {
    ExpansionHubEx expansionHub;
    OpenCvCamera webcam;
    CameraEx.SkystoneDeterminationPipeline pipeline;
    int rings=0;

    @Override
    public void runOpMode(){
        expansionHub = hardwareMap.get(ExpansionHubEx.class, "Expansion Hub 173");
        /*int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        webcam = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Webcam 1"), cameraMonitorViewId);
        pipeline = new CameraEx.SkystoneDeterminationPipeline();
        webcam.setPipeline(pipeline);

        webcam.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
            @Override
            public void onOpened() {
                webcam.startStreaming(640, 480, OpenCvCameraRotation.UPSIDE_DOWN); //keep it at 480p
            }
        });*/

        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        Pose2d startPose = new Pose2d(-65, 49, Math.toRadians(90));
        drive.setPoseEstimate(startPose);
        Trajectory myTrajectory1 = drive.trajectoryBuilder(startPose)
            .strafeLeft(10)
            .build();
        Trajectory secondOne1 = drive.trajectoryBuilder(myTrajectory1.end())
                .forward(90)
                .build();
        Trajectory thirdOne1=drive.trajectoryBuilder(secondOne1.end())
                .strafeRight(22)
                .build();
        Trajectory fourthOne1=drive.trajectoryBuilder(thirdOne1.end())
                .back(35)
                .build();
        Trajectory fifthOne1=drive.trajectoryBuilder(fourthOne1.end())
                .back(5)
                .build();
        Trajectory lastOne1=drive.trajectoryBuilder(fifthOne1.end())
                .forward(25)
                .build();

        while (!opModeIsActive()) {//just flash LED's for now

            telemetry.addData("Ring Count", CameraEx.ringCount);
            telemetry.update();
        rings=CameraEx.ringCount;
        }
        waitForStart();
        webcam.stopStreaming();
        if(rings == 4) {
            //get to zone C
        }
        else if(rings == 1){
            drive.grabNFlip.setPosition(1); //pickup wobble
            drive.grabber.setPosition(.4);
            drive.followTrajectory(myTrajectory1); //strafe left
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
            drive.spinnyThing.setVelocity(500);
            drive.ring_sander.setVelocity(-500);
            drive.followTrajectory(fifthOne1); //pickup ring 4
            sleep(250);
            drive.ring_kicker.setPosition(1);//shoot ring 4
            sleep(250);
            drive.ring_kicker.setPosition(.9);
            drive.followTrajectory(lastOne1); //go to park on line
        }
        else{
            //get to zone A
        }

    }

}
