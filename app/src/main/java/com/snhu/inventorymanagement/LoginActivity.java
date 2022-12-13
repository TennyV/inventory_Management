package com.snhu.inventorymanagement;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class LoginActivity extends AppCompatActivity {

    EditText username, password;
    Button loginButton, newUserButton;
    DataBaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText) findViewById(R.id.userName);
        password = (EditText) findViewById(R.id.passWord);

        loginButton = (Button) findViewById(R.id.loginButton);
        newUserButton = (Button) findViewById(R.id.newUser);
        db = new DataBaseHelper(getApplicationContext());


        loginButton.setOnClickListener(v -> {
            String user = username.getText().toString();
            String pass = password.getText().toString();

            if(user.equals("") || pass.equals("")) {
                Toast.makeText(LoginActivity.this, "Please enter your info", Toast.LENGTH_SHORT).show();
            }else {
                boolean checkUser = db.checkDBforUser(user);
                if(checkUser) {
                        Intent intent = new Intent(getApplicationContext(), AddItemActivity.class);
                        startActivity(intent);

                }
            }

        });
        newUserButton.setOnClickListener(v -> {

            String user = username.getText().toString();
            String pass = password.getText().toString();

            if(user.equals("") || pass.equals("")) {
                Toast.makeText(LoginActivity.this, "Fields are blank", Toast.LENGTH_SHORT).show();
            }else {
                boolean checkUser = db.checkDBforUser(user);
                if(!checkUser) {
                    boolean insert = db.addUser(user, pass);
                    if(insert) {
                        Toast.makeText(LoginActivity.this, "Success", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),AddItemActivity.class);
                        startActivity(intent);
                    }else {
                        Toast.makeText(LoginActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(LoginActivity.this,"Users exists", Toast.LENGTH_SHORT).show();
                }
            }





        });
    }


}

