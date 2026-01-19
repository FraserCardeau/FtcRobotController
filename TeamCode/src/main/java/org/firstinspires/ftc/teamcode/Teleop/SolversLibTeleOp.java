package org.firstinspires.ftc.teamcode.Teleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.command.Command;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.command.CommandScheduler;
import com.seattlesolvers.solverslib.command.RepeatCommand;
import com.seattlesolvers.solverslib.command.RunCommand;
import com.seattlesolvers.solverslib.command.button.Button;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;

import org.firstinspires.ftc.teamcode.Commands.Align;
import org.firstinspires.ftc.teamcode.Commands.Shoot.RunShooter;
import org.firstinspires.ftc.teamcode.Commands.Shoot.ShootKicker;
import org.firstinspires.ftc.teamcode.Subsystem.BreakBeamSensor;
import org.firstinspires.ftc.teamcode.Subsystem.Intake;
import org.firstinspires.ftc.teamcode.Subsystem.Kicker;
import org.firstinspires.ftc.teamcode.Subsystem.Shooter;
import org.firstinspires.ftc.teamcode.Subsystem.Drive;
import org.firstinspires.ftc.teamcode.Subsystem.Turret;

@TeleOp
public class SolversLibTeleOp extends CommandOpMode {
    Command driveCommand;
    Turret turret;
    Drive drive;
    Kicker kicker;
    Intake intake;
    Shooter shooter;
    BreakBeamSensor breakBeamSensor;
    Command shootSequence = new ShootKicker(kicker, intake);
    @Override
    public void initialize() {
        CommandScheduler.getInstance().enable();
        GamepadEx controller = new GamepadEx(gamepad1);
        turret = new Turret(hardwareMap, "turret");
        drive = new Drive(hardwareMap, controller, telemetry, turret);
        kicker = new Kicker(hardwareMap, "ballKick", telemetry);
        intake = new Intake(hardwareMap, "intake");
        shooter = new Shooter(hardwareMap, "shooter", telemetry);
        breakBeamSensor = new BreakBeamSensor(hardwareMap, "breakbeam");

        waitForStart();
        Command runShooter = new RunShooter(shooter);
        Button b = controller.getGamepadButton(GamepadKeys.Button.B);
        b.toggleWhenPressed(runShooter);

        Button rightBumper = controller.getGamepadButton(GamepadKeys.Button.RIGHT_BUMPER);
        rightBumper.whenPressed(new RepeatCommand(shootSequence, 3), false);

        Button leftBumper = controller.getGamepadButton(GamepadKeys.Button.LEFT_BUMPER);
        leftBumper.toggleWhenPressed((intake::enable), (intake::disable));

        Button a = controller.getGamepadButton(GamepadKeys.Button.A);
        a.toggleWhenPressed(new Align(drive, turret));

        driveCommand = new RunCommand(drive::periodic);
        CommandScheduler.getInstance().schedule(driveCommand);
    }
    @Override
    public void run(){
        super.run();
        telemetry.addData("shooter speed: ", shooter.shooter.getVelocity());
        telemetry.addData("x: ", drive.follower.getPose().getX());
        telemetry.addData("y: ", drive.follower.getPose().getY());
        telemetry.addData("heading: ", drive.follower.getPose().getHeading());
        telemetry.update();
    }
}
