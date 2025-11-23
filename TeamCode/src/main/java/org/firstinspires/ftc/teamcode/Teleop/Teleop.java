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
    ActionManager actionManager = new ActionManager();
    Hardware hw = new Hardware(hardwareMap);
    DcMotorEx frontLeft = hw.frontLeft, frontRight = hw.frontRight, backLeft = hw.backLeft, backRight = hw.backRight, intakeMotor = hw.intakeMotor, shooterMotor = hw.shooterMotor;
    Servo ballKick = hw.ballKick, hood = hw.hood;
    public static double bp = 0.03, bd = 0.0, bf = 0.0, sp = 0.01, sd = 0.0001, sf = 0.0;
    double pSwitch = 50;
    MotorPID motorPID = new MotorPID(shooterMotor, new PIDFCoefficients(bp, 0, bd, bf), new PIDFCoefficients(sp, 0, sd, sf), pSwitch);
    @Override
    public void runOpMode() {
        shooterMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        waitForStart();
        while (opModeIsActive()) {
            motorPID.update(gamepad1.right_trigger * 1150);

            telemetry.addData("velocity", motorPID.currentVel);

            double y = -gamepad1.left_stick_y;
            double x = gamepad1.left_stick_x;
            double rx = gamepad1.right_stick_x;

            frontLeft.setPower(y + x + rx);
            frontRight.setPower(y - x - rx);
            backLeft.setPower(y - x + rx);
            backRight.setPower(y + x - rx);

            if (gamepad1.xWasPressed()) {
                if (ballKick.getPosition() != 0.28) {
                    ballKick.setPosition(0.28);
                    intakeMotor.setPower(-1);
                } else {
                    intakeMotor.setPower(0);
                    ballKick.setPosition(0.75);
                }
            }
            if (gamepad1.dpadUpWasPressed()) {
                if (intakeMotor.getPower() != -1){
                    intakeMotor.setPower(-1);
                }
                else {
                    intakeMotor.setPower(0);
                }
            }
            if (gamepad1.dpad_right ) {
                hood.setPosition(0.24);
            }
            telemetry.update();
        }
    }
}