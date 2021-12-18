package com.example.pizza_ordering.ui.cart;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pizza_ordering.DatabaseHelper;
import com.example.pizza_ordering.R;
import com.example.pizza_ordering.ui.home.Recycle_touchListener;
import com.example.pizza_ordering.ui.home.getset;
import com.example.pizza_ordering.ui.home.recycle_adapter;

import java.util.ArrayList;

public class Cart_Items extends Fragment {


    private static RecyclerView recyclerView;
    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    getset getset1, getset2, getset3;
    private static ArrayList<getset> data;
    Button order;
    EditText total;
    DatabaseHelper databaseHelper;
    ArrayList<String> ordered_items;
    int orderid;
    double total_amount=0.0;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View root=inflater.inflate(R.layout.fragment_cart, container, false);



        databaseHelper = new DatabaseHelper(getContext());
        recyclerView = root.findViewById(R.id.recyclerView);
        order=root.findViewById(R.id.order);
        total=root.findViewById(R.id.total);
        recyclerView.setHasFixedSize(true);
        ordered_items=new ArrayList<>();
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        getset2 = new getset();
        data = new ArrayList<getset>();


        Cursor cursor = databaseHelper.getAllCart_items();

        if (cursor.moveToFirst()) {
            do {
                data.add(new getset(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getDouble(2)
                ));
                total_amount=cursor.getDouble(2)+total_amount;
                ordered_items.add(  cursor.getString(1));
            } while (cursor.moveToNext());

            //passing the databasehelper instance this time to the adapter


            adapter = new recycle_adapter(data);
            recyclerView.setAdapter(adapter);
            order.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    for(String name: ordered_items) {
                        double tot = Double.parseDouble(total.getText().toString());
                        Cursor cursor = databaseHelper.getOrder_id();

                        if (cursor.moveToFirst()) {
                            do {



                                        orderid=cursor.getInt(0);


                            } while (cursor.moveToNext());


                        }
                        if (databaseHelper.addOrders(orderid+1, name, tot)) {
                            Toast.makeText(getContext(), "Ordered has been placed", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });

        }
        recyclerView.addOnItemTouchListener(new Recycle_touchListener(getContext(), recyclerView, new Recycle_touchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {

                Toast.makeText(getContext(), "Press Long to delete Item from cart", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onLongClick(View view, int position) {

                if (databaseHelper.deleteCart_items(data.get(position).getId()))
                    Toast.makeText(getContext(), "        \n" +
                            "                Item has been deleted"+data.get(position).getId(), Toast.LENGTH_SHORT).show();
                    loadItemsFromDatabaseAgain();

            }
        }));
        total.setText("$"+total_amount);
        return root;
    }
        @Override
        public void onDestroyView () {
            super.onDestroyView();

        }

    private void loadItemsFromDatabaseAgain() {
        //calling the read method from database instance
        total_amount=0.0;
        Cursor cursor = databaseHelper.getAllCart_items();

        data.clear();
        if (cursor.moveToFirst()) {
            do {
                data.add(new getset(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getDouble(2)
                ));
                total_amount=cursor.getDouble(2)+total_amount;
            } while (cursor.moveToNext());
        }
        adapter.notifyDataSetChanged();
        total.setText(total_amount+"");
    }
    }
