package com.example.tinkoff_trainer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class variable extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_variable);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageButton level1 = findViewById(R.id.level1);
        ImageButton level2 = findViewById(R.id.level2);
        ImageButton level3 = findViewById(R.id.level3);

        level1.setOnClickListener(v -> openCategoryActivity(1));
        level2.setOnClickListener(v -> openCategoryActivity(2));
        level3.setOnClickListener(v -> openCategoryActivity(3));
    }

    private void openCategoryActivity(int level) {
        Intent intent = new Intent(variable.this, category.class);
        intent.putExtra("level", level);
        startActivity(intent);
    }
}
