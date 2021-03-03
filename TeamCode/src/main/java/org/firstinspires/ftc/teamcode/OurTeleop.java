package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.openftc.revextensions2.ExpansionHubEx;


/**
 ***************************************This code is the property of FTC team 12051 NotNotNerds***************************************
 **********************We do not guarantee that your robot will function correctly after you have used this code**********************
 **************************************************Please use some other team's code**************************************************
 */
@TeleOp(name="Teleop", group = "TeleOp")
public class OurTeleop extends LinearOpMode {
    private DcMotorEx fl;
    private DcMotorEx fr;
    private DcMotorEx bl;
    private DcMotorEx br;
    private DcMotor spinnyThing; //aka 1150 rpm motor, the one that makes a ton of noise by using zip ties.
    private DcMotorEx fasterSpinnyThing; //aka 6k rpm motor
    private DcMotor ring_sander; //it sands down the rings
    //private DcMotor wobble_lift; //it raises the wobble goal over the field edge ----we don't have this
    private Servo ring_kicker; //it kicks the rings into the fasterSpinnyThing
    ExpansionHubEx expansionHub;
    private Servo grabber; //who said we needed to give them normal names?
    private Servo grabNFlip; //seriously, you thought I would name this better?

    @Override
    public void runOpMode() {
        telemetry.addLine("Robot has been turned on. Run for your life!");
        telemetry.update();

        expansionHub = hardwareMap.get(ExpansionHubEx.class, "Expansion Hub 173");
        fl = hardwareMap.get(DcMotorEx.class, "fl");
        fr = hardwareMap.get(DcMotorEx.class, "fr");
        bl = hardwareMap.get(DcMotorEx.class, "bl");
        br = hardwareMap.get(DcMotorEx.class, "br");
        spinnyThing =hardwareMap.dcMotor.get("intake");
        fasterSpinnyThing=hardwareMap.get(DcMotorEx.class, "shooter");
        fasterSpinnyThing.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        grabber=hardwareMap.servo.get("grabber");
        grabNFlip=hardwareMap.servo.get("flipper");
        ring_sander=hardwareMap.dcMotor.get("ring_lift");
        ring_kicker=hardwareMap.servo.get("ring_push");
        fl.setDirection(DcMotor.Direction.REVERSE);
        bl.setDirection(DcMotor.Direction.REVERSE);
        spinnyThing.setDirection(DcMotor.Direction.REVERSE);

        double m = 1; //Danny's favorite
        double p = 1; //intake multiplier
        double a = 1; //reverse
        double counter = 0; //for fasterSpinnyThing
        waitForStart();

        while (opModeIsActive()) {


/*************** intake system control ****************/
/*            if (gamepad2.a) {//intake speed (half/full power)
                if (p == .5) {
                    p = 1;
                } else {
                    p = .5;
                }
            }
            if (gamepad2.b) { //intake reverse
                if (a == 1) {
                    a = -1;
                } else {
                    a = 1;
                }
            }*/

            //move rings up ramp
            if(gamepad2.x){
                ring_sander.setPower(-p);
                spinnyThing.setPower(p);
            }
            else{
                ring_sander.setPower(gamepad2.left_stick_y);
                spinnyThing.setPower(-gamepad2.right_stick_y);
            }
/*************** ring booster servo control **********/
            if(gamepad2.right_bumper){
                ring_kicker.setPosition(1);
            }
            else{
                ring_kicker.setPosition(.9);
            }
/**************** shooter control *******************/
            if (gamepad2.right_trigger > .1) {
                if (counter < 2000) {
                    fasterSpinnyThing.setVelocity(1000); //if you are holding me for too long, I will tell you that you have failed to use me correctly --6k rpm yellow jacket
                    telemetry.addLine("You are currently heating my special motor up --Rev Control Hub");
                    telemetry.addData("The motor usage counter says", counter);
                    telemetry.update();
                    counter = counter + 1;
                }
                if (counter >= 2000) {
                    fasterSpinnyThing.setPower(0); //Don't burn me
                    telemetry.addLine("You are currently heating my special motor up"); //--Rev Control Hub
                    String overheat_notice = "You have been using my poor motor for the last " + counter + "cycles of you holding down the right trigger\n" + "release it now!"; //--Rev Control Hub
                    telemetry.addLine(overheat_notice);
                    telemetry.update();
                    //telemetry.clear(); //not sure if I should use this one
                }
            } else if (gamepad2.right_trigger < .1) {
                counter = 0;
                fasterSpinnyThing.setVelocity(0);
            }

/**************** Wobble arm controls *******************/
            if(gamepad2.dpad_up){//flip arm in
                grabNFlip.setPosition(.25);
            }
            if(gamepad2.dpad_down){//flip arm out
                grabNFlip.setPosition(1);
            }

            if(gamepad2.dpad_left){//grab wobble
                grabber.setPosition(.4);
            }
            if (gamepad2.dpad_right){//release wobble
                grabber.setPosition(0);
            }

/***************** drive stuff beneath here ******************/
            if (gamepad1.right_bumper) {//drive speed multiplier
                if (m == .75) {
                    m = 1;
                } else {
                    m = .75;
                }
            }
            double drive = gamepad1.right_stick_y;
            double strafe = gamepad1.right_stick_x;
            double rotate = gamepad1.left_stick_x;

            bl.setPower(m * (-drive + rotate - strafe));
            br.setPower(m * (-drive - rotate + strafe));
            fr.setPower(m * (-drive + rotate + strafe));
            fl.setPower(m * (-drive - rotate - strafe));
        }
    }

}
