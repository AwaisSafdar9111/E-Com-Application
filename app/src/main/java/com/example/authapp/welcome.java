package com.example.authapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class welcome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        Intent intent = getIntent();
        String email = intent.getStringExtra("Email");
        TextView Name= findViewById(R.id.welcome_text);
        Name.setText("Welcome " + email);
        Button logout=(Button) findViewById(R.id.logout);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(welcome.this, login.class));
                finish();
            }
        });
    }
}