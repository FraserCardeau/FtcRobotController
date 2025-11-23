package org.firstinspires.ftc.teamcode.Util;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;
import static org.firstinspires.ftc.teamcode.Pedro.Constants.driveConstants;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Constants.Motors;

public class Hardware {
    public DcMotorEx frontLeft, frontRight, backLeft, backRight, intakeMotor, shooterMotor;
    public Servo ballKick, hood;
    public Hardware(HardwareMap hw){
        frontLeft = hardwareMap.get(DcMotorEx.class, driveConstants.leftFrontMotorName);
        frontRight = hardwareMap.get(DcMotorEx.class, driveConstants.rightFrontMotorName);
        backLeft = hardwareMap.get(DcMotorEx.class, driveConstants.leftRearMotorName);
        backRight = hardwareMap.get(DcMotorEx.class, driveConstants.rightRearMotorName);
        intakeMotor = hardwareMap.get(DcMotorEx.class, "intake");
        shooterMotor = hardwareMap.get(DcMotorEx.class, "shooter");
        shooterMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        ballKick = hardwareMap.get(Servo.class,("ballKick"));
        hood = hardwareMap.get(Servo.class,("hood"));
    }
}
