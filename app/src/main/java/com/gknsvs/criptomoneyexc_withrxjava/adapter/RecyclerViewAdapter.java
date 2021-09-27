package com.gknsvs.criptomoneyexc_withrxjava.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gknsvs.criptomoneyexc_withrxjava.R;
import com.gknsvs.criptomoneyexc_withrxjava.databinding.RecyclerviewRowBinding;
import com.gknsvs.criptomoneyexc_withrxjava.model.CryptoModel;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {

    ArrayList<CryptoModel> cryptoModelArrayList;
    private String[] colors = {"#a3ff00","#ff00aa","#b4a7d6","#a4c2f4","#8ee5ee","#cd950c","#f5f5f5","#f47932"};
    public RecyclerViewAdapter(ArrayList<CryptoModel> cryptoModelArrayList) {
        this.cryptoModelArrayList = cryptoModelArrayList;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerviewRowBinding recyclerviewRowBinding=RecyclerviewRowBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new RecyclerViewHolder(recyclerviewRowBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        holder.bind(cryptoModelArrayList.get(position),colors,position);
    }

    @Override
    public int getItemCount() {
        return cryptoModelArrayList.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        RecyclerviewRowBinding recyclerviewRowBinding;
        public RecyclerViewHolder(RecyclerviewRowBinding recyclerviewRowBinding) {
            super(recyclerviewRowBinding.getRoot());
            this.recyclerviewRowBinding=recyclerviewRowBinding;
        }
        public void bind(CryptoModel cryptoModel,String[] colors, int position){
            itemView.setBackgroundColor(Color.parseColor(colors[position % 8]));
            recyclerviewRowBinding.txtCurrency.setText(cryptoModel.getCurrency());
            recyclerviewRowBinding.txtPrice.setText(cryptoModel.getPrice());

        }
    }
}
