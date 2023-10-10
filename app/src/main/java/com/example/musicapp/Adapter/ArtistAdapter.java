package com.example.musicapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicapp.Model.Artist;
import com.example.musicapp.R;
import com.example.musicapp.SubActivity.SongActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ArtistAdapter extends RecyclerView.Adapter<ArtistAdapter.MyViewHolder> {
    private Context context;
    private List<Artist> data;

    public ArtistAdapter(Context context, List<Artist> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View row = inflater.inflate(R.layout.row_custom_artist, null);
        MyViewHolder holder = new MyViewHolder(row);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Artist item = data.get(position);
        holder.tvTenNgheSi.setText(item.getTenNS());
        Picasso.with(context).load(item.getHinhanhNS()).into(holder.ivHinhCasi);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int idnghesi = Integer.parseInt(item.getIdnghesi());
                Intent intent = new Intent(context, SongActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("rule", 1);
                intent.putExtra("code", idnghesi);
                intent.putExtra("title", "The song of " + item.getTenNS());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvTenNgheSi;
        ImageView ivHinhCasi;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTenNgheSi = itemView.findViewById(R.id.tvTenNgheSi);
            ivHinhCasi = itemView.findViewById(R.id.ivHinhCaSi);
        }
    }
}
