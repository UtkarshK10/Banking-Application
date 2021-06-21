package co.mandeep_singh.bankingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TransferScreen extends AppCompatActivity {
    private EditText transferAmount;
    private Button button;
    private Integer ID;
    private  String name, email;
    private Double balance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Transaction");
        setContentView(R.layout.activity_tranfer_screen);
        transferAmount = findViewById(R.id.transferAmount);
        button = findViewById(R.id.button_confirm);

        Bundle extras = getIntent().getExtras();
        ID = extras.getInt("CUSTOMER_ID");
        name = extras.getString("CUSTOMER_NAME");
        email = extras.getString("CUSTOMER_EMAIL");
        balance = extras.getDouble("BALANCE");

        Customer customer = new Customer(ID,name,email,balance);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(transferAmount.getText().toString().trim().equals("")){
                    Toast.makeText(getApplicationContext(),"TRANSACTION AMOUNT CANNOT BE EMPTY", Toast.LENGTH_SHORT).show();
                }else{
                DatabaseHandler db = new DatabaseHandler(getApplicationContext());
                db.updateCustomer(customer,Double.parseDouble(transferAmount.getText().toString()));
                db.addTransaction(new Transaction(ID,Double.parseDouble(transferAmount.getText().toString())));
                Intent i = new Intent(getApplicationContext(),AllCustomers.class);
                Toast.makeText(getApplicationContext(),"Hurray !! Added successfully", Toast.LENGTH_SHORT).show();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(i);
                    }
                },2000);
            }}
        });
    }
}