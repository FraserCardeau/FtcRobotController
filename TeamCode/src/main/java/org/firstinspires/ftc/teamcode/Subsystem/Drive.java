package org.firstinspires.ftc.teamcode.Subsystem;

import com.pedropathing.follower.Follower;
import com.pedropathing.ftc.localization.constants.PinpointConstants;
import com.pedropathing.geometry.Pose;
import com.pedropathing.localization.Localizer;
import com.pedropathing.paths.Path;
import com.pedropathing.paths.PathChain;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;
import org.firstinspires.ftc.teamcode.Pedro.Constants;
import org.firstinspires.ftc.teamcode.Pedro.VisionOdometryLocalizer;

public class Drive extends SubsystemBase {
    GamepadEx gamepadEx;
    Limelight3A limelight;
    public Follower follower;
    Telemetry telemetry;
    Pose pose;
    boolean isAuton = true;
    public Drive(HardwareMap hw, final GamepadEx gamepadEx, Telemetry telemetry, Turret turret){
        this.gamepadEx = gamepadEx;
        follower = Constants.createFollower(hw);
        follower.startTeleopDrive(true);
        this.isAuton = false;
        limelight = hw.get(Limelight3A.class, "limelight3A");
    }
    public Drive(HardwareMap hw, Telemetry telemetry, Turret turret){
        follower = Constants.createFollower(hw);
        this.isAuton = true;
    }
    public Pose getPose(){
        follower.update();
        return follower.getPose();
    }
    public void holdPoint(){
        follower.holdPoint(follower.getPose());
    }
    public void teleopDrive(){
        follower.startTeleopDrive();
    }
    public void followPath(Path path){
        follower.followPath(path, true);
    }
    public void followPath(PathChain path){
        follower.followPath(path, true);
    }
    @Override
    public void periodic(){
        if (!isAuton){
            follower.setTeleOpDrive(gamepadEx.getLeftY(), -gamepadEx.getLeftX(), -gamepadEx.getRightX());
        }
        pose = follower.getPose();
        follower.update();
    }

    public void setPose(Pose pose) {
        follower.setPose(pose);
        this.pose = pose;
    }
}
