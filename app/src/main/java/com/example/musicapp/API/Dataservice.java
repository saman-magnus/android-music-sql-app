package com.example.musicapp.API;

import com.example.musicapp.Model.Album;
import com.example.musicapp.Model.Artist;
import com.example.musicapp.Model.Song;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Dataservice {
    @GET("get_all_music.php")
    Call<List<Song>> getAllMusic();

    @GET("get_all_artist.php")
    Call<List<Artist>> getAllArtist();

    @GET("get_songs_by_artist.php")
    Call<List<Song>> getSongByArtist(@Query("idnghesi") int idnghesi);

    @GET("get_songs_ranking_by_id.php")
    Call<List<Song>> getSongByRankingId(@Query("idquocgia") int idquocgia);

    @GET("get_all_album.php")
    Call<List<Album>> getAllAlbum();

    @GET("get_songs_by_album.php")
    Call<List<Song>> getSongByAlbum(@Query("idalbum") int idalbum);

    @GET("get_random_songs.php")
    Call<List<Song>> getSongRandom();
}
