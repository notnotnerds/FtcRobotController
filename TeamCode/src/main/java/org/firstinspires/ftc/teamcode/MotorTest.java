package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
/*
 ***************************************This code is the property of FTC team 12051 NotNotNerds***************************************
 **********************We do not guarantee that your robot will function correctly after you have used this code**********************
 **************************************************Please use some other team's code**************************************************
 */
@TeleOp (name="MotorTest", group = "testing")
public class MotorTest extends LinearOpMode {
    public DcMotor Test;

    @Override
    public void runOpMode() {
        telemetry.addLine("Robot has been turned on. Run for your life!");
        telemetry.update();
        Test = hardwareMap.dcMotor.get("t");
        waitForStart();
        double a = -1;
        telemetry.addLine("To change motor power, use a and b: a to increase into positives, b to increase negatively");
        while (opModeIsActive()) {
            Test.setPower(-gamepad1.right_trigger);
            telemetry.addData("Motor Speed:", Test.getPower());
            telemetry.update();
        }
    }
    }