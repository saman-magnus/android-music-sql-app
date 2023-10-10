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

import com.example.musicapp.Model.Song;
import com.example.musicapp.R;

import java.util.List;

public class SongPlayingAdapter extends RecyclerView.Adapter<SongPlayingAdapter.MyViewHolder> {
    private Context context;
    private List<Song> data;

    public SongPlayingAdapter(Context context, List<Song> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_custom_playing, null);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Song item = data.get(position);
        holder.tvTenBH.setText(item.getTenBH());
        holder.tvTenNS.setText(item.getTenNS());
        holder.tvIndex.setText(position + 1 + "");

        //Picasso.with(context).load(item.getHinhanhBH()).into(holder.axmusic);





    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView axmusic;
        TextView tvIndex, tvTenBH, tvTenNS;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvIndex = itemView.findViewById(R.id.tvIndex);
            tvTenBH = itemView.findViewById(R.id.tvNameplaying);
            tvTenNS = itemView.findViewById(R.id.tvNSPlaying);
            axmusic = itemView.findViewById(R.id.ivXoaytron);
        }
    }
}
