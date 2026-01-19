package org.firstinspires.ftc.teamcode.Commands.Shoot;

import com.seattlesolvers.solverslib.command.CommandBase;
import com.seattlesolvers.solverslib.util.Timing;

import org.firstinspires.ftc.teamcode.Subsystem.Intake;
import org.firstinspires.ftc.teamcode.Subsystem.Kicker;
import org.firstinspires.ftc.teamcode.Subsystem.Shooter;

import java.util.concurrent.TimeUnit;

public class ShootKicker extends CommandBase {
    Kicker kicker;
    Shooter shooter;
    Intake intake;
    double downPos = 0.5, shootPos = 0.24;
    Timing.Timer downTimer = new Timing.Timer(600, TimeUnit.MILLISECONDS), shootTimer = new Timing.Timer(300, TimeUnit.MILLISECONDS);
    public ShootKicker(Kicker kicker, Intake intake){
        this.kicker = kicker;
        this.intake = intake;
        addRequirements(kicker, intake);
    }
    @Override
    public void initialize(){
        intake.disable();
        kicker.setPosition(shootPos);
        shootTimer.start();
    }
    @Override
    public void execute(){
        if (shootTimer.done()){
            kicker.setPosition(downPos);
            downTimer.start();
            intake.slowEnable();
        }
    }
    @Override
    public boolean isFinished(){
        return downTimer.done();
    }
    @Override
    public void end(boolean interrupted){
        intake.disable();
        kicker.setPosition(downPos);
    }
}
