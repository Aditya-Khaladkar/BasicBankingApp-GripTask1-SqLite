package com.example.basicbankingapp.newDb;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface NewTransactionDetailsDao {

    @Insert
    void insertDetails(NewTransactionDetails details);

    @Query("select * from new_table")
    List<NewTransactionDetails> getAll();
}
