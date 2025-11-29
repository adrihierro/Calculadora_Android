package com.example.calculadora;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button button5;
    Button button6;
    Button button7;
    Button button8;
    Button button9;
    Button button10;
    Button button11;
    Button button12;
    Button button13;
    Button button14;
    Button button15;
    Button button16;

    Button button17;
    
    TextView editTXT;

    

    Button mr;
    Button mc;
    Button mplus; //m+
    Button mmin; //m-
    Button ms;
    
    Boolean isenabled = false;






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

        button1 = findViewById(R.id.btn9);
        button2 = findViewById(R.id.btn7);
        button3 = findViewById(R.id.btn1);
        button4 = findViewById(R.id.btn2);
        button5 = findViewById(R.id.btn4);
        button6 = findViewById(R.id.btn5);
        button7 = findViewById(R.id.btn16);
        button8 = findViewById(R.id.btn8);
        button9 = findViewById(R.id.btn10);
        button10 = findViewById(R.id.btn11);
        button11 = findViewById(R.id.btn12);
        button12 = findViewById(R.id.btn3);
        button13 = findViewById(R.id.btn15);
        button14 = findViewById(R.id.btn14);
        button15 = findViewById(R.id.btn6);
        button16 = findViewById(R.id.btn17);
        button17 = findViewById(R.id.btn18);
        ms = findViewById(R.id.ms);
        mr = findViewById(R.id.mr);
        mc = findViewById(R.id.mc);
        mplus = findViewById(R.id.mplus);
        mmin = findViewById(R.id.mmin);



        editTXT = findViewById(R.id.txtResult);

        button9.setOnClickListener(v -> appendText("0"));
        button1.setOnClickListener(v -> appendText("1"));
        button7.setOnClickListener(v -> appendText("2"));
        button10.setOnClickListener(v -> appendText("3"));
        button6.setOnClickListener(v -> appendText("4"));
        button15.setOnClickListener(v -> appendText("5"));
        button2.setOnClickListener(v -> appendText("6"));
        button4.setOnClickListener(v -> appendText("7"));
        button12.setOnClickListener(v -> appendText("8"));
        button16.setOnClickListener(v -> appendText("9"));

        button3.setOnClickListener(v -> appendText("/"));
        button13.setOnClickListener(v -> appendText("."));
        button14.setOnClickListener(v -> appendText("+"));
        button8.setOnClickListener(v -> appendText("-"));
        button17.setOnClickListener(v -> {appendText("*");});

        button5.setOnClickListener(v -> editTXT.setText("")); // este es el botón de borrar

        button11.setOnClickListener(v -> {
            String actual = editTXT.getText().toString();
            getResult(actual);
        }); // botón igual

        mr.setEnabled(false);
        mc.setEnabled(false);

        //extra buttons

        mr.setOnClickListener(v -> {

        });

        ms.setOnClickListener(v -> {
            mr.setEnabled(true);
            mc.setEnabled(true);
        });

        mc.setOnClickListener(v -> {
            mr.setEnabled(false);
            mc.setEnabled(false);
        });

        mplus.setOnClickListener(v -> {
            mr.setEnabled(true);
            mc.setEnabled(true);
        });

        mmin.setOnClickListener(v -> {
            mr.setEnabled(true);
            mc.setEnabled(true);
        });



    }

    private void appendText(String value) {
        String actual = editTXT.getText().toString();
        editTXT.setText(actual + value);
    }

    private void getResult(String actual){
        char operador = ' ';

        if (actual.contains("+")) {
            operador = '+';
        } else if (actual.contains("-")) {
            operador = '-';
        } else if (actual.contains("*")) {
            operador = '*';
        } else if (actual.contains("/")) {
            operador = '/';
        }

        //posicion del operador
        int pos = actual.indexOf(operador);

        String num1 = actual.substring(0,pos);
        String num2 = actual.substring(pos+1);

        double n1 = Double.parseDouble(num1);
        double n2 = Double.parseDouble(num2);
        double resultado = 0;


        switch (operador) {
            case '+':
                resultado = n1 + n2;
                break;
            case '-':
                resultado = n1 - n2;
                break;
            case '*':
                resultado = n1 * n2;
                break;
            case '/':
                resultado = n1 / n2;
                break;
        }

        editTXT.setText(String.valueOf(resultado));
    }








}