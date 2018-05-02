package com.jimenaleon.calculadoraumb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity{

    private EditText edit_text;
    private Button one, two, three, four, five, six, seven, eight, nine, zero;
    private Button clear;
    private double valueOne;
    private double valueTwo;
    private static final char ADDITION = '+';
    private static final char SUBTRACTION = '-';
    private static final char MULTIPLICATION = '*';
    private static final char DIVISION = '/';
    private char current_action;
    private boolean new_operation = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edit_text = (EditText) findViewById(R.id.edit_text);
        one = (Button) findViewById(R.id.one);
        two = (Button) findViewById(R.id.two);
        three = (Button) findViewById(R.id.three);
        four = (Button) findViewById(R.id.four);
        five = (Button) findViewById(R.id.five);
        six = (Button) findViewById(R.id.six);
        seven = (Button) findViewById(R.id.seven);
        eight = (Button) findViewById(R.id.eight);
        nine = (Button) findViewById(R.id.nine);
        zero = (Button) findViewById(R.id.zero);
        clear = (Button) findViewById(R.id.clear);
    }

    public void writeNumber(int number){
        if(new_operation){
            edit_text.setText("");
            new_operation = false;
        }
        String text = edit_text.getText().toString();
        edit_text.setText(text + number);
    }

    public void setNumber(View view) {
        switch (view.getId()){
            case R.id.zero:
                writeNumber(0);
                break;

            case R.id.one:
                writeNumber(1);
                break;

            case R.id.two:
                writeNumber(2);
                break;

            case R.id.three:
                writeNumber(3);
                break;

            case R.id.four:
                writeNumber(4);
                break;

            case R.id.five:
                writeNumber(5);
                break;

            case R.id.six:
                writeNumber(6);
                break;

            case R.id.seven:
                writeNumber(7);
                break;

            case R.id.eight:
                writeNumber(8);
                break;

            case R.id.nine:
                writeNumber(9);
                break;
        }
    }

    public void clear(View view) {
        if(view.getId() == R.id.clear){
            edit_text.setText("");
        }
    }

    public void setOperation(View view) {

        valueOne = Double.parseDouble(edit_text.getText().toString());
        switch (view.getId()){
            case R.id.plus:
                current_action = ADDITION;
                edit_text.setText(edit_text.getText().toString() + " + ");
                break;

            case R.id.minus:
                current_action = SUBTRACTION;
                edit_text.setText(edit_text.getText().toString() + " - ");
                break;

            case R.id.multiply:
                current_action = MULTIPLICATION;
                edit_text.setText(edit_text.getText().toString() + " x ");
                break;

            case R.id.divide:
                current_action = DIVISION;
                edit_text.setText(edit_text.getText().toString() + " / ");
                break;
        }
    }

    public void setResult(View view) {
        new_operation = true;
        if(current_action != 0){
            String operation = edit_text.getText().toString();
            String[] elements = new String[2];
            elements = operation.split(" ");
            valueTwo = Double.parseDouble(elements[2]);
            double result = 0;
            switch (current_action){
                case '-':
                    result = valueOne - valueTwo;
                    break;

                case '/':
                    result = valueOne / valueTwo;
                    break;
            }
            edit_text.setText(String.valueOf(result));
            current_action = 0;
        }
    }
}
