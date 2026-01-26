package org.firstinspires.ftc.teamcode.Commands;

import com.pedropathing.paths.Path;
import com.pedropathing.paths.PathChain;
import com.seattlesolvers.solverslib.command.CommandBase;
import com.seattlesolvers.solverslib.command.CommandScheduler;

import org.firstinspires.ftc.teamcode.Subsystem.Drive;
import org.firstinspires.ftc.teamcode.Subsystem.Turret;

public class AutonDriveCommand extends CommandBase {
    Drive drive;
    public AutonDriveCommand(Drive drive, Path path){
        this.drive = drive;
        drive.follower.followPath(path);
        addRequirements(drive);
    }
    public AutonDriveCommand(Drive drive, PathChain path){
        this.drive = drive;
        drive.follower.followPath(path);
        addRequirements(drive);
    }
    @Override
    public void execute(){
        drive.periodic();
    }
    @Override
    public boolean isFinished(){
        return !drive.follower.isBusy();
    }
}