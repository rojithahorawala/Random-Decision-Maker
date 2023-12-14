package com.example.comp490;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.comp490.databinding.ActivityListBinding;
import com.example.comp490.databinding.ActivitySettingsBinding;

public class List extends DrawerBase {
    private Button button;
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;

    ActivityListBinding activityListBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityListBinding = ActivityListBinding.inflate(getLayoutInflater());
        setContentView(activityListBinding.getRoot());
        allocateActivityTitle("Lists");

        Button button = (Button) findViewById(R.id.back);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHome();
            }
        });
        /*Button button1 = (Button) findViewById(R.id.newlist);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewList();
            }
        });*/
        Button button1 = (Button) findViewById(R.id.fitness);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewDec();
            }
        });
        Button button2 = (Button) findViewById(R.id.food);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFood();
            }
        });
        Button button3 = (Button) findViewById(R.id.entertain);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEntertainment();
            }
        });
        Button button4 = (Button) findViewById(R.id.sightseeing);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSightseeing();
            }
        });
    }
    public void openHome(){
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }
    /*public void openNewList(){
        Intent intent = new Intent(this, NewList.class);
        startActivity(intent);
    }*/
     public void openNewDec(){
        Intent intent = new Intent(this, Fitnesss.class);
        startActivity(intent);
    }
    public void openFood(){
        Intent intent = new Intent(this, FoodList.class);
        startActivity(intent);
    }
    public void openEntertainment(){
        Intent intent = new Intent(this, Entertainment.class);
        startActivity(intent);
    }
    public void openSightseeing(){
        Intent intent = new Intent(this, Spots.class);
        startActivity(intent);
    }
}