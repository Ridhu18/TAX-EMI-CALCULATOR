package com.example.taxemicalculator;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button b1;
    Button b2;
    Button b3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b1 = findViewById(R.id.button1);
        b2 = findViewById(R.id.button2);
        b3=findViewById(R.id.button3);

        b1.setOnClickListener(view -> {
            Intent intent= new Intent(MainActivity.this,taxsplashscreen.class);
            startActivity(intent);
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        });

        b2.setOnClickListener(view -> {
            Intent intent= new Intent(MainActivity.this, emisplashscreen.class);
            startActivity(intent);
        });

        b3.setOnClickListener(view -> {
            Intent intent= new Intent(MainActivity.this, SipActivity.class);
            startActivity(intent);
        });



    }
}
