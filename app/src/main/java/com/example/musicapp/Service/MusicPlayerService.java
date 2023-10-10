package com.example.musicapp.Service;


import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.musicapp.Model.Song;
import com.example.musicapp.SubActivity.SongPlayingActivity;

import java.io.IOException;
import java.util.List;

public class MusicPlayerService extends Service implements MediaPlayer.OnPreparedListener {
    private final Binder mBinder = new MyServiceBinder();
    public List<Song> songList;
    public MediaPlayer mediaPlayer;
    private SongPlayingActivity songPlayingActivity;
    public int position = 0;

    @Override
    public void onCreate() {
        super.onCreate();
        mediaPlayer = new MediaPlayer();

        songList = SongPlayingActivity.songList;
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                if (mp != null && mp.isPlaying()) {
                    Log.e("TAG","there's a song playing");
                }
                else {
                    mp.stop();
                }
            }
        });
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mediaPlayer.stop();
                mediaPlayer.reset();
                if (position + 1 > songList.size() - 1) {
                    position = 0;
                } else {
                    position++;
                }
                setData();

            }
        });
        if (mediaPlayer.isPlaying()) {
            Log.e("TAG", "DEVELOPING");
        }
        if (!mediaPlayer.isPlaying())
            setData();
    }


    public void setData() {
        try {
            if (songList.size() > 0) {
                mediaPlayer.setDataSource(songList.get(position).getLinkBH());
                mediaPlayer.prepare();
                mediaPlayer.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }


    @Override
    public void onPrepared(MediaPlayer mp) {
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {

                if (songList.size() > 0) {
                    try {
                        mediaPlayer.setDataSource(songList.get(position).getLinkBH());
                        mediaPlayer.start();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public String getName() {
        return songList.get(position).getTenBH();
    }

    public class MyServiceBinder extends Binder {
        public MusicPlayerService getService() {
            return MusicPlayerService.this;
        }
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    public boolean isPlaying() {
        return mediaPlayer.isPlaying();
    }

    public void play() {
        mediaPlayer.start();
    }

    public void pause() {
        mediaPlayer.pause();
    }

    public int getDuration() {
        return mediaPlayer.getDuration();
    }

    public void seekPosition(int pos) {
        mediaPlayer.seekTo(pos);
    }

    public int getCurrentPosition() {

        return mediaPlayer.getCurrentPosition();
    }

    public void next() {
        mediaPlayer.stop();
        mediaPlayer.reset();
        if (position + 1 > songList.size() - 1) {
            position = 0;
        } else {
            position++;
        }
        setData();
    }

    public void pre() {
        mediaPlayer.stop();
        mediaPlayer.reset();
        if (position - 1 < 0) {
            position = songList.size() - 1;
        } else {
            position--;
        }
        setData();
    }

    public void reset() {
        mediaPlayer.stop();
        position = 0;
        setData();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        return START_STICKY;
    }

    public void Clear() {
        stopSelf();
    }

    @Override
    public void onDestroy() {
        mediaPlayer.reset();
        mediaPlayer.release();
        Log.e("Service", "Destroy");
    }
}
