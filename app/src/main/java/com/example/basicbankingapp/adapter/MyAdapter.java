package com.example.basicbankingapp.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.basicbankingapp.R;
import com.example.basicbankingapp.UserDetails;
import com.example.basicbankingapp.db.User;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    List<User> myUserList;

    public MyAdapter(List<User> myUserList) {
        this.myUserList = myUserList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bank_user_list, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final User user = myUserList.get(position);
        holder.txt_user_name.setText(user.getSql_userName());
        holder.txt_user_balance.setText("Balance: " + String.valueOf(user.getSql_userBalance()) + "/-");
        holder.cardView.setOnClickListener(view -> {
            Intent intent = new Intent(holder.cardView.getContext(), UserDetails.class);
            intent.putExtra("name", user.getSql_userName());
            intent.putExtra("acc_no", user.getSql_accName());
            intent.putExtra("email", user.getSql_email());
            intent.putExtra("ifsc", user.getSql_ifsc());
            intent.putExtra("mobile", user.getSql_mobileNum());
            intent.putExtra("balance", user.getSql_userBalance());
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            holder.cardView.getContext().startActivity(intent);

        });
    }

    @Override
    public int getItemCount() {
        return myUserList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txt_user_name, txt_user_balance;
        CardView cardView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            txt_user_name = itemView.findViewById(R.id.txt_user_name);
            txt_user_balance = itemView.findViewById(R.id.txt_user_balance);
            cardView = itemView.findViewById(R.id.cardView);
        }
    }
}
