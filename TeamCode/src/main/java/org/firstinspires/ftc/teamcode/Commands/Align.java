package org.firstinspires.ftc.teamcode.Commands;

import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.Subsystem.Drive;
import org.firstinspires.ftc.teamcode.Subsystem.Turret;

public class Align extends CommandBase {
    Drive drive;
    Turret turret;
    double targetAngle;
    public Align(Drive drive, Turret turret){
        this.drive = drive;
        this.turret = turret;
        addRequirements(turret);
    }
    @Override
    public void initialize(){
        targetAngle = Math.toDegrees(Math.atan2((Constants.target.getY() - drive.getPose().getY()), (Constants.target.getX() - drive.getPose().getX())) - drive.getPose().getHeading());
        targetAngle = ((targetAngle + 180) % 360 + 360) % 360 - 180;
        turret.setPosition(targetAngle);
    }
    /*@Override
    public void execute(){
        turret.setPosition(targetAngle + Math.toDegrees(drive.getPose().getHeading()));
    }*/
}
