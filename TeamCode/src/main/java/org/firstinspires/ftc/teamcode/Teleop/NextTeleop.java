package org.firstinspires.ftc.teamcode.Teleop;

import static org.firstinspires.ftc.teamcode.Pedro.Constants.driveConstants;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.Subsystem.IntakeSystem;
import org.firstinspires.ftc.teamcode.Subsystem.ShooterSystem;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

import dev.nextftc.core.commands.Command;
import dev.nextftc.core.commands.CommandManager;
import dev.nextftc.core.components.BindingsComponent;
import dev.nextftc.core.components.SubsystemComponent;
import dev.nextftc.ftc.Gamepads;
import dev.nextftc.ftc.NextFTCOpMode;
import dev.nextftc.ftc.components.BulkReadComponent;
import dev.nextftc.hardware.driving.MecanumDriverControlled;
import dev.nextftc.hardware.impl.MotorEx;
@TeleOp
public class NextTeleop extends NextFTCOpMode {
    public NextTeleop(){
        addComponents(
                new SubsystemComponent(ShooterSystem.INSTANCE),
                BulkReadComponent.INSTANCE,
                BindingsComponent.INSTANCE
        );
    }
    final MotorEx frontLeft = new MotorEx(driveConstants.leftFrontMotorName).reversed();
    final MotorEx frontRight = new MotorEx(driveConstants.rightFrontMotorName).reversed();
    final MotorEx backLeft = new MotorEx(driveConstants.leftRearMotorName).reversed();
    final MotorEx backRight = new MotorEx(driveConstants.rightRearMotorName);
    Queue<Command> shooterQueue = new LinkedList<>();
    @Override
    public void onStartButtonPressed(){
        Command driverControlled = new MecanumDriverControlled(
                frontLeft,
                frontRight,
                backLeft,
                backRight,
                Gamepads.gamepad1().leftStickY().negate(),
                Gamepads.gamepad1().leftStickX(),
                Gamepads.gamepad1().rightStickX()
        );
        driverControlled.schedule();
        Gamepads.gamepad1().dpadUp().toggleOnBecomesTrue()
                .whenBecomesTrue(IntakeSystem.INSTANCE.enableIntake)
                .whenBecomesFalse(IntakeSystem.INSTANCE.disableIntake);
        Gamepads.gamepad1().rightBumper().toggleOnBecomesTrue()
                .whenBecomesTrue(ShooterSystem.INSTANCE.prime)
                .whenBecomesFalse(ShooterSystem.INSTANCE.stopPrime);
        Gamepads.gamepad2().dpadUp().whenBecomesTrue(() -> {
            if (shooterQueue.size() < 3) {
                shooterQueue.add(ShooterSystem.INSTANCE.shoot);
            }
        });
    }
    @Override
    public void onUpdate(){
        CommandManager.INSTANCE.run();
        if (!CommandManager.INSTANCE.hasCommandsUsing(ShooterSystem.INSTANCE.shoot) && !shooterQueue.isEmpty()){
            Objects.requireNonNull(shooterQueue.poll()).schedule();
        }
    }
}