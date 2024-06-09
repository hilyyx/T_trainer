package com.example.tinkoff_trainer;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class game extends AppCompatActivity {

    private LinearLayout circlesLayout;
    private int filledCircles = 0;
    private static final int MAX_CIRCLES = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        circlesLayout = findViewById(R.id.circlesLayout);
        Button button = findViewById(R.id.button);

        for (int i = 0; i < MAX_CIRCLES; i++) {
            ImageView circle = new ImageView(this);
            circle.setImageResource(R.drawable.circle_empty); // Пустой кружок
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(80, 80);
            params.setMargins(8, 8, 8, 8);
            circle.setLayoutParams(params);
            circlesLayout.addView(circle);
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (filledCircles < MAX_CIRCLES) {
                    ImageView circle = (ImageView) circlesLayout.getChildAt(filledCircles);
                    circle.setImageResource(R.drawable.circle_filled);
                    filledCircles++;
                }
            }
        });
    }
}
