package com.example.ex4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private Integer first, second;
    private Boolean isFinished;
    private Operation lastOperation = Operation.EQUAL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.tv_text);
    }

    public void onNumberClick(View view) {
        String number = ((MaterialButton) view).getText().toString();
        if (view.getId() == R.id.btn_ac) {
            textView.setText("0");

        } else if (textView.getText().toString().equals("0") || isFinished) {
            textView.setText(number);
        } else {
            textView.append(number);
        }
        isFinished = false;
    }

    public void onOperationClick(View view) {
        if (lastOperation != Operation.EQUAL) {
            onEqualClick(view);
        }
        String operation = ((MaterialButton) view).getText().toString();
        String lastChar = textView.toString().substring(textView.toString().length() - 2);
        if (lastChar.equals("+") || lastChar.equals("-") || lastChar.equals("/") || lastChar.equals("X")) {
            textView.setText(textView.toString().replace(lastChar, operation));
        }
        first = Integer.valueOf(textView.getText().toString());
        textView.append(operation);
        isFinished = false;


        switch (operation) {
            case "+":
                lastOperation = Operation.PLUS;
                break;
            case "-":
                lastOperation = Operation.MINUS;
                break;
            case "X":
                lastOperation = Operation.MULTIPLY;
                break;
            case "/":
                lastOperation = Operation.DIVISION;
                break;
        }

    }

    public void onEqualClick(View view) {

        second = Integer.valueOf(textView.getText().toString().substring(first.toString().length() + 1));
        Integer res = 0;
        try {
            switch (lastOperation) {
                case PLUS:
                    res = first + second;
                    break;
                case MINUS:
                    res = first - second;
                    break;
                case DIVISION:
                    res = first / second;
                    break;
                case MULTIPLY:
                    res = first * second;
                    break;
            }
        } catch (ArithmeticException e) {
            Toast.makeText(this, "Ошибка", Toast.LENGTH_LONG).show();
        }


        textView.setText(res.toString());
        lastOperation = Operation.EQUAL;
        isFinished = true;

    }
}