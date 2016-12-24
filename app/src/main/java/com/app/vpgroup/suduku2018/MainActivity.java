package com.app.vpgroup.suduku2018;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

public class MainActivity extends Activity implements View.OnClickListener{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View NewGame = findViewById(R.id.btnNewGame);
        NewGame.setOnClickListener(this);
        View ContinueGame = findViewById(R.id.btnContinue);
        ContinueGame.setOnClickListener(this);
        View AboutGame = findViewById(R.id.btnAbout);
        AboutGame.setOnClickListener(this);
        View ExitGame = findViewById(R.id.btnExit);
        ExitGame.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnNewGame:
                openNewGameDialog();
                break;
            case R.id.btnContinue:
                starGame(Game.DIFFICULTY_CONTINUE);
                break;
            case R.id.btnExit:
                finish();
                break;
            case R.id.btnAbout:
                Dialog dialog = new Dialog(MainActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.show_dialog);
                dialog.show();
                break;
//            case R.id.btnStory:
//                Game.story_mode = true;
//                starGame(Game.DIFFICULTY_EASY);
//                break;
        }
    }

    private void openNewGameDialog() {
        new AlertDialog.Builder(this).setTitle(R.string.new_game_title)
                .setItems(R.array.difficulty, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        starGame(i);
                    }
                }).show();
    }

    private void starGame(int i) {
        Intent intent = new Intent(MainActivity.this, Game.class);
        intent.putExtra(Game.KEY_DIFFICULTY,i );
        startActivity(intent);
    }
}
