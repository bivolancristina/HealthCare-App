package com.example.android.healthcareapp.Models;

import android.content.Context;

import java.util.ArrayList;

public class MyTaskParams {
    public Context context;
    public ArrayList list;
    public MyTaskParams(Context context, ArrayList list){
        this.context=context;
        this.list=list;
    }

}
