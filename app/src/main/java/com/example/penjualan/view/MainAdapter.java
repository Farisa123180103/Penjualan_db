package com.example.penjualan.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.penjualan.R;
import com.example.penjualan.entity.DataPenjualan;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.viewHolder> {
    Context context;
    List<DataPenjualan> list;
    MainContact.view mView;

    public MainAdapter(Context context, List<DataPenjualan> list, MainContact.view mView) {
        this.context = context;
        this.list = list;
        this.mView = mView;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_penjualan,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        final  DataPenjualan item = list.get(position);
        holder.tvTgl.setText(item.getTgl());
        holder.tvKotor.setText(item.getKotor());
        holder.tvKeluar.setText(item.getKeluar());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mView.editData(item);
            }
        });
        holder.cardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                mView.deleteData(item);
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView tvKotor,tvTgl,tvKeluar,tvBersih;
        CardView cardView;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            tvKotor = itemView.findViewById(R.id.tv_kotor);
            tvTgl = itemView.findViewById(R.id.tv_tgl);
            tvKeluar = itemView.findViewById(R.id.tv_keluar);
            tvBersih = itemView.findViewById(R.id.tv_bersih);
            cardView = itemView.findViewById(R.id.cv_item);
        }
    }
}
