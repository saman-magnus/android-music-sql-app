package com.example.musicapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicapp.Databasehelper.DataBaseHelper;
import com.example.musicapp.Model.FavoriteModel;
import com.example.musicapp.R;

import java.util.ArrayList;

public class FavAdapter extends RecyclerView.Adapter<FavAdapter.viewHolder> {
    private Context context;
    private ArrayList<FavoriteModel> favArrayList;
    private DataBaseHelper favDB;
    View.OnClickListener onClickListener;
    View.OnClickListener onClickListenerDelete;


    public FavAdapter(Context context, ArrayList<FavoriteModel> favArrayList, View.OnClickListener onClickListenerDelete) {
        this.context = context;
        this.favArrayList = favArrayList;
        this.onClickListenerDelete = onClickListenerDelete;
    }

    @NonNull
    @Override
    public FavAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_custom_song,parent,false);
        favDB = new DataBaseHelper(context);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavAdapter.viewHolder holder, int position) {
        FavoriteModel themesModel = favArrayList.get(position);
        holder.favImageView.setImageResource(themesModel.getImage());
        holder.titletexView.setText(themesModel.getTitle());
        holder.favImageView.setTag(position);
        holder.favBtn.setImageResource(R.drawable.baseline_favorite_border_24);
        holder.favBtn.setOnClickListener(onClickListenerDelete);

    }

    @Override
    public int getItemCount() {
        return favArrayList.size();
    }
    public class viewHolder extends RecyclerView.ViewHolder{
        ImageView favBtn;
        ImageView favImageView;
        TextView titletexView;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            favBtn = favImageView.findViewById(R.id.fav_Btn);
            favImageView = favImageView.findViewById(R.id.ivHinhBH);
            titletexView = favImageView.findViewById(R.id.tvTenBaiHat);

        }
    }
}
