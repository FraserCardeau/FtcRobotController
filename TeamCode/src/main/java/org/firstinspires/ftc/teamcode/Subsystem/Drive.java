package org.firstinspires.ftc.teamcode.Subsystem;

import com.pedropathing.follower.Follower;
import com.pedropathing.ftc.localization.constants.PinpointConstants;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;

import org.firstinspires.ftc.teamcode.Pedro.VisionOdometryLocalizer;

public class Drive extends SubsystemBase {
    Follower follower;
    GamepadEx gamepadEx;
    public Drive(final Follower follower, final GamepadEx gamepadEx){
        this.follower = follower;
        this.gamepadEx = gamepadEx;
    }
    public void teleopDrive(){
        follower.startTeleopDrive();
    }
    @Override
    public void periodic(){
        follower.setTeleOpDrive(-Math.pow(gamepadEx.getLeftX(), 1.5), -Math.pow(gamepadEx.getLeftY(), 1.5), -Math.pow(gamepadEx.getRightX(), 1.5), true);
    }
}
