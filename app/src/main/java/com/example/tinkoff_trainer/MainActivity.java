package com.example.tinkoff_trainer;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;


import android.content.Context;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
    private TextView textViewExample;
    private EditText editTextAnswer;
    private Button buttonCheck, buttonSkip;

    private int num1, num2, trueCount, totalQuestions, number_question, count, col_question;
    private double result;
    private int score = 0;
    private int score_task=0;
    int level = 3; // !!!установить уровень в зависимости от выбранного
    int i = 0;

    private ArrayList<Integer> oper_level = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Находим элементы интерфейса по их идентификаторам
        textViewExample = findViewById(R.id.textViewExample);
        editTextAnswer = findViewById(R.id.editTextAnswer);
        buttonCheck = findViewById(R.id.buttonCheck);
        buttonSkip = findViewById(R.id.buttonSkip);
        //чистим список после обновления экрана
        ArrayList<Integer> oper_level = new ArrayList<>();
        // !!! количество вопросов
        totalQuestions = 15;
        //номер вопроса текущего
        number_question = 0;
        //выбранные типы примеров
        if (level == 1){
            //сложение if выбрана галочка сложение, то добавляем это
            oper_level.add(1);
            oper_level.add(2);
            oper_level.add(3);
            // if выбрана галочка вычитание, то добавляем это
            oper_level.add(4);
            oper_level.add(5);
            oper_level.add(6);
            // if выбрана галочка умножение, то добавляем это
            oper_level.add(7);
            oper_level.add(8);
            oper_level.add(9);
            // if выбрана галочка деление, то добавляем это
            oper_level.add(10);
            oper_level.add(11);
            oper_level.add(12);
            // if выбрана галочка уравнения, то добавляем это
            oper_level.add(13);
            oper_level.add(14);
            oper_level.add(15);
            count = (int) totalQuestions / oper_level.size();
        } else if (level == 2){
            // if выбрана галочка арифметические операции, то добавляем это
            oper_level.add(6);
            // if выбрана галочка возведение в степень, то добавляем это
            oper_level.add(1);
            oper_level.add(2);
            oper_level.add(3);
            // if выбрана галочка квадратные уравнения, то добавляем это
            oper_level.add(4);
            oper_level.add(5);
            // if выбрана галочка неравенства, то добавляем это
            oper_level.add(7);
            oper_level.add(8);
            oper_level.add(9);
            count = (int) totalQuestions / oper_level.size();
        } else if (level == 3){
            // if выбрана галочка квадратное неравенства, то добавляем это
            oper_level.add(3);
            oper_level.add(4);
            // if выбрана галочка логарифмы, то добавляем это
            oper_level.add(1);
            // if выбрана галочка тригонометрия, то добавляем это
            oper_level.add(2);
            count = (int) totalQuestions / oper_level.size();
        }

        // Генерируем пример
        generateNewExample();

        // обработка кнопки следующий пример
        buttonSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Вы пропустили пример", Toast.LENGTH_SHORT).show();
                generateNewExample();
            }
        });

        //обработка нажатия кнопки проверить
        buttonCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Получаем введенный ответ
                String userAnswer = editTextAnswer.getText().toString();

                // Проверяем правильность ответа
                if (Double.parseDouble(userAnswer) == result) {
                    //Toast.makeText(MainActivity.this, "Правильно! " + i, Toast.LENGTH_SHORT).show();


                    number_question++;
                    if ((number_question % count == 0) && (i <= oper_level.size())){
                        i++;
                    }
                    score += score_task;
                    Toast.makeText(MainActivity.this, "Правильно! " + score, Toast.LENGTH_SHORT).show();
                    if (number_question <= totalQuestions){
                        generateNewExample();// Генерируем новый пример
                    } else {
                        //конец тренажера - переход на предыдущий экран
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Неправильно!" + score, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // функция для генерации примеров по типу и уровню сложности
    private void generateNewExample(){
        if (level == 1) {
            if (oper_level.get(i) == 1){
                generateNewExample_add(0); // сложение от 1 до 10
            } else if (oper_level.get(i) == 2) {
                generateNewExample_add(1); //сложение от 10 до 100
            } else if (oper_level.get(i) == 3) {
                generateNewExample_add(2); //сложение от 100 до 1000
            } else if (oper_level.get(i) == 4) {
                generateNewExample_subtract(0); //вычитание от 1 до 10
            } else if (oper_level.get(i) == 5) {
                generateNewExample_subtract(1); //вычитание от 10 до 100
            } else if (oper_level.get(i) == 6) {
                generateNewExample_subtract(2); //вычитание от 100 до 1000
            } else if (oper_level.get(i) == 7) {
                generateNewExample_multiplication(0); //умножение от 1 до 10
            } else if (oper_level.get(i) == 8) {
                generateNewExample_multiplication(1); //умножение от 10 до 100
            } else if (oper_level.get(i) == 9) {
                generateNewExample_multiplication(2); //умножение от 100 до 1000
            } else if (oper_level.get(i) == 10) {
                generateNewExample_division(0); //деление от 1 до 10
            } else if (oper_level.get(i)== 11) {
                generateNewExample_division(1); //деление от 10 до 100
            } else if (oper_level.get(i) == 12) {
                generateNewExample_division(2); //деление от 100 до 1000
            } else if (oper_level.get(i) == 13){
                generateNewExample_equation(0); //уравнение с одной переменной от 1 до 10
            } else if (oper_level.get(i) == 14) {
                generateNewExample_equation(1); //уравнение с одной переменной от 10 до 100
            } else if (oper_level.get(i) == 15) {
                generateNewExample_equation(2); //уравнение с одной переменной от 100 до 1000
            }

        } else if (level == 2) {
            if (oper_level.get(i) == 1){
                generateNewExample_degree(0); // возведение в степень, основание от 1 до 5, степень от 0 до 3
            } else if (oper_level.get(i)== 2) {
                generateNewExample_degree(1); // возведение в степень, основание от 1 до 4, степень от 3 до 9
            } else if (oper_level.get(i) == 3) {
                generateNewExample_degree(2); // возведение в степень, основание от 10 до 15, степень от 0 до 3
            } else if (oper_level.get(i) == 4) {
                generateNewExample_equation_sq(0); // квадратное уравнение
            } else if (oper_level.get(i) == 5) {
                generateNewExample_equation_sq(1); // квадратное уравнение
            } else if (oper_level.get(i) == 6) {
                generateRandomExpression(); // арифм. выражения
            } else if (oper_level.get(i) == 7) {
                generateRandominequality(0); //неравенство простое от 1 до 10 числа
            } else if (oper_level.get(i) == 8) {
                generateRandominequality(1); //неравенство простое от 10 до 100 числа
            } else if (oper_level.get(i) == 9) {
                generateRandominequality(2); //неравенство простое от 100 до 1000 числа
            }


        } else if (level == 3) {
            if (oper_level.get(i)== 1){
                generateNewExample_log(); // логарифмы
            } else if (oper_level.get(i) == 2){
                generate_trig_problem(); // тригонометрия
            } else if (oper_level.get(i) == 3){
                generateNewExample_equality_sq(0); //квадратное неравенство от 1 до 22
            }else if (oper_level.get(i) == 4){
                generateNewExample_equality_sq(1); // квадратное неравенство от 10 до 40
            }
        }

    }

    //квадратное неравенство
    private void generateNewExample_equality_sq(int sl) {
        int a, b;
        String symbol;
        Random random = new Random();
        if (sl == 0){
            a = random.nextInt(10) + 1;
            b = random.nextInt(12) + 10;
        } else {
            a = random.nextInt(10) + 10;
            b = random.nextInt(20) + 20;
        }
        String[] symbols = {"<", "<="};
        symbol = symbols[(int) (Math.random() * symbols.length)];
        score_task = 2;
        if (symbol == "<"){
            result = b - a - 1;
        } else if (symbol == "<="){
            result = b - a + 1;
        }
        score_task = 3;
        textViewExample.setText("Укажите количество целых решений неравенства \n x² - " + String.valueOf((b + a)) + "x + " + String.valueOf((b * a)) + " " + symbol + " 0");
        // Очищаем поле ввода ответа
        editTextAnswer.setText("");
    }
    //неравенство
    public void generateRandominequality(int sl) {
        Random random = new Random();
        int num1, num2;
        String symbol;
        String[] symbols = {">", "<", ">=", "<="};
        symbol = symbols[(int) (Math.random() * symbols.length)];
        num1 = random.nextInt((int) Math.pow(10, sl + 1) - (int) Math.pow(10, sl)) + (int) Math.pow(10, sl);
        num2 = random.nextInt((int) Math.pow(10, sl + 1) - num1) + num1;
        score_task = 2;
        if (symbol == "<" || symbol == "<=") {
            if (symbol == "<"){
                result = num2 - num1 + 1;
            } else {
                result = num2 - num1;
            }
            textViewExample.setText("Наименьшим целым решением неравенства \n" + num2 + " - x " + symbol + " " + num1);
        } else {
            if (symbol == ">"){
                result = num2 - num1 - 1;
            } else {
                result = num2 - num1;
            }
            textViewExample.setText("Наибольшим целым решением неравенства \n" + num2 + " - x " + symbol + " " + num1);
        }

        editTextAnswer.setText("");

    }
    //арифметические операции + - несколько чисел
    public void generateRandomExpression() {
        Random random = new Random();
        int num1, op, count_op, result_p;
        count_op = random.nextInt(2) + 2;
        num1 = random.nextInt(20) + 1;
        String expression = String.valueOf(num1);
        result_p = num1;
        for (int i = 0; i <= count_op; i++) {
            // Формируем первое выражение
            op = random.nextInt(2);
            num1 = random.nextInt(20) + 1; // Случайное число от 1 до 20
            if (op == 0) { // Сложение
                result_p = result_p + num1;
                expression = expression + " + " + num1;
            } else if (op == 1) { // Вычитание
                result_p = result_p - num1;
                expression = expression + " - " + num1;
            }
        }
        score_task = 2;
        result = result_p;
        textViewExample.setText(expression + " = ?");

        // Очищаем поле ввода ответа
        editTextAnswer.setText("");
    }

    // тригонометрия
    private void generate_trig_problem() {
        Random random = new Random();
        int angle;
        String trigFunction = getRandomTrigFunction();

        if (trigFunction.equals("sin")) {
            angle = getRandomAngle();
            result = Math.round(Math.sin(Math.toRadians(angle))* 10.0) / 10.0;
        } else if (trigFunction.equals("cos")) {
            angle = getRandomAngle();
            result = Math.round(Math.cos(Math.toRadians(angle))* 10.0) / 10.0;
        } else if (trigFunction.equals("tg")) {
            angle = getRandomAngleTan();
            result = Math.round(Math.tan(Math.toRadians(angle))* 10.0) / 10.0;
        } else {
            angle = getRandomAngleCtg();
            result = Math.round(Math.cos(Math.toRadians(angle)) / Math.sin(Math.toRadians(angle))* 10.0) / 10.0;
        }
        score_task = 3;
        textViewExample.setText(trigFunction + "(" + angle + "°) = ?");
        editTextAnswer.setText("");

    }

    //вспомогательная функция для тригонометрии
    public static String getRandomTrigFunction() {
        String[] trigFunctions = {"ctg", "tg", "sin", "cos"}; //
        Random random = new Random();
        return trigFunctions[random.nextInt(trigFunctions.length)];
    }

    //вспомогательная функция для тригонометрии
    public static int getRandomAngle() {
        int[] sincosFunctions = {0, 90, 180, 270, 360};
        Random random = new Random();
        return sincosFunctions[random.nextInt(sincosFunctions.length)];
    }

    //вспомогательная функция для тригонометрии
    public static int getRandomAngleTan() {
        int[] tanFunctions = {0, 45, 135, 180, 225, 315, 360};
        Random random = new Random();
        return tanFunctions[random.nextInt(tanFunctions.length)];
    }

    //вспомогательная функция для тригонометрии
    public static int getRandomAngleCtg() {
        int[] ctgFunctions = {45, 90, 270};
        Random random = new Random();
        return ctgFunctions[random.nextInt(ctgFunctions.length)];
    }


    //генерация логарифмов
    private void generateNewExample_log() {
        int base, argument;
        Random random = new Random();
        base = random.nextInt(8) + 2;
        score_task = 3;
        result = random.nextInt(4) + 2;
        argument = (int) Math.pow(base, result);
        if (base == 2) {
            textViewExample.setText("log₂" + argument + " = ?");
        }
        else if (base == 3) {
            textViewExample.setText("log₃" + argument + " = ?");
        }
        else if (base == 4) {
            textViewExample.setText("log₄" + argument + " = ?");
        }
        else if (base == 5) {
            textViewExample.setText("log₅" + argument + " = ?");
        }
        else if (base == 6) {
            textViewExample.setText("log₆" + argument + " = ?");
        }
        else if (base == 7) {
            textViewExample.setText("log₇" + argument + " = ?");
        }
        else if (base == 8) {
            textViewExample.setText("log₈" + argument + " = ?");
        }
        else if (base == 9) {
            textViewExample.setText("log₉" + argument + " = ?");
        }
        // Очищаем поле ввода ответа
        editTextAnswer.setText("");

    }


    // сложение
    private void generateNewExample_add(int sl) {
        int num1, num2;
        Random random = new Random();
        num2 = random.nextInt((int) Math.pow(10, sl + 1) - (int) Math.pow(10, sl)) + (int) Math.pow(10, sl);
        num1 = random.nextInt((int) Math.pow(10, sl + 1) - (int) Math.pow(10, sl)) + (int) Math.pow(10, sl);
        result = num1 + num2;
        score_task = 1;
        textViewExample.setText(String.valueOf(num2) + " + " + String.valueOf(num1) + " = ?");
        // Очищаем поле ввода ответа
        editTextAnswer.setText("");
    }

    // вычитание
    private void generateNewExample_subtract(int sl) {
        int num1, num2;
        Random random = new Random();
        num2 = random.nextInt((int) Math.pow(10, sl + 1) - (int) Math.pow(10, sl)) + (int) Math.pow(10, sl);
        num1 = random.nextInt((int) Math.pow(10, sl + 1) - num2) + num2;
        result = num1 - num2;
        score_task = 1;
        textViewExample.setText(String.valueOf(num1) + " - " + String.valueOf(num2) + " = ?");
        // Очищаем поле ввода ответа
        editTextAnswer.setText("");
    }

    // умножение
    private void generateNewExample_multiplication(int sl) {
        int num1, num2;
        Random random = new Random();
        num2 = random.nextInt((int) Math.pow(10, sl + 1) - (int) Math.pow(10, sl)) + (int) Math.pow(10, sl);
        num1 = random.nextInt((int) Math.pow(10, sl + 1) - (int) Math.pow(10, sl)) + (int) Math.pow(10, sl);
        result = num2 * num1;
        score_task = 1;
        textViewExample.setText(String.valueOf(num2) + " * " + String.valueOf(num1) + " = ?");
        // Очищаем поле ввода ответа
        editTextAnswer.setText("");
    }

    // деление
    private void generateNewExample_division(int sl) {
        int num1, num2;
        Random random = new Random();
        num2 = random.nextInt((int) Math.pow(10, sl + 1) - (int) Math.pow(10, sl)) + (int) Math.pow(10, sl);
        num1 = random.nextInt((int) Math.pow(10, sl + 1) - (int) Math.pow(10, sl)) + (int) Math.pow(10, sl);
        result = num1;
        score_task = 1;
        textViewExample.setText(String.valueOf(num2 * num1) + " / " + String.valueOf(num2) + " = ?");
        // Очищаем поле ввода ответа
        editTextAnswer.setText("");
    }

    // простое уравнение
    private void generateNewExample_equation(int sl) {
        int num1, num2;
        Random random = new Random();
        num1 = random.nextInt((int) Math.pow(10, sl + 1) - (int) Math.pow(10, sl)) + (int) Math.pow(10, sl);
        num2 = random.nextInt((int) Math.pow(10, sl + 1) - num1) + num1;
        String[] operators = {"+", "-", "*", "/"};
        score_task = 1;
        // Получаем случайный индекс из списка операторов
        int randomIndex = random.nextInt(operators.length);
        if (operators[randomIndex].equals("+")) {
            result = num2 - num1;
            textViewExample.setText(String.valueOf(num1) + " + x = " + String.valueOf(num2));}
        else if (operators[randomIndex].equals("*")) {
            result = num2;
            textViewExample.setText(String.valueOf(num1) + " * x = " + String.valueOf(num2 * num1));}
        else if (operators[randomIndex].equals("-")) {
            result = num2 - num1;
            textViewExample.setText(String.valueOf(num2) + " - x = " + String.valueOf(num1));}
        else {
            result = num1;
            textViewExample.setText(String.valueOf(num1 * num2) + " / x = " + String.valueOf(num2));
        }
        // Очищаем поле ввода ответа
        editTextAnswer.setText("");
    }

    // возведение в степень
    private void generateNewExample_degree(int sl) {
        int numDegree, numBase;

        Random random = new Random();
        if (sl == 0){
            numDegree = random.nextInt(3) ;
            numBase = random.nextInt(4) + 1;
        } else if (sl == 1){
            numDegree = random.nextInt(7) + 3;
            numBase = random.nextInt(3) + 2;
        } else {
            numDegree = random.nextInt(3) + 1;
            numBase = random.nextInt(5) + 10;
        }
        score_task = 3;
        result = (int) Math.pow(numBase, numDegree);
        if (numDegree == 0) {
            textViewExample.setText(numBase +  "⁰" + " = ?");
        }
        else if (numDegree == 1) {
            textViewExample.setText(numBase + "¹" + " = ?");
        }
        else if (numDegree == 2) {
            textViewExample.setText(numBase + "²" + " = ?");
        }
        else if (numDegree == 3) {
            textViewExample.setText(numBase + "³" + " = ?");
        }
        else if (numDegree == 4) {
            textViewExample.setText(numBase + "⁴" + " = ?");
        }
        else if (numDegree == 5) {
            textViewExample.setText(numBase + "⁵" + " = ?");
        }
        else if (numDegree == 6) {
            textViewExample.setText(numBase + "⁶" + " = ?");
        }
        else if (numDegree == 7) {
            textViewExample.setText(numBase + "⁷" + " = ?");
        }
        else if (numDegree == 8) {
            textViewExample.setText(numBase + "⁸" + " = ?");
        }
        else if (numDegree == 9) {
            textViewExample.setText(numBase + "⁹" + " = ?");
        }
        // Очищаем поле ввода ответа
        editTextAnswer.setText("");
    }

    // квадратное уравнение
    private void generateNewExample_equation_sq(int sl) {
        int a, b;
        Random random = new Random();
        if (sl == 0){
            a = random.nextInt(10) + 1;
            b = random.nextInt(12) + 10;
        } else {
            a = random.nextInt(10) + 10;
            b = random.nextInt(20) + 20;
        }
        score_task = 2;
        result = b;
        textViewExample.setText("Решите квадратное уравнение. В ответе укажите наибольший корень. \n x² - " + String.valueOf((b + a)) + "x + " + String.valueOf((b * a)) + " = 0");
        // Очищаем поле ввода ответа
        editTextAnswer.setText("");
    }


}
