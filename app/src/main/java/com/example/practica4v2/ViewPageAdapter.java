package com.example.practica4v2;

import android.net.wifi.hotspot2.pps.HomeSp;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPageAdapter extends FragmentStateAdapter {
    public ViewPageAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch (position){
            case 0: return new Search();
            case 1: return new Bookings();
            case 2: return new Profile();
            default: return new Search();

        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
