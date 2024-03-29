package com.app.vpgroup.suduku2018;

import android.content.Context;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;

/**
 * Created by hokie on 12/24/2016.
 */

public class Prefs extends PreferenceActivity {
    protected boolean musicOn;
    protected boolean hintsOn;

    private static final String OPT_MUSIC = "musicOn" ;
    private static final boolean OPT_MUSIC_DEF = true;
    private static final String OPT_HINTS = "hintsOn" ;
    private static final boolean OPT_HINTS_DEF = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.setting);
    }
    /** Get the current value of the music option */
    public static boolean getMusic(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getBoolean(OPT_MUSIC, OPT_MUSIC_DEF);
    }

    /** Get the current value of the hints option */
    public static boolean getHints(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getBoolean(OPT_HINTS, OPT_HINTS_DEF);
    }
}
