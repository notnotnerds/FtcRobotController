package org.firstinspires.ftc.teamcode;

import android.view.contentcapture.DataRemovalRequest;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.teamcode.vision.CameraEx;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.revextensions2.ExpansionHubEx;

/*
 ***************************************This code is the property of FTC team 12051 NotNotNerds***************************************
 **********************We do not guarantee that your robot will function correctly after you have used this code**********************
 **************************************************Please use some other team's code**************************************************
 */
@Autonomous(name = "Red side right", group = "Autonomous stuff")
public class AutoRR extends LinearOpMode {
    /* where is the starting position (depending on which line it's starting at) (repeat until certain amount of time idk)
         sensor how many rings
             put wobble goal in square A if 0 rings & go back to launch zone
             put wobble goal in square B if 1 ring & go back to launch zone
             put wobble goal in square C if 4 rings & go back to launch zone
             locate rings, pick up rings, and get in position to shoot
             shoot at tower 1st, 2nd, 3rd hole
             shoot at power shot 1st, 2nd, 3rd stick thing
     (when time is certain amount) sensor line and park over it
*/
    OpenCvCamera webcam;
    CameraEx.SkystoneDeterminationPipeline pipeline;
    DirectionalMovement DirectionalMovement;
    public DcMotorEx fl;
    public DcMotorEx fr;
    public DcMotorEx bl;
    public DcMotorEx br;
    public DcMotor spinnyThing; //aka 1150 rpm motor, the one that makes a ton of noise by using zip ties.
    public DcMotorEx fasterSpinnyThing; //aka 6k rpm motor
    public DcMotor ring_sander; //it sands down the rings
    //public DcMotor wobble_lift; //it raises the wobble goal over the field edge ----we don't have this
    public Servo ring_kicker; //it kicks the rings into the fasterSpinnyThing
    ExpansionHubEx expansionHub;
    public Servo grabber; //who said we needed to give them normal names?
    public Servo grabNFlip; //seriously, you thought I would name this better?
    int f = 0;
    int r = 0;
    int sl = 0;
    int sr = 0;
    @Override
    public void runOpMode() {
        telemetry.addLine("Robot has been turned on. Run for your life!");
        telemetry.update();

        expansionHub = hardwareMap.get(ExpansionHubEx.class, "Expansion Hub 173");
        fl = hardwareMap.get(DcMotorEx.class, "fl");
        fr = hardwareMap.get(DcMotorEx.class, "fr");
        bl = hardwareMap.get(DcMotorEx.class, "bl");
        br = hardwareMap.get(DcMotorEx.class, "br");
        spinnyThing = hardwareMap.dcMotor.get("intake");
        //wobble_lift=hardwareMap.dcMotor.get("lift");
        fasterSpinnyThing = hardwareMap.get(DcMotorEx.class, "shooter");
        fasterSpinnyThing.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        fl.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        fr.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        bl.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        br.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        grabber = hardwareMap.servo.get("grabber");
        grabNFlip = hardwareMap.servo.get("flipper");
        ring_sander = hardwareMap.dcMotor.get("ring_lift");
        ring_kicker = hardwareMap.servo.get("ring_push");
        fl.setDirection(DcMotorEx.Direction.REVERSE);
        bl.setDirection(DcMotorEx.Direction.REVERSE);
        spinnyThing.setDirection(DcMotorEx.Direction.REVERSE);
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        webcam = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Webcam 1"), cameraMonitorViewId);
        pipeline = new CameraEx.SkystoneDeterminationPipeline();
        webcam.setPipeline(pipeline);
        webcam.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
            @Override
            public void onOpened() {
                webcam.startStreaming(640, 480, OpenCvCameraRotation.UPSIDE_DOWN); //keep it at 480p
            }
        });
        ring_kicker.setPosition(.6);//set launch servo to ready position
        grabNFlip.setPosition(.95);//set wobble servo to usage position
        grabber.setPosition(.5);//set wobble grabber to hold wobble goal
        telemetry.addLine("Robot can now start the game");
        waitForStart();


       //grabNFlip.setPosition(.05);//set wobble servo to usage position
        //grabber.setPosition(0);//set wobble grabber to hold wobble goal
        f = 1500;
        forward();
        sleep(1000);
        fasterSpinnyThing.setVelocity(-3000);
        sleep(500);
        ring_kicker.setPosition(.8);
        sleep(500);
        ring_kicker.setPosition(.6);
        sleep(100);
        ring_sander.setPower(-1);
        spinnyThing.setPower(1);
        ring_kicker.setPosition(.8);
        sleep(500);
        ring_kicker.setPosition(.6);
        ring_sander.setPower(0);
        spinnyThing.setPower(0);
        fasterSpinnyThing.setVelocity(0);

