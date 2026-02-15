package org.firstinspires.ftc.teamcode.Subsystem;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.controller.PIDController;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;
import com.seattlesolvers.solverslib.hardware.servos.ServoEx;

public class Turret extends SubsystemBase {
    public ServoEx turret;
    public Turret(final HardwareMap hwMap, final String name) {
        turret = new ServoEx(hwMap, name, 0, 2);
    }
    public void setPosition(double position){
        turret.set(((position / 180) + 1) * ((360 * 0.869) / 360));
    }
    public double getCurrentPosition(){
        return (turret.get() - 1) * 180;
    }
}