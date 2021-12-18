package com.example.pizza_ordering.ui.orders;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pizza_ordering.DatabaseHelper;
import com.example.pizza_ordering.R;

import com.example.pizza_ordering.ui.home.Recycle_touchListener;
import com.example.pizza_ordering.ui.home.getset;
import com.example.pizza_ordering.ui.home.recycle_adapter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class OrdersList extends Fragment {

    private SlideshowViewModel slideshowViewModel;
    //private FragmentSlideshowBinding binding;
    private static RecyclerView recyclerView;
    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    getset getset1, getset2, getset3;
    private static ArrayList<getset> data;
    private static ArrayList<getset> data1;
//    Button order;
//    EditText total;
    DatabaseHelper databaseHelper;
    //double total_amount=0.0;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                new ViewModelProvider(this).get(SlideshowViewModel.class);

        View root = inflater.inflate(R.layout.fragment_orders_list, container, false);
      //  View root = binding.getRoot();

        databaseHelper = new DatabaseHelper(getContext());
        recyclerView = root.findViewById(R.id.recyclerView);
       // order=root.findViewById(R.id.order);
        //total=root.findViewById(R.id.total);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        getset2 = new getset();
        data = new ArrayList<getset>();
        data1 = new ArrayList<getset>();

        Cursor cursor = databaseHelper.getAllOrders();

        if (cursor.moveToFirst()) {
            do {


                    data.add(new getset(
                            cursor.getInt(0),
                            cursor.getInt(1),
                            cursor.getString(2),
                            cursor.getDouble(3)
                    ));
//                Set<getset> s = new HashSet<getset>(data1);
//               s.add(new getset(
//                        cursor.getInt(0),
//                        cursor.getInt(1),
//                        cursor.getString(2),
//                        cursor.getDouble(3)
//                ));
//
//                for (getset x : s)
//                    data.add(x);
                // total_amount=cursor.getDouble(2)+total_amount;
            } while (cursor.moveToNext());

            //passing the databasehelper instance this time to the adapter


            adapter = new order_adapter(data);
            recyclerView.setAdapter(adapter);

        }
        recyclerView.addOnItemTouchListener(new Recycle_touchListener(getContext(), recyclerView, new Recycle_touchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {

                Toast.makeText(getContext(), "Press Long to delete Item from cart", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onLongClick(View view, int position) {
               // Toast.makeText(getContext(), ""+data.get(position).getId(), Toast.LENGTH_SHORT).show();
                if (databaseHelper.deleteOrder_items(data.get(position).getId()))
                    Toast.makeText(getContext(), "Successfully Deleted", Toast.LENGTH_SHORT).show();
                    loadItemsFromDatabaseAgain();

            }
        }));
      //  total.setText(total_amount+"");
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }




    private void loadItemsFromDatabaseAgain() {
        //calling the read method from database instance
      //  total_amount=0.0;
        Cursor cursor = databaseHelper.getAllOrders();

        data.clear();
        if (cursor.moveToFirst()) {
            do {
                data.add(new getset(
                        cursor.getInt(0),
                        cursor.getInt(1),
                        cursor.getString(2),
                        cursor.getDouble(3)
                ));
               // total_amount=cursor.getDouble(2)+total_amount;
            } while (cursor.moveToNext());
        }
        adapter.notifyDataSetChanged();
       // total.setText(total_amount+"");
    }
}