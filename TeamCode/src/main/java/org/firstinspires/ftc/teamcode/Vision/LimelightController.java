package org.firstinspires.ftc.teamcode.Vision;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLStatus;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.sun.tools.javac.code.Attribute;

import java.lang.reflect.Array;

public class LimelightController {
    public double tx, ty, ta;
    Limelight3A limelight;
    LLResult result;
    public LLStatus status;
    public LimelightController(HardwareMap hw){
        limelight = hw.get(Limelight3A.class, "limelight3A");
        limelight.setPollRateHz(100);
        limelight.pipelineSwitch(0);
        limelight.start();
    }
    public void update(){
        limelight.reloadPipeline();
        status = limelight.getStatus();
        result = limelight.getLatestResult();
        tx = result.getTx();
        ty = result.getTy();
        ta = result.getTa();
    }
}
