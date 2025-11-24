package org.firstinspires.ftc.teamcode.Pedro;

import com.pedropathing.follower.Follower;
import com.pedropathing.follower.FollowerConstants;
import com.pedropathing.ftc.FollowerBuilder;
import com.pedropathing.ftc.drivetrains.MecanumConstants;
import com.pedropathing.ftc.localization.Encoder;
import com.pedropathing.ftc.localization.constants.ThreeWheelConstants;
import com.pedropathing.localization.Localizer;
import com.pedropathing.paths.PathConstraints;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Constants {
    public static FollowerConstants followerConstants = new FollowerConstants().mass(8.8)
            .forwardZeroPowerAcceleration(-42.65)
            .lateralZeroPowerAcceleration(-61.12)
            .centripetalScaling(0.005);

    public static MecanumConstants driveConstants = new MecanumConstants()
            .maxPower(1)
            .rightFrontMotorName("FR")
            .rightRearMotorName("BR")
            .leftRearMotorName("BL")
            .leftFrontMotorName("FL")
            .leftFrontMotorDirection(DcMotorSimple.Direction.REVERSE)
            .leftRearMotorDirection(DcMotorSimple.Direction.REVERSE)
            .rightFrontMotorDirection(DcMotorSimple.Direction.REVERSE)
            .rightRearMotorDirection(DcMotorSimple.Direction.FORWARD)
            .xVelocity(69)
            .yVelocity(39);

    public static PathConstraints pathConstraints = new PathConstraints(0.99, 100, 1, 1);

    public static Follower createFollower(HardwareMap hardwareMap) {
        Localizer VisionOdometryLocalizer = new VisionOdometryLocalizer(hardwareMap, localizerConstants, hardwareMap.get(Limelight3A.class, "limelight3A"), 1.0);
        return new FollowerBuilder(followerConstants, hardwareMap)
                .pathConstraints(pathConstraints)
                .mecanumDrivetrain(driveConstants)
                .threeWheelLocalizer(localizerConstants)
                .setLocalizer(VisionOdometryLocalizer)
                .build();
    }
    public static ThreeWheelConstants localizerConstants = new ThreeWheelConstants()
            .forwardTicksToInches(.0022)
            .strafeTicksToInches(.0022)
            .turnTicksToInches(.0033)
            .leftPodY(2.5)
            .rightPodY(-2.5)
            .strafePodX(-4.5)
            //.leftPodX(-4.5)
            //.rightPodX(-4.5)
            //.strafePodY(0.)
            .leftEncoder_HardwareMapName("leftFront")
            .rightEncoder_HardwareMapName("rightRear")
            .strafeEncoder_HardwareMapName("rightFront")
            .leftEncoderDirection(Encoder.REVERSE)
            .rightEncoderDirection(Encoder.REVERSE)
            .strafeEncoderDirection(Encoder.REVERSE)
            .forwardTicksToInches(0.0022)
            .strafeTicksToInches(.0022)
            .turnTicksToInches(0.0033);
}