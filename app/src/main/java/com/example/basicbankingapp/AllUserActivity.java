package com.example.basicbankingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import com.example.basicbankingapp.adapter.MyAdapter;
import com.example.basicbankingapp.db.User;
import com.example.basicbankingapp.db.UserDao;
import com.example.basicbankingapp.db.UserDb;

import java.util.List;

public class AllUserActivity extends AppCompatActivity {
    RecyclerView user_recyclerView;
    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_user);

        user_recyclerView = findViewById(R.id.user_recyclerView);
        user_recyclerView.setLayoutManager(new LinearLayoutManager(this));
        user_recyclerView.setHasFixedSize(true);

        MainAsyncTask mainAsyncTask = new MainAsyncTask();
        mainAsyncTask.execute();

    }

    class MainAsyncTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            final UserDb userDb = initDb();
            final UserDao userDao = userDb.userDao();
            final List<User> userList = userDao.getAllUser();
            adapter = new MyAdapter(userList);
            user_recyclerView.setAdapter(adapter);
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            Toast.makeText(getApplicationContext(), "User Loaded", Toast.LENGTH_SHORT).show();
        }
    }

    public UserDb initDb() {
        return Room
                .databaseBuilder(getApplicationContext(), UserDb.class, "bankuser")
                .build();
    }
}