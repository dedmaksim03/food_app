package com.example.food_app;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ViewFlipper;

public class Main extends AppCompatActivity {
    private float fromPosition;
    private ViewFlipper flipper = null;
    private float MOVE_LENGTH = 300;
    private int direct = 0;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Устанавливаем listener касаний, для последующего перехвата жестов
        LinearLayout mainLayout = (LinearLayout) findViewById(R.id.main_layout);
        mainLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                switch (event.getAction())
                {
                    case MotionEvent.ACTION_DOWN: // Пользователь нажал на экран, т.е. начало движения
                        // fromPosition - координата по оси X начала выполнения операции
                        fromPosition = event.getX();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        float toPosition = event.getX();
                        // MOVE_LENGTH - расстояние по оси X, после которого можно переходить на след. экран
                        if (((fromPosition - MOVE_LENGTH) > toPosition) && (direct == 0))
                        {
                            fromPosition = toPosition;
                            flipper.setInAnimation(AnimationUtils.loadAnimation(Main.this,R.anim.go_next_in));
                            flipper.setOutAnimation(AnimationUtils.loadAnimation(Main.this,R.anim.go_next_out));
                            flipper.showNext();
                            direct = 1;
                        }
                        else if (((fromPosition + MOVE_LENGTH) < toPosition) && (direct == 1))
                        {
                            fromPosition = toPosition;
                            flipper.setInAnimation(AnimationUtils.loadAnimation(Main.this,R.anim.go_prev_in));
                            flipper.setOutAnimation(AnimationUtils.loadAnimation(Main.this,R.anim.go_prev_out));
                            flipper.showPrevious();
                            direct = 0;
                        }
                    default:
                        break;
                }
                return true;
            }
        });

        // Получаем объект ViewFlipper
       flipper = (ViewFlipper) findViewById(R.id.flipper);

        // Создаем View и добавляем их в уже готовый flipper
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        int[] layouts = new int[]{ R.layout.activity_onboardingscreen, R.layout.activity_onboardingscreen2};
        for (int layout : layouts)
            flipper.addView(inflater.inflate(layout, null));
    }
}