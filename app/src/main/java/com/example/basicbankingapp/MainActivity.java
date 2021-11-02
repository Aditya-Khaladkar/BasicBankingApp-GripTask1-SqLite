package com.example.basicbankingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import com.example.basicbankingapp.db.User;
import com.example.basicbankingapp.db.UserDao;
import com.example.basicbankingapp.db.UserDb;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onStart() {
        super.onStart();
        BankAsyncTask bankAsyncTask = new BankAsyncTask();
        bankAsyncTask.execute();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_showTransfer).setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), AmountTransferActivity.class));
        });

        findViewById(R.id.btn_showUsers).setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), AllUserActivity.class));
        });
    }

    class BankAsyncTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            final UserDb userDb = initDb();
            final UserDao userDao = userDb.userDao();

            if (userDao.checkUserData() == false) {
                // user 1
                final User user1 = new User();
                user1.setSql_userName("Aditya");
                user1.setSql_userBalance(1000);
                user1.setSql_email("user1@gmail.com");
                user1.setSql_accName(101);
                user1.setSql_ifsc("IFSC101");
                user1.setSql_mobileNum(1234567890L);

                // user 2
                final User user2 = new User();
                user2.setSql_userName("Piyush");
                user2.setSql_userBalance(500);
                user2.setSql_email("user2@gmail.com");
                user2.setSql_accName(102);
                user2.setSql_ifsc("IFSC102");
                user2.setSql_mobileNum(1234567890L);

                // user 3
                final User user3 = new User();
                user3.setSql_userName("Varun");
                user3.setSql_userBalance(1000);
                user3.setSql_email("user3@gmail.com");
                user3.setSql_accName(103);
                user3.setSql_ifsc("IFSC103");
                user3.setSql_mobileNum(1234567890L);

                // user 4
                final User user4 = new User();
                user4.setSql_userName("Jay");
                user4.setSql_userBalance(1000);
                user4.setSql_email("user4@gmail.com");
                user4.setSql_accName(104);
                user4.setSql_ifsc("IFSC104");
                user4.setSql_mobileNum(1234567890L);

                // user 5
                final User user5 = new User();
                user5.setSql_userName("omkar");
                user5.setSql_userBalance(1000);
                user5.setSql_email("user5@gmail.com");
                user5.setSql_accName(105);
                user5.setSql_ifsc("IFSC105");
                user5.setSql_mobileNum(1234567890L);

                // user 6
                final User user6 = new User();
                user6.setSql_userName("Nishant");
                user6.setSql_userBalance(1000);
                user6.setSql_email("user6@gmail.com");
                user6.setSql_accName(106);
                user6.setSql_ifsc("IFSC106");
                user6.setSql_mobileNum(1234567890L);

                // user 7
                final User user7 = new User();
                user7.setSql_userName("Sahil");
                user7.setSql_userBalance(10007);
                user7.setSql_email("user7@gmail.com");
                user7.setSql_accName(107);
                user7.setSql_ifsc("IFSC107");
                user7.setSql_mobileNum(1234567890L);

                // user 8
                final User user8 = new User();
                user8.setSql_userName("Prathamesh");
                user8.setSql_userBalance(1000);
                user8.setSql_email("user8@gmail.com");
                user8.setSql_accName(108);
                user8.setSql_ifsc("IFSC108");
                user8.setSql_mobileNum(1234567890L);

                userDao.insertUsers(user1, user2, user3, user4, user5, user6, user7, user8);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            Toast.makeText(MainActivity.this, "Bank Data Loading ...", Toast.LENGTH_LONG).show();
        }
    }

    public UserDb initDb() {
        return Room
                .databaseBuilder(getApplicationContext(), UserDb.class, "bankuser")
                .build();
    }
}