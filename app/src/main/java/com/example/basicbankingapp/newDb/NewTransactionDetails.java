package com.example.basicbankingapp.newDb;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "new_table")
public class NewTransactionDetails {

    @PrimaryKey(autoGenerate = true)
    public int newId;

    @ColumnInfo(name = "transaction_details")
    public String newTransactionDetails;

    public int getNewId() {
        return newId;
    }

    public void setNewId(int newId) {
        this.newId = newId;
    }

    public String getNewTransactionDetails() {
        return newTransactionDetails;
    }

    public void setNewTransactionDetails(String newTransactionDetails) {
        this.newTransactionDetails = newTransactionDetails;
    }
}
