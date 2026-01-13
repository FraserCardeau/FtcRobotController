package org.firstinspires.ftc.teamcode.Commands;

import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystem.Drive;

public class DriveCommand extends CommandBase {
    Drive drive;
    private double targetAngle;
    public DriveCommand(Drive drive){
        this.drive = drive;
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