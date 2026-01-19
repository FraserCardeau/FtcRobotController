package org.firstinspires.ftc.teamcode.Commands.Shoot;

import com.seattlesolvers.solverslib.command.CommandBase;
import com.seattlesolvers.solverslib.util.Timing.Timer;

import org.firstinspires.ftc.teamcode.Subsystem.Drive;
import org.firstinspires.ftc.teamcode.Subsystem.Kicker;
import org.firstinspires.ftc.teamcode.Subsystem.Shooter;

public class RunShooter extends CommandBase {
    private double targetVel = 1100;
    Shooter shooter;
    public RunShooter(Shooter shooter){
        this.shooter = shooter;
        addRequirements(shooter);
    }
    @Override
    public void initialize() {
        shooter.setVelocity(targetVel);
    }
}