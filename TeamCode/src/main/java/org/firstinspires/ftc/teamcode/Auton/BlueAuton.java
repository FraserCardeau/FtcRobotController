package org.firstinspires.ftc.teamcode.Auton;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.Path;
import com.pedropathing.paths.PathChain;
import com.seattlesolvers.solverslib.command.Command;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.command.CommandScheduler;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;

import org.firstinspires.ftc.teamcode.Subsystem.BreakBeamSensor;
import org.firstinspires.ftc.teamcode.Subsystem.Drive;
import org.firstinspires.ftc.teamcode.Subsystem.Intake;
import org.firstinspires.ftc.teamcode.Subsystem.Kicker;
import org.firstinspires.ftc.teamcode.Subsystem.Shooter;
import org.firstinspires.ftc.teamcode.Subsystem.Turret;

public class BlueAuton extends CommandOpMode {
    Command driveCommand;
    Turret turret;
    Drive drive;
    Kicker kicker;
    Intake intake;
    Shooter shooter;
    BreakBeamSensor breakBeamSensor;
    Follower follower;
    @Override
    public void initialize() {
        CommandScheduler.getInstance().enable();
        drive = new Drive(hardwareMap, telemetry, turret);
    }
    @Override
    public void run(){
        super.run();
    }
    // all the paths (copied from old auton)
    private final Pose startPose = new Pose(25, 130, Math.toRadians(235));

    private final Pose scorePose  = new Pose(54, 90, Math.toRadians(180)); // preload score
    private final Pose scorePose1 = new Pose(54, 90, Math.toRadians(180)); // normal shots
    private final Pose scorePose2 = new Pose(54, 90, Math.toRadians(180)); // last shots

    // Park
    private final Pose Park = new Pose(32, 96, Math.toRadians(140));

    // Lane 1
    private final Pose pickup1Pose_lane1 = new Pose(48, 90, Math.toRadians(180));
    private final Pose pickup2Pose_lane1 = new Pose(23, 90, Math.toRadians(180));
    private final Pose pickup3Pose_lane1 = new Pose(20, 90, Math.toRadians(180));

    // --------- FIXED NAMES: Gate Open POSES (no longer collide with PathChains) ---------
    private final Pose gateOpenPose1 = new Pose(23, 90, Math.toRadians(180));
    private final Pose gateOpenPose2 = new Pose(20, 84, Math.toRadians(180));
    // ----------------------------------------------------------------------------------

    // Lane 2
    private final Pose pickup1Pose_lane2 = new Pose(48, 65, Math.toRadians(180));
    private final Pose pickup2Pose_lane2 = new Pose(19, 65, Math.toRadians(180));
    private final Pose pickup3Pose_lane2 = new Pose(18, 65, Math.toRadians(180));

    // Lane 3
    private final Pose pickup1Pose_lane3 = new Pose(48, 44, Math.toRadians(180));
    private final Pose pickup2Pose_lane3 = new Pose(19, 44, Math.toRadians(180));
    private final Pose pickup3Pose_lane3 = new Pose(15, 44, Math.toRadians(180));

    // ---------------- Paths ----------------
    private Path scorePreload;

    private PathChain grabPickup1_lane1, grabPickup2_lane1, grabPickup3_lane1,
            gateOpenPath1, gateOpenPath2, scorePickup1,

    grabPickup1_lane2, grabPickup2_lane2, grabPickup3_lane2, scorePickup2,
            grabPickup1_lane3, grabPickup2_lane3, grabPickup3_lane3, scorePickup3,
            park;
    public void buildPaths() {

        // startPose -> scorePose
        scorePreload = new Path(new BezierLine(startPose, scorePose));
        scorePreload.setLinearHeadingInterpolation(startPose.getHeading(), scorePose.getHeading());

        // LOCK HEADING ON PICKUPS (pickup heading = 180)
        double pickupHeading = Math.toRadians(180);

        // lane 1 pickups
        grabPickup1_lane1 = follower.pathBuilder()
                .addPath(new BezierLine(scorePose, pickup1Pose_lane1))
                .setConstantHeadingInterpolation(pickupHeading)
                .build();

        grabPickup2_lane1 = follower.pathBuilder()
                .addPath(new BezierLine(pickup1Pose_lane1, pickup2Pose_lane1))
                .setConstantHeadingInterpolation(pickupHeading)
                .build();

        grabPickup3_lane1 = follower.pathBuilder()
                .addPath(new BezierLine(pickup2Pose_lane1, pickup3Pose_lane1))
                .setConstantHeadingInterpolation(pickupHeading)
                .build();

        // -------- Gate Open Paths (NOW THEY COMPILE + ACTUALLY RUN) --------
        // After you reach pickup3 lane1, you’ll go to gateOpenPose1 then gateOpenPose2, then back to scoring.
        gateOpenPath1 = follower.pathBuilder()
                .addPath(new BezierLine(pickup3Pose_lane1, gateOpenPose1))
                .setConstantHeadingInterpolation(pickupHeading)
                .build();

        gateOpenPath2 = follower.pathBuilder()
                .addPath(new BezierLine(gateOpenPose1, gateOpenPose2))
                .setConstantHeadingInterpolation(pickupHeading)
                .build();

        // score after gate open
        scorePickup1 = follower.pathBuilder()
                .addPath(new BezierLine(gateOpenPose2, scorePose1))
                .setLinearHeadingInterpolation(gateOpenPose2.getHeading(), scorePose1.getHeading())
                .build();
        // ------------------------------------------------------------------

        // lane 2 pickups (start from scorePose1)
        grabPickup1_lane2 = follower.pathBuilder()
                .addPath(new BezierLine(scorePose1, pickup1Pose_lane2))
                .setConstantHeadingInterpolation(pickupHeading)
                .build();

        grabPickup2_lane2 = follower.pathBuilder()
                .addPath(new BezierLine(pickup1Pose_lane2, pickup2Pose_lane2))
                .setConstantHeadingInterpolation(pickupHeading)
                .build();

        grabPickup3_lane2 = follower.pathBuilder()
                .addPath(new BezierLine(pickup2Pose_lane2, pickup3Pose_lane2))
                .setConstantHeadingInterpolation(pickupHeading)
                .build();

        scorePickup2 = follower.pathBuilder()
                .addPath(new BezierLine(pickup3Pose_lane2, scorePose1))
                .setLinearHeadingInterpolation(pickup3Pose_lane2.getHeading(), scorePose1.getHeading())
                .build();

        // lane 3 pickups
        grabPickup1_lane3 = follower.pathBuilder()
                .addPath(new BezierLine(scorePose1, pickup1Pose_lane3))
                .setConstantHeadingInterpolation(pickupHeading)
                .build();

        grabPickup2_lane3 = follower.pathBuilder()
                .addPath(new BezierLine(pickup1Pose_lane3, pickup2Pose_lane3))
                .setConstantHeadingInterpolation(pickupHeading)
                .build();

        grabPickup3_lane3 = follower.pathBuilder()
                .addPath(new BezierLine(pickup2Pose_lane3, pickup3Pose_lane3))
                .setConstantHeadingInterpolation(pickupHeading)
                .build();

        scorePickup3 = follower.pathBuilder()
                .addPath(new BezierLine(pickup3Pose_lane3, scorePose2))
                .setLinearHeadingInterpolation(pickup3Pose_lane3.getHeading(), scorePose2.getHeading())
                .build();

        park = follower.pathBuilder()
                .addPath(new BezierLine(scorePose2, Park))
                .setLinearHeadingInterpolation(scorePose2.getHeading(), Park.getHeading())
                .build();
    }
}
