package com.example.comp490;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.comp490.databinding.ActivitySettingsBinding;

public class settings extends DrawerBase {
    private Button button;
    private Button button2;
    private Switch aSwitch;
    private TextView textView;

    ActivitySettingsBinding activitySettingsBinding;
    /*SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
    Boolean statusLocked = statusLocked = prefs.edit().putBoolean("locked", false).commit();*/

/*   public SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences();
    Boolean statusLocked = prefs.edit().putBoolean("locked", true).commit();*/



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activitySettingsBinding = ActivitySettingsBinding.inflate(getLayoutInflater());
        setContentView(activitySettingsBinding.getRoot());
        allocateActivityTitle("Settings");
       aSwitch = findViewById(R.id.mode);

        /*if(AppCompatDelegate.getDefaultNightMode() ==AppCompatDelegate.MODE_NIGHT_YES) {
            aSwitch.setChecked(true);
        }*/
        //aSwitch.setChecked(true);
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    ConstraintLayout layout =(ConstraintLayout)findViewById(R.id.test);
                    layout.setBackgroundResource(R.drawable.background_dark);
                    aSwitch.setChecked(true);
                    //statusLocked = statusLocked = prefs.edit().putBoolean("locked", true).commit();;
                    //reset();

                }else{
                    ConstraintLayout layout =(ConstraintLayout)findViewById(R.id.test);
                    layout.setBackgroundResource(R.drawable.background_light);
                    aSwitch.setChecked(false);
                    //reset();
                }
            }
        });



        //boolean ischecked = aSwitch.isChecked();

        /*if (statusLocked) {
            ConstraintLayout layout =(ConstraintLayout)findViewById(R.id.test);
            layout.setBackgroundResource(R.drawable.darkbackground);
        }
        else {
            ConstraintLayout layout =(ConstraintLayout)findViewById(R.id.test);
            layout.setBackgroundResource(R.drawable.background);
        }*/



        Button button = (Button) findViewById(R.id.back);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHome();
            }
        });
        Button button2 = (Button) findViewById(R.id.logout);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opensignin();
            }
        });
    }

    private void reset() {
        Intent intent = new Intent(getApplicationContext(), settings.class);
        startActivity(intent);
        finish();
    }

    public void openHome(){
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }
    public void opensignin(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}