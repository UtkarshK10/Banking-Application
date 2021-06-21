package co.mandeep_singh.bankingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CustomerInfo extends AppCompatActivity {
    private TextView email,name,balance;
    private Button transferButton;
    private Integer ID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Customer");
        setContentView(R.layout.activity_customer_info);
        name = findViewById(R.id.name_customer);
        email = findViewById(R.id.email_customer);
        balance = findViewById(R.id.balance_customer);
        Bundle extras = getIntent().getExtras();
        name.setText("Name : " + extras.getString("NAME"));
        email.setText("Email : "  + extras.getString("EMAIL"));
        balance.setText("Balance : "  + String.valueOf(extras.getDouble("BALANCE")));
        ID = extras.getInt("CUSTOMER_ID");

        transferButton = findViewById(R.id.button_transfer);
        transferButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),TransferScreen.class);
                i.putExtra("NAME",extras.getString("NAME"));
                i.putExtra("EMAIL",extras.getString("NAME"));
                i.putExtra("BALANCE",extras.getDouble("BALANCE"));
                i.putExtra("CUSTOMER_ID",extras.getInt("CUSTOMER_ID"));
                startActivity(i);
            }
        });
    }
}