package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;


@Autonomous(name="SSArmDown", group="SS")  // @Autonomous(...) is the other common choice
public class SSArmDown extends SSAutoClasses
{




    @Override
    public void runOpMode() throws InterruptedException {
       initSensors();
        waitForStart();

        while(opModeIsActive()) {
robot.AutoArm.setPosition(robot.servoarmdown);
robot.AutoArmJoint.setPosition(robot.servojointdown);
robot.AutoArmRotate.setPosition(robot.servorotateback);

            sleep(1000000);
        }

    }
}

