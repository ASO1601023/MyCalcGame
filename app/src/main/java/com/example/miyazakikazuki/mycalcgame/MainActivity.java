package com.example.miyazakikazuki.mycalcgame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements OnClickListener {
    private int result = 0;
    private int before_operator = -1;
    private int value = 0;
    private boolean wait = false;

    private int[] Numbers = { R.id.button0, R.id.button1, R.id.button2,
            R.id.button3, R.id.button4, R.id.button5, R.id.button6,
            R.id.button7, R.id.button8, R.id.button9 };
    private int[] Operators = {R.id.buttonP, R.id.buttonM, R.id.buttonK, R.id.buttonW};

    private void calculate(){
        switch(before_operator){
            case R.id.buttonP:
                result = value + result;
                break;

            case R.id.buttonM:
                result = value - result;
                break;

            case R.id.buttonK:
                result = value * result;
                break;

            case R.id.buttonW:
                result = value / result;
                break;
        }
        value = 0;
        before_operator = -1;
        wait = true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for(int i=0;i<10;i++){
            Button button = (Button)findViewById(Numbers[i]);
            button.setOnClickListener(this);
        }

        for(int i=0;i<4;i++){
            Button button = (Button)findViewById(Operators[i]);
            button.setOnClickListener(this);
        }

        Button clear = (Button)findViewById(R.id.buttonC);
        clear.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                result = 0;
                before_operator = -1;
                value = 0;
                show();
            }
        });

    }

    private void show(){
        TextView textview = (TextView)findViewById(R.id.textView);
        textview.setText(Integer.toString(result));
    }

    @Override
    public void onClick(View v) {
        for(int i=0;i<10;i++){
            if(v.getId() == Numbers[i]){
                if(wait){
                    wait = false;
                    result = 0;
                }
                result = result*10 + i;
                show();
                return ;
            }
        }

        if(before_operator != -1){
            calculate();
            show();
        }
        before_operator = v.getId();
        value = result;
        wait = true;
    }

    public void onClickGame(View view){
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }



}