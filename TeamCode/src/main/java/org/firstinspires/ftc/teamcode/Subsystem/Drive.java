package org.firstinspires.ftc.teamcode.Subsystem;

import com.pedropathing.follower.Follower;
import com.pedropathing.ftc.localization.constants.PinpointConstants;
import com.pedropathing.geometry.Pose;
import com.pedropathing.localization.Localizer;
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
    public Follower follower;
    Pose pose;
    Telemetry telemetry;
    public Drive(HardwareMap hw, final GamepadEx gamepadEx, Telemetry telemetry, Turret turret){
        this.gamepadEx = gamepadEx;
        follower = Constants.createFollower(hw, turret);
        follower.startTeleopDrive(true);
    }
    public Drive(HardwareMap hw, Telemetry telemetry, Turret turret){
        follower = Constants.createFollower(hw, turret);
        follower.startTeleopDrive(true);
    }
    public Pose getPose(){
        return follower.getPose();
    }
    public void holdPoint(){
        follower.holdPoint(follower.getPose());
    }
    public void teleopDrive(){
        follower.startTeleopDrive();
    }
    @Override
    public void periodic(){
        follower.setTeleOpDrive(gamepadEx.getLeftY(), -gamepadEx.getLeftX(), -gamepadEx.getRightX());
        pose = follower.getPose();
        follower.update();
    }
}
