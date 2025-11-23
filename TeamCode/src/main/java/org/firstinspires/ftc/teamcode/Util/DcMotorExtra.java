package org.firstinspires.ftc.teamcode.Util;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public abstract class DcMotorExtra {
    public String name;
    public DcMotorEx motor;
    public DcMotor.RunMode runMode = DcMotor.RunMode.RUN_WITHOUT_ENCODER;
    public DcMotor.Direction direction = DcMotor.Direction.FORWARD;
    public double power = 0.0;
    public DcMotorExtra(String name) {
        this.name = name;
    }
    public DcMotorExtra init(HardwareMap hw) {
        motor = hw.get(DcMotorEx.class, name);
        motor.setMode(runMode);
        motor.setDirection(direction);
        motor.setPower(power);
        return this;
    }
    public DcMotorExtra setPower(double power) {
        this.power = power;
        if (motor != null) motor.setPower(power);
        return this;
    }
    public DcMotorExtra setRunMode(DcMotor.RunMode runMode) {
        this.runMode = runMode;
        if (motor != null) motor.setMode(runMode);
        return this;
    }
    public DcMotorExtra setDirection(DcMotor.Direction direction) {
        this.direction = direction;
        if (motor != null) motor.setDirection(direction);
        return this;
    }
}