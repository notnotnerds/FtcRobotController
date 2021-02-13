package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.opencv.core.Mat;

/*
 ***************************************This code is the property of FTC team 12051 NotNotNerds***************************************
 **********************We do not guarantee that your robot will function correctly after you have used this code**********************
 **************************************************Please use some other team's code**************************************************
 */
public class DirectionalMovement {
    public static DcMotorEx fl;
    public static DcMotorEx fr;
    public static DcMotorEx bl;
    public static DcMotorEx br;
    public int f = 0;
    public int r = 0;
    public int sl = 0;
    public int sr = 0;

    public void forward() {
        fl.setTargetPosition(f);
        fr.setTargetPosition(f);
        fl.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        fr.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        fl.setVelocity(100);
        fr.setVelocity(100);
        bl.setVelocity(100);
        br.setVelocity(100);
        fl.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        fr.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
while(Math.abs(fl.getCurrentPosition())< Math.abs(f) && (Math.abs(fr.getCurrentPosition())< Math.abs(f)) && Math.abs(bl.getCurrentPosition())< Math.abs(f) && (Math.abs(br.getCurrentPosition())< Math.abs(f))) {
    //wait to get to new location
        }
    }

    public void backward() {
        fl.setTargetPosition(r);
        fr.setTargetPosition(r);
        bl.setTargetPosition(r);
        br.setTargetPosition(r);
        fl.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        fr.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        bl.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        br.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        fl.setVelocity(-100);
        fr.setVelocity(-100);
        bl.setPower(-.5);
        br.setPower(-.5);
        fl.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        fr.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        bl.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        br.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        while(Math.abs(fl.getCurrentPosition())< Math.abs(r) && (Math.abs(fr.getCurrentPosition())< Math.abs(r)) && Math.abs(bl.getCurrentPosition())< Math.abs(r) && (Math.abs(br.getCurrentPosition())< Math.abs(r))) {
            //wait to get to new location
        }
    }

    public void strafeLeft() {
        fl.setTargetPosition(sl);
        fr.setTargetPosition(sl);
        bl.setTargetPosition(sl);
        br.setTargetPosition(sl);
        fl.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        fr.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        bl.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        br.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        fl.setVelocity(-100);
        fr.setVelocity(100);
        bl.setVelocity(100);
        br.setVelocity(-100);
        fl.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        fr.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        bl.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        br.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        while(Math.abs(fl.getCurrentPosition())< Math.abs(sl) && (Math.abs(fr.getCurrentPosition())< Math.abs(sl)) && Math.abs(bl.getCurrentPosition())< Math.abs(sl) && (Math.abs(br.getCurrentPosition())< Math.abs(sl))) {
            //wait to get to new location
        }
    }

    public void strafeRight() {
        fl.setTargetPosition(sr);
        fr.setTargetPosition(sr);
        bl.setTargetPosition(sr);
        br.setTargetPosition(sr);
        fl.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        fr.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        bl.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        br.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        fl.setVelocity(100);
        fr.setVelocity(-100);
        bl.setVelocity(-100);
        br.setVelocity(100);
        fl.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        fr.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        bl.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        br.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        while(Math.abs(fl.getCurrentPosition())< Math.abs(sr) && (Math.abs(fr.getCurrentPosition())< Math.abs(sr)) && Math.abs(bl.getCurrentPosition())< Math.abs(sr) && (Math.abs(br.getCurrentPosition())< Math.abs(sr))) {
            //wait to get to new location
        }
    }
}
