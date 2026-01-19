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

    }
    @Override
    public void execute(){
        drive.periodic();
    }
}