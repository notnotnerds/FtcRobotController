package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous
public class practiceCoding   extends LinearOpMode {
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
        telemetry.addLine("For now, you will be limited to quarter power. When you get better, you will be given the option to advance");
        telemetry.update();

        fl=hardwareMap.dcMotor.get("fl");
        fr=hardwareMap.dcMotor.get("fr");
        bl=hardwareMap.dcMotor.get("bl");
        br=hardwareMap.dcMotor.get("br");


        double drive=gamepad1.right_stick_y;
        double strafe=gamepad1.left_stick_x;
        double rotate=gamepad1.right_stick_x;
        double m=.25; //speed multiplier

        waitForStart();
        while (opModeIsActive()) {
            fl.setPower(1);
            bl.setPower(1);
            fr.setPower(-1);
            br.setPower(-1);
        }
    }
}
