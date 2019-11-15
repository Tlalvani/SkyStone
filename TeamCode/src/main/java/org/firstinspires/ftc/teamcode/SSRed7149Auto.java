package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.ClassFactory;


@Autonomous(name="SSRed7149Auto", group="SS")  // @Autonomous(...) is the other common choice
public class SSRed7149Auto extends SSAutoClasses
{
    int skystone = 0;

    @Override
    public void runOpMode() throws InterruptedException {

        initSensors();
        initVuforia();
        if (ClassFactory.getInstance().canCreateTFObjectDetector()) {
            initTfod();
        } else {
            telemetry.addData("Sorry!", "This device is not compatible with TFOD");
        }



        robot.DrivebaseWithEncoders();

        waitForStart();
        runtime.reset();

        DriveTargetPosition(-1000,0,0,-1000); //1650
        Drive(.25,.25); //.4
        while (robot.LF.isBusy() & robot.RB.isBusy() & opModeIsActive()) {
        }
        Drive(0,0);

        while((skystone==0)& runtime.seconds()<5 & opModeIsActive()) {
            if (tfod != null) {
                tfod.activate();
            }

            if(skystone == 0) {
                skystone = runPhoneDetection(skystone);
            }
        }
        telemetry.addData("stone:",skystone);
        telemetry.update();

        DriveTargetPosition(-700,0,0,-700);
        Drive(.3,.3);
        while (robot.LF.isBusy() & robot.RB.isBusy() & opModeIsActive()) {
        }
        Drive(0,0);


        DriveTargetPosition(-50,-50,-50,-50);
        Drive(.4,.4);
        DrivebaseBusy();
        Drive(0,0);


        DriveTargetPosition(170,170,170,170);
        Drive(.4,.4);
        while (robot.LB.isBusy() & robot.RF.isBusy() & robot.LF.isBusy() & robot.RB.isBusy() & opModeIsActive()) {
            robot.AutoArmRotate.setPosition(robot.servorotatered);
        }
        Drive(0,0);


        StoneMove(skystone,0,200,365);
        Drive(.4,.4);
        while (robot.LB.isBusy() & robot.RF.isBusy() & robot.LF.isBusy() & robot.RB.isBusy() & opModeIsActive()) {
        }
        Drive(0,0);

        robot.AutoArm.setPosition(robot.servoarmpickup);
        robot.Grab1.setPosition(robot.grab1open);
        robot.Grab2.setPosition(robot.grab2open);
        robot.AutoArmJoint.setPosition(robot.servojointdown);


        sleep(500);
        robot.Grab1.setPosition(robot.grab1close);
        robot.Grab2.setPosition(robot.grab2close);
        sleep(1000);
        robot.AutoArm.setPosition(robot.servoarmdown);
        robot.AutoArmJoint.setPosition(robot.servojointup);


        StoneMove(skystone,3420,3275,3020);
     Drive(RampPower(3600, .4,.2),RampPower(3600, .4,.2));


        while (robot.LB.isBusy() & robot.RF.isBusy() & robot.LF.isBusy() & robot.RB.isBusy() & opModeIsActive()) {
            if (robot.RB.getCurrentPosition() < (robot.RB.getTargetPosition()-100) & robot.RB.getCurrentPosition() > (robot.RB.getTargetPosition()-3000)) {
                robot.AutoArmRotate.setPosition(robot.servorotateback);
            }

            else {
               robot.AutoArmRotate.setPosition(robot.servorotatered);
            }
        }
     Drive(0,0);

        robot.AutoArm.setPosition(robot.servoarmdeliver);
        sleep(500);
      robot.AutoArmJoint.setPosition(robot.servojointdeliverred);
        sleep(500);
     robot.Grab1.setPosition(robot.grab1open);
     robot.Grab2.setPosition(robot.grab2open);
     sleep(500);
        DriveTargetPosition(-500,-500,-500,-500);
        Drive(.4,.4);
        while (robot.LB.isBusy() & robot.RF.isBusy() & robot.LF.isBusy() & robot.RB.isBusy() & opModeIsActive()) {
        }
        Drive(0,0);
        robot.AutoArm.setPosition(robot.servoarmup);
        ResetArm();

      DriveTargetPosition(-1800,-1800,-1800,-1800);
        Drive(.4,.4);
        while (robot.LB.isBusy() & robot.RF.isBusy() & robot.LF.isBusy() & robot.RB.isBusy() & opModeIsActive()) {
        }
        Drive(0,0);



        while(opModeIsActive()) {
            sleep(1000000);
        }

    }
}

