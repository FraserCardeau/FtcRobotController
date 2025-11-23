package org.firstinspires.ftc.teamcode.Util;

import com.pedropathing.control.PIDFCoefficients;
import com.pedropathing.control.PIDFController;
import com.qualcomm.robotcore.hardware.DcMotorEx;

public class MotorPID {
    DcMotorEx motor;
    PIDFController b, s;
    PIDFCoefficients bConstants, sConstants;
    public double targetVel, pSwitch, currentVel;
    public MotorPID(DcMotorEx motor, PIDFCoefficients b, PIDFCoefficients s, double pSwitch) {
        this.motor = motor;
        this.bConstants = b;
        this.sConstants = s;
        this.pSwitch = pSwitch;
    }
    public void update(){
        currentVel = motor.getVelocity();
        b.setCoefficients(bConstants);
        s.setCoefficients(sConstants);

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
