package com.app.vpgroup.suduku2018;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Toast;

/**
 * Created by hokie on 12/24/2016.
 */

public class Game extends Activity {
    public static final String KEY_DIFFICULTY = "com.app.vpgroup.suduku2018.difficulty";
    public static final int DIFFICULTY_EASY = 0;
    public static final int DIFFICULTY_MEDIUM = 1;
    public static final int DIFFICULTY_HARD = 2;
    public static final int DIFFICULTY_CONTINUE = -1;
    private int puzzle[];
    private PuzzleView puzzleView;

    private final String easyPuzzle = "360000000004230800000004200"
            + "070460003820000014500013020"
            +"001900000007048300000000045";
    private final String mediumPuzzle = "650000070000506000014000005"
            + "007009000002314700000700800"
            + "500000630000201000030000097";
    private final String hardPuzzle = "009000000080605020501078000"
            + "000000700706040102004000000"
            + "000720903090301080000000600";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int diff = getIntent().getIntExtra(KEY_DIFFICULTY, DIFFICULTY_EASY);
        puzzle = getPuzzle(diff);
        calculateUsedTiles();
        PuzzleView puzzleView = new PuzzleView(this);
        setContentView(puzzleView);
        puzzleView.requestFocus();

    }
    private void calculateUsedTiles(){
        for(int x= 0; x < 9; x++){
            for(int y = 0; y < 9; y++){
                used[x][y] = calculateUsedTiles(x, y);
            }
        }
    }
    private int[] calculateUsedTiles(int x, int y){
        int c[] = new int[9];
        for(int i = 0; i < 9; i++){
            if( i == y){
                continue;
            }
            int t = getTile(x, i);
            if(t!= 0){
                c[t - 1] = t;
            }
        }

        for(int i = 0; i < 9; i++){
            if( i == x){
                continue;
            }
            int t = getTile(i, y);
            if(t!= 0){
                c[t - 1] = t;
            }
        }

        int startX = (x /3) * 3;
        int startY = (y /3) * 3;
        for(int i = startX; i < startX + 3; i++){
            for(int j = startY; j < startY + 3; j++){
                if(i == x && j == y){
                    continue;
                }
                int t = getTile(x, y);
                if(t!= 0){
                    c[t - 1] = t;
                }
            }
        }

        int nused = 0;
        for(int t : c){
            if(t != 0){
                nused++;
            }
        }
        int c1[] = new int[nused];
        nused = 0;
        for(int t : c){
            if(t != 0){
                c1[nused++] = t;
            }
        }
        return c1;
    }

    private int[] getPuzzle(int diff) {
        String puzz;
        switch (diff){
            case DIFFICULTY_HARD:
                puzz = hardPuzzle;
                break;
            case DIFFICULTY_MEDIUM:
                puzz = mediumPuzzle;
                break;
            default: DIFFICULTY_EASY:
                puzz = easyPuzzle;
                break;
        }
        return fromPuzzSeting(puzz);
    }

    static protected int[] fromPuzzSeting(String string){
        int[] puzz = new int[string.length()];
        for(int i = 0; i < puzz.length; i++){
            puzz[i] = string.charAt(i) - '0';
        }
        return puzz;
    }

    private int getTile(int x, int y){
        return puzzle[y * 9 + x];
    }

    private void setTile(int x, int y, int value){
        puzzle[y * 9 + x] = value;
    }

    public String getTitleString(int x, int y){
        int v = getTile(x, y);
        if(v == 0){
            return "";
        }
        else{
            return String.valueOf(v);
        }

    }

    boolean setTileIfValid(int x, int y, int value){
        int tiles[] = getUsedTiles(x, y);
        if(value != 0){
            for(int tile: tiles){
                if(tile == value){
                    return false;
                }
            }
        }

        setTile(x, y, value);
        calculateUsedTiles();
        return true;
    }

    private final int used[][][] = new int[9][9][0];
    protected int[] getUsedTiles(int x, int y){
        return used[x][y];
    }

    void showKeypadOrError(int x, int y){
        int tiles[] = getUsedTiles(x, y);
        if(tiles.length == 9){
           Toast toast =  Toast.makeText(this, R.string.no_move_lable, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }
        else {
            Dialog dialog = new Keypad(this, tiles, puzzleView);
            dialog.show();

        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        super.onBackPressed();
    }
}
