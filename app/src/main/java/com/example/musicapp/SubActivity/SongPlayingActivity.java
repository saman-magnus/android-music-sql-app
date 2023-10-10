package com.example.musicapp.SubActivity;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.musicapp.Fragment.DiskplayingFragment;
import com.example.musicapp.Fragment.ViewPagerPlayingAdapter;
import com.example.musicapp.Model.Song;
import com.example.musicapp.Notification.MusicNotification;
import com.example.musicapp.R;
import com.example.musicapp.Service.MusicPlayerService;
import com.example.musicapp.Service.NotificationActionService;
import com.example.musicapp.Service.OnClearFromRecentService;
import com.example.musicapp.Service.Playable;
import com.squareup.picasso.Picasso;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;







public class SongPlayingActivity extends AppCompatActivity implements Playable {
    private Handler handler = new Handler();
    private ViewPager view_pager_playing;
    private Intent serviceIntent;
    public static List<Song> songList = new ArrayList<>();
    private TextView tvName, tvTimePre, tvTimePost;

    private ImageView ivPlay, ivStop, ivNext, ivPre, axmusic;


    private SeekBar seekBar;
    int position = 0;
    boolean isPlaying = false;
    MediaPlayer mediaPlayer;
    ViewPagerPlayingAdapter adapter;
    DiskplayingFragment diskplayingFragment;
    private MusicPlayerService mMusicPlayerService;
    private boolean mBound = false;

    NotificationManager notificationManager;
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MusicPlayerService.MyServiceBinder myServiceBinder = (MusicPlayerService.MyServiceBinder) service;

            mMusicPlayerService = myServiceBinder.getService();
            mBound = true;
            Log.e("TAG", "onServiceConnected");
            if(songList.size()>0){
                startPlayProgressUpdate();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mBound = false;
            Log.e("TAG", "onServiceDisconnected");
        }
    };
    ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_playing);
        getData();
        init();
        eventClick();
        serviceIntent = new Intent(getApplicationContext(), MusicPlayerService.class);
        stopService(serviceIntent);
