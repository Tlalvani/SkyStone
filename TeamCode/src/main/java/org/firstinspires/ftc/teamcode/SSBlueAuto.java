package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;


@Autonomous(name="SSBlueAuto", group="SS")  // @Autonomous(...) is the other common choice
public class SSBlueAuto extends SSAutoClasses
{
    int skystone = 0;

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

        DriveTargetPosition(0,-1000,-1000,0); //1650
        Drive(.25,.25); //.4
        while (robot.LB.isBusy() & robot.RF.isBusy() & opModeIsActive()) {
            robot.AutoArmRotate.setPosition(robot.servorotaterblue);
        }
        Drive(0,0);

        while((skystone==0)& runtime.seconds()<5 & opModeIsActive()) {
            if (tfod != null) {
                tfod.activate();
            }

            if(skystone == 0) {
                skystone = runDetection(skystone);
            }
        }
        telemetry.addData("stone:",skystone);
        telemetry.update();

        DriveTargetPosition(0,-800,-800,0);
        Drive(.3,.3);
        while (robot.LB.isBusy() & robot.RF.isBusy() & opModeIsActive()) {
        }
        Drive(0,0);

        DriveTargetPosition(50,-50,-50,50);
        Drive(.3,.3);
        while (robot.LB.isBusy() & robot.RF.isBusy() & opModeIsActive()) {
        }
        Drive(0,0);

      StoneMove(skystone,30,200,300);

        Drive(.4,.4);
        while (robot.LB.isBusy() & robot.RF.isBusy() & robot.LF.isBusy() & robot.RB.isBusy() & opModeIsActive()) {
        }
        Drive(0,0);

        robot.AutoArm.setPosition(robot.servoarmdown);
        robot.Grab1.setPosition(robot.grab1open);
        robot.Grab2.setPosition(robot.grab2open);
        robot.AutoArmJoint.setPosition(robot.servojointdown);


        sleep(500);
        robot.Grab1.setPosition(robot.grab1close);
        robot.Grab2.setPosition(robot.grab2close);
        sleep(1000);
        robot.AutoArmJoint.setPosition(robot.servojointup);


        StoneMove(skystone,3600,3415,3185);
     Drive(RampPower(3600, .5,.2),RampPower(3600, .5,.2));


        while (robot.LB.isBusy() & robot.RF.isBusy() & robot.LF.isBusy() & robot.RB.isBusy() & opModeIsActive()) {
            if (robot.RB.getCurrentPosition() < (robot.RB.getTargetPosition()-100) & robot.RB.getCurrentPosition() > (robot.RB.getTargetPosition()-3000)) {
                robot.AutoArmRotate.setPosition(robot.servorotateback);
            }

            else {
                robot.AutoArmRotate.setPosition(robot.servorotaterblue);
            }
        }
     Drive(0,0);

        robot.AutoArm.setPosition(robot.servoarmdeliver);
        sleep(1000);
        robot.AutoArmJoint.setPosition(robot.servojointdeliver);
        sleep(500);
     robot.Grab1.setPosition(robot.grab1open);
     robot.Grab2.setPosition(robot.grab2open);
     sleep(1000);
        robot.AutoArm.setPosition(robot.servoarmup);
        ResetArm();

        DriveTargetPosition(-200,-200,-200,-200);
        Drive(.4,.4);
        while (robot.LB.isBusy() & robot.RF.isBusy() & robot.LF.isBusy() & robot.RB.isBusy() & opModeIsActive()) {
        }
        Drive(0,0);


     imu(-90);
     DriveTargetPosition(300,300,300,300);
     Drive(.4,.4);
     while (robot.LB.isBusy() & robot.RF.isBusy() & robot.LF.isBusy() & robot.RB.isBusy() & opModeIsActive()) {
        }
     Drive(0,0);

        robot.LeftLatch.setPosition(robot.leftlatchclose);
        robot.RightLatch.setPosition(robot.rightlatchclose);

        sleep(1000);
        DriveTargetPosition(-1100,-1100,-1100,-1100);
        Drive(.4,.4);
        while (robot.LB.isBusy() & robot.RF.isBusy() & robot.LF.isBusy() & robot.RB.isBusy() & opModeIsActive()) {
        }
        Drive(0,0);

        robot.LeftLatch.setPosition(robot.leftlatchopen);
        robot.RightLatch.setPosition(robot.rightlatchopen);

        DriveTargetPosition(2000,-2000,-2000,2000);
        Drive(.3,.3);
        while (robot.LB.isBusy() & robot.RF.isBusy() & opModeIsActive()) {
        }
        Drive(0,0);

        while(opModeIsActive()) {
            sleep(1000000);
        }

    }
}

