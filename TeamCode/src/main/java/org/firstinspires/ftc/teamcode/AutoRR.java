package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;
import org.firstinspires.ftc.teamcode.DirectionalMovement;
import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.teamcode.vision.CameraEx;
import org.openftc.revextensions2.ExpansionHubEx;
/*
 ***************************************This code is the property of FTC team 12051 NotNotNerds***************************************
 **********************We do not guarantee that your robot will function correctly after you have used this code**********************
 **************************************************Please use some other team's code**************************************************
 */

@Autonomous (name = "Red side right", group = "Autonomous stuff")
public class AutoRR extends LinearOpMode{
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
   //public DirectionalMovement directionalMoves;
   //public int f = 2500;
    public int b = 0;
    public int sl = 0;
    public int sr = 0;
    public int f=0;
    @Override
    public void runOpMode()  {
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
        grabber = hardwareMap.servo.get("grabber");
        grabNFlip = hardwareMap.servo.get("flipper");
        ring_sander = hardwareMap.dcMotor.get("ring_lift");
        ring_kicker = hardwareMap.servo.get("ring_push");
        fl.setDirection(DcMotorEx.Direction.REVERSE);
        bl.setDirection(DcMotorEx.Direction.REVERSE);
        spinnyThing.setDirection(DcMotor.Direction.REVERSE);
        //directionalMoves = new DirectionalMovement();
        bl.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        br.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        fl.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        fr.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);


        double m = 0.5; //speed multiplier
        double p = 0.5;

        waitForStart();
        f = 750;
        fl.setTargetPosition(f);
        fr.setTargetPosition(f);
        bl.setTargetPosition(f);
        br.setTargetPosition(f);
        fl.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        fr.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        bl.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        br.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        fl.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        fr.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        bl.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        br.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        fl.setVelocity(500);
        fr.setVelocity(500);
        bl.setVelocity(500);
        br.setVelocity(500);
        fasterSpinnyThing.setVelocity(1000);
        sleep(250);
        ring_kicker.setPosition(1);
        sleep(250);
        ring_kicker.setPosition(.9);


        while(Math.abs(fl.getCurrentPosition())< Math.abs(f) && (Math.abs(fr.getCurrentPosition())< Math.abs(f)) && Math.abs(bl.getCurrentPosition())< Math.abs(f) && (Math.abs(br.getCurrentPosition())< Math.abs(f))) {
            //wait to get to new location
            sleep(10);
        }
        while (opModeIsActive()) {//just flash LED's for now
            if(CameraEx.ringCount == 4) {
                RedZoneA();
            }
            else if(CameraEx.ringCount == 1){
                RedZoneB();
            }
            else{
                RedZoneC();
            }
            telemetry.addData("Ring Count", CameraEx.ringCount);
            telemetry.update();

        }



    }


    public void RedZoneA(){
        //make it to the target zone A
        expansionHub.setLedColor(255, 0, 0);
        sleep(250);
        expansionHub.setLedColor(0, 0, 0);
        sleep(250);

    }

    public void RedZoneB(){
        //make it to the target zone B
        expansionHub.setLedColor(0, 255, 0);
        sleep(250);
        expansionHub.setLedColor(0, 0, 0);
        sleep(250);
    }

    public void RedZoneC(){
        //make it to the target zone C
        expansionHub.setLedColor(0, 0, 255);
        sleep(250);
        expansionHub.setLedColor(0, 0, 0);
        sleep(250);
    }

/*    public void forward() {
        fl.setTargetPosition(f);
        fr.setTargetPosition(f);
        bl.setTargetPosition(f);
        br.setTargetPosition(f);
        fl.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        fr.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        bl.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        br.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        fl.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        fr.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        bl.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        br.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        fl.setVelocity(750);
        fr.setVelocity(750);
        bl.setVelocity(750);
        br.setVelocity(750);
        while(Math.abs(fl.getCurrentPosition())< Math.abs(f) && (Math.abs(fr.getCurrentPosition())< Math.abs(f)) && Math.abs(bl.getCurrentPosition())< Math.abs(f) && (Math.abs(br.getCurrentPosition())< Math.abs(f))) {
            //wait to get to new location
        }
    }

    public void backward() {
        fl.setTargetPosition(b);
        fr.setTargetPosition(b);
        bl.setTargetPosition(b);
        br.setTargetPosition(b);
        fl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        br.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fl.setVelocity(-100);
        fr.setVelocity(-100);
        bl.setVelocity(-100);
        br.setVelocity(-100);
        fl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        fr.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        bl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        br.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        while(Math.abs(fl.getCurrentPosition())< Math.abs(b) && (Math.abs(fr.getCurrentPosition())< Math.abs(b))) {
            //wait to get to new location
        }
    }

    public void strafeLeft() {
        fl.setTargetPosition(sl);
        fr.setTargetPosition(sl);
        bl.setTargetPosition(sl);
        br.setTargetPosition(sl);
        fl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        br.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fl.setVelocity(-100);
        fr.setVelocity(100);
        bl.setVelocity(100);
        br.setVelocity(-100);
        fl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        fr.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        bl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        br.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        while(Math.abs(fl.getCurrentPosition())< Math.abs(sl) && (Math.abs(fr.getCurrentPosition())< Math.abs(sl))) {
            //wait to get to new location
        }
    }

    public void strafeRight() {
        fl.setTargetPosition(sr);
        fr.setTargetPosition(sr);
        bl.setTargetPosition(sr);
        br.setTargetPosition(sr);
        fl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        br.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fl.setVelocity(100);
        fr.setVelocity(-100);
        bl.setVelocity(-100);
        br.setVelocity(100);
        fl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        fr.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        bl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        br.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        while(Math.abs(fl.getCurrentPosition())< Math.abs(sr) && (Math.abs(fr.getCurrentPosition())< Math.abs(sr))) {
            //wait to get to new location
        }
    }*/
}
