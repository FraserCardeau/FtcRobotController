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

import org.firstinspires.ftc.teamcode.Commands.Shoot;
import org.firstinspires.ftc.teamcode.Pedro.Constants;
import org.firstinspires.ftc.teamcode.Subsystem.Kicker;
import org.firstinspires.ftc.teamcode.Subsystem.Shooter;
import org.firstinspires.ftc.teamcode.Subsystem.Drive;

@TeleOp
public class SolversLibTeleOp extends CommandOpMode {
    @Override
    public void initialize() {
        GamepadEx controller = new GamepadEx(gamepad1);

        Follower follower = Constants.createFollower(hardwareMap);
        Drive drive = new Drive(follower, controller);

        Button a = controller.getGamepadButton(GamepadKeys.Button.A);
        Command shootSequence = new RepeatCommand(new Shoot(new Kicker(hardwareMap, "kicker"), new Shooter(hardwareMap, "shooter")), 3);
        a.toggleWhenPressed(shootSequence);

        Command driveCommand = new SequentialCommandGroup(new InstantCommand(drive::teleopDrive), new RunCommand(drive::periodic));
        CommandScheduler.getInstance().schedule(driveCommand);
    }
}
