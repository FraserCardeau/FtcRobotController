package org.firstinspires.ftc.teamcode.Commands;

import static org.firstinspires.ftc.teamcode.Pedro.Constants.localizerConstants;

import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Pedro.VisionOdometryLocalizer;
import org.firstinspires.ftc.teamcode.Subsystem.Drive;

public class Recalibrate extends CommandBase {
    VisionOdometryLocalizer visionOdometryLocalizer;
    Drive drive;
    public Recalibrate(Drive drive, HardwareMap hwMap){
        visionOdometryLocalizer = new VisionOdometryLocalizer(hwMap, localizerConstants, hwMap.get(Limelight3A.class, "limelight3A"));
        this.drive = drive;
    }

    @Override
    public void initialize(){
        visionOdometryLocalizer.setHeading(drive.follower.getHeading());
        visionOdometryLocalizer.update();
        drive.follower.setPose(visionOdometryLocalizer.getPose());
    }

    @Override
    public void execute(){
        drive.follower.setPose(visionOdometryLocalizer.getPose());
    }
}
