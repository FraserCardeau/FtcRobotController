package org.firstinspires.ftc.teamcode.Auton;

import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.Path;
import com.pedropathing.paths.PathChain;
import com.seattlesolvers.solverslib.command.CommandOpMode;

public class BlueAuton extends CommandOpMode {
    @Override
    public void initialize() {

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
}
