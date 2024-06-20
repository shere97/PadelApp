package com.example.practica4v2.adapters;

import android.net.wifi.hotspot2.pps.HomeSp;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.practica4v2.Bookings;
import com.example.practica4v2.Profile;
import com.example.practica4v2.Search;

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
