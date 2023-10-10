package com.example.musicapp.SubActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.musicapp.Fragment.ViewPageRankingAdapter;
import com.example.musicapp.R;
import com.google.android.material.tabs.TabLayout;

public class RankingActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;

    private Button btnlike;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);
        tabLayout = findViewById(R.id.tab_ranking);
        viewPager = findViewById(R.id.view_pager_ranking);
        ViewPageRankingAdapter adapter = new ViewPageRankingAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        getSupportActionBar().setTitle("Charts");
    }

}