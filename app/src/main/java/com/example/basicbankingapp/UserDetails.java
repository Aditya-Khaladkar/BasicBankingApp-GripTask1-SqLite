package com.example.basicbankingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.basicbankingapp.db.User;
import com.example.basicbankingapp.db.UserDao;
import com.example.basicbankingapp.db.UserDb;
import com.example.basicbankingapp.newDb.NewTransactionDetails;
import com.example.basicbankingapp.newDb.NewTransactionDetailsDao;
import com.example.basicbankingapp.newDb.NewTransactionDetailsDb;

public class UserDetails extends AppCompatActivity {
    TextView detail_name, detail_accNo, detail_email,
            detail_ifsc, detail_mobile, detail_balance;
    AlertDialog dialog;
    EditText mIFSC, mAmount;
    String ifscCode, amount, ifsc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);

        detail_name = findViewById(R.id.detail_name);
        detail_accNo = findViewById(R.id.detail_accNo);
        detail_email = findViewById(R.id.detail_email);
        detail_ifsc = findViewById(R.id.detail_ifsc);
        detail_mobile = findViewById(R.id.detail_mobile);
        detail_balance = findViewById(R.id.detail_balance);

        String name = getIntent().getStringExtra("name");
        long accNo = getIntent().getLongExtra("acc_no", 0);
        String email = getIntent().getStringExtra("email");
        ifsc = getIntent().getStringExtra("ifsc");
        long mobile = getIntent().getLongExtra("mobile", 0);
        long balance = getIntent().getLongExtra("balance", 0);

        detail_name.setText("User Name:   " + name);
        detail_accNo.setText("Account Number:   " + String.valueOf(accNo));
        detail_email.setText("User Email:   " + email);
        detail_ifsc.setText("IFSC Code:   " + ifsc);
        detail_mobile.setText("Mobile Number:   " + String.valueOf(mobile));
        detail_balance.setText("Current Balance:   " + String.valueOf(balance));

        findViewById(R.id.btn_transferMoney).setOnClickListener(v -> {

            final AlertDialog.Builder mBuilder = new AlertDialog.Builder(UserDetails.this);
            View mView = getLayoutInflater().inflate(R.layout.transfer_details, null);
            mBuilder.setTitle("Transaction Details").setView(mView).setCancelable(false);

            mBuilder.setPositiveButton("SEND", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                    mIFSC = mView.findViewById(R.id.transfer_txt_ifsc);
                    mAmount = mView.findViewById(R.id.transfer_txt_amount);

                    ifscCode = mIFSC.getText().toString().trim();
                    amount = mAmount.getText().toString().trim();

                    if (TextUtils.isEmpty(ifscCode)) {
                        Toast.makeText(UserDetails.this, "Enter Receiver's IFSC ...", Toast.LENGTH_SHORT).show();
                    } else if (TextUtils.isEmpty(mAmount.getText().toString())) {
                        Toast.makeText(UserDetails.this, "Enter Amount ... ", Toast.LENGTH_LONG).show();
                    } else if (Long.parseLong(amount) > balance) {
                        Toast.makeText(UserDetails.this, "Insufficient Fund ... ", Toast.LENGTH_LONG).show();
                    } else if (ifscCode.equals(ifsc)) {
                        Toast.makeText(UserDetails.this, "You cannot enter your own IFSC Code", Toast.LENGTH_LONG).show();
                    } else {
                        TransferAsyncTask transferAsyncTask = new TransferAsyncTask();
                        transferAsyncTask.execute();

                        // init new Db
                        final NewTransactionDetailsDb db = initNewDb();
                        final NewTransactionDetailsDao dao = db.newTransactionDetailsDao();

                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                final NewTransactionDetails details = new NewTransactionDetails();
                                details.setNewTransactionDetails(name +
                                        " sent " + "rs " + amount + " to " + ifscCode);
                                dao.insertDetails(details);
                            }
                        }).start();
                    }
                }
            });

            dialog = mBuilder.create();
            dialog.show();

        });
    }

    class TransferAsyncTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            final UserDb userDb = initDb();
            final UserDao userDao = userDb.userDao();
            userDao.addMoney(Long.parseLong(amount), ifscCode);
            userDao.reduceMoney(Long.parseLong(amount), ifsc);

            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            startActivity(new Intent(getApplicationContext(), TransferSuccess.class));
            finish();
        }
    }

    public UserDb initDb() {
        return Room
                .databaseBuilder(getApplicationContext(), UserDb.class, "bankuser")
                .build();
    }

    public NewTransactionDetailsDb initNewDb() {
        return Room
                .databaseBuilder(getApplicationContext(), NewTransactionDetailsDb.class,
                        "transactionDetail")
                .build();
    }
}