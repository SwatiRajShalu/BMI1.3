package com.s1613.bmi12;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText height;
    EditText weight;
    Button btn_submit;
    Button btn_clear;
    TextView result1;
    TextView result2;
    TextView result3;

    TextView heading;
    RelativeLayout relativebody;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        height=findViewById(R.id.height);
        weight=findViewById(R.id.weight);
        result1=findViewById(R.id.result1);
        result2=findViewById(R.id.result2);
        result3=findViewById(R.id.result3);
        heading=findViewById(R.id.heading);
        relativebody=findViewById(R.id.relativebody);
        btn_submit=findViewById(R.id.submit_btn);
        btn_clear=findViewById(R.id.clear_btn);

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Animation alpha= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.alpha);
                heading.setAnimation(alpha);

                Animation scale= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.scale);
                result1.setAnimation(scale);


                int new_height = Integer.parseInt(height.getText().toString());
                int new_weight = Integer.parseInt(weight.getText().toString());

                //weight/(height)^2
                double n = (double) new_weight;
                double d = ((double) new_height / 100.0) * ((double) new_height / 100.0);
                double calculation = n / d;

                if (calculation < 18 && calculation != 18.00) {
                    double need = (18 * d) - n;
                    result1.setText(String.format("Your BMI : %.2f ", calculation));
                    result2.setText(String.format("Increase your weight by"));
                    result3.setText(String.format(" %.2f kg", need));
                    heading.setText("UNDERWEIGHT");
                    heading.setBackgroundColor(getResources().getColor(R.color.u_h));
                    relativebody.setBackgroundColor(getResources().getColor(R.color.u_b));
                } else if (calculation < 25) {
                    result1.setText(String.format("Your BMI : %.2f ", calculation));
                    result2.setText(String.format("You are healthy"));
                    result3.setText("");
                    heading.setText("HEALTHY");
                    heading.clearAnimation();
                    heading.setBackgroundColor(getResources().getColor(R.color.h_h));
                    relativebody.setBackgroundColor(getResources().getColor(R.color.h_b));
                } else if (calculation > 24) {
                    double need = n - (24 * d);
                    result1.setText(String.format("Your BMI : %.2f ", calculation));
                    result2.setText(String.format("Decrese your weight by"));
                    result3.setText(String.format(" %.2f kg", need));
                    heading.setText("OVERERWEIGHT");
                    heading.setBackgroundColor(getResources().getColor(R.color.o_h));
                    relativebody.setBackgroundColor(getResources().getColor(R.color.o_b));
                }
            }
        });

        btn_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                height.setText("");
                weight.setText("");
                result1.setText("");
                result2.setText("");
                result3.setText("");
                heading.setText("BMI");
                heading.clearAnimation();
                heading.setBackgroundColor(getResources().getColor(R.color.teal_blue));
                relativebody.setBackgroundColor(getResources().getColor(R.color.light_blue));
            }
        });


    }
}