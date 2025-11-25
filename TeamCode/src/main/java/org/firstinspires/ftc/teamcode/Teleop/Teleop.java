package org.firstinspires.ftc.teamcode.Teleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.pedropathing.control.PIDFCoefficients;
import com.pedropathing.control.PIDFController;

import org.firstinspires.ftc.teamcode.Util.ActionManager;
import org.firstinspires.ftc.teamcode.Util.Hardware;
import org.firstinspires.ftc.teamcode.Util.MotorPID;

import dev.nextftc.core.commands.CommandManager;

@TeleOp
public class Teleop extends LinearOpMode {
    @Override
    public void runOpMode() {
        Hardware hw = new Hardware(hardwareMap);
        DcMotorEx frontLeft = hw.frontLeft, frontRight = hw.frontRight, backLeft = hw.backLeft, backRight = hw.backRight, intakeMotor = hw.intakeMotor, shooterMotor = hw.shooterMotor;
        Servo ballKick = hw.ballKick, hood = hw.hood;
        shooterMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        waitForStart();
        while (opModeIsActive()) {
            double y = gamepad1.left_stick_x;
            double x = -gamepad1.left_stick_y;
            double rx = gamepad1.right_stick_x;

            frontLeft.setPower(y + x + rx);
            frontRight.setPower(y - x + rx);
            backLeft.setPower(y - x - rx);
            backRight.setPower(y + x - rx);

            if (gamepad1.x) {
                ballKick.setPosition(0.75);
                shooterMotor.setPower(0);
            }
            else{
                ballKick.setPosition(0.28);
                shooterMotor.setPower(-1);
            }
            if (gamepad1.dpadUpWasReleased()) {
                if (Math.abs(intakeMotor.getPower()) - 1 > 0.01){
                    shooterMotor.setPower(-1);
                }
                else {
                    shooterMotor.setPower(0);
                }
            }
            if (gamepad1.dpad_right) {
                hood.setPosition(0.24);
            }

            intakeMotor.setPower(gamepad1.right_trigger);

            telemetry.addData("ballkick", ballKick.getPosition());
            telemetry.update();
        }
    }
}