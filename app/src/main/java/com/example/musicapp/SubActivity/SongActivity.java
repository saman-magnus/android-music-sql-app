package com.example.musicapp.SubActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicapp.API.APIService;
import com.example.musicapp.API.Dataservice;
import com.example.musicapp.Adapter.FavAdapter;
import com.example.musicapp.Adapter.SongAdapter;
import com.example.musicapp.Adapter.ThemesAdapter;
import com.example.musicapp.Databasehelper.DataBaseHelper;
import com.example.musicapp.Model.FavoriteModel;
import com.example.musicapp.Model.Song;
import com.example.musicapp.Model.ThemeModel;
import com.example.musicapp.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SongActivity extends AppCompatActivity {
    private final ArrayList<ThemeModel> themeModels = new ArrayList<>();
    //RecyclerView recyclerView;

    private List<Song> songs;
    private RecyclerView recyclerViewSong;
    private SongAdapter adapter;
    private Button btnPlayAll;

    private RecyclerView recyclerView;
    private DataBaseHelper favDB;
    private FavAdapter favAdapter;
    private ArrayList<FavoriteModel> arrayListFav = new ArrayList<>();


    private int code;
    private int rule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song);
//       gate favorite in whole song list
        recyclerView = findViewById(R.id.recyclerViewSong);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        favDB = new DataBaseHelper(this);

        addToFavorite();

        btnPlayAll = findViewById(R.id.btnPlayAll);
        Intent intent = getIntent();

        //get all music when rule = 0, when artist = 1, when album =2, when ranking = 3, when today listening ? = 4
        rule = intent.getIntExtra("rule", 0);
        code = intent.getIntExtra("code", 0);
        String title = intent.getStringExtra("title");
        getSupportActionBar().setTitle(title);
        songs = new ArrayList<>();

        adapter = new SongAdapter(getApplicationContext(), songs);
        recyclerViewSong = findViewById(R.id.recyclerViewSong);
        if (rule == 0) {
            if (code == 0) {
                getAllSong();

            }
        }
        Log.e("TAG", "rule :" + rule);
        Log.e("TAG", "code" + code);
        if (rule == 1) {
            getSongByArtist(code);
        }
        if (rule == 2) {
            getSongByIDAlbum(code);
        }
        if (rule == 4) {
            getSongsRand();
        }

        getAllFavorite();

        recyclerViewSong.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration decoration = new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL);
        recyclerViewSong.addItemDecoration(decoration);
        recyclerViewSong.setHasFixedSize(true);
        recyclerViewSong.setAdapter(adapter);


        btnPlayAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentPlay = new Intent(getApplicationContext(), SongPlayingActivity.class);
                intentPlay.putExtra("listSong", (ArrayList<? extends Parcelable>) songs);
                startActivity(intentPlay);

            }
        });
    }

    private void getSongsRand() {
        Dataservice dataservice = APIService.getService();
        Call<List<Song>> call = dataservice.getSongRandom();
        call.enqueue(new Callback<List<Song>>() {
            @Override
            public void onResponse(Call<List<Song>> call, Response<List<Song>> response) {
                List<Song> result = response.body();
                // Log.e("TAG",result+"");
                if (result != null) {
                    songs.clear();
                    for (Song s : result) {
                        // Log.e("TAG",s.getTenBH());
                        songs.add(new Song(s.getIdbh(), s.getTenBH(), s.getTenNS(), s.getHinhanhBH(), s.getLinkBH()));

                    }
                    adapter.notifyDataSetChanged();
                    showButtonPlayAll();
                }
            }

            @Override
            public void onFailure(Call<List<Song>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Please check your internet", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getSongByIDAlbum(int code) {
        Dataservice dataservice = APIService.getService();
        Call<List<Song>> call = dataservice.getSongByAlbum(code);
        call.enqueue(new Callback<List<Song>>() {
            @Override
            public void onResponse(Call<List<Song>> call, Response<List<Song>> response) {
                List<Song> result = response.body();
                // Log.e("TAG",result+"");
                if (result != null) {
                    songs.clear();
                    for (Song s : result) {
                        // Log.e("TAG",s.getTenBH());
                        songs.add(new Song(s.getIdbh(), s.getTenBH(), s.getTenNS(), s.getHinhanhBH(), s.getLinkBH()));

                    }
                    adapter.notifyDataSetChanged();
                    showButtonPlayAll();
                }
            }

            @Override
            public void onFailure(Call<List<Song>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Please check your internet", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void getSongByArtist(int code) {
        Dataservice dataservice = APIService.getService();
        Call<List<Song>> call = dataservice.getSongByArtist(code);
        call.enqueue(new Callback<List<Song>>() {
            @Override
            public void onResponse(Call<List<Song>> call, Response<List<Song>> response) {
                List<Song> result = response.body();
                // Log.e("TAG",result+"");
                if (result != null) {
                    songs.clear();
                    for (Song s : result) {
                        // Log.e("TAG",s.getTenBH());
                        songs.add(new Song(s.getIdbh(), s.getTenBH(), s.getTenNS(), s.getHinhanhBH(), s.getLinkBH()));

                    }
                    adapter.notifyDataSetChanged();
                    showButtonPlayAll();
                }
            }

            @Override
            public void onFailure(Call<List<Song>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Please check your internet", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getAllSong() {
        Dataservice dataservice = APIService.getService();
        Call<List<Song>> call = dataservice.getAllMusic();
        call.enqueue(new Callback<List<Song>>() {
            @Override
            public void onResponse(Call<List<Song>> call, Response<List<Song>> response) {
                List<Song> result = response.body();
                if (result != null) {
                    songs.clear();
                    for (Song s : result) {
//                        Log.e("TAG", s.getTenBH());
                        songs.add(new Song(s.getIdbh(), s.getTenBH(), s.getTenNS(), s.getHinhanhBH(), s.getLinkBH()));
                    }
                    adapter.notifyDataSetChanged();
                    showButtonPlayAll();
                }

            }

            @Override
            public void onFailure(Call<List<Song>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Please check your internet", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showButtonPlayAll() {
        if (songs.size() == 0) {
            btnPlayAll.setVisibility(View.INVISIBLE);
        } else {
            btnPlayAll.setVisibility(View.VISIBLE);
        }
    }

    //btnLike b = (btnLike) findViewById(R.id.btnLike);

    private void getAllFavorite(){
        Cursor cursor = favDB.readAllData3();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        }
        else {
            while (cursor.moveToNext()){
            FavoriteModel themeModel = new FavoriteModel();
            themeModel.setKey_id(cursor.getString(0));
            themeModel.setTitle(cursor.getString(1));
            themeModel.setImage(cursor.getInt(3));
            arrayListFav.add(themeModel);
            }

            favAdapter = new FavAdapter(SongActivity.this,arrayListFav,onclickLisnetDelete);
            recyclerView.setAdapter(favAdapter);
            favAdapter.notifyDataSetChanged();
        }


    }
    View.OnClickListener onclickLisnetDelete = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int pos = (int) view.getTag();
            favDB.deleteItem(arrayListFav.get(pos).getKey_id());
            arrayListFav.remove(arrayListFav.get(pos));
            favAdapter.notifyDataSetChanged();
        }
    };

    public void addToFavorite(){

       // recyclerView.setAdapter(new ThemesAdapter(themeModels,SongActivity.this));
       // recyclerView.setLayoutManager(new GridLayoutManager(SongActivity.this,2));
       // themeModels.add(new ThemeModel(R.drawable.baseline_favorite_24,"1","them1"));
       // themeModels.add(new ThemeModel(R.drawable.abc_vector_test,"2","them2"));
       // themeModels.add(new ThemeModel(R.drawable.baseline_favorite_border_24,"3","them3"));


    }

    @Override
    protected void onResume() {
        super.onResume();
        addToFavorite();
    }

    public void Fav_Click(View view) {
        //Implement image click function
    }
}
