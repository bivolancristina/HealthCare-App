package com.example.android.healthcareapp.Controllers;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.android.healthcareapp.R;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Timer;
import java.util.TimerTask;

public class StartActivity extends AppCompatActivity {
    FirebaseAuth Auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        Auth = FirebaseAuth.getInstance();

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                if(Auth.getCurrentUser() != null) {
                    Intent intent1 = new Intent(StartActivity.this, MainActivity.class);
                    intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent1);
                    finish();
                }else{
                    Intent intent1 = new Intent(StartActivity.this, LoginActivity.class);
                    startActivity(intent1);
                    finish();
                }
            }
        },3000);
    }
}