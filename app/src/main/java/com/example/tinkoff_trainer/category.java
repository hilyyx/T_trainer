package com.example.tinkoff_trainer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class category extends AppCompatActivity {

    private LinearLayout checkBoxContainer;
    final private ArrayList<String> selectedCategories = new ArrayList<>();
    private int level;
    private CheckBox checkBoxAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        TextView levelTitle = findViewById(R.id.levelTitle);
        checkBoxContainer = findViewById(R.id.checkBoxContainer);
        Button submitBtn = findViewById(R.id.submitBtn);

        level = getIntent().getIntExtra("level", -1);
        if (level != -1) {
            switch (level) {
                case 1:
                    levelTitle.setText("Уровень 1");
                    addCheckBoxes(new String[]{"Сложение", "Вычитание", "Деление", "Умножение", "Уравнение"});
                    break;
                case 2:
                    levelTitle.setText("Уровень 2");
                    addCheckBoxes(new String[]{"Логарифмы", "Тригонометрия", "Статистика", "Алгебра", "Геометрия"});
                    break;
                case 3:
                    levelTitle.setText("Уровень 3");
                    addCheckBoxes(new String[]{"Дифференциалы", "Интегралы", "Математический анализ", "Вероятность", "Комбинаторика"});
                    break;
            }
        }

        submitBtn.setOnClickListener(v -> {
            Intent intent = new Intent(category.this, game.class);
            intent.putStringArrayListExtra("selectedCategories", selectedCategories);
            intent.putExtra("level", level);
            startActivity(intent);
        });
    }

    private void addCheckBoxes(String[] categories) {
        checkBoxAll = new CheckBox(this);
        checkBoxAll.setText("Все");
        checkBoxAll.setOnClickListener(v -> {
            boolean isChecked = checkBoxAll.isChecked();
            for (int i = 1; i < checkBoxContainer.getChildCount(); i++) {
                CheckBox checkBox = (CheckBox) checkBoxContainer.getChildAt(i);
                checkBox.setChecked(isChecked);
                if (isChecked) {
                    selectedCategories.add(checkBox.getText().toString());
                } else {
                    selectedCategories.remove(checkBox.getText().toString());
                }
            }
        });
        checkBoxContainer.addView(checkBoxAll);

        for (String category : categories) {
            CheckBox checkBox = new CheckBox(this);
            checkBox.setText(category);
            checkBox.setOnClickListener(onCheckBoxClickListener);
            checkBoxContainer.addView(checkBox);
        }
    }

    final private View.OnClickListener onCheckBoxClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            CheckBox checkBox = (CheckBox) v;
            String category = checkBox.getText().toString();
            if (checkBox.isChecked()) {
                selectedCategories.add(category);
            } else {
                selectedCategories.remove(category);
            }

            boolean allChecked = true;
            for (int i = 1; i < checkBoxContainer.getChildCount(); i++) {
                CheckBox checkBox1 = (CheckBox) checkBoxContainer.getChildAt(i);
                if (!checkBox1.isChecked()) {
                    allChecked = false;
                    break;
                }
            }
            checkBoxAll.setChecked(allChecked);
        }
    };
}
