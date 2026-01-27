package org.firstinspires.ftc.teamcode.Teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;

import org.firstinspires.ftc.teamcode.Util.AllianceConfig;

@TeleOp(name = "Switch Alliance")
public class SwitchAlliance extends LinearOpMode {

    @Override
    public void runOpMode() {
        GamepadEx gamepadEx1 = new GamepadEx(gamepad1);
        GamepadEx gamepadEx2 = new GamepadEx(gamepad2);

        waitForStart();

        while (opModeIsActive()) {
            AllianceConfig.Alliance alliance =
                    AllianceConfig.getAlliance(hardwareMap.appContext);

            for (GamepadKeys.Button button : GamepadKeys.Button.values()) {
                if (gamepadEx1.wasJustPressed(button) || gamepadEx2.wasJustPressed(button)) {
                    if (alliance == AllianceConfig.Alliance.BLUE){
                        AllianceConfig.setAlliance(hardwareMap.appContext, AllianceConfig.Alliance.RED);
                    }
                    else {
                        AllianceConfig.setAlliance(hardwareMap.appContext, AllianceConfig.Alliance.BLUE);
                    }
                    break;
                }
            }

            telemetry.addLine("Press any button to switch alliance");
            telemetry.addData("Current alliance is ", alliance);

            telemetry.update();
        }
    }
}