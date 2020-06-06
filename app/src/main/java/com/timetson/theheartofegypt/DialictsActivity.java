package com.timetson.theheartofegypt;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class DialictsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialicts);
        final Context context = this;

        final Button BtnBohiric = findViewById(R.id.btn_bohiric);
        BtnBohiric.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(context, BohiricLettersListActivity.class);
                startActivity(intent);
            }
        });

        final Button BtnSahidic = findViewById(R.id.btn_sahidic);
        BtnSahidic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(context, SahidicLettersListActivity.class);
                startActivity(intent);
            }
        });

    }
}
