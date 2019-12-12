package com.example.thirdassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText eusername, epassword;
    Button buttonlogin;
    String username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eusername = findViewById(R.id.etusername);
        epassword = findViewById(R.id.etpassword);
        buttonlogin = findViewById(R.id.btnlogin);

        buttonlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = eusername.getText().toString();
                password = epassword.getText().toString();
                if (username.equals("softwarica") && password.equals("coventry")) {
                    Intent intent = new Intent(MainActivity.this, BottomNavigationActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Invalid Username or Password", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}
