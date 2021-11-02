package com.example.basicbankingapp.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {

    @Insert
    void insertUsers(User user1,
                     User user2,
                     User user3,
                     User user4,
                     User user5,
                     User user6,
                     User user7,
                     User user8
    );

    @Query("select * from user_data")
    List<User> getAllUser();

    @Query("SELECT EXISTS(SELECT * FROM user_data WHERE id = 1)")
    boolean checkUserData();

    @Query("update user_data set user_balance = user_balance + :balance where ifsc = :ifsc")
    void addMoney(long balance, String ifsc);

    @Query("update user_data set user_balance = user_balance - :userBalance where ifsc = :userIfsc")
    void reduceMoney(long userBalance, String userIfsc);

    @Query("select exists(select * from user_data where ifsc = :ifsc)")
    boolean checkIfsc(String ifsc);
}
