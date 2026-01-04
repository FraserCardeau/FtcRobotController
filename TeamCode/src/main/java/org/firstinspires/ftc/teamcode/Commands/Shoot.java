package org.firstinspires.ftc.teamcode.Commands;

import com.seattlesolvers.solverslib.command.CommandBase;
import com.seattlesolvers.solverslib.util.Timing.Timer;

import org.firstinspires.ftc.teamcode.Subsystem.Kicker;
import org.firstinspires.ftc.teamcode.Subsystem.Shooter;

public class Shoot extends CommandBase {
    private double targetVel = 1200, maxError = 5, downPos = 0, shootPos = 0.5;
    private Kicker kicker;
    private Timer downTimer = new Timer(600), shootTimer = new Timer(300);
    private Shooter shooter;
    public Shoot(Kicker kicker, Shooter shooter){
        this.kicker = kicker;
        this.shooter = shooter;
        addRequirements(kicker, shooter);
    }
    @Override
    public void initialize() {
        shooter.setVelocity(1200);
    }
    @Override
    public void execute() {
        if (Math.abs(shooter.getCurrentVelocity() - targetVel) < maxError){
            if (!shootTimer.isTimerOn() && !shootTimer.done()){
                kicker.setPosition(shootPos);
                shootTimer.start();
            }
            else if (shootTimer.done() && !downTimer.isTimerOn()){
                kicker.setPosition(downPos);
                downTimer.start();
            }
        }
    }
    @Override
    public boolean isFinished() {
        return downTimer.done();
    }
}
