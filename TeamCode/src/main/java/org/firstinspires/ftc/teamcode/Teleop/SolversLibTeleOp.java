package org.firstinspires.ftc.teamcode.Teleop;

import com.pedropathing.follower.Follower;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.command.Command;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.command.CommandScheduler;
import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.command.RepeatCommand;
import com.seattlesolvers.solverslib.command.RunCommand;
import com.seattlesolvers.solverslib.command.SequentialCommandGroup;
import com.seattlesolvers.solverslib.command.button.Button;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;

import org.firstinspires.ftc.teamcode.Commands.DriveCommand;
import org.firstinspires.ftc.teamcode.Commands.Shoot;
import org.firstinspires.ftc.teamcode.Pedro.Constants;
import org.firstinspires.ftc.teamcode.Subsystem.Kicker;
import org.firstinspires.ftc.teamcode.Subsystem.Shooter;
import org.firstinspires.ftc.teamcode.Subsystem.Drive;

@TeleOp
public class SolversLibTeleOp extends CommandOpMode {
    Command driveCommand;
    boolean wasItRunning;
    @Override
    public void initialize() {
    }
    @Override
    public void runOpMode(){
        CommandScheduler.getInstance().enable();
        GamepadEx controller = new GamepadEx(gamepad1);
        Drive drive = new Drive(hardwareMap, controller);
        Kicker kicker = new Kicker(hardwareMap, "ballKick");
        Shooter shooter = new Shooter(hardwareMap, "shooter");

        Button a = controller.getGamepadButton(GamepadKeys.Button.A);
        Command shootSequence = new RepeatCommand(new Shoot(kicker, shooter, drive), 3);
        a.toggleWhenPressed(shootSequence);

        driveCommand = /*new SequentialCommandGroup(new InstantCommand(drive::teleopDrive), new RunCommand(drive::periodic));*/ new DriveCommand(drive);
        CommandScheduler.getInstance().schedule(driveCommand);
        wasItRunning = CommandScheduler.getInstance().isScheduled(driveCommand);
        waitForStart();
        while (opModeIsActive()){
            telemetry.addData("Is drive system running? ", CommandScheduler.getInstance().isScheduled(driveCommand));
            telemetry.addData("Did it run?", wasItRunning);
            telemetry.update();
            CommandScheduler.getInstance().run();
        }
    }
}
