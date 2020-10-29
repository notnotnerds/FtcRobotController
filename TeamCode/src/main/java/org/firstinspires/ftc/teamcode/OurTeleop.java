package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import org.openftc.revextensions2.ExpansionHubEx;


/*
 *************************This code is the property of FTC team 12051 NotNotNerds******************
 ***********************We do not guarantee that your robot will function correctly after you have used this code*************
 ***********************Please use some other team's code****************
 */
@TeleOp (group = "TeleOp")
public class OurTeleop extends LinearOpMode {
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
    @Override
    public void runOpMode()  {
        telemetry.addLine("Robot has been turned on. Run for your life!");
        telemetry.update();

        expansionHub = hardwareMap.get(ExpansionHubEx.class, "Expansion Hub 173");
        fl = hardwareMap.dcMotor.get("fl");
        fr = hardwareMap.dcMotor.get("fr");
        bl = hardwareMap.dcMotor.get("bl");
        br = hardwareMap.dcMotor.get("br");
        //spinnyThing =hardwareMap.dcMotor.get("intake");
        //fasterSpinnyThing=hardwareMap.dcMotor.get("shooter");
        //grabber=hardwareMap.servo.get("grabber";
        //grabNFlip=hardwareMap.servo.get("flipper");
        //angler=hardwareMap.servo.get("angler");
        fl.setDirection(DcMotor.Direction.REVERSE);
        bl.setDirection(DcMotor.Direction.REVERSE);
        FtcDashboard dashboard = FtcDashboard.getInstance();

        double m = .5; //Danny's favorite
        double p= .5;
        double a=1;
        double timer=0;
        waitForStart();


        while (opModeIsActive()) {
            TelemetryPacket packet = new TelemetryPacket();
            packet.put("fl", fl.getCurrentPosition());
            packet.put("fr", fr.getCurrentPosition());
            packet.put("br", br.getCurrentPosition());
            packet.put("bl", bl.getCurrentPosition());

            dashboard.sendTelemetryPacket(packet);
            if(gamepad1.right_bumper){
                if(m == .5){
                    m = 1;
                }
                else if(m == 1){
                    m = .5;
                }
            }
            if(gamepad1.left_bumper){
                if(p == .5){
                p = 1;
            }
            else if(p == 1){
                p = .5;
            }
            if(gamepad1.a){
                if(a==1){
                    a=-1;
                }
                else if(a==-1){
                    a=1;
                }
            }
        }
            if(gamepad1.right_trigger>.1){
                if(timer<=250) {
                    //  fasterSpinnyThing.setPower(gamepad1.right_trigger); //if you are holding me for too long, I will tell you that you have failed to use correctly --6k rpm yellow jacket
                    telemetry.addLine("You are currently heating my special motor up"); //--rev control hub
                    telemetry.addData("timer says", timer);
                    telemetry.update();
                    timer = timer + 1;
                }
                if(timer>=500){
                    //  fasterSpinnyThing.setPower(0); //Don't burn me
                    telemetry.addLine("You are currently heating my special motor up"); //--Rev Control Hub
                    String overheat_notice = "You have been using my poor motor for the last " + timer + "cycles of you holding down the right trigger\n" + "release it now!"; //--Rev Control Hub
                    telemetry.addLine(overheat_notice);
                    telemetry.update();
                }
            }
            else if(gamepad1.right_trigger<.1){
                timer=0;
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
