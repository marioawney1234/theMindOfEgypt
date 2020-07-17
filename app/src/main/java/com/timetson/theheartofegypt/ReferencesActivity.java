package com.timetson.theheartofegypt;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.timetson.theheartofegypt.helpers.localeHelper;
import com.timetson.theheartofegypt.modules.DataContainer;

public class ReferencesActivity extends AppCompatActivity {

    ////////// Declare views //////////
    private TextView referenceTitleText;
    private ListView referencesListView;
    ///////////////////////////////////

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_references);

        ////////initialization///////////
        final Context mContext = this;
        referenceTitleText = findViewById(R.id.references_title);
        referencesListView = findViewById(R.id.references_list);
        ////////////////////////////////

        ///////set language///////////
        updateLanguage(mContext, DataContainer.LanguageCode);
        //////////////////////////

        // Ads code
        DataContainer.AdmobLoad(this, mContext, R.id.adView);
        // end Ads code

        referencesListView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1, getResources().getStringArray(R.array.References)));

    }


    private void updateLanguage(Context context, String language) {
        Resources resources = localeHelper.getLocalizedResources(context, language);
        referenceTitleText.setText(resources.getString(R.string.references_title));
    }
}
