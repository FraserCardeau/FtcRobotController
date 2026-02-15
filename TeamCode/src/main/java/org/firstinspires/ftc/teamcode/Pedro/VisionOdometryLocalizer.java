package org.firstinspires.ftc.teamcode.Pedro;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

import com.pedropathing.ftc.localization.constants.PinpointConstants;
import com.pedropathing.localization.Localizer;
import com.pedropathing.math.Vector;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.pedropathing.geometry.Pose;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.hardware.limelightvision.LLResult;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;
import org.firstinspires.ftc.teamcode.Subsystem.Turret;

public class VisionOdometryLocalizer implements Localizer {
    private final double M_TO_IN = 39.37007874;
    Pose bp;
    Pose3D llPose;
    Turret turret;
    Limelight3A limelight;
    double vHeading, vx, vy;
    public VisionOdometryLocalizer(HardwareMap map, PinpointConstants constants, Limelight3A limelight) {
        this.limelight = limelight;
        this.turret = turret;
        limelight.pipelineSwitch(0);
        limelight.start();
        limelight.setPollRateHz(15);
    }

    @Override
    public Pose getPose() {
        return bp;
    }

    @Override
    public Pose getVelocity() {
        return null;
    }

    @Override
    public Vector getVelocityVector() {
        return null;
    }

    @Override
    public void setStartPose(Pose setStart) {

    }

    @Override
    public void setPose(Pose setPose) {
        bp = setPose.getPose();
    }

    @Override
    public void update() {
        LLResult result = limelight.getLatestResult();
        if (result.isValid() && result != null){
            llPose = result.getBotpose_MT2();
            vx = llPose.getPosition().x * M_TO_IN + 72;
            vy = llPose.getPosition().y * M_TO_IN + 72;
            vHeading = (Math.PI / 2) - llPose.getOrientation().getYaw(AngleUnit.RADIANS);
            vHeading = Math.atan2(Math.sin(vHeading), Math.cos(vHeading));
            this.setPose(new Pose(vx, vy, vHeading));
        }
    }

    @Override
    public double getTotalHeading() {
        return 0;
    }

    @Override
    public double getForwardMultiplier() {
        return 0;
    }

    @Override
    public double getLateralMultiplier() {
        return 0;
    }

    @Override
    public double getTurningMultiplier() {
        return 0;
    }
    @Override
    public void resetIMU() throws InterruptedException {

    }
    @Override
    public double getIMUHeading() {
        return 0;
    }

    @Override
    public boolean isNAN() {
        return false;
    }
    @Override
    public void setHeading(double heading){
        limelight.updateRobotOrientation(((Math.toDegrees(heading) + 180) % 360 + 360) % 360 - 180);
    }
}