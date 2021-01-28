/*
 * Copyright (c) 2018 OpenFTC Team
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package org.openftc.revextensions2.examples;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.openftc.revextensions2.ExpansionHubEx;
/*
 ***************************************This code is the property of FTC team 12051 NotNotNerds***************************************
 **********************We do not guarantee that your robot will function correctly after you have used this code**********************
 **************************************************Please use some other team's code**************************************************
 */

@TeleOp(name ="Party")
public class hubLED extends LinearOpMode
{
    ExpansionHubEx expansionHub;

    @Override
    public void runOpMode() throws InterruptedException {

        expansionHub = hardwareMap.get(ExpansionHubEx.class, "Expansion Hub 173");

        String header = "**********************************\n" +
                        "LED color changer                   \n" +
                        "**********************************\n";
        telemetry.addLine(header);

        /*
         * Setting ExpansionHub LED color
         */
        expansionHub.setLedColor(255, 0, 0);
        telemetry.addLine("Setting Hub LED color");


        telemetry.update();
        int r=0;
        int g=0;
        int b=0;
        waitForStart();
        while (opModeIsActive()) {
           /* -------this is definitely not a loop---------
            expansionHub.setLedColor(r, g, b);
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
            sleep(250);*/

 //         -------This is loop type 1----------
            for (r=0; r<255; r++){
                expansionHub.setLedColor(r, g, b);
            }
            for (g=0; g<255; g++){
                expansionHub.setLedColor(r, g, b);
            }
            for (b=0; b<255; b++){
                expansionHub.setLedColor(r, g, b);
            }
                if(r>=255){
                    r=0;
                }
                if(g>=255){
                    g=0;
                }
                if(b>=255){
                    b=0;
                }/*
            // --------This is loop type 2-----------
            for (r=0, g=100, b=200; r<255 & g<255 & b<255; r++, g++, b++){
                expansionHub.setLedColor(r, g, b);
                telemetry.addData("red", r);
                telemetry.addData("green", g);
                telemetry.addData("blue", b);
                telemetry.update();
                if(r>=255){
                    r=0;
                }
                if(g>=255){
                    g=0;
                }
                if(b>=255){
                    b=0;
                }
            }*/


        }

    }
    public void IHaveNothingToDoRightNow(){
        int r;
        int g;
        int b;
        for (r=0, g=100, b=200; r<255 & g<255 & b<255; r++, g++, b++){
            expansionHub.setLedColor(r, g, b);
            telemetry.addData("red", r);
            telemetry.addData("green", g);
            telemetry.addData("blue", b);
            telemetry.update();
            sleep(100);
            if(r>=255){
                r=0;
            }
            if(g>=255){
                g=0;
            }
            if(b>=255){
                b=0;
            }
        }
    }
}