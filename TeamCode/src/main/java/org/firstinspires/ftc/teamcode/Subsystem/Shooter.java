package org.firstinspires.ftc.teamcode.Subsystem;

import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.ServoImplEx;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.controller.PIDController;
import com.seattlesolvers.solverslib.controller.PIDFController;
import com.seattlesolvers.solverslib.hardware.SimpleServo;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;
import com.seattlesolvers.solverslib.hardware.servos.ServoEx;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Shooter extends SubsystemBase {
    public MotorEx shooter;
    PIDFController shooterPID = new PIDFController(0.001, 0, 0.000001, 0.01);
    public Shooter(final HardwareMap hwMap, final String name, Telemetry telemetry) {
        shooter = new MotorEx(hwMap, name);
    }
    public void setVelocity(double ticksPerSecond){
        shooterPID.setSetPoint(ticksPerSecond);
    }
    public double getCurrentVelocity(){
        return shooter.getVelocity();
    }
    @Override
    public void periodic(){
        shooter.set(shooterPID.calculate(shooter.getVelocity()));
    }
}
