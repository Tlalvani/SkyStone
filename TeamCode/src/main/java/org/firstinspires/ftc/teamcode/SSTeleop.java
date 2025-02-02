package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Func;
import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

import java.util.Locale;

/**
 *
 * LB = Left Back
 * LF = Left Front
 * RB = Right Back
 * RF = Right Front
 */


@TeleOp(name = "SSTeleop", group = "SS")  // @Autonomous(...) is the other common choice
public class SSTeleop extends OpMode {

    /* local OpMode members. */
    HardwareMap hwMap = null;
    private ElapsedTime runtime = new ElapsedTime();
    SSHardwareDrivebase robot = new SSHardwareDrivebase();


    /* Constructor */
    @Override
    public void init() {

        robot.init(hardwareMap);


      //  robot.DrivebaseWithEncoders();


    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */
    @Override
    public void init_loop() {
        runtime.reset();
    }

    /*
     * Code to run ONCE when the driver hits PLAY
     */


    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop() {
if(gamepad1.right_bumper){
    robot.blinkinLedDriver.setPattern(RevBlinkinLedDriver.BlinkinPattern.CP1_2_NO_BLENDING);
}
else if(runtime.seconds()>122){
            robot.blinkinLedDriver.setPattern(RevBlinkinLedDriver.BlinkinPattern.BLUE_GREEN);
        }
else if(runtime.seconds()>110){
    robot.blinkinLedDriver.setPattern(RevBlinkinLedDriver.BlinkinPattern.STROBE_RED);
}

else if(runtime.seconds()>90){
    robot.blinkinLedDriver.setPattern(RevBlinkinLedDriver.BlinkinPattern.RED);

}
else if (runtime.seconds()>60){
    robot.blinkinLedDriver.setPattern(RevBlinkinLedDriver.BlinkinPattern.CP1_HEARTBEAT_FAST);
        }
else{
    robot.blinkinLedDriver.setPattern(RevBlinkinLedDriver.BlinkinPattern.BLUE_GREEN);
}
        //robot.AutoArm.setPosition(robot.servoarmhome);
        robot.AutoArmRotate.setPosition(robot.servorotatehome);
        float Ch1 = (gamepad1.right_stick_x);
        float Ch3 = -gamepad1.left_stick_y;
        float Ch4 = gamepad1.left_stick_x;


        float rightfront = Ch3 - Ch1 - Ch4;
        float rightback = Ch3 - Ch1 + Ch4;
        float leftfront = Ch3 + Ch1 + Ch4;
        float leftback = Ch3 + Ch1 - Ch4;
        float lift = gamepad1.right_trigger-gamepad1.left_trigger;



        rightback = Range.clip(rightback, -1, 1);
        leftback = Range.clip(leftback, -1, 1);
        rightfront = Range.clip(rightfront, -1, 1);
        leftfront = Range.clip(leftfront, -1, 1);

        lift = Range.clip(lift, -1, 1);



        // scale the joystick value to make it easier to control
        // the robot more precisely at slower speeds.
        rightfront = (float) robot.scaleInput(rightfront);
        leftfront = (float) robot.scaleInput(leftfront);
        rightback = (float) robot.scaleInput(rightback);
        leftback = (float) robot.scaleInput(leftback);


        // write the values to the motors
        robot.RF.setPower(rightfront);
        robot.LF.setPower(leftfront);
        robot.RB.setPower(rightback);
        robot.LB.setPower(leftback);

        robot.Lift1.setPower(lift);
        robot.Lift2.setPower(lift);

        if(gamepad1.right_trigger>.1){
            robot.RightIntakeArm.setPosition(robot.rightintakearmout);
            robot.LeftIntakeArm.setPosition(robot.leftintakearmout);

        }

        if(gamepad1.right_bumper){
            robot.LeftIntake.setPower(1);
            robot.RightIntake.setPower(1);
            robot.RightIntakeArm.setPosition(robot.rightintakearmin);
            robot.LeftIntakeArm.setPosition(robot.leftintakearmin);
            robot.LeftClaw.setPosition(robot.leftclawopen);
            robot.RightClaw.setPosition(robot.rightclawopen);
        }

        else if (gamepad1.x){
            robot.LeftIntake.setPower(-1);
            robot.RightIntake.setPower(-1);
            robot.RightIntakeArm.setPosition(robot.rightintakearmin);
            robot.LeftIntakeArm.setPosition(robot.leftintakearmin);
        }
        else{
            robot.LeftIntake.setPower(0);
            robot.RightIntake.setPower(0);
            robot.RightIntakeArm.setPosition(robot.rightintakearmout);
            robot.LeftIntakeArm.setPosition(robot.leftintakearmout);


        }

        if(gamepad1.left_bumper){
            robot.LeftClaw.setPosition(robot.leftclawclose);
            robot.RightClaw.setPosition(robot.rightclawclose);

            robot.RightIntakeArm.setPosition(robot.rightintakearmout);
            robot.LeftIntakeArm.setPosition(robot.leftintakearmout);
        }

        if(gamepad1.a){
            robot.LeftClaw.setPosition(robot.leftclawopen);
            robot.RightClaw.setPosition(robot.rightclawopen);
        }

        if(gamepad1.right_stick_button){
            robot.LeftLatch.setPosition(robot.leftlatchclose);
            robot.RightLatch.setPosition(robot.rightlatchclose);
        }
        if(gamepad1.left_stick_button){
            robot.LeftLatch.setPosition(robot.leftlatchopen);
            robot.RightLatch.setPosition(robot.rightlatchopen);
        }


        telemetry.addData("LF: ", robot.LF.getCurrentPosition());
        telemetry.addData("LB: ", robot.LB.getCurrentPosition());
        telemetry.addData("RF: ", robot.RF.getCurrentPosition());
        telemetry.addData("RB: ", robot.RB.getCurrentPosition());
        telemetry.addData("Lift Encoders", robot.LiftCurrentPosition());
        telemetry.update();

    }

    @Override
    public void stop() {
    }
}







