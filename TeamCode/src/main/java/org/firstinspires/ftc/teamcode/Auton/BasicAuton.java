package org.firstinspires.ftc.teamcode.Auton;

import com.pedropathing.ftc.localization.constants.ThreeWheelConstants;
import com.pedropathing.ftc.localization.localizers.ThreeWheelLocalizer;
import com.pedropathing.geometry.Pose;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Pedro.VisionOdometryLocalizer;

@Autonomous
public class BasicAuton extends LinearOpMode {
    @Override
    public void runOpMode(){
        Limelight3A limelight = hardwareMap.get(Limelight3A.class, "limelight3A");
        ThreeWheelConstants constants = new ThreeWheelConstants();
        ThreeWheelLocalizer localizer = new ThreeWheelLocalizer(
                hardwareMap,
                constants
        );
        Pose startPose = new Pose(0, 0, 0);
        localizer.setStartPose(startPose);
        waitForStart();
        while(opModeIsActive()){
            localizer.update();
            Pose currentPose = localizer.getPose();
            telemetry.addData("X", currentPose.getX());
            telemetry.addData("Y", currentPose.getY());
            telemetry.addData("Heading (deg)", Math.toDegrees(currentPose.getHeading()));
            telemetry.update();
        }
    }
}
