package com.snhu.inventorymanagement;

import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddItemActivity extends AppCompatActivity {

    Button addButton, subtractButton, settingsButton;
    EditText inventoryAdd, addQuantity;
    DataBaseHelper DB;
    Switch toggleSMS;
    ListView inventoryList;
    ArrayAdapter itemArrayAdapter;
    DataBaseHelper dataBaseHelper;
    String phoneNumber = "867-5309", message = "Stock Depleted!";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        //Edit Text
        inventoryAdd = findViewById(R.id.inventoryAdd);
        addQuantity = findViewById(R.id.addQuantity);
        //Buttons for program
        addButton = findViewById(R.id.addButton);
        subtractButton = findViewById(R.id.subtractButton);
        settingsButton = findViewById(R.id.settingsButton);
        inventoryList = findViewById(R.id.lv_inventoryList);
        toggleSMS = (Switch) findViewById(R.id.toggleSMS);

        dataBaseHelper = new DataBaseHelper(AddItemActivity.this);

        showItemsonListView();


        DB = new DataBaseHelper(this);

        //button listeners for add and view buttons
        addButton.setOnClickListener(v -> {

            ItemAttributes itemAttributes;

            try {
                itemAttributes = new ItemAttributes(-1, inventoryAdd.getText().toString(),
                        Integer.parseInt(addQuantity.getText().toString()));
                Toast.makeText(AddItemActivity.this, itemAttributes.toString(), Toast.LENGTH_SHORT).show();

            } catch (Exception e) {
                Toast.makeText(AddItemActivity.this, "Error creating item", Toast.LENGTH_SHORT).show();
                itemAttributes = new ItemAttributes(-1, "error", 0);
            }

            DataBaseHelper dataBaseHelper = new DataBaseHelper(AddItemActivity.this);

            boolean success = dataBaseHelper.addOne(itemAttributes);
            Toast.makeText(AddItemActivity.this, "Success= " + success, Toast.LENGTH_SHORT).show();
            itemArrayAdapter = new ArrayAdapter<>(AddItemActivity.this, android.R.layout.simple_list_item_1, dataBaseHelper.getEverything());
            inventoryList.setAdapter(itemArrayAdapter);

        });
        subtractButton.setOnClickListener(v -> Toast.makeText(AddItemActivity.this, "Click item to DELETE", Toast.LENGTH_SHORT).show());
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(toggleSMS.isChecked())
                    toggleSMS.getTextOn().toString();
                    sendSMS(phoneNumber);



            }
            // Send SMS to imaginary hardcoded phone
            public void sendSMS(String phoneNumber) {
                try {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(phoneNumber, null, null, null, null);
                    Toast.makeText(getApplicationContext(), "Stock Depleted!", Toast.LENGTH_SHORT).show();
                } catch (Exception ex) {
                    Toast.makeText(getApplicationContext(), ex.getMessage(),Toast.LENGTH_SHORT).show();
                    ex.printStackTrace();
                }

            }


        });

        // modify my listview
    inventoryList.setOnItemClickListener((parent, view, position, id) -> {
        ItemAttributes clickedItem = (ItemAttributes) parent.getItemAtPosition(position);
        dataBaseHelper.deleteOne(clickedItem);
        itemArrayAdapter = new ArrayAdapter<>(AddItemActivity.this, android.R.layout.simple_list_item_1, dataBaseHelper.getEverything());
        inventoryList.setAdapter(itemArrayAdapter);
        Toast.makeText(AddItemActivity.this, "Deleted" + clickedItem, Toast.LENGTH_SHORT).show();
    });

    }

    private void showItemsonListView() {
        itemArrayAdapter = new ArrayAdapter<>(AddItemActivity.this, android.R.layout.simple_list_item_1, dataBaseHelper.getEverything());
        inventoryList.setAdapter(itemArrayAdapter);
    }





}