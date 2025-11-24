package org.firstinspires.ftc.teamcode.Teleop;

import com.bylazar.telemetry.PanelsTelemetry;
import com.bylazar.telemetry.TelemetryManager;
import com.pedropathing.control.PIDFCoefficients;
import com.pedropathing.control.PIDFController;
import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.HeadingInterpolator;
import com.pedropathing.paths.Path;
import com.pedropathing.paths.PathChain;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Pedro.Constants;
import org.firstinspires.ftc.teamcode.Util.Hardware;
import org.firstinspires.ftc.teamcode.Vision.LimelightController;
import java.util.function.Supplier;

@Disabled
@TeleOp
public class Drive extends LinearOpMode {
    DcMotor ExtentionMotor = null;
    DcMotor IntakeMotor = null;
    DcMotor ShooterMotor = null;
    Servo ballKick = null;
    Servo hood = null;
    private PIDFController b, s;

    private double t = 0;
    public static double bp = 0.03, bd = 0.0, bf = 0.0, sp = 0.01, sd = 0.0001, sf = 0.0;
    double targetvel = 1150;
    double pSwitch = 50;
    private Follower follower;
    public static Pose startingPose;
    private boolean automatedDrive;
    private Supplier<PathChain> pathChain;
    private TelemetryManager telemetryM;
    private Hardware hw;
    private LimelightController limel;
    @Override
    public void runOpMode(){
        follower = Constants.createFollower(hardwareMap);
        follower.setStartingPose(startingPose == null ? new Pose() : startingPose);
        follower.update();
        telemetryM = PanelsTelemetry.INSTANCE.getTelemetry();
        pathChain = () -> follower.pathBuilder() //Lazy Curve Generation
                .addPath(new Path(new BezierLine(follower::getPose, new Pose(45, 98))))
                .setHeadingInterpolation(HeadingInterpolator.linearFromPoint(follower::getHeading, Math.toRadians(45), 0.8))
                .build();
        IntakeMotor = hardwareMap.dcMotor.get("intake");
        ShooterMotor = hardwareMap.dcMotor.get("shooter");
        ShooterMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        ballKick = hardwareMap.get(Servo.class,("ballKick"));
        hood = hardwareMap.get(Servo.class,("hood"));
        b = new PIDFController(new PIDFCoefficients(bp, 0, bd, bf));
        s = new PIDFController(new PIDFCoefficients(sp, 0, sd, sf));
        waitForStart();
        follower.startTeleopDrive();
        while (opModeIsActive()){
            follower.update();
            telemetryM.update();
            follower.setTeleOpDrive(
                    -gamepad1.left_stick_y,
                    -gamepad1.left_stick_x,
                    -gamepad1.right_stick_x,
                    true);
            if(gamepad1.dpad_left) {
                hood.setPosition(0.4);
                targetvel = 1150;
            }
            if(gamepad1.xWasPressed()){
                ballKick.setPosition(0.28);
                IntakeMotor.setPower(-1.);
            }
            else if (gamepad1.xWasReleased()) {
                IntakeMotor.setPower(0.);
                ballKick.setPosition(0.75);
            }
        }
    }
}