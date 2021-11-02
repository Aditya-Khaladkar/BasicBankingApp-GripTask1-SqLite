package com.example.basicbankingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;

import com.example.basicbankingapp.adapter.TransactionAdapter;
import com.example.basicbankingapp.newDb.NewTransactionDetails;
import com.example.basicbankingapp.newDb.NewTransactionDetailsDao;
import com.example.basicbankingapp.newDb.NewTransactionDetailsDb;

import java.util.List;

public class AmountTransferActivity extends AppCompatActivity {
    RecyclerView amount_recyclerView;
    TransactionAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amount_transfer);

        final NewTransactionDetailsDb db = initNewDb();
        final NewTransactionDetailsDao dao = db.newTransactionDetailsDao();

        amount_recyclerView = findViewById(R.id.amount_recyclerView);
        amount_recyclerView.setLayoutManager(new LinearLayoutManager(this));
        amount_recyclerView.setHasFixedSize(true);

        new Thread(new Runnable() {
            @Override
            public void run() {
                final List<NewTransactionDetails> myList = dao.getAll();
                adapter = new TransactionAdapter(myList);
                amount_recyclerView.setAdapter(adapter);
            }
        }).start();
    }

    public NewTransactionDetailsDb initNewDb() {
        return Room
                .databaseBuilder(getApplicationContext(), NewTransactionDetailsDb.class,
                        "transactionDetail")
                .build();
    }
}