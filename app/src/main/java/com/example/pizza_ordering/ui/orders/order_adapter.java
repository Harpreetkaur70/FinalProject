package com.example.pizza_ordering.ui.orders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pizza_ordering.R;
import com.example.pizza_ordering.ui.home.getset;

import java.util.ArrayList;

public class order_adapter extends RecyclerView.Adapter<order_adapter.Myviewholder> {
    private ArrayList<getset> values;

    public order_adapter(ArrayList<getset> data) {
        this.values=data;

    }

    @NonNull
    @Override
    public order_adapter.Myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.orders_row_design, parent, false);

        // view.setOnClickListener(MainActivity.myOnClickListener);

        Myviewholder myViewHolder = new Myviewholder(view);
        return myViewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull order_adapter.Myviewholder holder, int position) {
        getset model=values.get(position);

                holder.order_id.setText(model.getOrderid()+"");
                holder.item_name.setText(model.getName());
                holder.price.setText("$"+model.getPrice());

    }

    @Override
    public int getItemCount() {
        return values.size();
    }

    public class Myviewholder extends RecyclerView.ViewHolder {
        TextView item_name,price,order_id;

        public Myviewholder(@NonNull View itemView) {
            super(itemView);
            this.order_id=itemView.findViewById(R.id.orderid);
            this.item_name=itemView.findViewById(R.id.item_name);
            this.price=itemView.findViewById(R.id.price);
        }
    }
}

