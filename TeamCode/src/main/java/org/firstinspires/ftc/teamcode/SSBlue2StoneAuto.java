package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.ClassFactory;

import static java.lang.Math.abs;


@Autonomous(name="SSBlue2StoneAuto", group="SS")  // @Autonomous(...) is the other common choice
public class SSBlue2StoneAuto extends SSAutoClasses
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

        //First Diagonal

        DriveTargetPosition(0,-1000,-1000,0);
        Drive(.5,.5); //.4
        while (robot.LB.isBusy() & robot.RF.isBusy() & opModeIsActive()) {
            robot.AutoArmRotate.setPosition(robot.servorotaterblue);
        }
        Drive(0,0);

        //Detect Skystone
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

        //2nd Diagonal
        DriveTargetPosition(0,-800,-800,0);
        Drive(.5,.5);
        while (robot.LB.isBusy() & robot.RF.isBusy() & opModeIsActive()) {
        }
        Drive(0,0);
/*
        //Strafe to Block
        DriveTargetPosition(20,-20,-20,20);
        Drive(.4,.4);
        while (robot.LB.isBusy() & robot.RF.isBusy() & opModeIsActive()) {
        }
        Drive(0,0);
*/
        //Square Wall
        DriveTargetPosition(-50,-50,-50,-50);
        Drive(.4,.4);
        while (robot.LB.isBusy() & robot.RF.isBusy() & robot.LF.isBusy() & robot.RB.isBusy() & opModeIsActive()) {
        }
        Drive(0,0);

//Move to skystone
      StoneMove(skystone,30,200,330);

        Drive(.4,.4);
        while (robot.LB.isBusy() & robot.RF.isBusy() & robot.LF.isBusy() & robot.RB.isBusy() & opModeIsActive()) {
        }
        Drive(0,0);

        //Stone pickup
        robot.AutoArm.setPosition(robot.servoarmpickup);
        robot.Grab1.setPosition(robot.grab1open);
        robot.Grab2.setPosition(robot.grab2open);
        robot.AutoArmJoint.setPosition(robot.servojointdown);
        sleep(500);
        robot.Grab1.setPosition(robot.grab1close);
        robot.Grab2.setPosition(robot.grab2close);
        sleep(500);
        robot.AutoArmJoint.setPosition(robot.servojointup);
        robot.AutoArm.setPosition(robot.servoarmdown);
        sleep(500);

//Move to Foundation
        StoneMove(skystone,3615,3415,3315);
     Drive(RampPower(3615, .5,.1),RampPower(3615, .5,.1));
        while (robot.LB.isBusy() & robot.RF.isBusy() & robot.LF.isBusy() & robot.RB.isBusy() & opModeIsActive()) {
            if (robot.RB.getCurrentPosition() < (robot.RB.getTargetPosition()-100) & robot.RB.getCurrentPosition() > (robot.RB.getTargetPosition()-3000)) {
                robot.AutoArmRotate.setPosition(robot.servorotateback);
            }

            else {
                robot.AutoArmRotate.setPosition(robot.servorotaterblue);
            }
        }
     Drive(0,0);

        //Deliver
        robot.AutoArm.setPosition(robot.servoarmdeliver);
        sleep(500);
        robot.AutoArmJoint.setPosition(robot.servojointdeliver);
        sleep(500);
     robot.Grab1.setPosition(robot.grab1open);
     robot.Grab2.setPosition(robot.grab2open);
     sleep(500);

     period.reset();
     PDimu(0,period);

     //Move back for 2nd Skystone
     robot.AutoArmRotate.setPosition(robot.servorotateback);
        StoneMove(skystone,-2975,-2750,-2575);
       Drive(RampPower(-2975, -.2,-.4),RampPower(-2975, -.2,-.4));
        while (robot.LB.isBusy() & robot.RF.isBusy() & robot.LF.isBusy() & robot.RB.isBusy() & opModeIsActive()) {
                robot.AutoArmRotate.setPosition(robot.servorotateback);
        }
        Drive(0,0);
        robot.AutoArm.setPosition(robot.servoarmup);
        robot.AutoArmRotate.setPosition(robot.servorotaterblue);
        robot.AutoArmJoint.setPosition(robot.servojointup);
        sleep(1000);
        //Stone pickup
        robot.AutoArm.setPosition(robot.servoarmpickup);
        robot.Grab1.setPosition(robot.grab1open);
        robot.Grab2.setPosition(robot.grab2open);
        robot.AutoArmJoint.setPosition(robot.servojointdown);
        sleep(500);
        robot.Grab1.setPosition(robot.grab1close);
        robot.Grab2.setPosition(robot.grab2close);
        sleep(500);
        robot.AutoArmJoint.setPosition(robot.servojointup);
        robot.AutoArm.setPosition(robot.servoarmdown);
        sleep(500);

        //Move to Foundation
        StoneMove(skystone,2975,2750,2575);
        Drive(RampPower(2975, .4,.2),RampPower(2975, .4,.2));
        while (robot.LB.isBusy() & robot.RF.isBusy() & robot.LF.isBusy() & robot.RB.isBusy() & opModeIsActive()) {
            if (robot.RB.getCurrentPosition() < (robot.RB.getTargetPosition()-100) & robot.RB.getCurrentPosition() > (robot.RB.getTargetPosition()-2500)) {
                robot.AutoArmRotate.setPosition(robot.servorotateback);
            }

            else {
                robot.AutoArmRotate.setPosition(robot.servorotaterblue);
            }
        }
        Drive(0,0);

        //Deliver
        robot.AutoArm.setPosition(robot.servoarmdeliver);
        sleep(500);
        robot.AutoArmJoint.setPosition(robot.servojointdeliver);
        sleep(500);
        robot.Grab1.setPosition(robot.grab1open);
        robot.Grab2.setPosition(robot.grab2open);
        sleep(500);
        robot.AutoArmJoint.setPosition(robot.servojointup);
        robot.AutoArm.setPosition(robot.servoarmup);
        robot.Grab1.setPosition(robot.grab1home);
        robot.Grab2.setPosition(robot.grab2home);


        DriveTargetPosition(-150,-150,-150,-150);
        Drive(.4,.4);
        while (robot.LB.isBusy() & robot.RF.isBusy() & robot.LF.isBusy() & robot.RB.isBusy() & opModeIsActive()) {
            robot.AutoArmRotate.setPosition(robot.servorotatehome);
        }
        Drive(0,0);

        robot.AutoArm.setPosition(robot.servoarmhome);
        robot.AutoArm.setPwmDisable();
        robot.AutoArmJoint.setPwmDisable();


        while(opModeIsActive()) {
            sleep(1000000);
        }

    }
}

