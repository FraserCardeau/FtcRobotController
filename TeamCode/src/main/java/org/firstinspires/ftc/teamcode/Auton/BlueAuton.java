package org.firstinspires.ftc.teamcode.Auton;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierCurve;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.Path;
import com.pedropathing.paths.PathChain;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.seattlesolvers.solverslib.command.Command;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.command.CommandScheduler;
import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.command.ParallelCommandGroup;
import com.seattlesolvers.solverslib.command.ParallelDeadlineGroup;
import com.seattlesolvers.solverslib.command.ParallelRaceGroup;
import com.seattlesolvers.solverslib.command.RepeatCommand;
import com.seattlesolvers.solverslib.command.RunCommand;
import com.seattlesolvers.solverslib.command.ScheduleCommand;
import com.seattlesolvers.solverslib.command.SelectCommand;
import com.seattlesolvers.solverslib.command.SequentialCommandGroup;
import com.seattlesolvers.solverslib.command.WaitCommand;
import com.seattlesolvers.solverslib.command.WaitUntilCommand;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;

import org.firstinspires.ftc.teamcode.Commands.Align;
import org.firstinspires.ftc.teamcode.Commands.AutonDriveCommand;
import org.firstinspires.ftc.teamcode.Commands.Shoot.RunShooter;
import org.firstinspires.ftc.teamcode.Commands.Shoot.ShootKicker;
import org.firstinspires.ftc.teamcode.Subsystem.BreakBeamSensor;
import org.firstinspires.ftc.teamcode.Subsystem.Drive;
import org.firstinspires.ftc.teamcode.Subsystem.Intake;
import org.firstinspires.ftc.teamcode.Subsystem.Kicker;
import org.firstinspires.ftc.teamcode.Subsystem.Shooter;
import org.firstinspires.ftc.teamcode.Subsystem.Turret;

import java.util.concurrent.TimeUnit;

@Autonomous
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
        turret = new Turret(hardwareMap, "turret");
        drive = new Drive(hardwareMap, telemetry, turret);
        kicker = new Kicker(hardwareMap, "ballKick", telemetry);
        intake = new Intake(hardwareMap, "intake");
        shooter = new Shooter(hardwareMap, "shooter", telemetry);
        breakBeamSensor = new BreakBeamSensor(hardwareMap, "breakbeam");
        Paths(drive.follower);
        Command shootSequence = new RepeatCommand(new ShootKicker(kicker, intake), 3);

        waitForStart();
        CommandScheduler.getInstance().schedule(new RunShooter(shooter));

        CommandScheduler.getInstance().schedule(
                new SequentialCommandGroup(
                        // Drive to position then shoot and align while doing it
                        new ParallelRaceGroup(
                                new SequentialCommandGroup(
                                        new AutonDriveCommand(drive, Path1),
                                        shootSequence
                                ),
                                new Align(drive, turret)
                        ),

                        // Drive, intake on, intake off, align, shoot
                        new ParallelRaceGroup(
                                new SequentialCommandGroup(
                                        new AutonDriveCommand(drive, Path2),
                                        shootSequence
                                ),
                                new SequentialCommandGroup(
                                        new WaitCommand(500),
                                        new InstantCommand(() -> intake.enable()),
                                        new WaitCommand(600),
                                        new InstantCommand(() -> intake.disable()),
                                        new Align(drive, turret)
                                )
                        ),

                        new ParallelRaceGroup(
                                new SequentialCommandGroup(
                                        new AutonDriveCommand(drive, Path3),
                                        shootSequence
                                ),
                                new SequentialCommandGroup(
                                        new WaitCommand(600),
                                        new InstantCommand(() -> intake.enable()),
                                        new WaitCommand(1000),
                                        new InstantCommand(() -> intake.disable()),
                                        new Align(drive, turret)
                                )
                        ),

                        new AutonDriveCommand(drive, Path4),

                        new ParallelCommandGroup(
                                new AutonDriveCommand(drive, Path5),
                                new SequentialCommandGroup(
                                        new WaitCommand(2000),
                                        new InstantCommand(() -> intake.enable())
                                )
                        ),
                        new InstantCommand(() -> intake.disable()),

                        new ParallelRaceGroup(
                                new SequentialCommandGroup(
                                        new AutonDriveCommand(drive, Path6),
                                        shootSequence
                                ),
                                new Align(drive, turret)
                        ),

                        new ParallelCommandGroup(
                                new AutonDriveCommand(drive, Path7),
                                new SequentialCommandGroup(
                                        new WaitCommand(2000),
                                        new InstantCommand(() -> intake.enable())
                                )
                        ),
                        new InstantCommand(() -> intake.disable()),

                        new ParallelRaceGroup(
                                new SequentialCommandGroup(
                                        new AutonDriveCommand(drive, Path8),
                                        shootSequence
                                ),
                                new Align(drive, turret)
                        ),

                        new ParallelRaceGroup(
                                new SequentialCommandGroup(
                                        new AutonDriveCommand(drive, Path9),
                                        shootSequence
                                ),
                                new SequentialCommandGroup(
                                        new WaitCommand(1200),
                                        new InstantCommand(() -> intake.enable()),
                                        new WaitCommand(2000),
                                        new InstantCommand(() -> intake.disable()),
                                        new Align(drive, turret)
                                )
                        ),

                        new AutonDriveCommand(drive, Path10)
                )
        );
    }

    @Override
    public void run(){
        super.run();
    }

    public PathChain Path1, Path2, Path3, Path4, Path5, Path6, Path7, Path8, Path9, Path10;

    public void Paths(Follower follower) {
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
