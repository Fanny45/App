package com.example.application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;

import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.example.application.beans.Grid;
import com.example.application.beans.Square;
import com.example.application.beans.OnSwipeTouchListener;

import static com.example.application.beans.Square.context;

public class GameActivity extends AppCompatActivity {
    public static final String PREF_NAME = "Preferences";
    public static final int SWIPE_TRESHOLD = 100;
    public static final int VELOCITY_TRESHOLD = 100;


    Grid grid;
    SharedPreferences settings;


    Button scoreBtn;
    Button bestBtn;
    Button restartB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game2);

        final View[] viewsId = new View[16];
        settings = getApplicationContext().getSharedPreferences(PREF_NAME, 0);
        int best = settings.getInt("best", 0);
        grid = new Grid(4, this, best);
        scoreBtn = findViewById(R.id.score);
        bestBtn = findViewById(R.id.best);

        //obtener todos los viewa

        viewsId[0] = findViewById(R.id.btn_0_0);
        viewsId[1] = findViewById(R.id.btn_0_1);
        viewsId[2] = findViewById(R.id.btn_0_2);
        viewsId[3] = findViewById(R.id.btn_0_3);
        viewsId[4] = findViewById(R.id.btn_1_0);
        viewsId[5] = findViewById(R.id.btn_1_1);
        viewsId[6] = findViewById(R.id.btn_1_2);
        viewsId[7] = findViewById(R.id.btn_1_3);
        viewsId[8] = findViewById(R.id.btn_2_0);
        viewsId[9] = findViewById(R.id.btn_2_1);
        viewsId[10] = findViewById(R.id.btn_2_2);
        viewsId[11] = findViewById(R.id.btn_2_3);
        viewsId[12] = findViewById(R.id.btn_3_0);
        viewsId[13] = findViewById(R.id.btn_3_1);
        viewsId[14] = findViewById(R.id.btn_3_2);
        viewsId[15] = findViewById(R.id.btn_3_3);

        //obtener el resto de botones
        //TODO
        //Crear movimientos del dedo
        for (View v : viewsId) {
            v.setOnTouchListener(new OnSwipeTouchListener(GameActivity.this) {
                public void onSwipeTop() {
                    grid.swipeOnGrid(Grid.UP);
                    setScoreAndBest();
                }

                public void onSwipeRight() {
                    grid.swipeOnGrid(Grid.RIGHT);
                    setScoreAndBest();
                }

                public void onSwipeLeft() {
                    grid.swipeOnGrid(Grid.LEFT);
                    setScoreAndBest();
                }

                public void onSwipeBottom() {
                    grid.swipeOnGrid(Grid.DOWN);
                    setScoreAndBest();
                }
            });

        }

        grid.setGridSquares(viewsId);
        grid.restartGrid();
        grid.addRandomNumber();

        restartB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


    }

    public boolean onFiles(MotionEvent downEvent, MotionEvent moveEvent,
                           float vx, float vy) {
        boolean result = false;
        float diftY = moveEvent.getY() - downEvent.getY();
        float diftX = moveEvent.getX() - downEvent.getX();

        if (Math.abs(diftX) > Math.abs(diftY)) {
            if (Math.abs(diftX) > SWIPE_TRESHOLD &&
                    Math.abs(vx) > VELOCITY_TRESHOLD) {
                if (diftX > 0) {
                    grid.swipeOnGrid(Grid.RIGHT);
                } else {
                    grid.swipeOnGrid(Grid.LEFT);
                }
                result = true;
                setScoreAndBest();
            }
        } else {
            if (Math.abs(diftY) > SWIPE_TRESHOLD &&
                    Math.abs(vy) > VELOCITY_TRESHOLD) {
                if (diftY > 0) {
                    grid.swipeOnGrid(Grid.DOWN);
                } else {
                    grid.swipeOnGrid(Grid.UP);
                }
                result = true;
                setScoreAndBest();
            }
        }
        return result;
    }


    private void setScoreAndBest() {
        int score = grid.getScore();
        int best = grid.getBest();
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt("best", best);
        editor.apply();
        scoreBtn.setText("SCORE\n" + score);
        bestBtn.setText("BEST\n" + best);

    }
}
