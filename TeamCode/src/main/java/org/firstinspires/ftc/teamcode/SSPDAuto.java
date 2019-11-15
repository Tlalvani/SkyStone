package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;


@Autonomous(name="SSPD", group="SS")  // @Autonomous(...) is the other common choice
public class SSPDAuto extends SSAutoClasses
{




    @Override
    public void runOpMode() throws InterruptedException {
       initSensors();
        waitForStart();
        robot.AutoArm.setPwmDisable();

        while(opModeIsActive()) {
PDimu(90);

        }

    }
}

