package com.example.taxemicalculator;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Intent;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;

public class EmiActivity extends AppCompatActivity {
    Button emiCalcBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.emi_activity);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.nav_emi_calculator);

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
            Intent splashIntent = new Intent(EmiActivity.this, emisplashscreen.class);
            if (id == R.id.nav_income_calculator)
            {
                splashIntent = new Intent(EmiActivity.this, taxsplashscreen.class);

            } else if (id == R.id.nav_sip_calculator)
            {
                splashIntent = new Intent(EmiActivity.this, taxsplashscreen.class);

            } else if (id == R.id.nav_emi_calculator)
            {
                return true;
            }
            startActivity(splashIntent);
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

            return true;
        });

        final EditText P = findViewById(R.id.principal);
        final EditText I = findViewById(R.id.interest);
        final EditText Y = findViewById(R.id.years);
        final TextView TI = findViewById(R.id.interest_total);
        final TextView result = findViewById(R.id.emi) ;

        emiCalcBtn = findViewById(R.id.btn_calculate2);
        emiCalcBtn.setOnClickListener(v ->
        {
            String st1 = P.getText().toString();
            String st2 = I.getText().toString();
            String st3 = Y.getText().toString();
            if (TextUtils.isEmpty(st1))
            {
                P.setError("Enter Principal Amount");
                P.requestFocus();
                return;
            }
            if (TextUtils.isEmpty(st2))
            {
                I.setError("Enter Interest Rate");
                I.requestFocus();
                return;
            }
            if (TextUtils.isEmpty(st3))
            {
                Y.setError("Enter Years");
                Y.requestFocus();
                return;
            }
            float p = Float.parseFloat(st1);
            float i = Float.parseFloat(st2);
            float y = Float.parseFloat(st3);
            float Principal = calPric(p);
            float Rate = calInt(i);
            float Months = calMonth(y);
            float Dvdnt = calDvdnt( Rate, Months);
            float FD = calFinalDvdnt (Principal, Rate, Dvdnt);
            float D = calDivider(Dvdnt);
            float emi = calEmi(FD, D);
            float TA = calTa (emi, Months);
            float ti = calTotalInt(TA, Principal);
            result.setText(String.valueOf(emi));
            TI.setText(String.valueOf(ti));
        });
    }
    public  float calPric(float p)
    {
        return (p);
    }

    public  float calInt(float i)
    {
        return (i/12/100);
    }

    public  float calMonth(float y)
    {
        return (y * 12);
    }

    public  float calDvdnt(float Rate, float Months)
    {
        return (float) (Math.pow(1+Rate, Months));
    }

    public  float calFinalDvdnt(float Principal, float Rate, float Dvdnt)
    {
        return (Principal * Rate * Dvdnt);
    }

    public  float calDivider(float Dvdnt)
    {
        return (Dvdnt-1);
    }

    public  float calEmi(float FD, Float D)
    {
        return (FD/D);
    }

    public  float calTa(float emi, Float Months)
    {
        return (emi*Months);
    }

    public  float calTotalInt(float TA, float Principal)
    {
        return (TA - Principal);
    }
}