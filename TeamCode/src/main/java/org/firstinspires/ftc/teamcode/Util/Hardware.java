package org.firstinspires.ftc.teamcode.Util;
import static com.qualcomm.robotcore.hardware.DcMotorSimple.Direction.FORWARD;
import static com.qualcomm.robotcore.hardware.DcMotorSimple.Direction.REVERSE;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;
import static org.firstinspires.ftc.teamcode.Pedro.Constants.driveConstants;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Constants.Motors;

public class Hardware {
    public DcMotorEx frontLeft, frontRight, backLeft, backRight, intakeMotor, shooterMotor;
    public Servo ballKick, hood;
    public Hardware(HardwareMap hw){
        frontLeft = hw.get(DcMotorEx.class, driveConstants.leftFrontMotorName);
        frontLeft.setDirection(REVERSE);
        frontRight = hw.get(DcMotorEx.class, driveConstants.rightFrontMotorName);
        frontLeft.setDirection(REVERSE);
        backLeft = hw.get(DcMotorEx.class, driveConstants.leftRearMotorName);
        backLeft.setDirection(REVERSE);
        backRight = hw.get(DcMotorEx.class, driveConstants.rightRearMotorName);
        backLeft.setDirection(FORWARD);
        intakeMotor = hw.get(DcMotorEx.class, "intake");
        shooterMotor = hw.get(DcMotorEx.class, "shooter");
        shooterMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        ballKick = hw.get(Servo.class,"ballKick");
        hood = hw.get(Servo.class,"hood");
    }
}
