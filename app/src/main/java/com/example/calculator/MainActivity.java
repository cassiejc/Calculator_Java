package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button b1, b2, b3, b4, b5, b6, b7, b8, b9, b0, bac, bc, bplus, bminus, bmultiplied, bdivided, btotal, bpercentage, bcoma;
    TextView t1, t2;
    ImageView i1;
    private int Counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b1 = findViewById(R.id.button);
        b2 = findViewById(R.id.button2);
        b3 = findViewById(R.id.button3);
        b4 = findViewById(R.id.button4);
        b5 = findViewById(R.id.button5);
        b6 = findViewById(R.id.button6);
        b7 = findViewById(R.id.button7);
        b8 = findViewById(R.id.button8);
        b9 = findViewById(R.id.button9);
        b0 = findViewById(R.id.button10);
        bac = findViewById(R.id.button11);
        bc = findViewById(R.id.button12);
        bplus = findViewById(R.id.buttonplus);
        bminus = findViewById(R.id.buttonmin);
        bmultiplied = findViewById(R.id.buttonmultiplied);
        bdivided = findViewById(R.id.buttondivided);
        btotal = findViewById(R.id.buttontotal);
        bpercentage = findViewById(R.id.buttonpercentage);
        bcoma = findViewById(R.id.buttoncoma);
        t1 = findViewById(R.id.textView1);
        t2 = findViewById(R.id.textViewtotal);
        i1 = findViewById(R.id.iconImageView);
        i1.setVisibility(View.INVISIBLE);
        bc.setVisibility(View.INVISIBLE);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update("1");
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update("2");
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update("3");
            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update("4");
            }
        });

        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update("5");
            }
        });

        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update("8");
            }
        });

        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update("7");
            }
        });

        b8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update("6");
            }
        });

        b9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update("9");
            }
        });

        b0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update("0");
            }
        });
        bac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearNumber();
            }
        });
        bc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteLastCharacter();
            }
        });
        bplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update("+");
            }
        });
        bminus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update("-");
            }
        });
        bdivided.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update(":");
            }
        });
        bmultiplied.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update("*");
            }
        });
        btotal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateTotal();
            }
        });
        bpercentage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update("%");
//                calculatePercentage();
            }
        });
        bcoma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update(".");
            }
        });
    }
    private void update(String newnumber){
        String number = t1.getText().toString();
        if(number.length()<11){
            t1.setText(number + newnumber);
            Counter++;
            bc.setVisibility(View.VISIBLE);
            i1.setVisibility(View.VISIBLE);
        }else {
            Toast.makeText(this, "Maximum number of digits reached", Toast.LENGTH_SHORT).show();
        }
    }
    private void clearNumber(){
        t1.setText("");
        t2.setText("");
        Counter = 0;
        bc.setVisibility(View.INVISIBLE);
        i1.setVisibility(View.INVISIBLE);
    }
    private void deleteLastCharacter() {
        String currentText = t1.getText().toString();
        if (currentText.length() > 0) {
            String newText = currentText.substring(0, currentText.length() - 1);
            t1.setText(newText);
            if (newText.isEmpty()) {
                bc.setVisibility(View.INVISIBLE);
                i1.setVisibility(View.INVISIBLE);
            }
        }
    }
    private void calculateTotal() {
        String expression = t1.getText().toString();
        String[] parts;
        double total = 0;

        if (expression.contains("+")) {
            parts = expression.split("\\+");
            for (String part : parts) {
                total += Double.parseDouble(part.trim());
            }
        } else if (expression.contains("-")) {
            parts = expression.split("-");
            if (parts.length == 2) {
                total = Double.parseDouble(parts[0].trim()) - Double.parseDouble(parts[1].trim());
            } else {
                Toast.makeText(this, "Invalid input", Toast.LENGTH_SHORT).show();
                return;
            }
        } else if (expression.contains("*")) {
            parts = expression.split("\\*");
            for (String part : parts) {
                if (total == 0) {
                    total = 1; // Initialize with 1 for multiplication
                }
                total *= Double.parseDouble(part.trim());
            }
        } else if (expression.contains(":")) {
            parts = expression.split(":");
            if (parts.length == 2) {
                total = Double.parseDouble(parts[0].trim()) / Double.parseDouble(parts[1].trim());
            } else {
                Toast.makeText(this, "Invalid input", Toast.LENGTH_SHORT).show();
                return;
            }
        } else if (expression.contains("%")) {
            parts = expression.split("%");
            if (parts.length == 2) {
                total = (Double.parseDouble(parts[0].trim()) * Double.parseDouble(parts[1].trim())) / 100;
            } else {
                Toast.makeText(this, "Invalid input for percentage", Toast.LENGTH_SHORT).show();
                return;
            }
        } else {
            Toast.makeText(this, "Invalid input", Toast.LENGTH_SHORT).show();
            return;
        }
        if(total % 1 == 0){
            t2.setText("= " + (int) total);
        }else {
            t2.setText("= " + total);
        }
    }

}