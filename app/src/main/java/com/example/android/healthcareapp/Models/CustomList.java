package com.example.android.healthcareapp.Models;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.healthcareapp.R;

import java.util.List;
import java.util.Random;


public class CustomList extends ArrayAdapter<String> {
    private List names;
    private String[] desc;
    private Activity context;

    public CustomList(Activity context, List names, String[] desc) {
        super(context, R.layout.list_medinfo, names);
        this.context = context;
        this.names = names;
        this.desc = desc;

    }
    String color_hex[]={"#ff4000","#0000ff","#003EFF","#5C246E","#8B668B","#CD2990","#D41A1F","#FBDB0C","#FF6600"};
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //********************************************************************
        ViewHolder holder;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        // If holder not exist then locate all view from UI file.
        if (convertView == null) {
            // inflate UI from XML file
            convertView = inflater.inflate(R.layout.list_medinfo, parent, false);
            // get all UI view
            holder = new ViewHolder(convertView);
            // set tag for holder
            convertView.setTag(holder);
        } else {
            // if holder created, get tag from view
            holder = (ViewHolder) convertView.getTag();
        }

        holder.textView.setText(getItem(position));
        String firstLetter = null;


        Log.d("String",getItem(position));
        String str=getItem(position);

        firstLetter=String.valueOf(str.charAt(0)).toUpperCase();

        ColorsGenerator generator = ColorsGenerator.MATERIAL; // or use DEFAULT

        int color = generator.getRandomColor();
        int pos= new Random().nextInt(color_hex.length);
        color = Color.parseColor(color_hex[pos]);
        Log.d("Color",""+pos);

        TextDrawable drawable = TextDrawable.builder()
                .buildRound(firstLetter, color); // radius in px

        holder.imageView.setImageDrawable(drawable);

        return convertView;

    }

    private class ViewHolder {
        private ImageView imageView;
        private TextView textView;

        public ViewHolder(View v) {
            imageView = (ImageView) v.findViewById(R.id.medimage);
            textView = (TextView) v.findViewById(R.id.medname);
            Typeface typeface=Typeface.createFromAsset(context.getAssets(), "fonts/georgia.ttf");
            textView.setTypeface(typeface);
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP,20);
        }
    }
}
