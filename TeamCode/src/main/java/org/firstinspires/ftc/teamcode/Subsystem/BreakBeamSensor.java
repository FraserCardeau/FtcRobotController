package org.firstinspires.ftc.teamcode.Subsystem;

import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;

public class BreakBeamSensor extends SubsystemBase {
    public DigitalChannel breakBeamSensor;
    public int artifactCount = 0;
    boolean hasBeamBroken;
    public BreakBeamSensor(HardwareMap hwMap, String name){
        this.breakBeamSensor = hwMap.get(DigitalChannel.class, name);
        breakBeamSensor.setMode(DigitalChannel.Mode.INPUT);
    }
    public boolean isBeamBroken() {
        return breakBeamSensor != null && !breakBeamSensor.getState();
    }
    @Override
    public void periodic(){
        if (isBeamBroken() && !hasBeamBroken){
            hasBeamBroken = true;
            artifactCount++;
        } else if (!isBeamBroken() && hasBeamBroken) {
            hasBeamBroken = false;
        }
    }
}
