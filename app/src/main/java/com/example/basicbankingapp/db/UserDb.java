package com.example.basicbankingapp.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {User.class}, version = 1)
public abstract class UserDb extends RoomDatabase {
    public  abstract UserDao userDao();
}
