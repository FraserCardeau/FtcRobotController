package org.firstinspires.ftc.teamcode.Constants;

import com.bylazar.configurables.annotations.Configurable;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.Util.DcMotorExtra;

public class Motors{
    @Configurable
    public static class FrontLeft extends DcMotorExtra {
        public FrontLeft() {
            super("leftFront");
            runMode = DcMotorEx.RunMode.RUN_USING_ENCODER;
            direction = DcMotorEx.Direction.FORWARD;
        }
    }
    @Configurable
    public static class FrontRight extends DcMotorExtra {
        public FrontRight() {
            super("rightFront");
            runMode = DcMotorEx.RunMode.RUN_USING_ENCODER;
            direction = DcMotorEx.Direction.REVERSE;
        }
    }
    @Configurable
    public static class BackLeft extends DcMotorExtra {
        public BackLeft() {
            super("rightFront");
            runMode = DcMotorEx.RunMode.RUN_USING_ENCODER;
            direction = DcMotorEx.Direction.REVERSE;
        }
    }
    @Configurable
    public static class BackRight extends DcMotorExtra {
        public BackRight() {
            super("rightFront");
            runMode = DcMotorEx.RunMode.RUN_USING_ENCODER;
            direction = DcMotorEx.Direction.REVERSE;
        }
    }
}
