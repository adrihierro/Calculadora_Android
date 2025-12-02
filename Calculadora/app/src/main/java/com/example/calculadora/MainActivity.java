package com.example.calculadora;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    TextView editTXT;

    // Números
    Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;
    // Operadores
    Button btnAdd, btnSub, btnMul, btnDiv;
    // Otros
    Button btnClear, btnEqual, btnDot;

    // Memoria
    Button mc, mr, mplus, mmin, ms;

    double num1 = 0;
    double num2 = 0;
    char operador = ' ';
    boolean decimalMode = false;
    StringBuilder currentInput = new StringBuilder();

    private double memory = 0;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        editTXT = findViewById(R.id.txtResult);

        // Números
        btn0 = findViewById(R.id.btn10);
        btn1 = findViewById(R.id.btn9);
        btn2 = findViewById(R.id.btn16);
        btn3 = findViewById(R.id.btn11);
        btn4 = findViewById(R.id.btn5);
        btn5 = findViewById(R.id.btn6);
        btn6 = findViewById(R.id.btn7);
        btn7 = findViewById(R.id.btn2);
        btn8 = findViewById(R.id.btn3);
        btn9 = findViewById(R.id.btn17);

        // Operadores
        btnAdd = findViewById(R.id.btnsuma);
        btnSub = findViewById(R.id.btnresta);
        btnMul = findViewById(R.id.btnmultiplicar);
        btnDiv = findViewById(R.id.btn1);

        // Otros
        btnClear = findViewById(R.id.btnclear);
        btnEqual = findViewById(R.id.btnequals);
        btnDot = findViewById(R.id.btn15);

        // Memoria
        mc = findViewById(R.id.mc);
        mr = findViewById(R.id.mr);
        mplus = findViewById(R.id.mplus);
        mmin = findViewById(R.id.mmin);
        ms = findViewById(R.id.ms);

        // listeners números
        btn0.setOnClickListener(v -> numberPressed("0"));
        btn1.setOnClickListener(v -> numberPressed("1"));
        btn2.setOnClickListener(v -> numberPressed("2"));
        btn3.setOnClickListener(v -> numberPressed("3"));
        btn4.setOnClickListener(v -> numberPressed("4"));
        btn5.setOnClickListener(v -> numberPressed("5"));
        btn6.setOnClickListener(v -> numberPressed("6"));
        btn7.setOnClickListener(v -> numberPressed("7"));
        btn8.setOnClickListener(v -> numberPressed("8"));
        btn9.setOnClickListener(v -> numberPressed("9"));

        // punto decimal
        btnDot.setOnClickListener(v -> {
            if (!decimalMode) {
                currentInput.append(".");
                decimalMode = true;
                editTXT.setText(currentInput.toString());
            }
        });

        // operadores
        btnAdd.setOnClickListener(v -> setOperator('+'));
        btnSub.setOnClickListener(v -> setOperator('-'));
        btnMul.setOnClickListener(v -> setOperator('*'));
        btnDiv.setOnClickListener(v -> setOperator('/'));

        // igual
        btnEqual.setOnClickListener(v -> calculate());

        // clear
        btnClear.setOnClickListener(v -> clearAll());

        // memoria
        mc.setOnClickListener(v -> memory = 0);

        mr.setOnClickListener(v -> {
            currentInput.setLength(0);
            currentInput.append(memory);
            editTXT.setText(String.valueOf(memory));
        });

        ms.setOnClickListener(v -> {
            if (currentInput.length() > 0) {
                memory = Double.parseDouble(currentInput.toString());
            }
        });

        mplus.setOnClickListener(v -> {
            if (currentInput.length() > 0) {
                memory += Double.parseDouble(currentInput.toString());
            }
        });

        mmin.setOnClickListener(v -> {
            if (currentInput.length() > 0) {
                memory -= Double.parseDouble(currentInput.toString());
            }
        });
    }

    private void numberPressed(String value) {
        currentInput.append(value);
        editTXT.setText(currentInput.toString());
    }

    private void setOperator(char op) {
        if (currentInput.length() > 0) {
            if (operador != ' ') {
                calculate();
            }
            num1 = Double.parseDouble(editTXT.getText().toString());
            currentInput.setLength(0);
            decimalMode = false;
            operador = op;

            editTXT.setText(num1 + " " + operador);
        }
    }

    private void calculate() {
        if (currentInput.length() > 0) {
            num2 = Double.parseDouble(currentInput.toString());
        }

        double resultado = num1;
        switch (operador) {
            case '+':
                resultado = num1 + num2;
                break;
            case '-':
                resultado = num1 - num2;
                break;
            case '*':
                resultado = num1 * num2;
                break;
            case '/':
                if (num2 != 0) {
                    resultado = num1 / num2;
                } else {
                    editTXT.setText("Error");
                    return;
                }
                break;
        }

        editTXT.setText(String.valueOf(resultado));
        num1 = resultado;
        num2 = 0;
        operador = ' ';
        currentInput.setLength(0);
        decimalMode = false;
    }

    private void clearAll() {
        num1 = 0;
        num2 = 0;
        operador = ' ';
        currentInput.setLength(0);
        decimalMode = false;
        editTXT.setText("0");
    }
}
