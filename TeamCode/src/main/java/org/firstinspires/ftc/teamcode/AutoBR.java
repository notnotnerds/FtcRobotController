package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.teamcode.vision.CameraEx;
import org.opencv.core.Mat;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.revextensions2.ExpansionHubEx;

/*
 *************************This code is the property of FTC team 12051 NotNotNerds******************
 ***********************We do not guarantee that your robot will function correctly after you have used this code*************
 ***********************Please use some other team's code****************
 */
@Autonomous(name = "Blue side right", group = "Autonomous stuff")
public class AutoBR extends LinearOpMode {
    /* where is the starting position (depending on which line it's starting at) (repeat until certain amount of time idk)
         sensor how many rings
             put wobble goal in square A if 0 rings & go back to launch zone
             put wobble goal in square B if 1 ring & go back to launch zone
             put wobble goal in square C if 4 rings & go back to launch zone
         sensor rings, pick up rings, and get in position to shoot
             shoot at tower 1st, 2nd, 3rd hole
             shoot at power shot 1st, 2nd, 3rd stick thing
     (when time is certain amount) sensor line and park over it
*/
    OpenCvCamera webcam;
    CameraEx.SkystoneDeterminationPipeline pipeline;
    public DcMotor fl;
    public DcMotor fr;
    public DcMotor bl;
    public DcMotor br;
    public DcMotor spinnyThing; //aka slow motor
    public DcMotor fasterSpinnyThing; //aka 6k rpm motor
    //public DcMotor waitWeNeedAnotherMotor; //oh come on, don't force me to give them names
    ExpansionHubEx expansionHub;
    public Servo grabber; //who said we needed to give them normal names?
    public Servo grabNFlip; //seriously, you thought I would name this better?
    //public Servo IDK; //since when do I have to give them all proper names?
    //public Servo angler; //It just angles the launcher mechanism
    DirectionalMovement DirectionalMovement = new DirectionalMovement();
    int f = 0;
    int r = 0;

    @Override
    public void runOpMode() {
        telemetry.addLine("Robot has been turned on. Run for your life!");
        telemetry.update();

        expansionHub = hardwareMap.get(ExpansionHubEx.class, "Expansion Hub 173");
        fl = hardwareMap.dcMotor.get("fl");
        fl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        fr = hardwareMap.dcMotor.get("fr");
        fr.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        bl = hardwareMap.dcMotor.get("bl");
        //bl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        br = hardwareMap.dcMotor.get("br");
        //br.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        //spinnyThing =hardwareMap.dcMotor.get("intake");
        //fasterSpinnyThing=hardwareMap.dcMotor.get("shooter");
        //grabber=hardwareMap.servo.get("grabber";
        //grabNFlip=hardwareMap.servo.get("flipper");
        //angler=hardwareMap.servo.get("angler");
        fl.setDirection(DcMotor.Direction.REVERSE);
        bl.setDirection(DcMotor.Direction.REVERSE);
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

        waitForStart();
        f = 1000;
       /* while (opModeIsActive()) {//just flash LED's for now
            if(CameraEx.ringCount==4) {
                BlueZoneA();
            }
            else if(CameraEx.ringCount==1){
                f=1000;
                fl.setTargetPosition(f);
                fr.setTargetPosition(f);
                bl.setTargetPosition(f);
                br.setTargetPosition(f);
                fl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                fr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                bl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                br.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                fl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                fr.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                bl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                br.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                fl.setPower(-.5);
                fr.setPower(-.5);
                bl.setPower(-.5);
                br.setPower(-.5);
                BlueZoneB();
            }
            else{
                BlueZoneC();
            }
            telemetry.addData("Ring Count", CameraEx.ringCount);
            telemetry.update();

        }*/
    }


    public void BlueZoneA() {
        //make it to the target zone A
        expansionHub.setLedColor(255, 0, 0);
        sleep(250);
        expansionHub.setLedColor(0, 0, 0);
        sleep(250);
        DirectionalMovement.f = 1000;
        DirectionalMovement.forward();
    }

    public void BlueZoneB() {
        //make it to the target zone B
        expansionHub.setLedColor(0, 255, 0);
        sleep(250);
        expansionHub.setLedColor(0, 0, 0);
        sleep(250);
        DirectionalMovement.f = 1000;
        DirectionalMovement.forward();
        while (opModeIsActive() &&
                Math.abs(fl.getCurrentPosition()) <= f &&
                Math.abs(fr.getCurrentPosition()) <= f) {
            telemetry.addData("fl", fl.getCurrentPosition());
            telemetry.addData("fr", fr.getCurrentPosition());
            //telemetry.addData("bl", bl.getCurrentPosition());
            //telemetry.addData("br", br.getCurrentPosition());
            telemetry.update();
        }
        fl.setPower(0);
        fr.setPower(0);
        bl.setPower(0);
        br.setPower(0);
    }

    public void BlueZoneC() {
        //make it to the target zone C
        expansionHub.setLedColor(0, 0, 255);
        sleep(250);
        expansionHub.setLedColor(0, 0, 0);
        sleep(250);
        DirectionalMovement.f = 1000;
        DirectionalMovement.forward();
    }

}
