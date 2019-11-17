package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;


@Autonomous(name="SSPD", group="SS")  // @Autonomous(...) is the other common choice
public class SSPDAuto extends SSAutoClasses
{
    ElapsedTime loopTime = new ElapsedTime();





    @Override
    public void runOpMode() throws InterruptedException {
       initSensors();

        waitForStart();
        robot.AutoArm.setPwmDisable();
        loopTime.reset();

        while(opModeIsActive()) {
PDimu(90,loopTime);

        }

    }
}

