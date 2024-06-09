package com.example.tinkoff_trainer;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

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

        checkBoxContainer = findViewById(R.id.checkBoxContainer);
        ImageButton submitBtn = findViewById(R.id.submitBtn);
        ImageView num_lvl = findViewById(R.id.lvl);

        level = getIntent().getIntExtra("level", -1);
        if (level != -1) {
            switch (level) {
                case 1:
                    num_lvl.setImageResource(getResources().getIdentifier("lvl1", "drawable", getPackageName()));
                    addCheckBoxes(new String[]{"Сложение", "Вычитание", "Деление", "Умножение", "Уравнение"});
                    break;
                case 2:
                    num_lvl.setImageResource(getResources().getIdentifier("lvl2", "drawable", getPackageName()));
                    addCheckBoxes(new String[]{"Степенные выражения", "Квадратные уравнения", "Неравенства"});
                    break;
                case 3:
                    num_lvl.setImageResource(getResources().getIdentifier("lvl3", "drawable", getPackageName()));
                    addCheckBoxes(new String[]{"Тригонометрия", "Интегралы", "Логарифмические выражения"});
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
        checkBoxAll = new CheckBox(new ContextThemeWrapper(this, R.style.CygreCheckBox));
        checkBoxAll.setText("Все");
        setCheckBoxDrawableRight(checkBoxAll, false); // Изначально чекбокс не выбран
        checkBoxAll.setTextSize(32);
        checkBoxAll.setTypeface(ResourcesCompat.getFont(this, R.font.cygre_regular));
        checkBoxAll.setOnClickListener(v -> {
            boolean isChecked = checkBoxAll.isChecked();
            selectedCategories.clear(); // Очищаем список перед добавлением значений
            for (int i = 1; i < checkBoxContainer.getChildCount(); i++) {
                CheckBox checkBox = (CheckBox) checkBoxContainer.getChildAt(i);
                checkBox.setChecked(isChecked);
                setCheckBoxDrawableRight(checkBox, isChecked);
                if (isChecked) {
                    selectedCategories.add(checkBox.getText().toString());
                }
            }
        });
        checkBoxContainer.addView(checkBoxAll);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 40);
        for (String category : categories) {
            CheckBox checkBox = new CheckBox(new ContextThemeWrapper(this, R.style.CygreCheckBox));
            checkBox.setText(category);
            setCheckBoxDrawableRight(checkBox, false); // Изначально чекбокс не выбран
            checkBox.setTextSize(32);
            checkBox.setTypeface(ResourcesCompat.getFont(this, R.font.cygre_regular));
            checkBox.setOnClickListener(onCheckBoxClickListener);
            checkBoxContainer.addView(checkBox);
            View spacer = new View(this);
            spacer.setLayoutParams(layoutParams);
            checkBoxContainer.addView(spacer);
        }
    }

    private void setCheckBoxDrawableRight(CheckBox checkBox, boolean isChecked) {
        int drawableResId = isChecked ? R.drawable.check_checked : R.drawable.check_unchecked;
        checkBox.setCompoundDrawablesWithIntrinsicBounds(null, null, getResources().getDrawable(drawableResId), null);
        checkBox.setButtonDrawable(android.R.color.transparent);  // Устанавливаем прозрачное изображение для чекбокса
    }


    final private View.OnClickListener onCheckBoxClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v instanceof CheckBox) {
                CheckBox checkBox = (CheckBox) v;
                boolean isChecked = checkBox.isChecked();
                setCheckBoxDrawableRight(checkBox, isChecked);
                String category = checkBox.getText().toString();
                if (isChecked) {
                    selectedCategories.add(category);
                } else {
                    selectedCategories.remove(category);
                }

                boolean allChecked = true;
                for (int i = 1; i < checkBoxContainer.getChildCount(); i++) {
                    View childView = checkBoxContainer.getChildAt(i);
                    if (childView instanceof CheckBox) {
                        CheckBox checkBox1 = (CheckBox) childView;
                        if (!checkBox1.isChecked()) {
                            allChecked = false;
                            break;
                        }
                    }
                }
                checkBoxAll.setChecked(allChecked);
            }
        }
    };
}
