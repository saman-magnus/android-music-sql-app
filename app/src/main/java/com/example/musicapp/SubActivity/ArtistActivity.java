package com.example.musicapp.SubActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicapp.API.APIService;
import com.example.musicapp.API.Dataservice;
import com.example.musicapp.Adapter.ArtistAdapter;
import com.example.musicapp.Model.Artist;
import com.example.musicapp.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArtistActivity extends AppCompatActivity {
    private RecyclerView recyclerViewArtist;
    private ArtistAdapter adapter;
    private List<Artist> artists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist);
        artists = new ArrayList<>();
        getAllArtist();
        recyclerViewArtist = findViewById(R.id.recyclerViewArtist);
        adapter = new ArtistAdapter(this, artists);
        recyclerViewArtist.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration decoration = new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL);
        recyclerViewArtist.addItemDecoration(decoration);
        recyclerViewArtist.setHasFixedSize(false);
        recyclerViewArtist.setAdapter(adapter);
        getSupportActionBar().setTitle("List of singers");

    }

    private void getAllArtist() {
        Dataservice dataservice = APIService.getService();
        Call<List<Artist>> call = dataservice.getAllArtist();
        call.enqueue(new Callback<List<Artist>>() {
            @Override
            public void onResponse(Call<List<Artist>> call, Response<List<Artist>> response) {
                List<Artist> result = response.body();
                Log.e("TAG", result + "");
                if (result != null) {
                    artists.clear();
                    for (Artist a : result) {
                        //  Log.e("TAG",a.getTenNS());
                        artists.add(new Artist(a.getIdnghesi(), a.getTenNS(), a.getHinhanhNS()));
                    }
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<Artist>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Please check your internet", Toast.LENGTH_SHORT).show();

            }
        });
    }
}