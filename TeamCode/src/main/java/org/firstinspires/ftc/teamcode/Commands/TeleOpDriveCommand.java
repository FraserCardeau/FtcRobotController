package org.firstinspires.ftc.teamcode.Commands;

import com.seattlesolvers.solverslib.command.CommandBase;
import com.seattlesolvers.solverslib.command.CommandScheduler;

import org.firstinspires.ftc.teamcode.Subsystem.Drive;
import org.firstinspires.ftc.teamcode.Subsystem.Turret;

public class TeleOpDriveCommand extends CommandBase {
    Drive drive;
    Turret turret;
    private double targetAngle;
    public TeleOpDriveCommand(Drive drive, Turret turret){
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
    }
}