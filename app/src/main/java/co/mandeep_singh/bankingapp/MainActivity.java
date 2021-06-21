package co.mandeep_singh.bankingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private Button button,button2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        button = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);

        DatabaseHandler db = new DatabaseHandler(this);
        List list = db.getAllCustomers();
        if(list.size()!=0){
            button2.setVisibility(View.GONE);
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),AllCustomers.class));
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.addCustomer(new Customer("MANDY", "mandy@gmail.com", 5000.00));
                db.addCustomer(new Customer("SANDY", "sandy@gmail.com", 6000.00));
                db.addCustomer(new Customer("RANDY", "randy@gmail.com", 7000.00));
                db.addCustomer(new Customer("CANDY", "candy@gmail.com", 8000.00));
                db.addCustomer(new Customer("KANDY", "kandy@gmail.com", 9000.00));
                db.addCustomer(new Customer("RANJAN", "ranjan@gmail.com", 5000.00));
                db.addCustomer(new Customer("KRISHN", "krishn@gmail.com", 6000.00));
                db.addCustomer(new Customer("YUGANT", "yugant@gmail.com", 7000.00));
                db.addCustomer(new Customer("PRAJAPATI", "prajapati@gmail.com", 8000.00));
                db.addCustomer(new Customer("YASH", "yash@gmail.com", 9000.00));
            }
        });
    }
}