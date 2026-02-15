package org.firstinspires.ftc.teamcode;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierCurve;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;

public final class Constants {
    private Constants() {}
    public static final Pose blueTarget = new Pose(0, 144);
    public static final Pose redTarget = new Pose(144, 144);
    public static boolean isBlueAlliance = true;
    public static PathChain Path1, Path2, Path3, Path4, Path5, Path6, Path7, Path8, Path9, Path10;
    public static void Paths(Follower follower) {
        Path1 = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(23.000, 126.000),
                                new Pose(45.000, 107.000)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(55), Math.toRadians(150))
                .build();

        Path2 = follower.pathBuilder().addPath(
                        new BezierCurve(
                                new Pose(45.000, 107.000),
                                new Pose(71.000, 74.000),
                                new Pose(0.000, 76.000),
                                new Pose(18.000, 85.000),
                                new Pose(42.000, 103.000)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(150), Math.toRadians(180))
                .build();

        Path3 = follower.pathBuilder().addPath(
                        new BezierCurve(
                                new Pose(42.000, 103.000),
                                new Pose(74.000, 46.000),
                                new Pose(0.000, 45.000),
                                new Pose(12.000, 68.000),
                                new Pose(46.000, 98.000)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(150))
                .build();

        Path4 = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(46.000, 98.000),
                                new Pose(15.000, 69.000)
                        )
                )
                .setLinearHeadingInterpolation(Math.toRadians(150), Math.toRadians(90))
                .build();

        Path5 = follower.pathBuilder().addPath(
                        new BezierCurve(
                                new Pose(15.000, 69.000),
                                new Pose(79.000, 30.000),
                                new Pose(21.000, 33.000)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(90), Math.toRadians(170))
                .build();

        Path6 = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(21.000, 33.000),
                                new Pose(45.000, 7.000)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(170), Math.toRadians(180))
                .build();

        Path7 = follower.pathBuilder().addPath(
                        new BezierCurve(
                                new Pose(45.000, 7.000),
                                new Pose(13.000, 39.000),
                                new Pose(10.000, 31.000),
                                new Pose(7.000, 7.000)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(270))
                .build();

        Path8 = follower.pathBuilder().addPath(
                        new BezierCurve(
                                new Pose(7.000, 7.000),
                                new Pose(26.000, 36.000),
                                new Pose(45.000, 7.000)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(270), Math.toRadians(90))
                .build();

        Path9 = follower.pathBuilder().addPath(
                        new BezierCurve(
                                new Pose(45.000, 7.000),
                                new Pose(-13.000, 5.000),
                                new Pose(7.000, 83.000),
                                new Pose(12.000, 71.000),
                                new Pose(18.000, 47.000),
                                new Pose(40.000, 50.000),
                                new Pose(55.000, 94.000)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(90), Math.toRadians(90))
                .build();

        Path10 = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(55.000, 94.000),

                                new Pose(46.000, 79.000)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(90), Math.toRadians(205))
                .build();
    }
}
