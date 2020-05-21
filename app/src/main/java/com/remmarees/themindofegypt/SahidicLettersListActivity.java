package com.remmarees.themindofegypt;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.remmarees.themindofegypt.sqlHelper.DataContainer;
import com.remmarees.themindofegypt.sqlHelper.LetterModule;
import com.remmarees.themindofegypt.sqlHelper.bohiricLettersSqlHelper;

import java.util.List;

public class SahidicLettersListActivity extends AppCompatActivity {

    final Context context = this;
    List<LetterModule> moduleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_letters_list);
        ListView lettersListView=findViewById(R.id.lettersListView);
        moduleList = new bohiricLettersSqlHelper(context).getSahidicLetters();
        DataContainer.sahidicLetterModuleList =moduleList;
        ArrayAdapter<LetterModule> letterListAdapter = new ArrayAdapter<>(context,android.R.layout.simple_list_item_1,android.R.id.text1, moduleList);
        lettersListView.setAdapter(letterListAdapter);

        AdapterView.OnItemClickListener aa = new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent intent = new Intent(context, SahidicLetterDisplayActivity.class);
                intent.putExtra("POSITION",position);
                startActivity(intent);
            }
        };
        lettersListView.setOnItemClickListener(aa);
    }
}
