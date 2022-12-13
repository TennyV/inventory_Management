package com.snhu.inventorymanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton addItem;
    RecyclerView recyclerView;
    List<ItemAttributes> itemList= new ArrayList<ItemAttributes>();
    DataBaseHelper DB;
    Adapter adapter;
    EditText itemName,itemQuantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        recyclerView = findViewById(R.id.my_recycler_view);
        //adapter = new Adapter(this, itemName, itemQuantity);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        displayData();
    }
    private void displayData() {
     //   Cursor cursor = DB.addOne();
       // if(cursor.getCount()==0)
        {
            Toast.makeText(MainActivity.this, "No Valid Entry", Toast.LENGTH_SHORT).show();
           // return;

    }

        addItem = (findViewById(R.id.floatingActionButton));



        addItem.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), AddItemActivity.class);
            startActivity(intent);
        });
    }
}