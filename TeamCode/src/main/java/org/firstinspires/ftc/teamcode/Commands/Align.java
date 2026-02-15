package org.firstinspires.ftc.teamcode.Commands;

import static org.firstinspires.ftc.teamcode.Constants.blueTarget;
import static org.firstinspires.ftc.teamcode.Constants.redTarget;

import static java.lang.Math.atan;
import static java.lang.Math.atan2;

import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.Subsystem.Drive;
import org.firstinspires.ftc.teamcode.Subsystem.Turret;
import org.firstinspires.ftc.teamcode.Util.AllianceConfig;

public class Align extends CommandBase {
    Drive drive;
    Turret turret;
    double targetAngle;
    Pose target;
    public Align(AllianceConfig.Alliance alliance, Drive drive, Turret turret){
        this.drive = drive;
        this.turret = turret;
        addRequirements(turret);
        if (alliance == AllianceConfig.Alliance.BLUE){
            target = blueTarget;
        }
        else{
            target = redTarget;
        }
    }
    @Override
    public void execute(){
        targetAngle = Math.toDegrees(atan2(target.getY() - drive.getPose().getY(), target.getX() - drive.getPose().getX()) - drive.getPose().getHeading());
        targetAngle = ((targetAngle + 180) % 360 + 360) % 360 - 180;
        turret.setPosition(targetAngle);
    }
    /*@Override
    public void end(boolean interrupted){
        turret.setPosition(0);
    }*/
}
