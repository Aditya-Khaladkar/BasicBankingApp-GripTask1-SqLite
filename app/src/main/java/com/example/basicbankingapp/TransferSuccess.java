package com.example.basicbankingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class TransferSuccess extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer_success);

        Toast.makeText(this, "Amount Transaction Success !", Toast.LENGTH_SHORT).show();

        findViewById(R.id.btn_back).setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), AllUserActivity.class));
            finish();
        });
    }
}