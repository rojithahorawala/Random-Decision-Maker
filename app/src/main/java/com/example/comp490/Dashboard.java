package com.example.comp490;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.comp490.databinding.ActivityDashboardBinding;

public class Dashboard extends DrawerBase {

    ActivityDashboardBinding activityDashboardBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityDashboardBinding = activityDashboardBinding.inflate(getLayoutInflater());
        setContentView(activityDashboardBinding.getRoot());
        allocateActivityTitle("Dashboard");
    }
}