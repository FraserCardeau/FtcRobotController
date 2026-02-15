package org.firstinspires.ftc.teamcode.Commands;

import static org.firstinspires.ftc.teamcode.Constants.blueTarget;

import static java.lang.Math.atan;
import static java.lang.Math.atan2;

import com.pedropathing.geometry.Pose;
import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.Subsystem.Drive;
import org.firstinspires.ftc.teamcode.Subsystem.Turret;

public class Align extends CommandBase {
    Drive drive;
    Turret turret;
    double targetAngle;
    Pose target;
    public Align(Drive drive, Turret turret){
        this.drive = drive;
        this.turret = turret;
        addRequirements(turret);
        target = blueTarget;
    }
    @Override
    public void initialize(){
        /*targetAngle = Math.toDegrees(atan2((Constants.blueTarget.getY() - drive.getPose().getY()), (Constants.blueTarget.getX() - drive.getPose().getX())) - drive.getPose().getHeading());
        targetAngle = ((targetAngle + 180) % 360 + 360) % 360 - 180;
        turret.setPosition(targetAngle);*/
    }
    @Override
    public void execute(){
        targetAngle = Math.toDegrees(atan2(blueTarget.getY() - drive.getPose().getY(), blueTarget.getX() - drive.getPose().getX()) - drive.getPose().getHeading());
        targetAngle = ((targetAngle + 180) % 360 + 360) % 360 - 180;
        turret.setPosition(targetAngle);
    }
    /*@Override
    public void end(boolean interrupted){
        turret.setPosition(0);
    }*/
}
