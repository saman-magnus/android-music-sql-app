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

import com.example.musicapp.Model.Album;
import com.example.musicapp.R;
import com.example.musicapp.SubActivity.SongActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.MyViewHolder> {
    private Context context;
    private List<Album> albums;

    public AlbumAdapter(Context context, List<Album> albums) {
        this.context = context;
        this.albums = albums;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View row = inflater.inflate(R.layout.row_custom_album, null);
        MyViewHolder holder = new MyViewHolder(row);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Album item = albums.get(position);
        holder.tvTenNSAlbum.setText(item.getTenNS());
        holder.tvTenAlbum.setText(item.getTenAlbum());
        Picasso.with(context).load(item.getHinhAlbum()).into(holder.ivHinhAlbum);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int idAlbum = Integer.parseInt(item.getIdAlbum());
                Intent intent = new Intent(context, SongActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("rule", 2);
                intent.putExtra("code", idAlbum);
                intent.putExtra("title", "Album song " + item.getTenAlbum());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return albums.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView ivHinhAlbum;
        TextView tvTenAlbum, tvTenNSAlbum;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ivHinhAlbum = itemView.findViewById(R.id.ivHinhAlbum);
            tvTenAlbum = itemView.findViewById(R.id.tvTenAlbum);
            tvTenNSAlbum = itemView.findViewById(R.id.tvTenNSAlbum);
        }
    }
}
