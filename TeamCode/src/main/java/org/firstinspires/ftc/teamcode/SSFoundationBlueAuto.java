package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.ClassFactory;


@Autonomous(name="SSFoundationBlueAuto", group="SS")  // @Autonomous(...) is the other common choice
public class SSFoundationBlueAuto extends SSAutoClasses
{
    int skystone;



    @Override
    public void runOpMode() throws InterruptedException {

        initSensors();
        initWebcamVuforia();
        if (ClassFactory.getInstance().canCreateTFObjectDetector()) {
            initTfod();
        } else {
            telemetry.addData("Sorry!", "This device is not compatible with TFOD");
        }

        robot.DrivebaseWithEncoders();

        waitForStart();
        runtime.reset();
     /*   DriveTargetPosition(0,-900,-900,0);
        Drive(.3,.3);
        while (robot.LB.isBusy() & robot.RF.isBusy() & opModeIsActive()) {
        }
        Drive(0,0);


        DriveTargetPosition(0,-800,-800,0);
        Drive(.4,.4);
        while (robot.LB.isBusy() & robot.RF.isBusy() & opModeIsActive()) {
        }
        Drive(0,0);

        Drive(.4,.4);
        while (robot.LB.isBusy() & robot.RF.isBusy() & robot.LF.isBusy() & robot.RB.isBusy() & opModeIsActive()) {
        }
        Drive(0,0);



     DriveTargetPosition(3600,3600,3600,3600);
     Drive(RampPower(3600, .5,.2),RampPower(3600, .5,.2));

        while (robot.LB.isBusy() & robot.RF.isBusy() & robot.LF.isBusy() & robot.RB.isBusy() & opModeIsActive()) {
        }
     Drive(0,0);

*/

     imu(-90);
        DriveTargetPosition(300,300,300,300);
        Drive(.4,.4);
        while (robot.LB.isBusy() & robot.RF.isBusy() & robot.LF.isBusy() & robot.RB.isBusy() & opModeIsActive()) {
        }
        Drive(0,0);

        robot.LeftLatch.setPosition(robot.leftlatchclose);
        robot.RightLatch.setPosition(robot.rightlatchclose);

        sleep(1000);
        DriveTargetPosition(-1000,-1000,-1000,-1000);
        Drive(.4,.4);
        while (robot.LB.isBusy() & robot.RF.isBusy() & robot.LF.isBusy() & robot.RB.isBusy() & opModeIsActive()) {
        }
        Drive(0,0);

        robot.LeftLatch.setPosition(robot.leftlatchopen);
        robot.RightLatch.setPosition(robot.rightlatchopen);


        while(opModeIsActive()) {
            sleep(1000000);
        }

    }
}

