package com.timetson.theheartofegypt;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.timetson.theheartofegypt.helpers.localeHelper;
import com.timetson.theheartofegypt.modules.DataContainer;

public class IntroductionActivity extends AppCompatActivity {

    ////////// Declare views ////////
    private TextView introductionTitleText;
    private TextView introductionText;
    //////////////////////////////////////////////////////////

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduction);

        ////////initialization///////////
        final Context mContext = this;
        introductionText = findViewById(R.id.introduction_text);
        introductionTitleText = findViewById(R.id.intoduction_title_text);
        ////////////////////////////////

        ///////set language///////////
        updateLanguage(mContext, DataContainer.LanguageCode);
        //////////////////////////

        // Ads code
        DataContainer.AdmobLoad(this, mContext, R.id.adView);
        // end Ads code

        introductionText.setMovementMethod(new ScrollingMovementMethod());
    }

    private void updateLanguage(Context context, String language) {
        Resources resources = localeHelper.getLocalizedResources(context, language);
        introductionTitleText.setText(resources.getString(R.string.string_introduction_title));
        introductionText.setText(resources.getString(R.string.string_introduction));
    }

}
