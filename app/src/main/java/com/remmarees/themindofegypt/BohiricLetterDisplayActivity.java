package com.remmarees.themindofegypt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.remmarees.themindofegypt.sqlHelper.DataContainer;
import com.remmarees.themindofegypt.sqlHelper.bohiricLettersSqlHelper;
import com.remmarees.themindofegypt.sqlHelper.PronounceModule;

import net.cachapa.expandablelayout.ExpandableLayout;

import java.util.List;

public class BohiricLetterDisplayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_letter_display);
        final Context context=this;
        final int position=getIntent().getIntExtra("POSITION",0);
        final TextView Capital_letter= findViewById(R.id.cabital_letter);
        final TextView Small_letter= findViewById(R.id.small_letter);
        final TextView letter_type= findViewById(R.id.letter_type);
        final TextView letter_name= findViewById(R.id.letter_name);
        final String[] types= getResources().getStringArray(R.array.letters_type);
        //final ScrollView scrollView=findViewById(R.id.scrollview);


        Capital_letter.setText(DataContainer.bohiricLetterModuleList.get(position).getCapital());
        Small_letter.setText(DataContainer.bohiricLetterModuleList.get(position).getLetter());
        letter_type.setText(types[DataContainer.bohiricLetterModuleList.get(position).getType()]);
        letter_name.setText(DataContainer.bohiricLetterModuleList.get(position).getName());

        DataContainer.generalBohiricPronouncation = new bohiricLettersSqlHelper(context).getBohiricPronouncation(DataContainer.bohiricLetterModuleList.get(position).getLetter(),"g");
        DataContainer.acadimicBohiricPronouncation = new bohiricLettersSqlHelper(context).getBohiricPronouncation(DataContainer.bohiricLetterModuleList.get(position).getLetter(),"a");
        DataContainer.newBohiricPronouncation = new bohiricLettersSqlHelper(context).getBohiricPronouncation(DataContainer.bohiricLetterModuleList.get(position).getLetter(),"n");
        DataContainer.lateBohiricPronouncation = new bohiricLettersSqlHelper(context).getBohiricPronouncation(DataContainer.bohiricLetterModuleList.get(position).getLetter(),"l");
        setLayoutContents(context,findViewById(R.id.general),"Ⲡⲓϫⲓⲛⲧⲁⲟⲩⲟ ⲛ̀ⲣⲉⲙⲉⲙϩⲓⲧ","النطق البحيري",DataContainer.generalBohiricPronouncation);
        setLayoutContents(context,findViewById(R.id.acadimic_bohiric),"Ⲡⲓϫⲓⲛⲧⲁⲟⲩⲟ ⲛ̀ⲣⲉⲙⲉⲙϩⲓⲧ ⲛ̀ⲁⲡⲁⲥ","النطق البحيري الاكاديمي (القديم)",DataContainer.acadimicBohiricPronouncation);
        setLayoutContents(context,findViewById(R.id.new_bohiric),"Ⲡⲓϫⲓⲛⲧⲁⲟⲩⲟ ⲛ̀ⲣⲉⲙⲉⲙϩⲓⲧ ⲙ̀ⲃⲉⲣⲓ","النطق البحيري الحديث (الكنسي)",DataContainer.newBohiricPronouncation);
        setLayoutContents(context,findViewById(R.id.late_bohiric),"Ⲡⲓϫⲓⲛⲧⲁⲟⲩⲟ ⲛ̀ⲣⲉⲙⲉⲙϩⲓⲧ ⲙ̀ⲡⲓϧⲁⲉ̀ ","النطق البحيري المتاخر (المتوارث)",DataContainer.lateBohiricPronouncation);

        /*scrollView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                // Ready, move up
                scrollView.fullScroll(View.FOCUS_UP);
            }
        });*/
    }

    private void setLayoutContents(Context context, final View view, String copticTitle, String title, List<PronounceModule> list){
        if(list.size()==0) {
            view.setVisibility(View.GONE);
            return;
        }
        final ScrollView scrollView=findViewById(R.id.scrollview);
        TextView copticTitleText= view.findViewById(R.id.coptic_title);
        TextView translatedTitleText= view.findViewById(R.id.translated_title);
        copticTitleText.setText(copticTitle);
        translatedTitleText.setText(title);

        ProunouncationAdapter adapter= new ProunouncationAdapter(context,list);
        ListView listView = view.findViewById(R.id.general_list);
        listView.setAdapter(adapter);
        listView.setEnabled(false);

        final LinearLayout expand_button= view.findViewById(R.id.button_layout);
        final ExpandableLayout expandableLayout = view.findViewById(R.id.expandable_layout);
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
                    //scrollView.fullScroll(ScrollView.FOCUS_UP);
                } else {
                    expandableLayout.expand();
                    scrollView.smoothScrollTo(0,scrollView.getScrollY()+120);
                }
            }
        });
    }

}
