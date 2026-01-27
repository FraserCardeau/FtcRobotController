package org.firstinspires.ftc.teamcode.Util;

import android.content.Context;
import android.content.SharedPreferences;

public class AllianceConfig {

    private static final String PREFS = "FTC_CONFIG";
    private static final String KEY_ALLIANCE = "ALLIANCE";

    public enum Alliance {
        RED, BLUE
    }

    public static void setAlliance(Context context, Alliance alliance) {
        SharedPreferences prefs =
                context.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
        prefs.edit().putString(KEY_ALLIANCE, alliance.name()).apply();
    }

    public static Alliance getAlliance(Context context) {
        SharedPreferences prefs =
                context.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
        String value = prefs.getString(KEY_ALLIANCE, Alliance.RED.name());
        return Alliance.valueOf(value);
    }
}