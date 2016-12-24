package com.app.vpgroup.suduku2018;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;

/**
 * Created by hokie on 12/24/2016.
 */

public class PuzzleView extends View {
    private Game game = null;

    public PuzzleView(Context context) {
        super(context);
        this.game = (Game) context;
        setFocusable(true);
        setFocusableInTouchMode(true);
    }

    private float with;
    private float heigh;
    private int selX;
    private int selY;
    private final Rect rect = new Rect();

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        with = w / 9f;
        heigh = h / 9f;
        getRect(selX, selY, rect);

        super.onSizeChanged(w, h, oldw, oldh);

    }

    private void getRect(int x, int y, Rect rect) {
        rect.set((int) (x * with), (int) (y * heigh), (int) (x * with + with), (int) (y * heigh + heigh));

    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint background = new Paint();
        background.setColor(getResources().getColor(R.color.puzzle_background));
        canvas.drawRect(0, 0, getWidth(), getHeight(), background);
        Paint dark = new Paint();
        dark.setColor(getResources().getColor(R.color.puzzle_dark));
        Paint hitle = new Paint();
        hitle.setColor(getResources().getColor(R.color.puzzle_hilite));
        Paint light = new Paint();
        light.setColor(getResources().getColor(R.color.puzzle_light));
        Paint selected = new Paint();
        //to mau o tro toi
        selected.setColor(getResources().getColor(R.color.puzzle_select));
        canvas.drawRect(rect, selected);

        Paint foreGround = new Paint(Paint.ANTI_ALIAS_FLAG);
        foreGround.setColor(getResources().getColor(R.color.puzzle_foreground));
        foreGround.setStyle(Paint.Style.FILL);
        foreGround.setTextSize(heigh * 0.75f);
        foreGround.setTextScaleX(with / heigh);
        foreGround.setTextAlign(Paint.Align.CENTER);
        Paint.FontMetrics fm = foreGround.getFontMetrics();

        //ve so trong o
        float x = with /2;
        float y = heigh / 2 - (fm.ascent + fm.descent) / 2;
        for(int i = 0 ; i < 9; i++){
            for(int j = 0; j < 9; j++){
                canvas.drawText(this.game.getTitleString(i, j), i*with + x, j*heigh + y, foreGround);

            }
        }
        //ve duong luoi nho
        for(int i = 0; i < 9; i++){
            canvas.drawLine(0, i * heigh, getWidth(), i * heigh, light);
            canvas.drawLine(0, i * heigh, getWidth(), i * heigh + 1, hitle);
            canvas.drawLine(i*with, 0, i* with, getHeight(), light);
            canvas.drawLine(i*with + 1, 0, i* with + 1, getHeight(), hitle);
        }

        //ve duoong luoi dam
        for(int i = 0; i < 9; i++){
            if(i %3 != 0){
                continue;
            }
            canvas.drawLine(0, i * heigh, getWidth(), i * heigh, dark);
            canvas.drawLine(0, i * heigh, getWidth(), i * heigh + 1, hitle);
            canvas.drawLine(i*with, 0, i* with, getHeight(), dark);
            canvas.drawLine(i*with + 1, 0, i* with + 1, getHeight(), hitle);

        }

        super.onDraw(canvas);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode){
            case KeyEvent.KEYCODE_DPAD_UP:
                select(selX, selY - 1);
                break;
            case KeyEvent.KEYCODE_DPAD_DOWN:
                select(selX, selY + 1);
                break;
            case KeyEvent.KEYCODE_DPAD_LEFT:
                select(selX -1, selY);
                break;
            case KeyEvent.KEYCODE_DPAD_RIGHT:
                select(selX+ 1, selY);
                break;
            case KeyEvent.KEYCODE_0:
            case KeyEvent.KEYCODE_SPACE:
                setSelectedTile(0);
                break;
            case KeyEvent.KEYCODE_1:
                setSelectedTile(1);
                break;
            case KeyEvent.KEYCODE_2:
                setSelectedTile(2);
                break;
            case KeyEvent.KEYCODE_3:
                setSelectedTile(3);
                break;
            case KeyEvent.KEYCODE_4:
                setSelectedTile(4);
                break;
            case KeyEvent.KEYCODE_5:
                setSelectedTile(5);
                break;
            case KeyEvent.KEYCODE_6:
                setSelectedTile(6);
                break;
            case KeyEvent.KEYCODE_7:
                setSelectedTile(7);
                break;
            case KeyEvent.KEYCODE_8:
                setSelectedTile(8);
                break;
            case KeyEvent.KEYCODE_9:
                setSelectedTile(9);
                break;
            case KeyEvent.KEYCODE_ENTER:
            case KeyEvent.KEYCODE_DPAD_CENTER:
                game.showKeypadOrError(selX, selY);
                break;
        }
        return true;
    }

    public void setSelectedTile(int i) {
        if(game.setTileIfValid(selX, selY, i)){
            invalidate();
        }
        else{
            startAnimation(AnimationUtils.loadAnimation(game,R.anim.anim ));
        }
    }


    private void select(int x, int y){
        invalidate(rect);
        selX = Math.min(Math.max(x, 0), 8);
        selY = Math.min(Math.max(y, 0), 8);
        getRect(selX, selY, rect);
        invalidate(rect);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() != MotionEvent.ACTION_DOWN){
            return super.onTouchEvent(event);
        }
        select((int) (event.getX() / with), (int) (event.getY()/ heigh));
        return true;
    }

}
