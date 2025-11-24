package org.firstinspires.ftc.teamcode.Util;

import com.pedropathing.control.PIDFCoefficients;
import com.pedropathing.control.PIDFController;
import com.qualcomm.robotcore.hardware.DcMotorEx;

public class MotorPID {
    DcMotorEx motor;
    PIDFCoefficients bConstants, sConstants;
    public double targetVel, pSwitch, currentVel;
    PIDFController b, s;
    public MotorPID(DcMotorEx motor, PIDFCoefficients bConstants, PIDFCoefficients sConstants, double pSwitch) {
        this.motor = motor;
        this.bConstants = bConstants;
        this.sConstants = sConstants;
        this.pSwitch = pSwitch;
        this.b = new PIDFController(bConstants);
        this.s = new PIDFController(sConstants);
    }
    public void update(){
        currentVel = motor.getVelocity();
        if (Math.abs(targetVel - currentVel) < pSwitch) {
            s.updateError(targetVel - currentVel);
            motor.setPower(s.run());
        } else {
            b.updateError(targetVel - currentVel);
            motor.setPower(b.run());
        }
    }
    public void update(double targetVel){
        this.targetVel = targetVel;
        this.update();
    }
}
