package com.example.taxemicalculator;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Intent;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.appcompat.app.AppCompatActivity;

public class SipActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sip_calculator);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.nav_sip_calculator);

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
            Intent splashIntent = new Intent(SipActivity.this, sipsplashscreen.class);
            if (id == R.id.nav_income_calculator)
            {
                splashIntent = new Intent(SipActivity.this,taxsplashscreen.class);

            } else if (id == R.id.nav_sip_calculator)
            {
                return true;

            } else if (id == R.id.nav_emi_calculator)
            {
                splashIntent = new Intent(SipActivity.this, emisplashscreen.class);
            }
            startActivity(splashIntent);
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

            return true;
        });



        EditText amountInput = findViewById(R.id.amountInput);
        EditText interestRateInput = findViewById(R.id.interestRateInput);
        EditText yearsInput = findViewById(R.id.yearsInput);
        Button calculateButton = findViewById(R.id.calculateButton);
        TextView ExpectedAmountTextView = findViewById(R.id.ExpectedAmountTextView);
        TextView AmountInvestedTextView = findViewById(R.id.AmountInvestedTextView);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView WealthGainTextView = findViewById(R.id.WealthGainTextView);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String amountStr = amountInput.getText().toString();
                String interestRateStr = interestRateInput.getText().toString();
                String yearsStr = yearsInput.getText().toString();

                if (!amountStr.isEmpty() && !interestRateStr.isEmpty() && !yearsStr.isEmpty())
                {
                    double amount = Double.parseDouble(amountStr);
                    double annualRate = Double.parseDouble(interestRateStr);
                    int years = Integer.parseInt(yearsStr);

                    double monthlyRate = annualRate / 100 / 12;
                    int months = years * 12;


                    double ExpectedAmount = amount * ((Math.pow(1 + monthlyRate, months) - 1) *(1+monthlyRate)/ monthlyRate);
                    ExpectedAmountTextView.setText(String.format("Expected Amount:%.2f", ExpectedAmount));

                    double AmountInvested=amount*12;
                    AmountInvestedTextView.setText(String.format("Amount Invested:%.2f",AmountInvested));

                    double WealthGain=ExpectedAmount-AmountInvested;
                    WealthGainTextView.setText(String.format("Wealthh Gain:%.2f",WealthGain));

                }
                else
                {
                    if (TextUtils.isEmpty(amountStr))
                    {
                        amountInput.setError("Enter Principal Amount");
                        amountInput.requestFocus();
                        return;
                    }
                    if (TextUtils.isEmpty(interestRateStr))
                    {
                        interestRateInput.setError("Enter Interest Rate");
                        interestRateInput.requestFocus();
                        return;
                    }
                    if (TextUtils.isEmpty(yearsStr))
                    {
                        yearsInput.setError("Enter Number Of Years");
                        yearsInput.requestFocus();
                        return;
                    }
                }
            }
        });
    }
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(SipActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}