package com.app.vpgroup.suduku2018;

import android.content.Context;
import android.media.MediaPlayer;

/**
 * Created by hokie on 12/24/2016.
 */

public class Music {
    private static MediaPlayer mp = null;

    /* stop old song and start a new one*/
    public static void play(Context context, int resource) {
        stop(context);
        if(Prefs.getMusic(context)) {
            mp = MediaPlayer.create(context, resource);
            mp.setLooping(true);
            mp.start();
        }
    }

    /*Stop the music */
    public static void stop(Context context) {
        if (mp != null) {
            mp.stop();
            mp.release();
            mp = null;
        }
    }
}
