package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.StringTokenizer;
import java.util.ArrayList;


public class ArnavGoel extends AppCompatActivity {

    Button b1, b2, b3, b4, b5, b6, b7, b8, b9, b0, add, sub, mult, div, c, eq, per, bksp, sqrt;
    TextView t1;
    double num1;
    ArrayList<String> ops = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1 = findViewById(R.id.button);
        b2 = findViewById(R.id.button2);
        b3 = findViewById(R.id.button3);
        b4 = findViewById(R.id.button10);
        b5 = findViewById(R.id.button11);
        b6 = findViewById(R.id.button12);
        b7 = findViewById(R.id.button14);
        b8 = findViewById(R.id.button15);
        b9 = findViewById(R.id.button16);
        b0 = findViewById(R.id.button19);
        add = findViewById(R.id.button4);
        sub = findViewById(R.id.button13);
        mult = findViewById(R.id.button17);
        div = findViewById(R.id.button21);
        c = findViewById(R.id.button18);
        eq = findViewById(R.id.button20);
        per = findViewById(R.id.button5);
        bksp = findViewById(R.id.button6);
        sqrt = findViewById(R.id.button8);

        t1 = findViewById(R.id.textView);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                t1.setText(t1.getText()+"1");
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                t1.setText(t1.getText()+"2");
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                t1.setText(t1.getText()+"3");
            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                t1.setText(t1.getText()+"4");
            }
        });

        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                t1.setText(t1.getText()+"5");
            }
        });

        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                t1.setText(t1.getText()+"6");
            }
        });

        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                t1.setText(t1.getText()+"7");
            }
        });

        b8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                t1.setText(t1.getText()+"8");
            }
        });

        b9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                t1.setText(t1.getText()+"9");
            }
        });

        b0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                t1.setText(t1.getText()+"0");
            }
        });

        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                t1.setText("");
                ops.clear();
                num1 = 0.0;
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //num1 = Double.parseDouble(t1.getText().toString());
                t1.setText(t1.getText()+"+");
                //ops.add("addition");
            }
        });

        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //num1 = Double.parseDouble(t1.getText().toString());
                t1.setText(t1.getText()+"-");
                //ops.add("subtraction");
            }
        });

        mult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // num1 = Double.parseDouble(t1.getText().toString());
                t1.setText(t1.getText()+"*");
                //ops.add("multiplication");
            }
        });

        div.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // num1 = Double.parseDouble(t1.getText().toString());
                t1.setText(t1.getText()+"/");
                //ops.add("division");
            }
        });

        eq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str = t1.getText().toString();
                System.out.println("103str: " + str);
                ArrayList<String> list = new ArrayList<String>();
                StringTokenizer number = new StringTokenizer(str, "+-");
                StringTokenizer operations = new StringTokenizer(str, "*/0123456789.");
                while(operations.hasMoreElements()){
                    String s = operations.nextToken();
                    ops.add(s);
                }
                System.out.println("103ops: " + ops);
                while(number.hasMoreElements()){
                    String s = number.nextToken();
                    list.add(s);
                }
                for(int i = 0; i < list.size(); i++){
                    list.set(i, pemdas(list.get(i))+"");
                    System.out.println("103pemdas: " + pemdas(list.get(i)));
                }
                System.out.println("103list: " + list);
                if(ops.size() >= 1) {
                    if(str.charAt(0)=='-'){
                        list.set(0, String.valueOf(Double.parseDouble(list.get(0))*-1.0));
                        System.out.println("103minuslist: " + list);
                        ops.remove(0);
                        System.out.println("103minusremovedlist: " + ops);
                    }
                    num1 = Double.parseDouble(list.get(0));
                    System.out.println("103initial" + num1);
                    for (int i = 1; i < list.size() - 1; i++) {
                        System.out.println("103enteredforloop");
                        if (ops.get(i - 1).equals("+")) {
                            num1 += Double.parseDouble(list.get(i));
                            System.out.println("103 1+: " + num1 + "");
                        }
                        if (ops.get(i - 1).equals("-")) {
                            num1 -= Double.parseDouble(list.get(i));
                            System.out.println("103 1-: " + num1 + "");

                        }
                        if (ops.get(i - 1).equals("*")) {
                            num1 *= Double.parseDouble(list.get(i));
                            System.out.println("103 1*: " + num1 + "");

                        }
                        if (ops.get(i - 1).equals("/")) {
                            num1 /= Double.parseDouble(list.get(i));
                            System.out.println("103 1/: " + num1 + "");

                        }
                    }
                    if(ops.size()==0){
                        num1 = Double.parseDouble(list.get(0));
                        System.out.println("103finalnum" + num1);
                    }
                    else{
                        if (ops.get(ops.size() - 1).equals("+")) {
                            num1 += Double.parseDouble(list.get(list.size() - 1));
                            System.out.println("103 2+: " + num1 + "");
                        }
                        if (ops.get(ops.size() - 1).equals("-")) {
                            num1 -= Double.parseDouble(list.get(list.size() - 1));
                            System.out.println("103 2-: " + num1 + "");
                        }

                        if (ops.get(ops.size() - 1).equals("*")) {
                            num1 *= Double.parseDouble(list.get(list.size() - 1));
                            System.out.println("103 2*: " + num1 + "");
                        }
                        if (ops.get(ops.size() - 1).equals("/")) {
                            num1 /= Double.parseDouble(list.get(list.size() - 1));
                            System.out.println("103 2/: " + num1 + "");
                        }
                    }
                }
                else{
                    num1 = Double.parseDouble(list.get(0));
                    System.out.println("103finalnum" + num1);
                }
                t1.setText(num1+"");
                int count = 0;
                StringTokenizer checkOps = new StringTokenizer(str, "0123456789.");
                ArrayList<String> opsCheck = new ArrayList<String>();
                while(checkOps.hasMoreElements()){
                    String s = checkOps.nextToken();
                    opsCheck.add(s);
                }
                for(int i = 0; i < opsCheck.size(); i++){
                    if(opsCheck.get(i).length() > 1)
                        count++;
                }
                if(count > 0){
                    t1.setText("Error: Please type only one operator at a time");
                }
                if(t1.getText().toString().equalsIgnoreCase("Infinity")){
                    t1.setText("Error: Cannot divide by 0");
                }
                if(str.charAt(0) == '+' || str.charAt(0) == '*' || str.charAt(0) == '/'){
                    t1.setText("Error: Start with a number or negative sign");
                }
                ops.clear();
            }
        });
        per.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double num = Double.parseDouble(t1.getText().toString());
                double percent = 100.0;
                double value = num/percent;
                t1.setText(String.valueOf(value));
            }
        });
        bksp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                t1.setText(t1.getText().toString().substring(0,t1.getText().toString().length()-1));
            }
        });
        sqrt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double val = Double.parseDouble(t1.getText().toString());
                if(val < 0){
                    t1.setText("Error: only sqrt positive numbers");
                }
                else {
                    double sqrt = Math.sqrt(val);
                    t1.setText(String.valueOf(sqrt));
                }
            }
        });
    }
    public double pemdas(String str){
        double sum;
        StringTokenizer num = new StringTokenizer(str, "*/");
        StringTokenizer oper = new StringTokenizer(str, "0123456789.");
        if(num.countTokens() < 1)
            return 0;
        sum = Double.parseDouble(num.nextToken());
        while(num.hasMoreElements()){
            String opera = oper.nextToken();
            String numb = num.nextToken();

            if(opera.equals("*"))
                sum *= Double.parseDouble(numb);
            if(opera.equals("/"))
                sum /= Double.parseDouble(numb);

        }
        return sum;
    }
}









