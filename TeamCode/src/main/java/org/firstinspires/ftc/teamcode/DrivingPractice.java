package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
//Code property of team 12051 NotNotNerds. Please do not just take this for your own use
@TeleOp (name="Robot Driving Practice, beginner level", group="drivers")
public class DrivingPractice   extends LinearOpMode {
    public DcMotor fl;
    public DcMotor fr;
    public DcMotor bl;
    public DcMotor br;

    @Override
    public void runOpMode()  {
        telemetry.addLine("Welcome to the robot tutorial");
        telemetry.addLine("To move forward, use the right joystick y");
        telemetry.addLine("To rotate, use the right joystick x");
        telemetry.addLine("To strafe side to side, use the left joystick x");
        telemetry.addLine("For now, you will be limited to half power. When you get better, you will be given the option to advance");
        telemetry.addLine("Do NOT crash the robot! The coders will be furious and will forever keep you at 10% power while they enjoy their turbo boost");
        telemetry.addLine("DANNY, you will not be using turbo speed!");
        telemetry.update();

        fl=hardwareMap.dcMotor.get("fl");
        fr=hardwareMap.dcMotor.get("fr");
        bl=hardwareMap.dcMotor.get("bl");
        br=hardwareMap.dcMotor.get("br");
        br.setDirection(DcMotor.Direction.REVERSE);
        bl.setDirection(DcMotor.Direction.REVERSE);

        boolean back=false;
        double m=.25; //speed multiplier

        waitForStart();
        while (opModeIsActive()) {
            if(gamepad1.back){//the area that drives danny crazy
                if(back==false){
                    back=true;
                    m=5;
                }
                else if(back==true){
                    back=false;
                    m=.25;
                }
            }
            bl.setPower(m * (gamepad1.right_stick_x - gamepad1.right_stick_y)+gamepad1.left_stick_x);
            br.setPower(m * (gamepad1.right_stick_x + gamepad1.right_stick_y)+gamepad1.left_stick_x);
            fr.setPower(m * (gamepad1.right_stick_x - gamepad1.right_stick_y)+gamepad1.left_stick_x);
            fl.setPower(m * (gamepad1.right_stick_x + gamepad1.right_stick_y)+gamepad1.left_stick_x);
        }
    }
}
