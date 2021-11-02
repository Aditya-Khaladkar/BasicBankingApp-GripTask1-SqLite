package com.example.basicbankingapp.newDb;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {NewTransactionDetails.class}, version = 1)
public abstract class NewTransactionDetailsDb extends RoomDatabase {
    public abstract NewTransactionDetailsDao newTransactionDetailsDao();
}
