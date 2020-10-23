package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp (name = "Robot Driving Practice, beginner level", group = "drivers")
public class DrivingPractice extends LinearOpMode {
    public DcMotor fl;
    public DcMotor fr;
    public DcMotor bl;
    public DcMotor br;
    public DcMotor spinnyThing;



    @Override
    public void runOpMode()  {
        telemetry.addLine("Welcome to the robot tutorial");
        telemetry.addLine("To move forward, use the right joystick y");
        telemetry.addLine("To rotate, use the left joystick x");
        telemetry.addLine("To strafe side to side, use the right joystick x");
        telemetry.addLine("For now, you will be limited to half power. When you get better, you will be given the option to advance");
        telemetry.update();

        fl = hardwareMap.dcMotor.get("fl");
        fr = hardwareMap.dcMotor.get("fr");
        bl = hardwareMap.dcMotor.get("bl");
        br = hardwareMap.dcMotor.get("br");
        spinnyThing =hardwareMap.dcMotor.get("intake");
        fl.setDirection(DcMotor.Direction.REVERSE);
        bl.setDirection(DcMotor.Direction.REVERSE);
        FtcDashboard dashboard = FtcDashboard.getInstance();

        double m = .5; //speed multiplier
        double p= .5;
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
        }
            double drive = gamepad1.right_stick_y;
            double strafe = gamepad1.right_stick_x;
            double rotate = gamepad1.left_stick_x;
            spinnyThing.setPower(p);
            bl.setPower(m * (-drive + rotate - strafe));
            br.setPower(m * (-drive - rotate + strafe));
            fr.setPower(m * (-drive + rotate + strafe));
            fl.setPower(m * (-drive - rotate - strafe));
        }
    }
}
