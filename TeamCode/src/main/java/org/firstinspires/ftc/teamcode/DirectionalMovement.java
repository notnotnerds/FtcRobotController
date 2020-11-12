package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public class DirectionalMovement {
    public static DcMotor fl;
    public static DcMotor fr;
    public static DcMotor bl;
    public static DcMotor br;
    public int f = 0;
    public static int r = 0;
    public static int sl = 0;
    public static int sr = 0;

    public void forward() {
        fl.setTargetPosition(f);
        fr.setTargetPosition(f);
        fl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fl.setPower(-.5);
        fr.setPower(-.5);
        bl.setPower(-.5);
        br.setPower(-.5);
        fl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        fr.setMode(DcMotor.RunMode.RUN_TO_POSITION);

    }

    public static void backward() {
        fl.setTargetPosition(r);
        fr.setTargetPosition(r);
        bl.setTargetPosition(r);
        br.setTargetPosition(r);
        fl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        br.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fl.setPower(-.5);
        fr.setPower(-.5);
        bl.setPower(-.5);
        br.setPower(-.5);
        fl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        fr.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        bl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        br.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public static void strafeLeft() {
        fl.setTargetPosition(sl);
        fr.setTargetPosition(sl);
        bl.setTargetPosition(sl);
        br.setTargetPosition(sl);
        fl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        br.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fl.setPower(-.5);
        fr.setPower(.5);
        bl.setPower(.5);
        br.setPower(-.5);
        fl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        fr.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        bl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        br.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public static void strafeRight() {
        fl.setTargetPosition(sr);
        fr.setTargetPosition(sr);
        bl.setTargetPosition(sr);
        br.setTargetPosition(sr);
        fl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        br.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fl.setPower(.5);
        fr.setPower(-.5);
        bl.setPower(-.5);
        br.setPower(.5);
        fl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        fr.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        bl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        br.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
}
