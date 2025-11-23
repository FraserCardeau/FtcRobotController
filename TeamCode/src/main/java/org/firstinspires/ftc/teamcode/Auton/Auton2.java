package org.firstinspires.ftc.teamcode.Auton;

import static org.firstinspires.ftc.teamcode.Pedro.Constants.driveConstants;
import static org.firstinspires.ftc.teamcode.Pedro.Constants.followerConstants;
import static org.firstinspires.ftc.teamcode.Pedro.Tuning.follower;

import com.pedropathing.Drivetrain;
import com.pedropathing.follower.Follower;
import com.pedropathing.ftc.localization.constants.ThreeWheelConstants;
import com.pedropathing.geometry.Pose;
import com.pedropathing.localization.Localizer;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.sun.tools.javac.util.List;

import org.firstinspires.ftc.teamcode.Pedro.Constants;
import org.firstinspires.ftc.teamcode.Pedro.VisionOdometryLocalizer;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;

public class Auton2 extends LinearOpMode {
    @Override
    public void runOpMode(){
        Limelight3A limelight = hardwareMap.get(Limelight3A.class, "limelight3A");
        ThreeWheelConstants constants = new ThreeWheelConstants();
        Localizer localizer = new VisionOdometryLocalizer(hardwareMap, constants, limelight, 0.8);
        Follower drive = new Follower(followerConstants, localizer, follower.drivetrain);
        Pose startPose = new Pose(0, 0, Math.toRadians(0));
        drive.setPose(startPose);
    }
}
