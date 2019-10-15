package com.fotl.development.mustach_test;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

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

        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                String t = String.valueOf(i + 1);
                tv.setText(t);



                int sb_Width    = seekBar.getWidth();
                int sb_Max      = seekBar.getMax()+1;
                float temp      = (float) sb_Width / sb_Max;
                tv.setX(50 + (i * temp));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                tv.setVisibility(View.VISIBLE);
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                tv.setVisibility(View.INVISIBLE);
            }
        });
    }
}
