package org.firstinspires.ftc.teamcode.Pedro;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

import com.pedropathing.ftc.localization.constants.PinpointConstants;
import com.pedropathing.ftc.localization.localizers.PinpointLocalizer;
import com.pedropathing.ftc.localization.localizers.ThreeWheelLocalizer;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.pedropathing.ftc.localization.constants.ThreeWheelConstants;
import com.pedropathing.geometry.Pose;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;
import org.firstinspires.ftc.teamcode.Subsystem.Turret;

public class VisionOdometryLocalizer extends PinpointLocalizer {
    private final double M_TO_IN = 39.37007874;
    Pose3D bp;
    Turret turret;
    Limelight3A limelight;
    public VisionOdometryLocalizer(HardwareMap map, PinpointConstants constants, Limelight3A limelight, Turret turret) {
        super(map, constants);
        this.limelight = limelight;
        this.turret = turret;
        limelight.start();
    }

    @Override
    public void update() {
        super.update();
        LLResult result = limelight.getLatestResult();
        if (result.isValid() && result != null){
            bp = result.getBotpose_MT2();
            double vx = bp.getPosition().x * M_TO_IN;
            double vy = bp.getPosition().y * M_TO_IN;
            double vYawDeg = bp.getOrientation().getYaw() + turret.getCurrentPosition();
            double vHeading = Math.toRadians(vYawDeg);
            this.setPose(new Pose(vx, vy, Math.atan2(sin(vHeading), cos(vHeading))));
        }
    }
}