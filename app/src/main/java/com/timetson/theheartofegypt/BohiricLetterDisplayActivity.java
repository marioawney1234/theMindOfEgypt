package com.timetson.theheartofegypt;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.timetson.theheartofegypt.helpers.LettersHelper;
import com.timetson.theheartofegypt.helpers.localeHelper;
import com.timetson.theheartofegypt.modules.DataContainer;
import com.timetson.theheartofegypt.modules.PronounceModule;

import net.cachapa.expandablelayout.ExpandableLayout;

import java.util.List;

public class BohiricLetterDisplayActivity extends AppCompatActivity {

    ////////// assign views ////////
    private TextView textViewName;
    private TextView textViewType;
    private TextView textViewCommentTitle;
    private TextView textViewCommentAlert;
    private String titleGeneral;
    private String titleAcadimic;
    private String titleLate;
    private String titlemodern;
    private Button button_letter_name_acadimic;
    private Button button_letter_name_late;
    private Button button_letter_name_reformed;
    ////////////////////////////////

    ////////////for Language Setting///////////////////

    private void setLanguage(Context context, String language) {
        Resources resources = localeHelper.getLocalizedResources(context, language);
        textViewName.setText(resources.getString(R.string.letter_display_name));
        textViewType.setText(resources.getString(R.string.letter_display_type));
        textViewCommentTitle.setText(resources.getString(R.string.letter_display_comment));
        textViewCommentAlert.setText(resources.getString(R.string.letter_display_comment_alert));
        button_letter_name_acadimic.setText(resources.getString(R.string.acadimic));
        button_letter_name_late.setText(resources.getString(R.string.late));
        button_letter_name_reformed.setText(resources.getString(R.string.reformed));
        titleGeneral = resources.getString(R.string.letter_display_general_title);
        titleAcadimic = resources.getString(R.string.letter_display_acadimic_title);
        titleLate = resources.getString(R.string.letter_display_late_title);
        titlemodern = resources.getString(R.string.letter_display_modern_title);
    }
    ////////////////////////////////////////////////////


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_letter_display);

        ////////initialization///////////
        final Context mContext = this;
        textViewName = findViewById(R.id.letter_name_title);
        textViewType = findViewById(R.id.letter_type_title);
        textViewCommentTitle = findViewById(R.id.comment_title);
        textViewCommentAlert = findViewById(R.id.comment_alert);
        button_letter_name_acadimic = findViewById(R.id.letter_name_acadimic);
        button_letter_name_reformed = findViewById(R.id.letter_name_reformed);
        button_letter_name_late = findViewById(R.id.letter_name_late);


        ///////////////////////////////

        ///////set language///////////
        setLanguage(mContext, DataContainer.LanguageCode);
        //////////////////////////

        // Ads code
        DataContainer.AdmobLoad(this, mContext, R.id.adView);
        // end Ads code

        final int position = getIntent().getIntExtra("POSITION", 0);
        final TextView Capital_letter = findViewById(R.id.cabital_letter);
        final TextView Small_letter = findViewById(R.id.small_letter);
        final TextView letter_type = findViewById(R.id.letter_type);
        final TextView letter_name = findViewById(R.id.letter_name);
        final TextView letter_comment = findViewById(R.id.letter_comment);
        final String[] types = getResources().getStringArray(R.array.letters_type);

        Capital_letter.setText(DataContainer.bohiricLetterModuleList.get(position).getCapital());
        Small_letter.setText(DataContainer.bohiricLetterModuleList.get(position).getLetter());
        letter_type.setText(types[DataContainer.bohiricLetterModuleList.get(position).getType()]);
        letter_name.setText(DataContainer.bohiricLetterModuleList.get(position).getName());
        if (DataContainer.LanguageCode.equals("ar")) {
            letter_comment.setText(DataContainer.bohiricLetterModuleList.get(position).getComment());
        } else {
            letter_comment.setText(DataContainer.bohiricLetterModuleList.get(position).getEnglishComment());

        }

        DataContainer.generalBohiricPronouncation = new LettersHelper(mContext).getBohiricPronouncation(DataContainer.bohiricLetterModuleList.get(position).getLetter(), "g");
        DataContainer.acadimicBohiricPronouncation = new LettersHelper(mContext).getBohiricPronouncation(DataContainer.bohiricLetterModuleList.get(position).getLetter(), "a");
        DataContainer.newBohiricPronouncation = new LettersHelper(mContext).getBohiricPronouncation(DataContainer.bohiricLetterModuleList.get(position).getLetter(), "n");
        DataContainer.lateBohiricPronouncation = new LettersHelper(mContext).getBohiricPronouncation(DataContainer.bohiricLetterModuleList.get(position).getLetter(), "l");
        setLayoutContents(mContext, findViewById(R.id.general), mContext.getResources().getString(R.string.letter_display_general_title_coptic), titleGeneral, DataContainer.generalBohiricPronouncation);
        setLayoutContents(mContext, findViewById(R.id.acadimic_bohiric), mContext.getResources().getString(R.string.letter_display_acadimic_title_coptic), titleAcadimic, DataContainer.acadimicBohiricPronouncation);
        setLayoutContents(mContext, findViewById(R.id.new_bohiric), mContext.getResources().getString(R.string.letter_display_modern_title_coptic), titlemodern, DataContainer.newBohiricPronouncation);
        setLayoutContents(mContext, findViewById(R.id.late_bohiric), mContext.getResources().getString(R.string.letter_display_late_title_coptic), titleLate, DataContainer.lateBohiricPronouncation);

        if (!DataContainer.bohiricLetterModuleList.get(position).getComment().equals("-")) {
            findViewById(R.id.comment_layout).setVisibility(View.VISIBLE);
            findViewById(R.id.comment_viewline).setVisibility(View.VISIBLE);
            findViewById(R.id.expandable_layout).setVisibility(View.VISIBLE);
        }
        expand_coolapse((ExpandableLayout) findViewById(R.id.expandable_comment), findViewById(R.id.comment_layout), (ImageView) findViewById(R.id.expand_image1));

        button_letter_name_acadimic.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                DataContainer.playSound(mContext, "letters_names/" + DataContainer.bohiricLetterModuleList.get(position).getLetter() + "/" + DataContainer.bohiricLetterModuleList.get(position).getLetter() + "ⲁ.mp3");
            }
        });

        button_letter_name_late.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                DataContainer.playSound(mContext, "letters_names/" + DataContainer.bohiricLetterModuleList.get(position).getLetter() + "/" + DataContainer.bohiricLetterModuleList.get(position).getLetter() + "ϧ.mp3");
            }
        });

        button_letter_name_reformed.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                DataContainer.playSound(mContext, "letters_names/" + DataContainer.bohiricLetterModuleList.get(position).getLetter() + "/" + DataContainer.bohiricLetterModuleList.get(position).getLetter() + "ⲃ.mp3");
            }
        });
    }

    private void setLayoutContents(Context context, final View view, String copticTitle, String title, List<PronounceModule> list) {
        if (list.size() == 0) {
            view.setVisibility(View.GONE);
            return;
        }
        TextView copticTitleText = view.findViewById(R.id.coptic_title);
        TextView translatedTitleText = view.findViewById(R.id.translated_title);
        copticTitleText.setText(copticTitle);
        translatedTitleText.setText(title);

        ProunouncationAdapter adapter = new ProunouncationAdapter(context, list);
        ListView listView = view.findViewById(R.id.general_list);
        listView.setAdapter(adapter);
        listView.setEnabled(false);
        if (copticTitle.equals(context.getResources().getString(R.string.letter_display_general_title_coptic))) {
            ((ExpandableLayout) view.findViewById(R.id.expandable_layout)).expand();
        } else {
            expand_coolapse((ExpandableLayout) view.findViewById(R.id.expandable_layout), view.findViewById(R.id.button_layout), (ImageView) view.findViewById(R.id.expand_image));
        }

    }

    private void expand_coolapse(final ExpandableLayout expandableLayout, final View expand_button, final ImageView image) {
        final ScrollView scrollView = findViewById(R.id.scrollview);

        expandableLayout.setOnExpansionUpdateListener(new ExpandableLayout.OnExpansionUpdateListener() {
            @Override
            public void onExpansionUpdate(float expansionFraction, int state) {
                Log.d("ExpandableLayout0", "State: " + state);
            }
        });
        expandableLayout.collapse();
        expand_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (expandableLayout.isExpanded()) {
                    expandableLayout.collapse();
                    image.setImageResource(R.drawable.ic_action_expand);
                    //scrollView.fullScroll(ScrollView.FOCUS_UP);
                } else {
                    expandableLayout.expand();
                    image.setImageResource(R.drawable.ic_action_collapse);
                    scrollView.smoothScrollTo(0, scrollView.getScrollY() + 120);
                }
            }
        });
    }
}
