package com.example.android.healthcareapp.Utils;

import android.os.AsyncTask;

import com.example.android.healthcareapp.Database.DatabaseHandler;
import com.example.android.healthcareapp.Models.MyTaskParams;

public class AddReminderAsync extends AsyncTask<MyTaskParams, Void, Void> {
    DatabaseHandler dh;
    @Override
    protected Void doInBackground(MyTaskParams... myTaskParams) {
        dh= new DatabaseHandler(myTaskParams[0].context);
        dh.addReminder(myTaskParams[0].list);
        return null;
    }
}
