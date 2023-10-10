package com.example.musicapp.Fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class ViewPageRankingAdapter extends FragmentStatePagerAdapter {
    public ViewPageRankingAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new RVpopFragment();
            case 1:
                return new RUSUKFragment();
            case 2:
                return new RKpopFragment();
            default:
                return new RVpopFragment();
        }

    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position) {
            case 0:
                title = "VIETNAM";
                break;
            case 1:
                title = "US-UK";
                break;
            case 2:
                title = "KOREAN MUSIC";
                break;
        }
        return title;
    }
}
