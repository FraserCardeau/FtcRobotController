package org.firstinspires.ftc.teamcode.Subsystem;

import com.qualcomm.robotcore.hardware.DcMotorEx;

import dev.nextftc.core.commands.Command;
import dev.nextftc.core.commands.utility.LambdaCommand;
import dev.nextftc.core.subsystems.Subsystem;
import dev.nextftc.hardware.impl.MotorEx;

public class IntakeSystem implements Subsystem {
    public static final IntakeSystem INSTANCE = new IntakeSystem();
    private IntakeSystem() {}
    private final MotorEx intake = new MotorEx("intake");
    public Command enableIntake = new LambdaCommand().setStart(() -> {intake.setPower(-1);});
    public Command disableIntake = new LambdaCommand().setStart(() -> {intake.setPower(0);});
}