//while loop only needed to actively count rings... Not needed for running auto
      /*  while (opModeIsActive()) {
            if (CameraEx.ringCount == 4) {//4 rings
                RedZoneA();
            } else if (CameraEx.ringCount == 1) {//1 ring
                RedZoneB();
            } else {//0 rings
                RedZoneC();
            }
            telemetry.addData("Ring Count", CameraEx.ringCount);
            telemetry.update();

        }
        if (CameraEx.ringCount == 4) {//4 rings
            telemetry.addLine("4 rings");
            RedZoneA();
        } else if (CameraEx.ringCount == 1) {//1 ring
            RedZoneB();
        } else {//0 rings
            RedZoneC();
        }
    }

    public void RedZoneA() {
        //make it to the target zone A
        f = 1000;
        forward();
        //shoot whatever is loaded into robot
        fasterSpinnyThing.setVelocity(3000);
        sleep(200);
        ring_kicker.setPosition(.8);
        sleep(500);
        ring_kicker.setPosition(.6);
        ring_kicker.setPosition(.8);
        sleep(500);
        ring_kicker.setPosition(.6);
        fasterSpinnyThing.setVelocity(0);
        ring_sander.setPower(-1);
        spinnyThing.setPower(1);
        sleep(500);
        fasterSpinnyThing.setVelocity(3000);
        ring_kicker.setPosition(.8);
        sleep(500);
        fasterSpinnyThing.setVelocity(0);
        ring_kicker.setPosition(.6);
        sl=2000;
        strafeLeft();
        f=1000;
        forward();
        //release wobble
        r=1000;
        backward();
    }

    public void RedZoneB() {
        //make it to the target zone B
        f = 1000;
        forward();
        //shoot whatever is preloaded into robot
        fasterSpinnyThing.setVelocity(3000);
        sleep(200);
        ring_kicker.setPosition(.8);
        sleep(500);
        ring_kicker.setPosition(.6);
        ring_kicker.setPosition(.8);
        sleep(500);
        ring_kicker.setPosition(.6);
        fasterSpinnyThing.setVelocity(0);/*
        ring_sander.setPower(-1);
        spinnyThing.setPower(1);
        sleep(500);
        fasterSpinnyThing.setVelocity(3000);
        ring_kicker.setPosition(.8);
        sleep(500);
        fasterSpinnyThing.setVelocity(0);
        ring_kicker.setPosition(.6);
        //collect the one ring
        f=1000;
        forward();

        //drop wobble goal
    }

    public void RedZoneC() {
        //make it to the target zone C
        f = 1000;
        forward();
        fasterSpinnyThing.setVelocity(3000);
        sleep(100);
        ring_kicker.setPosition(.8);
        sleep(500);
        ring_kicker.setPosition(.6);
        ring_kicker.setPosition(.8);
        sleep(500);
        ring_kicker.setPosition(.6);
        fasterSpinnyThing.setVelocity(0);
        ring_sander.setPower(-1);
        spinnyThing.setPower(1);
        sleep(500);
        fasterSpinnyThing.setVelocity(3000);
        ring_kicker.setPosition(.8);
        sleep(500);
        fasterSpinnyThing.setVelocity(0);
        ring_kicker.setPosition(.6);
        //shoot all preloaded rings
        sl=500;
        strafeLeft();
        f=1000;
        forward();
        //drop wobble goal
        r=1000;
        backward();
    }
    */

    }
    public void forward() {
        fl.setTargetPosition(f);
        fr.setTargetPosition(f);
        br.setTargetPosition(f);
        bl.setTargetPosition(f);
        fl.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        fr.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        bl.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        br.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        fl.setVelocity(1000);
        fr.setVelocity(1000);
        bl.setVelocity(1000);
        br.setVelocity(1000);
        fl.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        fr.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        bl.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        br.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        while(Math.abs(fl.getCurrentPosition())< Math.abs(f) && (Math.abs(fr.getCurrentPosition())< Math.abs(f)) && Math.abs(bl.getCurrentPosition())< Math.abs(f) && (Math.abs(br.getCurrentPosition())< Math.abs(f))) {
            //wait to get to new location
        }
    }

    public void backward() {
        fl.setTargetPosition(r);
        fr.setTargetPosition(r);
        bl.setTargetPosition(r);
        br.setTargetPosition(r);
        fl.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        fr.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        bl.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        br.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        fl.setVelocity(-1000);
        fr.setVelocity(-1000);
        bl.setVelocity(-1000);
        br.setVelocity(-1000);
        fl.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        fr.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        bl.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        br.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        while(Math.abs(fl.getCurrentPosition())< Math.abs(r) && (Math.abs(fr.getCurrentPosition())< Math.abs(r)) && Math.abs(bl.getCurrentPosition())< Math.abs(r) && (Math.abs(br.getCurrentPosition())< Math.abs(r))) {
            //wait to get to new location
        }
    }

    public void strafeLeft() {
        fl.setTargetPosition(sl);
        fr.setTargetPosition(sl);
        bl.setTargetPosition(sl);
        br.setTargetPosition(sl);
        fl.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        fr.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        bl.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        br.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        fl.setVelocity(-1000);
        fr.setVelocity(1000);
        bl.setVelocity(1000);
        br.setVelocity(-1000);
        fl.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        fr.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        bl.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        br.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        while(Math.abs(fl.getCurrentPosition())< Math.abs(sl) && (Math.abs(fr.getCurrentPosition())< Math.abs(sl)) && Math.abs(bl.getCurrentPosition())< Math.abs(sl) && (Math.abs(br.getCurrentPosition())< Math.abs(sl))) {
            //wait to get to new location
        }
    }

    public void strafeRight() {
        fl.setTargetPosition(sr);
        fr.setTargetPosition(sr);
        bl.setTargetPosition(sr);
        br.setTargetPosition(sr);
        fl.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        fr.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        bl.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        br.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        fl.setVelocity(1000);
        fr.setVelocity(-1000);
        bl.setVelocity(-1000);
        br.setVelocity(1000);
        fl.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        fr.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        bl.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        br.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        while(Math.abs(fl.getCurrentPosition())< Math.abs(sr) && (Math.abs(fr.getCurrentPosition())< Math.abs(sr)) && Math.abs(bl.getCurrentPosition())< Math.abs(sr) && (Math.abs(br.getCurrentPosition())< Math.abs(sr))) {
            //wait to get to new location
        }
    }


}
