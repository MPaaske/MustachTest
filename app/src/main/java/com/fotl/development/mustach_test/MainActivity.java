package com.fotl.development.mustach_test;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Region;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity{

    private SeekBar sb;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sb = findViewById(R.id.seekBar);
        tv = findViewById(R.id.floatingValue);

        tv.setVisibility(View.INVISIBLE);


        //region JSON
        String gStart = "";

        try {
            JSONObject jObj = new JSONObject(readJsonFromAsset());
            JSONArray jArr = jObj.getJSONArray("colors");

            JSONObject internal_jObj = null;

            for(int i = 0; i < jArr.length(); i++){
                internal_jObj = jArr.getJSONObject(i);
                gStart = internal_jObj.getString("gradiant_start");

            }

            String Test = internal_jObj.getString("gradiant_end");

            String jStrVal_final = gStart;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        //endregion

        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                // Attempt to make a floating TextView that keeps up with SeekBarThumb

                String t = String.valueOf(i + 1);
                tv.setText(t);

                int sb_Width = seekBar.getWidth();
                int sb_Max = seekBar.getMax()+1;
                float intervalSpace = (float) sb_Width / sb_Max;
                tv.setX(50 + (i * intervalSpace));// 50 is margin and the radius of the thumb, that gives us a starting point, then we add a the selected number * it with intervalSpace
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                tv.setVisibility(View.VISIBLE);// Set the TextView Visible
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                tv.setVisibility(View.INVISIBLE);// Set the TextView Invisible
            }
        });
    }

    public String readJsonFromAsset(){
        String jStr = null;

        try{
            InputStream inpStr = getAssets().open("resources.json");

            int size = inpStr.available();

            byte[] buffer = new byte[size];

            inpStr.read(buffer);

            inpStr.close();

            jStr = new String(buffer);

        }catch (IOException e){
            e.printStackTrace();
        }

        return jStr;
    }
}
