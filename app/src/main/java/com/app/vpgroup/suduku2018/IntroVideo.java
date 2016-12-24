package com.app.vpgroup.suduku2018;

/**
 * Created by hokie on 12/24/2016.
 */

public class IntroVideo {
    public static final int NONE = -1;
    public static final int SUDOKU = 0;
    public static final int GAME = 1;
    public static final int SKILLS = 2;
    String path;

    public IntroVideo() {
    }

    public IntroVideo(int TAG)
    {
        switch (TAG)
        {
            case NONE:
                break;
            case SUDOKU:
                path = "android.resource://" + "org.example.sudoku" + "/"
                        + R.raw.intro;
                break;
            case GAME:
                int level = Game.level;
                switch (level)
                {
                    case 0:
                        path = "android.resource://" + "org.example.sudoku" + "/"
                                + R.raw.hulijing_intro;
                        break;
                    case 1:
                        path = "android.resource://" + "org.example.sudoku" + "/"
                                + R.raw.intro;
                        break;
                    default:
                        path = "android.resource://" + "org.example.sudoku" + "/"
                                + R.raw.intro;
                        break;
                }

                break;
            case SKILLS:
                path = "android.resource://" + "org.example.sudoku" + "/"
                        + R.raw.intro;
                break;

        }

    }

    public String getIntroVideo() {
        return path;
    }
}