//        Log.e("TAG",  + "");
        startService(serviceIntent);

        bindService(serviceIntent, connection, BIND_AUTO_CREATE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChanelNotification();

            registerReceiver(broadcastReceiver, new IntentFilter("TRACKS_TRACKS"));
            startService(new Intent(getBaseContext(), OnClearFromRecentService.class));
            if(songList.size()>0){
                MusicNotification.musicNotification(getApplicationContext(), songList.get(position), R.drawable.btn_pause, position, songList.size() - 1);
            }
        }
    }

    private void createChanelNotification() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(MusicNotification.CHANEL_ID, "3PM", NotificationManager.IMPORTANCE_HIGH);
            notificationManager = getSystemService(NotificationManager.class);
            try {
                notificationManager.deleteNotificationChannel(MusicNotification.CHANEL_ID);
            }
            catch (Exception e){
                Log.e("TAG","Can't delete this");
            }
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(channel);
            }

        }
    }


    private void getData() {
        Intent intent = getIntent();
        if (intent.hasExtra("song")) {
            Log.e("TAG", "Received");
            Song song = intent.getParcelableExtra("song");
            songList.clear();
            songList.add(song);
            Toast.makeText(this, song.getTenBH(), Toast.LENGTH_SHORT).show();
        }
        if (intent.hasExtra("listSong")) {
            songList.clear();
            Log.e("TAG", "Received");
            List<Song> listSongIntent = intent.getParcelableArrayListExtra("listSong");
            for (int i = 0; i < listSongIntent.size(); i++) {
                Log.e("TAG", listSongIntent.get(i).getTenBH());
                songList.add(listSongIntent.get(i));
            }
        }
    }

    private void eventClick() {
        ivStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mBound && songList.size()>0) {
                    try{
                        if (mMusicPlayerService.isPlaying()) {
                            ivPlay.setImageResource(R.drawable.btn_pause);
                            mMusicPlayerService.seekPosition(0);

                        } else {
                            ivPlay.setImageResource(R.drawable.btn_play);
                            mMusicPlayerService.seekPosition(0);
                            mMusicPlayerService.pause();
                        }
                        startPlayProgressUpdate();
                    }
                    catch (Exception e){
                        Log.e("TAG","Waiting");
                    }

                }
            }
        });
        ivPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mBound) {
                    if (mMusicPlayerService.isPlaying()) {
                        onTrackPause();
                        startPlayProgressUpdate();
                    } else {
                        onTrackPlay();
                    }
                }
            }
        });
        ivNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onTrackNext();
            }
        });
        ivPre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onTrackPrevious();

            }
        });
        seekBar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                updateSeekBarChange(v);
                return false;
            }
        });

    }

    private void updateSeekBarChange(View v) {
        if (mMusicPlayerService.isPlaying()) {
            SeekBar sb = (SeekBar) v;
            Log.e("TAG", sb.getProgress() + "/" + mMusicPlayerService.getDuration());
            mMusicPlayerService.seekPosition(sb.getProgress());
        }
    }


    private void init() {
        getSupportActionBar().setTitle("Music Player");
        tvName = findViewById(R.id.tvNamePlaying);
        axmusic = findViewById(R.id.ivXoaytron);
        seekBar = findViewById(R.id.seekBar1);
        ivPlay = findViewById(R.id.ivPlay);
        ivNext = findViewById(R.id.ivNext);
        ivStop = findViewById(R.id.ivStop);
        ivPre = findViewById(R.id.ivPre);
        tvTimePost = findViewById(R.id.tvTimePost);
        tvTimePre = findViewById(R.id.tvTimePre);
        view_pager_playing = findViewById(R.id.view_pager_playing);
        adapter = new ViewPagerPlayingAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        view_pager_playing.setAdapter(adapter);

        diskplayingFragment = (DiskplayingFragment) adapter.getItem(1);

        if (songList.size() > 0) {
            Log.e("LINK", songList.get(0).getLinkBH());
        }
    }

    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getExtras().getString("action_name");
            switch (action) {
                case MusicNotification.ACTION_PRE:
                    onTrackPrevious();
                    break;
                case MusicNotification.ACTION_PLAY:
                    if (isPlaying) {
                        onTrackPause();
                    } else {
                        onTrackPlay();
                    }
                    break;
                case MusicNotification.ACTION_NEXT:
                    onTrackNext();
                    break;
            }
        }
    };

    @Override
    public void onTrackPrevious() {
        try {
            if(songList.size()>0){
                if (position - 1 < 0) {
                    position = songList.size() - 1;
                } else {
                    position--;
                }
                startPlayProgressUpdate();
                mMusicPlayerService.pre();
                MusicNotification.musicNotification(getApplicationContext(), songList.get(position), R.drawable.btn_pause, position, songList.size() - 1);
                isPlaying = true;
            }
        }
        catch (Exception e){
            Log.e("TAG","Waitimg");
        }

    }

    @Override
    public void onTrackPlay() {
        try {
            if(songList.size()>0){
                startPlayProgressUpdate();
                if(!mMusicPlayerService.isPlaying()){
                    int pos = mMusicPlayerService.getCurrentPosition();
                    mMusicPlayerService.seekPosition(pos);
                    mMusicPlayerService.play();
                }
                MusicNotification.musicNotification(getApplicationContext(), songList.get(position), R.drawable.btn_pause, position, songList.size() - 1);
                ivPlay.setImageResource(R.drawable.btn_pause);
                isPlaying = true;
            }
        }catch (Exception e){
            Log.e("TAG","Waiting");
        }

    }

    @Override
    public void onTrackPause() {
        try {
            if(songList.size()>0){
                startPlayProgressUpdate();
                mMusicPlayerService.pause();
                MusicNotification.musicNotification(getApplicationContext(), songList.get(position), R.drawable.btn_play, position, songList.size() - 1);
                ivPlay.setImageResource(R.drawable.btn_play);
                isPlaying = false;
            }
        }catch (Exception e){
            Log.e("TAG","Waiting");
        }


    }

    @Override
    public void onTrackNext() {
        try {
            if(songList.size()>0){
                if (position + 1 > songList.size() - 1) {
                    position = 0;
                } else {
                    position++;
                }
                mMusicPlayerService.next();
                MusicNotification.musicNotification(getApplicationContext(), songList.get(position), R.drawable.btn_pause, position, songList.size() - 1);
                isPlaying = true;
            }
        }catch (Exception e){
            Log.e("TAG","Waiting");
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            notificationManager.cancelAll();
//            unregisterReceiver(broadcastReceiver);
        }
        handler.removeCallbacks(null);

    }

    private void startPlayProgressUpdate() {
        try {

            tvName.setText(mMusicPlayerService.getName());
            seekBar.setMax(mMusicPlayerService.getDuration());
            SimpleDateFormat dateFormat = new SimpleDateFormat("mm:ss");
            tvTimePost.setText(dateFormat.format(mMusicPlayerService.getDuration()));
            tvTimePre.setText(dateFormat.format(mMusicPlayerService.getCurrentPosition()));
            seekBar.setProgress(mMusicPlayerService.getCurrentPosition());
            if (mMusicPlayerService.isPlaying()) {
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        startPlayProgressUpdate();
                    }
                };
                handler.postDelayed(runnable, 1000);
            }
        }
        catch (Exception e){
            Log.e("NULL","NAME NULL");
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        if (mMusicPlayerService.mediaPlayer != null) {
            outState.putInt("location", mMusicPlayerService.getCurrentPosition());
            Log.d("TAG", "onSaveInstanceState: " + mMusicPlayerService.getCurrentPosition());
        } else
            outState.putInt("location", 0);

    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        try {
            Thread.sleep(1000);
            mMusicPlayerService.seekPosition(savedInstanceState.getInt("location", 0));
        } catch (Exception e) {

        }

    }





}


