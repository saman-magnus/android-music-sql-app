package com.example.musicapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicapp.Model.ListButton;
import com.example.musicapp.Model.Song;
import com.example.musicapp.R;
import com.example.musicapp.SubActivity.AlbumActivity;
import com.example.musicapp.SubActivity.ArtistActivity;
import com.example.musicapp.SubActivity.RankingActivity;
import com.example.musicapp.SubActivity.SongActivity;
import com.example.musicapp.SubActivity.SongPlayingActivity;

import java.util.List;

public class ButtonAdapter extends RecyclerView.Adapter<ButtonAdapter.MyViewHolder> {
    private Context context;
    private List<ListButton> data;
    private List<Song> songs;


    public ButtonAdapter(Context context, List<ListButton> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View row = inflater.inflate(R.layout.list_button, null);
        MyViewHolder holder = new MyViewHolder(row);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ListButton item = data.get(position);
        holder.tvButton.setText(item.getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("TAG", "id click: " + item.getId());
                if (item.getId() == 6) {
                    Intent intent = new Intent(context, SongPlayingActivity.class);
                    context.startActivity(intent);
                }
                if (item.getId() == 1) {
                    Intent intent = new Intent(context, SongActivity.class);
                    intent.putExtra("code", 0);
                    intent.putExtra("title", "All songs");
                    context.startActivity(intent);
                }
                if (item.getId() == 2) {
                    Intent intent = new Intent(context, ArtistActivity.class);
                    intent.putExtra("code", 0);
                    context.startActivity(intent);
                }
                if (item.getId() == 3) {
                    Intent intent = new Intent(context, AlbumActivity.class);
                    context.startActivity(intent);
                }
                if (item.getId() == 4) {
                    Intent intent = new Intent(context, RankingActivity.class);
                    context.startActivity(intent);
                }
                if (item.getId() == 5) {
                    Intent intent = new Intent(context, SongActivity.class);
                    intent.putExtra("rule", 4);
                    intent.putExtra("title", "What do you hear today?");
                    context.startActivity(intent);

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tvButton;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvButton = itemView.findViewById(R.id.tvButton);
        }
    }
}
