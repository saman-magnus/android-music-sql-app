package com.example.musicapp.Notification;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v4.media.session.MediaSessionCompat;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.musicapp.Model.Song;
import com.example.musicapp.R;
import com.example.musicapp.Service.NotificationActionService;

public class MusicNotification {
    public static final String CHANEL_ID = "chanelappmusic";
    public static final String ACTION_PRE = "actionpre";
    public static final String ACTION_PLAY = "actionplay";
    public static final String ACTION_NEXT = "actionnext";

    public static Notification notification;

    public static void musicNotification(Context context, Song song, int btnPlay, int pos, int size) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
            MediaSessionCompat mediaSessionCompat = new MediaSessionCompat(context, "TAG");
            PendingIntent pendingIntentPre;
            int btn_previous;
            if (pos == 0) {
                pendingIntentPre = null;
                btn_previous = 0;
            } else {
                Intent intentPre = new Intent(context, NotificationActionService.class)
                        .setAction(ACTION_PRE);
                pendingIntentPre = PendingIntent.getBroadcast(context, 0, intentPre, PendingIntent.FLAG_UPDATE_CURRENT);
                btn_previous = R.drawable.btn_pre;
            }


            Intent intentPlay = new Intent(context, NotificationActionService.class)
                    .setAction(ACTION_PLAY);
            PendingIntent pendingIntentPlay = PendingIntent.getBroadcast(context, 0, intentPlay, PendingIntent.FLAG_UPDATE_CURRENT);


            PendingIntent pendingIntentNext;
            int btn_next;
            if (pos == size) {
                pendingIntentNext = null;
                btn_next = 0;
            } else {
                Intent intentNext = new Intent(context, NotificationActionService.class)
                        .setAction(ACTION_NEXT);
                pendingIntentNext = PendingIntent.getBroadcast(context, 0, intentNext, PendingIntent.FLAG_UPDATE_CURRENT);
                btn_next = R.drawable.btn_next;
            }

            Bitmap icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.headallbum);
            notification = new NotificationCompat.Builder(context, CHANEL_ID)
                    .setSmallIcon(R.drawable.ic_baseline_play_circle_filled_24)
                    .setContentTitle(song.getTenBH())
                    .setContentText(song.getTenNS())
                    .setLargeIcon(icon)
                    .setOnlyAlertOnce(true)
                    .setShowWhen(false)
                    .addAction(btn_previous, "Previous", pendingIntentPre)
                    .addAction(btnPlay, "Play", pendingIntentPlay)
                    .addAction(btn_next, "Next", pendingIntentNext)
                    .setStyle(new androidx.media.app.NotificationCompat.MediaStyle()
                            .setShowActionsInCompactView(0, 1, 2)
                            .setMediaSession(mediaSessionCompat.getSessionToken()))
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .build();
            notificationManagerCompat.notify(1, notification);
        }
    }

}
