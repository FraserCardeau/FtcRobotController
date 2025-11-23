package org.firstinspires.ftc.teamcode.Pedro;

import com.pedropathing.ftc.localization.localizers.ThreeWheelLocalizer;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.pedropathing.ftc.localization.constants.ThreeWheelConstants;
import com.pedropathing.geometry.Pose;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.hardware.limelightvision.LLResult;
import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;

public class VisionOdometryLocalizer extends ThreeWheelLocalizer {
    private final Limelight3A limelight;
    private final double visionWeight;
    private final double M_TO_IN = 39.37007874;
    public VisionOdometryLocalizer(HardwareMap map, ThreeWheelConstants constants,
                                   Limelight3A limelight, double visionWeight) {
        super(map, constants);
        this.limelight = limelight;
        this.visionWeight = Math.max(0.0, Math.min(1.0, visionWeight));
        this.limelight.setPollRateHz(60);
        this.limelight.start();
    }
    @Override
    public void update() {
        super.update();
        LLResult result = limelight.getLatestResult();
        if (result != null && result.isValid()) {
            Pose3D bp = result.getBotpose_MT2();
            if (bp != null) {
                double vx = bp.getPosition().x * M_TO_IN;
                double vy = bp.getPosition().y * M_TO_IN;
                double vYawDeg = bp.getOrientation().getYaw();
                double vHeading = Math.toRadians(vYawDeg);
                this.setPose(new Pose(vx, vy, normalizeAngle(vHeading)));
            }
        }
    }

    private double normalizeAngle(double a) {
        while (a <= -Math.PI) a += 2.0*Math.PI;
        while (a > Math.PI) a -= 2.0*Math.PI;
        return a;
    }
}