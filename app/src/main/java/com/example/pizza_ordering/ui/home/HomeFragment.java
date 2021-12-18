package com.example.pizza_ordering.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.example.pizza_ordering.databinding.FragmentHomeBinding;

import java.util.ArrayList;

public class HomeFragment extends Fragment {


    private FragmentHomeBinding binding;
    private static RecyclerView recyclerView;
    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
getset getset1,getset2,getset3,getset4,getset5,getset6,getset7,getset8,getset9,getset10,getset11;
    private static ArrayList<getset> data;
    DatabaseHelper databaseHelper;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


      View root= inflater.inflate(R.layout.fragment_home, container, false);

         databaseHelper=new DatabaseHelper(getContext());
        recyclerView = root.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
getset2=new getset();
        data = new ArrayList<getset>();
        getset1=new getset("Margherita",21);
         getset3=new getset("Farmhouse",45);
        getset4=new getset("Peppy Paneer",45);
         getset5=new getset("Veg Extravaganza",49);
        getset6=new getset("Vegie Paradise",41);
        getset7=new getset("Cheese n Corn",54);
        getset8=new getset("The Cheese Dominator",89);
        getset9=new getset("The 4 Cheese Pizza",55);
        getset10=new getset("Deluxe Veggie",43);
        getset11=new getset("Fresh Veggie",70);
        data.add(getset1);
        data.add(getset3);
        data.add(getset4);        data.add(getset5);        data.add(getset6);        data.add(getset7);
        data.add(getset8);        data.add(getset9);        data.add(getset10);        data.add(getset11);


        adapter = new recycle_adapter(data);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnItemTouchListener(new Recycle_touchListener(getContext(), recyclerView, new Recycle_touchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {

                //Toast.makeText(getContext(), ""+data.get(position), Toast.LENGTH_SHORT).show();
                String item_name=data.get(position).getName();
              double price= data.get(position).getPrice();
                if(databaseHelper.addItems(item_name,price)){
                Toast.makeText(getContext(), "Added to Cart ", Toast.LENGTH_SHORT).show();}
                else{
                    Toast.makeText(getContext(), "not added", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}