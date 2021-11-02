package com.example.basicbankingapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.basicbankingapp.R;
import com.example.basicbankingapp.newDb.NewTransactionDetails;

import java.util.List;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder> {

    List<NewTransactionDetails> detailsList;

    public TransactionAdapter(List<NewTransactionDetails> detailsList) {
        this.detailsList = detailsList;
    }

    @NonNull
    @Override
    public TransactionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.show_all_transaction,
                parent, false);
        return new TransactionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionViewHolder holder, int position) {
        final NewTransactionDetails details = detailsList.get(position);
        holder.show_transaction.setText(details.getNewTransactionDetails());
    }

    @Override
    public int getItemCount() {
        return detailsList.size();
    }

    public class TransactionViewHolder extends RecyclerView.ViewHolder {
        TextView show_transaction;
        CardView detail_cardView;

        public TransactionViewHolder(@NonNull View itemView) {
            super(itemView);

            show_transaction = itemView.findViewById(R.id.show_transaction);
            detail_cardView = itemView.findViewById(R.id.detail_cardView);
        }
    }
}
