package org.firstinspires.ftc.teamcode.Auton;

import static org.firstinspires.ftc.teamcode.Constants.*;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.seattlesolvers.solverslib.command.Command;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.command.CommandScheduler;
import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.command.ParallelCommandGroup;
import com.seattlesolvers.solverslib.command.ParallelRaceGroup;
import com.seattlesolvers.solverslib.command.RepeatCommand;
import com.seattlesolvers.solverslib.command.SequentialCommandGroup;
import com.seattlesolvers.solverslib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.Commands.Align;
import org.firstinspires.ftc.teamcode.Commands.AutonDriveCommand;
import org.firstinspires.ftc.teamcode.Commands.Shoot.RunShooter;
import org.firstinspires.ftc.teamcode.Commands.Shoot.ShootKicker;
import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.Subsystem.BreakBeamSensor;
import org.firstinspires.ftc.teamcode.Subsystem.Drive;
import org.firstinspires.ftc.teamcode.Subsystem.Intake;
import org.firstinspires.ftc.teamcode.Subsystem.Kicker;
import org.firstinspires.ftc.teamcode.Subsystem.Shooter;
import org.firstinspires.ftc.teamcode.Subsystem.Turret;
import org.firstinspires.ftc.teamcode.Util.AllianceConfig;

@Autonomous
public class FrontAuton extends CommandOpMode {
    Command driveCommand;
    Turret turret;
    Drive drive;
    Kicker kicker;
    Intake intake;
    Shooter shooter;
    BreakBeamSensor breakBeamSensor;
    Follower follower;

    private Command shootSequence(Kicker kicker, Intake intake){
        return new RepeatCommand(new ShootKicker(kicker, intake), 3);
    }
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
        Constants.Paths(drive.follower);
        drive.follower.setStartingPose(new Pose(23.000, 126.000, Math.toRadians(50)));

        waitForStart();
        //CommandScheduler.getInstance().schedule(new RunShooter(shooter));
        CommandScheduler.getInstance().schedule(new RepeatCommand(new InstantCommand(() -> drive.periodic())));

        CommandScheduler.getInstance().schedule(
                new SequentialCommandGroup(
                        // Drive to position then shoot and align while doing it
                        new ParallelRaceGroup(
                                new SequentialCommandGroup(
                                        new AutonDriveCommand(drive, Path1),
                                        shootSequence(kicker, intake)
                                ),
                                new Align(AllianceConfig.getAlliance(hardwareMap.appContext), drive, turret)
                        ),

                        // Drive, intake on, intake off, align, shoot
                        new ParallelRaceGroup(
                                new SequentialCommandGroup(
                                        new AutonDriveCommand(drive, Path2),
                                        shootSequence(kicker, intake)
                                ),
                                new SequentialCommandGroup(
                                        new WaitCommand(500),
                                        new InstantCommand(() -> intake.enable()),
                                        new WaitCommand(600),
                                        new InstantCommand(() -> intake.disable()),
                                        new Align(AllianceConfig.getAlliance(hardwareMap.appContext), drive, turret)
                                )
                        ),

                        new ParallelRaceGroup(
                                new SequentialCommandGroup(
                                        new AutonDriveCommand(drive, Path3),
                                        shootSequence(kicker, intake)
                                ),
                                new SequentialCommandGroup(
                                        new WaitCommand(600),
                                        new InstantCommand(() -> intake.enable()),
                                        new WaitCommand(1000),
                                        new InstantCommand(() -> intake.disable()),
                                        new Align(AllianceConfig.getAlliance(hardwareMap.appContext), drive, turret)
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
                                        shootSequence(kicker, intake)
                                ),
                                new Align(AllianceConfig.getAlliance(hardwareMap.appContext), drive, turret)
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
                                        shootSequence(kicker, intake)
                                ),
                                new Align(AllianceConfig.getAlliance(hardwareMap.appContext), drive, turret)
                        ),

                        new ParallelRaceGroup(
                                new SequentialCommandGroup(
                                        new AutonDriveCommand(drive, Path9),
                                        shootSequence(kicker, intake)
                                ),
                                new SequentialCommandGroup(
                                        new WaitCommand(1200),
                                        new InstantCommand(() -> intake.enable()),
                                        new WaitCommand(2000),
                                        new InstantCommand(() -> intake.disable()),
                                        new Align(AllianceConfig.getAlliance(hardwareMap.appContext), drive, turret)
                                )
                        ),

                        new AutonDriveCommand(drive, Path10)
                )
        );
    }

    @Override
    public void run(){
        super.run();
        telemetry.addData("shooter speed: ", shooter.shooter.getVelocity());
        telemetry.addData("x: ", drive.follower.getPose().getX());
        telemetry.addData("y: ", drive.follower.getPose().getY());
        telemetry.addData("heading: ", drive.follower.getPose().getHeading());
        telemetry.addData("turretPos: ", turret.turret.get());
        telemetry.update();
    }
}
