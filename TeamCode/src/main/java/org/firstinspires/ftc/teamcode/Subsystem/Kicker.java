package org.firstinspires.ftc.teamcode.Subsystem;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;
import com.seattlesolvers.solverslib.hardware.servos.ServoEx;

public class Kicker extends SubsystemBase {
    ServoEx kicker;
    public Kicker(final HardwareMap hwMap, final String name){
        kicker = hwMap.get(ServoEx.class, name);
    }
    public void setPosition(double position){
        kicker.set(position);
    }
}