package com.example.basicbankingapp.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user_data")
public class User {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "user_name")
    public String sql_userName;

    @ColumnInfo(name = "acc_no")
    public long sql_accName;

    @ColumnInfo(name = "user_email")
    public String sql_email;

    @ColumnInfo(name = "ifsc")
    public String sql_ifsc;

    @ColumnInfo(name = "user_mobile")
    public long sql_mobileNum;

    @ColumnInfo(name = "user_balance")
    public long sql_userBalance;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSql_userName() {
        return sql_userName;
    }

    public void setSql_userName(String sql_userName) {
        this.sql_userName = sql_userName;
    }

    public long getSql_accName() {
        return sql_accName;
    }

    public void setSql_accName(long sql_accName) {
        this.sql_accName = sql_accName;
    }

    public String getSql_email() {
        return sql_email;
    }

    public void setSql_email(String sql_email) {
        this.sql_email = sql_email;
    }

    public String getSql_ifsc() {
        return sql_ifsc;
    }

    public void setSql_ifsc(String sql_ifsc) {
        this.sql_ifsc = sql_ifsc;
    }

    public long getSql_mobileNum() {
        return sql_mobileNum;
    }

    public void setSql_mobileNum(long sql_mobileNum) {
        this.sql_mobileNum = sql_mobileNum;
    }

    public long getSql_userBalance() {
        return sql_userBalance;
    }

    public void setSql_userBalance(long sql_userBalance) {
        this.sql_userBalance = sql_userBalance;
    }
}
