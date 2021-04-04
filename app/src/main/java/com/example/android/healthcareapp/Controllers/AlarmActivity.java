package com.example.android.healthcareapp.Controllers;

import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.android.healthcareapp.R;

public class AlarmActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_alarm);
        TextView tv=(TextView)findViewById(R.id.AlarmActtextView) ;
        TextView name = (TextView) findViewById(R.id.medname);
        TextView inst = (TextView) findViewById(R.id.instructions);


    }

    @Override
    public void onBackPressed() {

    }
}