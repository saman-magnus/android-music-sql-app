package com.example.musicapp.Fragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class ViewPagerPlayingAdapter extends FragmentStatePagerAdapter {
    public ViewPagerPlayingAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new PlayingFragment();
            case 1:
                return new DiskplayingFragment();
            default:
                return new PlayingFragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
