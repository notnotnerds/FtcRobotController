package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.openftc.revextensions2.ExpansionHubEx;

@Autonomous
public class AutonomousPeriod extends LinearOpMode{
    /* where is the starting position (depending on which line it's starting at) (repeat until certain amount of time idk)
         sensor how many rings
             put wobble goal in square A (depending on how many rings) & go back to launch zone
             put wobble goal in square B (depending on how many rings) & go back to launch zone
             put wobble goal in square C (depending on how many rings) & go back to launch zone
         sensor rings, pick up rings, and get in position to shoot
             shoot at tower 1st, 2nd, 3rd hole
             shoot at power shot 1st, 2nd, 3rd stick thing
     (when time is certain amount) sensor line and park over it
*/
    public DcMotor fl;
    public DcMotor fr;
    public DcMotor bl;
    public DcMotor br;
    public DcMotor spinnyThing;
    ExpansionHubEx expansionHub;
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
        //spinnyThing =hardwareMap.dcMotor.get("intake");
        fl.setDirection(DcMotor.Direction.REVERSE);
        bl.setDirection(DcMotor.Direction.REVERSE);
        expansionHub = hardwareMap.get(ExpansionHubEx.class, "Expansion Hub 173");

        double m = .5; //speed multiplier
        double p= .5;
        waitForStart();

        fl.setPower(-1);
        fr.setPower(-1);
        bl.setPower(-1);
        br.setPower(-1);
        sleep(1000);
        fl.setPower(1);
        fr.setPower(-1);
        bl.setPower(-1);
        br.setPower(1);
        sleep(1000);
        fl.setPower(-1);
        fr.setPower(-1);
        bl.setPower(-1);
        br.setPower(-1);
        sleep(500);

        fl.setPower(0);
        fr.setPower(0);
        bl.setPower(0);
        br.setPower(0);
        sleep(1000);



        while (opModeIsActive()) {
            expansionHub.setLedColor(100, 100, 0);
            sleep(250);
            expansionHub.setLedColor(100, 0, 100);
            sleep(250);
            expansionHub.setLedColor(0, 100, 100);
            sleep(250);
            expansionHub.setLedColor(100, 100, 100);
            sleep(250);
            expansionHub.setLedColor(200, 200, 200);
            sleep(250);
            expansionHub.setLedColor(255, 0, 0);
            sleep(250);
        }
    }


}
