package com.example.comp490;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class SignUp extends AppCompatActivity {
    private Button button;

    //Possible way to edit text, although receives an error on line 32
    TextInputEditText editUsername, editEmail, editPassword, editConfirmPassword;

    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        EditText editUsername = (EditText)findViewById(R.id.username);
        EditText editEmail = (EditText)findViewById(R.id.email);
        EditText editPassword = (EditText)findViewById(R.id.password);
        EditText editConfirmPassword = (EditText)findViewById(R.id.confirmPassword);
        //TextView viewUsername =(TextView) findViewById(R.id.username);
        //TextView viewEmail = (TextView) findViewById(R.id.email);
        //TextView viewPassword = (TextView) findViewById(R.id.password);
        //TextView viewConfirmPassword = (TextView) findViewById(R.id.confirmPassword);
        TextView viewAccountExists = (TextView) findViewById(R.id.forgotpass3);
        progressBar = findViewById(R.id.progress);

        viewAccountExists.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        button = (Button) findViewById(R.id.sign_up);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username, email, password, confirmPassword;
                //username = String.valueOf(viewUsername.getText());
                username = editUsername.getText().toString();
                email = editEmail.getText().toString();
                password = editPassword.getText().toString();
                confirmPassword = editConfirmPassword.getText().toString();
                //email = String.valueOf(viewEmail.getText());
                //password = String.valueOf(viewPassword.getText());
                //confirmPassword = String.valueOf(viewConfirmPassword.getText());
                Log.d(TAG,"Response: "+username);
                Log.d(TAG,"Response: "+email);
                Log.d(TAG,"Response: "+password);
                Log.d(TAG,"Response: "+confirmPassword);


                if(!username.equals("") && !email.equals("") && !password.equals("") && !confirmPassword.equals("")) {
                    //Start ProgressBar first (Set visibility VISIBLE)
                    progressBar.setVisibility(View.VISIBLE);
                    Handler handler = new Handler();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            //Starting Write and Read data with URL
                            //Creating array for parameters
                            String[] field = new String[4];
                            field[0] = "fullname";
                            field[1] = "username";
                            field[2] = "password";
                            field[3] = "email";
                            //Creating array for data
                            String[] data = new String[4];
                            data[0] = username;
                            data[1] = email;
                            data[2] = password;
                            data[3] = confirmPassword;
                            PutData putData = new PutData("http://192.168.64.3/LoginRegister/signup.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    progressBar.setVisibility(View.GONE);
                                    String result = putData.getResult();
                                    if(result.equals("Sign Up Success")){
                                        Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                    else{
                                        Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                            //End Write and Read data with URL
                        }
                    });
                }
                else{
                    Toast.makeText(getApplicationContext(), "All fields requireds.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}