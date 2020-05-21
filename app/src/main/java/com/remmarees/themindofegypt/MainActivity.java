package com.remmarees.themindofegypt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Context context = this;

        final Button BtnAlphabet=findViewById(R.id.btn_alphabet);
        BtnAlphabet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(context, DialictsActivity.class);
                startActivity(intent);
            }
        });

        final Button BtnTTS=findViewById(R.id.btn_tts);
        BtnTTS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(context, TTsActivity.class);
                startActivity(intent);
            }
        });

    }
}
