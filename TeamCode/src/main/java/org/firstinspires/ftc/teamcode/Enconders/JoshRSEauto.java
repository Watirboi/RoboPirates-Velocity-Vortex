package org.firstinspires.ftc.teamcode.Enconders;


import android.app.Activity;
import android.graphics.Color;
import android.view.View;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.I2cAddr;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.JoshRobot.JoshRobot;

/**
 * Created by robot on 2/3/2017.
 */
@Autonomous (name = "JoshRSEauto", group = "robot")
public class JoshRSEauto extends LinearOpMode {
    JoshRobot robot = new JoshRobot();
    private ElapsedTime runtime = new ElapsedTime();
    static final double COUNTS_PER_MOTOR_REV = 1120;    // eg: TETRIX Motor Encoder
    static final double DRIVE_GEAR_REDUCTION = .3;     // This is < 1.0 if geared UP
    static final double WHEEL_DIAMETER_INCHES = 4.0;     // For figuring circumference
    static final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * 3.1415);

    static final double DRIVE_SPEED = .3;
    static final double TURN_SPEED = .3;
    public static final I2cAddr COLOR_SENSOR_ORIGINAL_ADDRESS = I2cAddr.create8bit(0x3c);
    ColorSensor color_sensor;
    ColorSensor white_sensor;
    I2cAddr newAddress = I2cAddr.create8bit(0x60);

    @Override
    public void runOpMode() {

        robot.init(hardwareMap);

        float hsvValues[] = {0F,0F,0F};

        final float values[] = hsvValues;

        final View relativeLayout = ((Activity) hardwareMap.appContext).findViewById(com.qualcomm.ftcrobotcontroller.R.id.RelativeLayout);





        color_sensor = hardwareMap.colorSensor.get("sensor_color");

        white_sensor = hardwareMap.colorSensor.get("sensor_white");
        I2cAddr currentAddress = COLOR_SENSOR_ORIGINAL_ADDRESS;
        // I2c addresses on Modern Robotics devices must be divisible by 2, and between 0x7e and 0x10
        // Different hardware may have different rules.
        // Be sure to read the requirements for the hardware you're using!
        // If you use an invalid address, you may make your device completely unusable.
        white_sensor.setI2cAddress(newAddress);
        color_sensor.enableLed(true);
        white_sensor.enableLed(true);

        robot.leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.rightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        idle();

        robot.leftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.rightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        telemetry.addData("Path0", "Starting at %7d :%7d",
                robot.leftMotor.getCurrentPosition(),
                robot.rightMotor.getCurrentPosition());
        telemetry.update();

        waitForStart();
        while (opModeIsActive()) {

            //encoderDrive(DRIVE_SPEED/3, 20, 20, 4.0);// S1: Forward 47 Inches with 5 Sec timeout
            //sleep(500);
            //encoderDrive(TURN_SPEED, 16,-16, 4.0);  // S2: Turn Right 12 Inches with 4 Sec timeout

            //sleep(100);
        /*robot.armMotor.setPower(-1);
        robot.leftMotor.setPower(0);
        robot.rightMotor.setPower(0);
        sleep(500);
        robot.armMotor.setPower(1);
        sleep(800);
        robot.armMotor.setPower(0);
        sleep(500);
        robot.armMotor.setPower(-1);
        sleep(800);
        robot.armMotor.setPower(0);*/
            //encoderDrive(DRIVE_SPEED, 24, 24, 4.0);
            //sleep(500);
            //encoderDrive(TURN_SPEED, 37, -37, 4.0);
            //sleep(100);

           encoderDrive(DRIVE_SPEED/3, 20, 20, 4.0);
            sleep(1000);//TEMP

            // *MAKE SURE TO ADD THIS BACK IN* \\
            robot.armMotor.setPower(-1);
            robot.leftMotor.setPower(0);
            robot.rightMotor.setPower(0);
            sleep(500);
            robot.armMotor.setPower(1);
            sleep(800);
            robot.armMotor.setPower(0);
            sleep(500);
            robot.armMotor.setPower(-1);
            sleep(800);
            robot.armMotor.setPower(0);

            //sleep(100);
            encoderDrive(TURN_SPEED/2,43, -43, 10.0);
            //sleep(100);
            encoderDrive(DRIVE_SPEED/2, -100, -100, 3.5);

            encoderDrive(TURN_SPEED/2, 13, -13, 4.0);
            //sleep(100);
            robot.rightMotor.setPower(-.1);
            robot.leftMotor.setPower(-.12);
            sleep(4000);

            if (color_sensor.red() <=200) {
                robot.rightMotor.setPower(.1);
                robot.leftMotor.setPower(.12);
                sleep(100);
                while (color_sensor.red() >= 230) {
                    robot.rightMotor.setPower(0);
                    robot.leftMotor.setPower(0);


                    if (color_sensor.red() >= 230) {

                        robot.colorMotor.setPower(-1);
                        sleep(800);
                        robot.colorMotor.setPower(1);
                        sleep(800);
                        robot.rightMotor.setPower(.1);
                        robot.leftMotor.setPower(.1);
                        robot.colorMotor.setPower(0);
                        sleep(1500);
                    } else {
                        robot.colorMotor.setPower(0);
                    }
                }
            }
            sleep(8000);
            /*while (color_sensor.red() >= 230) {
                robot.rightMotor.setPower(0);
                robot.leftMotor.setPower(0);


                if (color_sensor.red() >= 230) {

                    robot.colorMotor.setPower(-1);
                    sleep(800);
                    robot.colorMotor.setPower(1);
                    sleep(800);
                    robot.rightMotor.setPower(.1);
                    robot.leftMotor.setPower(.1);
                    robot.colorMotor.setPower(0);
                    sleep(1500);
                } else {
                    robot.colorMotor.setPower(0);
                }
                /*if (color_sensor.green() >= ) {
                    robot.colorMotor.setPower(0);
                    sleep(1250);
                    robot.colorMotor.setPower(0);
                    sleep(1250);
                    robot.rightMotor.setPower(.1);
                    robot.leftMotor.setPower(.1);
                    sleep(1500);
                } else {
                    robot.colorMotor.setPower(0);
                }*/
        /*
            }
            sleep(8000);*/

        /*robot.colorMotor.setPower(-1);
        sleep(1250);
        robot.colorMotor.setPower(1);
        sleep(500);
        robot.colorMotor.setPower(0);
        sleep(4000);
        if (color_sensor.red() >= color_sensor.green()) {

            robot.colorMotor.setPower(-1);
            sleep(1250);
            robot.colorMotor.setPower(1);
            sleep(1250);
        }
        else {
            robot.colorMotor.setPower(0);
        }
        if (color_sensor.green() >= color_sensor.red()) {
            robot.colorMotor.setPower(0);
            sleep(1250);
            robot.colorMotor.setPower(0);
            sleep(1250);
        }
        else {
            robot.colorMotor.setPower(0);
        }
        sleep(3000);*/
            encoderDrive(DRIVE_SPEED/3, -30, 30, 4.0);
            sleep(2000);






            Color.RGBToHSV(color_sensor.red() * 8, color_sensor.green() * 8, color_sensor.blue() * 8, hsvValues);

            telemetry.addData("2 Clear", white_sensor.alpha());
            telemetry.addData("3 Red  ", color_sensor.red());
            telemetry.addData("4 Green", color_sensor.green());
            telemetry.addData("5 Blue ", color_sensor.blue());
            telemetry.addData("6 Hue", hsvValues[0]);

            idle();
        }


    }

    public void encoderDrive(double speed,
                             double leftInches, double rightInches,
                             double timeoutS) {
        int newLeftTarget;
        int newRightTarget;

        // Ensure that the opmode is still active
        if (opModeIsActive()) {

            // Determine new target position, and pass to motor controller
            newLeftTarget = robot.leftMotor.getCurrentPosition() + (int) (leftInches * COUNTS_PER_INCH);
            newRightTarget = robot.rightMotor.getCurrentPosition() + (int) (rightInches * COUNTS_PER_INCH);
            robot.leftMotor.setTargetPosition(newLeftTarget);
            robot.rightMotor.setTargetPosition(newRightTarget);

            // Turn On RUN_TO_POSITION
            robot.leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.rightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // reset the timeout time and start motion.
            runtime.reset();
            robot.leftMotor.setPower(Math.abs(speed));
            robot.rightMotor.setPower(Math.abs(speed));

            // keep looping while we are still active, and there is time left, and both motors are running.
            while (opModeIsActive() &&
                    (runtime.seconds() < timeoutS) &&
                    (robot.leftMotor.isBusy() && robot.rightMotor.isBusy())) {

                // Display it for the driver.
                telemetry.addData("Path1", "Running to %7d :%7d", newLeftTarget, newRightTarget);
                telemetry.addData("Path2", "Running at %7d :%7d",
                        robot.leftMotor.getCurrentPosition(),
                        robot.rightMotor.getCurrentPosition());
                telemetry.update();
            }

            // Stop all motion;
            robot.leftMotor.setPower(0);
            robot.rightMotor.setPower(0);

            // Turn off RUN_TO_POSITION
            robot.leftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.rightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            //  sleep(250);   // optional pause after each move
        }

    }
}



