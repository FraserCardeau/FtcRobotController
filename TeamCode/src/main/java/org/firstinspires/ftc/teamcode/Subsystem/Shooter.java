package org.firstinspires.ftc.teamcode.Subsystem;

import com.pedropathing.control.PIDFCoefficients;
import com.pedropathing.control.PIDFController;

import dev.nextftc.control.ControlSystem;
import dev.nextftc.control.KineticState;
import dev.nextftc.core.commands.Command;
import dev.nextftc.core.subsystems.Subsystem;
import dev.nextftc.hardware.impl.MotorEx;

public class Shooter implements Subsystem {

    public static final Shooter INSTANCE = new Shooter();
    private Shooter() { }

    private MotorEx motor = new MotorEx("shooter_motor");

    private ControlSystem controlSystem = ControlSystem.builder()
            .posPid(0.5, 0.5, 0.5)
            .build();

    private boolean controlActive = false;

    private double targetVelocity = 0;

    public Command runVelocity(double velocity) {
        return new Command() {
            @Override
            public void start() {
                targetVelocity = velocity;
                controlSystem.reset();
                controlActive = true;
            }

            @Override
            public void update() {
                motor.setPower(controlSystem.calculate(motor.getState()));
            }

            @Override
            public boolean isDone() {
                return false;
            }
        };
    }
    public Command coast() {
        return new Command() {
            @Override
            public void start() {
                controlActive = false;
                motor.setPower(0);
            }

            @Override
            public boolean isDone() {
                return true;
            }
        };
    }

    @Override
    public void periodic() {
        if (controlActive) {
            controlSystem.setGoal(new KineticState(0, targetVelocity, 0));
            motor.setPower(controlSystem.calculate(motor.getState()));
        }
    }
}