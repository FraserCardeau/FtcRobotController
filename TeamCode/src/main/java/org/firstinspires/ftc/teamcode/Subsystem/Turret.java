package org.firstinspires.ftc.teamcode.Subsystem;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.controller.PIDController;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;

public class Turret extends SubsystemBase {
    MotorEx turret;
    PIDController turretPID = new PIDController(0, 0, 0);
    public Turret(final HardwareMap hwMap, final String name) {
        turret = hwMap.get(MotorEx.class, name);
    }
    public void setPosition(double position){
        turretPID.setSetPoint(position);
    }
    public double getCurrentPosition(){ // REMINDER: tune this later
        return turret.getCurrentPosition();
    }
    @Override
    public void periodic(){
        turret.set(turretPID.calculate(turret.getCurrentPosition()));
    }
}