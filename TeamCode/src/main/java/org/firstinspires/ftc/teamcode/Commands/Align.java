package org.firstinspires.ftc.teamcode.Commands;

import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.Subsystem.Drive;
import org.firstinspires.ftc.teamcode.Subsystem.Turret;

public class Align extends CommandBase {
    Drive drive;
    Turret turret;
    private double targetAngle;
    public Align(Drive drive, Turret turret){
        this.drive = drive;
        this.turret = turret;
        addRequirements(drive, turret);
    }
    @Override
    public void initialize(){
        targetAngle = Math.atan((drive.getPose().getY() - Constants.target.getY())/(drive.getPose().getX() - Constants.target.getX()));
        turret.setPosition(targetAngle - drive.getPose().getHeading());
        drive.holdPoint();
    }
}
