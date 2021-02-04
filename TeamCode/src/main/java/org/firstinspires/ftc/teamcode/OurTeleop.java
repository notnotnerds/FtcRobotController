package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.openftc.revextensions2.ExpansionHubEx;


/*
 ***************************************This code is the property of FTC team 12051 NotNotNerds***************************************
 **********************We do not guarantee that your robot will function correctly after you have used this code**********************
 **************************************************Please use some other team's code**************************************************
 */
@TeleOp(name="Teleop", group = "TeleOp")
public class OurTeleop extends LinearOpMode {
    public DcMotor fl;
    public DcMotor fr;
    public DcMotor bl;
    public DcMotor br;
    public DcMotor spinnyThing; //aka 1150 rpm motor, the one that makes a ton of noise by using zip ties.
    public DcMotor fasterSpinnyThing; //aka 6k rpm motor
    public DcMotor belt_sander; //it sands down the rings
    ExpansionHubEx expansionHub;
    public Servo grabber; //who said we needed to give them normal names?
    public Servo grabNFlip; //seriously, you thought I would name this better?

    @Override
    public void runOpMode() {
        telemetry.addLine("Robot has been turned on. Run for your life!");
        telemetry.update();

        expansionHub = hardwareMap.get(ExpansionHubEx.class, "Expansion Hub 173");
        fl = hardwareMap.dcMotor.get("fl");
        fr = hardwareMap.dcMotor.get("fr");
        bl = hardwareMap.dcMotor.get("bl");
        br = hardwareMap.dcMotor.get("br");
        spinnyThing =hardwareMap.dcMotor.get("intake");
        //fasterSpinnyThing=hardwareMap.dcMotor.get("shooter");
        //grabber=hardwareMap.servo.get("grabber";
        //grabNFlip=hardwareMap.servo.get("flipper");
        //belt_sander=hardwareMap.dcMotor.get("ring_lift");
        fl.setDirection(DcMotor.Direction.REVERSE);
        bl.setDirection(DcMotor.Direction.REVERSE);
        //FtcDashboard dashboard = FtcDashboard.getInstance();

        double m = .5; //Danny's favorite
        double p = .5;
        double a = 1;
        double counter = 0;

        waitForStart();

        while (opModeIsActive()) {
            /*TelemetryPacket packet = new TelemetryPacket();
            packet.put("fl", fl.getCurrentPosition());
            packet.put("fr", fr.getCurrentPosition());
            packet.put("br", br.getCurrentPosition());
            packet.put("bl", bl.getCurrentPosition());

            dashboard.sendTelemetryPacket(packet);
            telemetry.addData("fl", fl.getCurrentPosition());
            telemetry.addData("fr", fr.getCurrentPosition());
            telemetry.addData("bl", bl.getCurrentPosition());
            telemetry.addData("br", br.getCurrentPosition());
            telemetry.update();*/
            if (gamepad1.right_bumper) {
                if (m == .5) {
                    m = 1;
                } else if (m == 1) {
                    m = .5;
                }
            }
            if (gamepad1.left_bumper) {
                if (p == .5) {
                    p = 1;
                } else if (p == 1) {
                    p = .5;
                }
                if (gamepad1.a) {
                    if (a == 1) {
                        a = -1;
                    } else if (a == -1) {
                        a = 1;
                    }
                }
            }
            if(gamepad1.x){
                belt_sander.setPower(1);
            }
            else if(gamepad1.x && gamepad1.y){
                belt_sander.setPower(-1);
            }
            spinnyThing.setPower(-gamepad1.left_trigger);
            if (gamepad1.right_trigger > .1) {
                if (counter < 500) {
                    //  fasterSpinnyThing.setPower(gamepad1.right_trigger); //if you are holding me for too long, I will tell you that you have failed to use correctly --6k rpm yellow jacket
                    telemetry.addLine("You are currently heating my special motor up --definitely not Stephan"); //--rev control hub
                    telemetry.addData("counter says", counter);
                    telemetry.update();
                    counter = counter + 1;
                }
                if (counter >= 500) {
                    //  fasterSpinnyThing.setPower(0); //Don't burn me
                    telemetry.addLine("You are currently heating my special motor up"); //--Rev Control Hub
                    String overheat_notice = "You have been using my poor motor for the last " + counter + "cycles of you holding down the right trigger\n" + "release it now!"; //--Rev Control Hub
                    telemetry.addLine(overheat_notice);
                    telemetry.update();
                    //telemetry.clear(); //not sure if I should use this one
                }
            } else if (gamepad1.right_trigger < .1) {
                counter = 0;
            }
            //spinnyThing.setPower(p*a);

            //drive stuff beneath here
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
