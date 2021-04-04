package com.example.android.healthcareapp.Controllers;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.android.healthcareapp.R;

public class AddReminderActivity extends AppCompatActivity {

    Spinner interval,time_unit,times;
    LinearLayout freq_layout,starting_time_layout,weekdays_layout;
    RelativeLayout frequency_rl;
    ImageView back;
    TextView timers[] = new TextView[6];
    TextView starting_time,starting_date,ending_date,medname,medinfo;
    CheckBox weekday; //TODO : Handle Weekday

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reminder);

        // weekday=(CheckBox) findViewById(R.id.weekday);
        back=(ImageView) findViewById(R.id.back_add_reminder);
        frequency_rl = (RelativeLayout) findViewById(R.id.frequency_rl);
        medinfo=(TextView)findViewById(R.id.medinf);
        medname=(TextView)findViewById(R.id.medname);
        interval = (Spinner)findViewById(R.id.interval);
        times = (Spinner)findViewById(R.id.times);
        time_unit = (Spinner)findViewById(R.id.time_unit);
        freq_layout = (LinearLayout) findViewById(R.id.freq_layout);
        weekdays_layout = (LinearLayout) findViewById(R.id.weekdays_layout);
        starting_time_layout = (LinearLayout) findViewById(R.id.starting_time_layout);
        starting_time = (TextView) findViewById(R.id.starting_time);
        starting_date =(TextView) findViewById(R.id.starting_date);
        ending_date = (TextView) findViewById(R.id.ending_date);
        timers[0]=(TextView) findViewById(R.id.time1);
        timers[1]=(TextView) findViewById(R.id.time2);
        timers[2]=(TextView) findViewById(R.id.time3);
        timers[3]=(TextView) findViewById(R.id.time4);
        timers[4]=(TextView) findViewById(R.id.time5);
        timers[5]=(TextView) findViewById(R.id.time6);


        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.interval, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        interval.setAdapter(adapter1);

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.time_unit, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        time_unit.setAdapter(adapter2);


        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this,
                R.array.times, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        times.setAdapter(adapter3);


    }

}