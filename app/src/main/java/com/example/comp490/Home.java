package com.example.comp490;

import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.navigation.ui.AppBarConfiguration;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.comp490.databinding.ActivityHomeBinding;
import com.example.comp490.databinding.ActivityMainBinding;
import com.google.android.material.button.MaterialButton;

public class Home extends DrawerBase{
    private Button button;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Switch aSwitch;

    ActivityHomeBinding activityHomeBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityHomeBinding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(activityHomeBinding.getRoot());
        allocateActivityTitle("Home");

        Button button = (Button) findViewById(R.id.signout);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMain();
            }
        });
        Button button3 = (Button) findViewById(R.id.settings);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openset();
            }
        });
        Button button4 = (Button) findViewById(R.id.newDecision);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openratings();
            }
        });
        Button button5 = (Button) findViewById(R.id.list);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLists();
            }
        });
    }
    public void openMain(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void openset(){
        Intent intent = new Intent(this, settings.class);
        startActivity(intent);
    }
    public void openratings(){
        Intent intent = new Intent(this, NewDec.class);
        startActivity(intent);
    }
    public void openLists(){
        Intent intent = new Intent(this, List.class);
        startActivity(intent);
    }
}