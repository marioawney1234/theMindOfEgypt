package com.timetson.theheartofegypt;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.timetson.theheartofegypt.helpers.LettersSqlHelper;
import com.timetson.theheartofegypt.helpers.localeHelper;
import com.timetson.theheartofegypt.modules.DataContainer;
import com.timetson.theheartofegypt.modules.LetterModule;

import java.util.List;

public class BohiricLettersListActivity extends AppCompatActivity {

    ////////// assign views ////////
    List<LetterModule> moduleList;
    private TextView textViewTitleCoptic;
    private TextView textViewTitle;
    ////////////////////////////////

    ////////////for Language Setting///////////////////
    private void updateLanguage(Context context, String language) {
        Resources resources = localeHelper.getLocalizedResources(context, language);
        textViewTitle.setText(resources.getString(R.string.letter_list_title_bohiric));
    }
    ////////////////////////////////////////////////////

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_letters_list);

        ////////initialization///////////
        final Context mContext = this;
        textViewTitle = findViewById(R.id.letters_list_title);
        textViewTitleCoptic = findViewById(R.id.letters_list_title_coptic);
        ListView lettersListView = findViewById(R.id.lettersListView);
        ///////////////////////////////

        ///////set language///////////
        updateLanguage(mContext, DataContainer.LanguageCode);
        //////////////////////////

        // Ads code
        DataContainer.AdmobLoad(this, mContext, R.id.adView);
        // end Ads code

        textViewTitleCoptic.setText(mContext.getResources().getString(R.string.letter_list_title_bohiric_coptic));

        moduleList = new LettersSqlHelper(mContext).getBohiricLetters();
        DataContainer.bohiricLetterModuleList = moduleList;
        ArrayAdapter<LetterModule> letterListAdapter = new ArrayAdapter<>(mContext, android.R.layout.simple_list_item_1, android.R.id.text1, moduleList);
        lettersListView.setAdapter(letterListAdapter);

        AdapterView.OnItemClickListener lettersAdapter = new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent intent = new Intent(mContext, BohiricLetterDisplayActivity.class);
                intent.putExtra("POSITION", position);
                startActivity(intent);
            }
        };
        lettersListView.setOnItemClickListener(lettersAdapter);
    }
}
