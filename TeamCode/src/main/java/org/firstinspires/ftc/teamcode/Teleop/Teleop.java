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

@TeleOp
public class Teleop extends LinearOpMode {
    public static double bp = 0.03, bd = 0.0, bf = 0.0, sp = 0.01, sd = 0.0001, sf = 0.0;
    double pSwitch = 50;
    MotorPID motorPID;
    boolean wasPressed;
    PIDFCoefficients bConstants, sConstants;
    @Override
    public void runOpMode() {
        bConstants = new PIDFCoefficients(bp, 0, bd, bf);
        sConstants = new PIDFCoefficients(sp, 0, sd, sf);
        Hardware hw = new Hardware(hardwareMap);
        DcMotorEx frontLeft = hw.frontLeft, frontRight = hw.frontRight, backLeft = hw.backLeft, backRight = hw.backRight, intakeMotor = hw.intakeMotor, shooterMotor = hw.shooterMotor;
        Servo ballKick = hw.ballKick, hood = hw.hood;
        shooterMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorPID = new MotorPID(shooterMotor, bConstants, sConstants, pSwitch);
        waitForStart();
        while (opModeIsActive()) {
            motorPID.update(gamepad1.right_trigger * 1150);
            motorPID.update();

            telemetry.addData("velocity", motorPID.currentVel);

            double y = gamepad1.left_stick_x;
            double x = -gamepad1.left_stick_y;
            double rx = gamepad1.right_stick_x;

            frontLeft.setPower(y + x + rx);
            frontRight.setPower(y - x + rx);
            backLeft.setPower(y - x - rx);
            backRight.setPower(y + x - rx);

            if (gamepad1.xWasPressed()) {
                if (Math.abs(ballKick.getPosition() - 0.28) > 0.01) {
                    ballKick.setPosition(0.28);
                    intakeMotor.setPower(-1);
                } else {
                    intakeMotor.setPower(0);
                    ballKick.setPosition(0.75);
                }
            }
            if (gamepad1.dpadUpWasReleased()) {
                if (Math.abs(intakeMotor.getPower() + 1) > 0.01){
                    intakeMotor.setPower(-1);
                    wasPressed = true;
                }
                else {
                    intakeMotor.setPower(0);
                    wasPressed = false;
                }
            }
            if (gamepad1.dpad_right) {
                hood.setPosition(0.24);
            }
            telemetry.update();
        }
    }
}