package com.example.musicapp.Adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicapp.Databasehelper.DataBaseHelper;
import com.example.musicapp.Model.ThemeModel;
import com.example.musicapp.R;

import java.util.ArrayList;

public class ThemesAdapter extends RecyclerView.Adapter<ThemesAdapter.viewHolder> {

    private final ArrayList<ThemeModel> themeModels;
    private final Context context;
    private DataBaseHelper favDB;


    public ThemesAdapter(ArrayList<ThemeModel> themeModels, Context context) {
        this.themeModels = themeModels;
        this.context = context;
    }


    @NonNull
    @Override
    public ThemesAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_custom_song,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ThemesAdapter.viewHolder holder, int position) {
     final ThemeModel themeModelfav = themeModels.get(position);
        Cursor cursor = favDB.readAllData3();
        if (cursor.getCount() != 0){
            while (cursor.moveToNext()){
                ArrayList<String> ids = new ArrayList<>();
                ids.add(cursor.getString(0));
                if (ids.contains(themeModelfav.getKey_id())){
               holder.favBtm.setImageResource(R.drawable.baseline_favorite_24);
                }
            }
        }
    }

    @Override
    public int getItemCount() {

        return themeModels.size();
    }


    public class viewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView titleTextView;
        ImageView favBtm;
        CardView cardView;
        public viewHolder(@NonNull View itemView){
            super(itemView);
            imageView = itemView.findViewById(R.id.ivHinhBH);
            titleTextView = itemView.findViewById(R.id.tvTenBaiHat);
            favBtm = itemView.findViewById(R.id.fav_Btn);
            //cardView = itemView.findViewById(R.id.cardViewTheme);

        }
    }
    private void CheckFavorite(String title, int image, ImageView favorite, String unique_id){
        Cursor cursor = favDB.readAllData3();
        if (cursor.getCount() != 0){
            while (cursor.moveToNext()){
                if (cursor.getString(0).equalsIgnoreCase(unique_id)){
                    favDB.deleteItem(unique_id);
                    favorite.setImageResource(R.drawable.baseline_favorite_border_24);
                    return;
                }
            }
        }
        favDB.addscanRecord3(unique_id,title,image);
        favorite.setImageResource(R.drawable.baseline_favorite_24);
    }
}
