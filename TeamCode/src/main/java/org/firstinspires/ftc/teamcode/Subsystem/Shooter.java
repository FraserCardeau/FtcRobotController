package org.firstinspires.ftc.teamcode.Subsystem;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.controller.PIDController;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;

public class Shooter extends SubsystemBase {
    MotorEx shooter;
    PIDController shooterPID = new PIDController(0, 0, 0);
    public Shooter(final HardwareMap hwMap, final String name) {
        shooter = hwMap.get(MotorEx.class, name);
    }
    public void setVelocity(double ticksPerSecond){
        shooterPID.setSetPoint(ticksPerSecond);
    }
    public double getCurrentVelocity(){
        return shooter.getVelocity();
    }
    @Override
    public void periodic(){
        shooter.setVelocity(shooterPID.calculate(shooter.getVelocity()));
    }
}
