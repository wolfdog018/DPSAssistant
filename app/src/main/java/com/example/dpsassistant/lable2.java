package com.example.dpsassistant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class lable2 extends AppCompatActivity {

    LinearLayout dailyRoutine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lable2);

        dailyRoutine = findViewById(R.id.dailyRoutine);

        Button button = (Button)findViewById(R.id.remainder);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRemainder();
            }
        });





    }

    public void dailyroutine(View v){
        startActivity(new Intent(lable2.this, DailyRoutine.class));
    }

    public void openRemainder(){
        Intent intent = new Intent(this, Remainder.class);
        startActivity(intent);
    }

}
