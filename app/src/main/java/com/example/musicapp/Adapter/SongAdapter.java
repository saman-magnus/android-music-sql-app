package com.example.musicapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicapp.Databasehelper.DataBaseHelper;
import com.example.musicapp.Model.FavoriteModel;
import com.example.musicapp.Model.Song;
import com.example.musicapp.R;
import com.example.musicapp.SubActivity.SongPlayingActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.MyViewHolder> {
    private Context context;
    private List<Song> data;



    public SongAdapter(Context context, List<Song> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View row = inflater.inflate(R.layout.row_custom_song, null);
        MyViewHolder holder = new MyViewHolder(row);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Song item = data.get(position);
        Log.e("TAG", item.getTenBH());
        holder.tvTenCS.setText(item.getTenNS());
        holder.tvTenBH.setText(item.getTenBH());
        Picasso.with(context).load(item.getHinhanhBH()).into(holder.ivHinhBH);




        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Song> songList = new ArrayList<>();
                songList.clear();
                Intent intent = new Intent(context, SongPlayingActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("song", item);

                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView ivHinhBH;
        TextView tvTenBH, tvTenCS;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ivHinhBH = itemView.findViewById(R.id.ivHinhBH);
            tvTenBH = itemView.findViewById(R.id.tvTenBaiHat);
            tvTenCS = itemView.findViewById(R.id.tvTenCS);
        }
    }


}
