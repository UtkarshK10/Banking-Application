package co.mandeep_singh.bankingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import java.util.List;

public class AllCustomers extends AppCompatActivity {
    private RecyclerView recyclerView;
    DatabaseHandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_all_customers);
        db = new DatabaseHandler(this);
        recyclerView = findViewById(R.id.recycleview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        getAllCustomers();
    }


    public void getAllCustomers(){
            List<Customer> list = db.getAllCustomers();
        CustomerAdapter customerAdapter = new CustomerAdapter(this, list);
        recyclerView.setAdapter(customerAdapter);
    }
}