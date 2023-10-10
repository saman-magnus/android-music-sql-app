package com.example.musicapp;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.musicapp.Fragment.ViewPageApdater;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    private static int CODE_PERMISSION = 612;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Parsclip Online Music Center");
        Log.e("PERMISSION", "" + hasNetworkPermission());
        if (hasNetworkPermission()) {
            viewPager = findViewById(R.id.view_pager);
            tabLayout = findViewById(R.id.tab_home);
            ViewPageApdater viewPageApdater = new ViewPageApdater(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
            viewPager.setAdapter(viewPageApdater);
            tabLayout.setupWithViewPager(viewPager);
        } else {
            requestNetworkPermission();
        }
    }

    private boolean hasNetworkPermission() {
        return ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.INTERNET) == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_NETWORK_STATE) == PackageManager.PERMISSION_GRANTED;
    }

    private void requestNetworkPermission() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET, Manifest.permission.ACCESS_NETWORK_STATE}, CODE_PERMISSION);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode != CODE_PERMISSION) {

        } else {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            } else {
                Toast.makeText(getApplicationContext(), "Currently the application is not connected to the INTERNET", Toast.LENGTH_SHORT).show();
            }
        }

    }


}