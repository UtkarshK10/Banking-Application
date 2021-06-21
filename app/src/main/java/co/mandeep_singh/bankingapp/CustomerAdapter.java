package co.mandeep_singh.bankingapp;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.MyViewHolder> {

        private List<Customer> customerList;
        private Activity context;

        public CustomerAdapter(Activity context, List<Customer> list){
            this.customerList = list;
            this.context = context;
        }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = context.getLayoutInflater();
        View view = inflater.inflate(R.layout.customer_card,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
     Customer customer = customerList.get(position);
     holder.name.setText(customer.get_name());
     holder.card.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             Intent i = new Intent(context,CustomerInfo.class);
             i.putExtra("NAME",customer.get_name());
             i.putExtra("EMAIL",customer.get_email());
             i.putExtra("BALANCE",customer.get_balance());
             i.putExtra("CUSTOMER_ID",customer.get_id());
             context.startActivity(i);
         }
     });
    }


        @Override
        public int getItemCount() {
            return customerList.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder{
            TextView name;
            CardView card;
            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                name = itemView.findViewById(R.id.name);
                card = (CardView) itemView.findViewById(R.id.card);

            }
        }
    }

