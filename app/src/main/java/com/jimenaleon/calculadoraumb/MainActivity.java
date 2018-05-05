package com.jimenaleon.calculadoraumb;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import java.text.DecimalFormat;

import static com.jimenaleon.calculadoraumb.MainActivity.Operation.NONE;

public class MainActivity extends AppCompatActivity {

    private EditText edit_text;
    private static final String EMPTY = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edit_text = (EditText) findViewById(R.id.edit_text);
    }

    public void writeNumber(int number) {
        edit_text.append(String.valueOf(number));
    }

    public void setNumber(View view) {
        switch (view.getId()) {
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
        if (view.getId() == R.id.clear) edit_text.setText(EMPTY);
    }

    public void setOperation(View view) {

        String value = edit_text.getText().toString();
        value = value.replaceAll("[^\\d.]", EMPTY);
        if (value.equals(EMPTY)) return;
        edit_text.setText(EMPTY);
        switch (view.getId()) {
            case R.id.plus:
                edit_text.setText(String.format("%s+", value));
                break;
            case R.id.minus:
                edit_text.setText(String.format("%s-", value));
                break;
            case R.id.multiply:
                edit_text.setText(String.format("%sx", value));
                break;
            case R.id.divide:
                edit_text.setText(String.format("%s/", value));
                break;
            case R.id.module:
                edit_text.setText((value+"%"));
                break;
        }
    }

    public void setResult(View view) {
        String value = edit_text.getText().toString();
        String s = value.replaceAll("\\d|\\.", EMPTY);
        Operation operation = Operation.getOperation(s);
        if(operation == NONE) return;
        value = value.replaceAll("[^\\d.]", " ");
        String[] elements = value.split(" ");
        if (elements.length < 2) return;
        double valueOne = Double.parseDouble(elements[0]);
        double valueTwo = Double.parseDouble(elements[1]);
        double result = 0;
        switch (operation) {
            case ADDITION:
                result = valueOne + valueTwo;
                break;
            case SUBTRACTION:
                result = valueOne - valueTwo;
                break;
            case MULTIPLICATION:
                result = valueOne * valueTwo;
                break;
            case DIVISION:
                result = valueOne / valueTwo;
                break;
            case MODULE:
                result = valueOne % valueTwo;
                break;
        }
        DecimalFormat format = new DecimalFormat("0.#");
        edit_text.setText(format.format(result));
    }

    public void deleteLastNumber(View view) {
        String value = edit_text.getText().toString();
        if(value.length() <= 0) return;
        edit_text.setText(value.substring(0,value.length() -1));
    }

    public enum Operation {
        NONE(""),
        ADDITION("+"),
        SUBTRACTION("-"),
        MULTIPLICATION("x"),
        DIVISION("/"),
        MODULE("%");

        private final String value;
        public String getValue() {return value;}
        Operation(String s) {
            value = s;
        }
        public static Operation getOperation(String s){
            if (ADDITION.getValue().equals(s)) return ADDITION;
            if (SUBTRACTION.getValue().equals(s)) return SUBTRACTION;
            if (MULTIPLICATION.getValue().equals(s)) return MULTIPLICATION;
            if (DIVISION.getValue().equals(s)) return DIVISION;
            if (MODULE.getValue().equals(s)) return MODULE;
            return NONE;
        }
    }
}
