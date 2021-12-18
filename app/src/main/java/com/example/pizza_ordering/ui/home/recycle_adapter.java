package com.example.pizza_ordering.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pizza_ordering.R;

import java.util.ArrayList;

public class recycle_adapter extends RecyclerView.Adapter<recycle_adapter.Myviewholder> {
    private ArrayList<getset> values;

    public recycle_adapter(ArrayList<getset> data) {
        this.values=data;

    }

    @NonNull
    @Override
    public recycle_adapter.Myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_design, parent, false);

       // view.setOnClickListener(MainActivity.myOnClickListener);

        Myviewholder myViewHolder = new Myviewholder(view);
        return myViewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull recycle_adapter.Myviewholder holder, int position) {
getset model=values.get(position);
        holder.item_name.setText(model.getName());
        holder.price.setText("$"+model.getPrice());
    }

    @Override
    public int getItemCount() {
        return values.size();
    }

    public class Myviewholder extends RecyclerView.ViewHolder {
        TextView item_name,price;

        public Myviewholder(@NonNull View itemView) {
            super(itemView);
            this.item_name=itemView.findViewById(R.id.item_name);
            this.price=itemView.findViewById(R.id.price);
        }
    }
}
