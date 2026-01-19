package org.firstinspires.ftc.teamcode.Commands;

import com.seattlesolvers.solverslib.command.CommandBase;
import com.seattlesolvers.solverslib.command.CommandScheduler;

import org.firstinspires.ftc.teamcode.Subsystem.Drive;
import org.firstinspires.ftc.teamcode.Subsystem.Turret;

public class AutonDriveCommand extends CommandBase {
    Drive drive;
    Turret turret;
    private double targetAngle;
    public AutonDriveCommand(Drive drive, Turret turret){
        this.drive = drive;
        this.turret = turret;
        addRequirements(drive);
    }
    @Override
    public void initialize(){
        drive.teleopDrive();
    }
    @Override
    public void execute(){
        drive.periodic();
        if (CommandScheduler.getInstance().isScheduled(new Align(drive, turret))){
            turret.setPosition(Math.atan2(144 - drive.getPose().getY(), 144 - drive.getPose().getX()));
        }
    }
}