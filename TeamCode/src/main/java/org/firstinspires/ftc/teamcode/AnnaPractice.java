package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class AnnaPractice extends OpMode {
    @Override
    public void init() {
        telementry.addData("Hello", "World");
    }


}
