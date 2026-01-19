package org.firstinspires.ftc.teamcode.Subsystem;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;

public class Intake extends SubsystemBase {
    public MotorEx intake;
    public Intake(final HardwareMap hwMap, final String name){
        this.intake = new MotorEx(hwMap, name);
    }
    public void enable(){
        intake.set(-0.5);
    }
    public void slowEnable(){
        intake.set(-0.1);
    }
    public void disable(){
        intake.set(0);
    }
}