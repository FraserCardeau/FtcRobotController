package org.firstinspires.ftc.teamcode.Subsystem;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import static java.lang.Math.abs;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Util.Hardware;

import dev.nextftc.control.ControlSystem;
import dev.nextftc.control.KineticState;
import dev.nextftc.control.feedback.FeedbackElement;
import dev.nextftc.core.commands.Command;
import dev.nextftc.core.commands.delays.Delay;
import dev.nextftc.core.commands.groups.ParallelGroup;
import dev.nextftc.core.commands.groups.ParallelRaceGroup;
import dev.nextftc.core.commands.groups.SequentialGroup;
import dev.nextftc.core.commands.utility.LambdaCommand;
import dev.nextftc.core.subsystems.Subsystem;
import dev.nextftc.hardware.delegates.Velocity;
import dev.nextftc.hardware.impl.MotorEx;
import dev.nextftc.hardware.impl.ServoEx;

public class ShooterSystem implements Subsystem {
    public static final ShooterSystem INSTANCE = new ShooterSystem();
    private ShooterSystem() { }
    MotorEx shooter = new MotorEx("shooter_motor");
    ServoEx ballKick = new ServoEx("ballKick");
    boolean ballKickActive = false;
    ControlSystem shooterController = ControlSystem.builder()
            .velPid(0.03, 0.0, 0.0)
            .build();
    public Command prime = new LambdaCommand()
            .setStart(() -> {
                shooterController.setGoal(new KineticState(shooter.getCurrentPosition(), 1150));
            })
            .setUpdate(() -> {
                shooter.setPower(shooterController.calculate(new KineticState(shooter.getCurrentPosition(), shooter.getVelocity())));
            })
            .setStop(interrupted -> {
                shooter.setPower(0);
                shooter.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
            });
    public Command stopPrime = new LambdaCommand()
            .setStart(() -> {
                shooter.setPower(0);
                shooter.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
            });
    public Command shoot = new ParallelGroup(
            prime,
            new SequentialGroup(
                    new LambdaCommand().setStart(() -> {
                                ballKick.setPosition(0.75);
                            }),
                    new Delay(0.3),
                    new LambdaCommand().setStart(() -> {
                        ballKick.setPosition(0.28);
                    })
            )
    );
}