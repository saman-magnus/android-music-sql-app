package com.example.musicapp.SubActivity;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicapp.API.APIService;
import com.example.musicapp.API.Dataservice;
import com.example.musicapp.Adapter.AlbumAdapter;
import com.example.musicapp.Model.Album;
import com.example.musicapp.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlbumActivity extends AppCompatActivity {
    private RecyclerView recyclerViewAlbum;
    private AlbumAdapter adapter;
    private List<Album> albums;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);
        albums = new ArrayList<>();
        recyclerViewAlbum = findViewById(R.id.recyclerViewAlbum);
        adapter = new AlbumAdapter(getApplicationContext(), albums);
        getAllAlbum();
        recyclerViewAlbum.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        DividerItemDecoration decoration = new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL);
        recyclerViewAlbum.addItemDecoration(decoration);
        recyclerViewAlbum.setHasFixedSize(true);
        recyclerViewAlbum.setAdapter(adapter);
        getSupportActionBar().setTitle("Album List");
    }

    private void getAllAlbum() {
        Dataservice dataservice = APIService.getService();
        Call<List<Album>> call = dataservice.getAllAlbum();
        call.enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {
                List<Album> result = response.body();
                if (result != null) {
                    albums.clear();
                    for (Album a : result) {
                        albums.add(new Album(a.getIdAlbum(), a.getTenAlbum(), a.getTenNS(), a.getHinhAlbum()));
                    }
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {
                Toast.makeText(AlbumActivity.this, "Please check your internet", Toast.LENGTH_SHORT).show();
            }
        });
    }
}