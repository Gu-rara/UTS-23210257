package com.aderifkisabian.uts_23210257;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public boolean IsPrima(int n)
    {
        //Sumber: https://stackoverflow.com/questions/72109026/how-do-i-get-an-if-statement-to-check-if-a-user-given-number-is-a-prime-number#:~:text=Thus%2C%20you%20can%20keep%20a,1%2C8464%2019%2024

        // 1 and numbers less than 1 are not prime
        if (n <= 1) return false;

        // 2 and 3 are prime
        if (n <= 3) return true;

        // Check if n is divisible by 2 or 3
        if (n % 2 == 0 || n % 3 == 0) return false;

        // Optimization: Check divisors starting from 5 up to sqrt(n)
        // All primes except 2 and 3 follow the 6k ± 1 rule
        for (int i = 5; i * i <= n; i += 6) {
            if (n % i == 0 || n % (i + 2) == 0)
                return false;
        }

        return true;
    }

    public void InitBilanganPrima()
    {
        Spinner spinPrima = findViewById(R.id.spinnerPrima);

        List<String> primaList = new ArrayList<>();
        int j = 1;
        for(int i = 2; i <= 1000; i++)
        {
            if(IsPrima(i))
            {
                primaList.add("Bilangan prima ke-" + j + " adalah " + i);
                j++;
            }
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, primaList);
        spinPrima.setAdapter(arrayAdapter);
    }

    public void InitButton()
    {
        Button buttonPS = findViewById(R.id.buttonPlaystore);
        buttonPS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.android.vending");
                startActivity(launchIntent);
            }
        });
    }

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
        InitBilanganPrima();
        InitButton();
    }
}