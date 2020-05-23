package com.remmarees.themindofegypt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
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
        final TextView letter_comment= findViewById(R.id.letter_comment);
        final String[] types= getResources().getStringArray(R.array.letters_type);


        Capital_letter.setText(DataContainer.bohiricLetterModuleList.get(position).getCapital());
        Small_letter.setText(DataContainer.bohiricLetterModuleList.get(position).getLetter());
        letter_type.setText(types[DataContainer.bohiricLetterModuleList.get(position).getType()]);
        letter_name.setText(DataContainer.bohiricLetterModuleList.get(position).getName());
        letter_comment.setText(DataContainer.bohiricLetterModuleList.get(position).getComment());

        DataContainer.generalBohiricPronouncation = new bohiricLettersSqlHelper(context).getBohiricPronouncation(DataContainer.bohiricLetterModuleList.get(position).getLetter(),"g");
        DataContainer.acadimicBohiricPronouncation = new bohiricLettersSqlHelper(context).getBohiricPronouncation(DataContainer.bohiricLetterModuleList.get(position).getLetter(),"a");
        DataContainer.newBohiricPronouncation = new bohiricLettersSqlHelper(context).getBohiricPronouncation(DataContainer.bohiricLetterModuleList.get(position).getLetter(),"n");
        DataContainer.lateBohiricPronouncation = new bohiricLettersSqlHelper(context).getBohiricPronouncation(DataContainer.bohiricLetterModuleList.get(position).getLetter(),"l");
        setLayoutContents(context,findViewById(R.id.general),"Ⲡⲓϫⲓⲛⲧⲁⲟⲩⲟ ⲛ̀ⲣⲉⲙⲉⲙϩⲓⲧ","النطق البحيري",DataContainer.generalBohiricPronouncation);
        setLayoutContents(context,findViewById(R.id.acadimic_bohiric),"Ⲡⲓϫⲓⲛⲧⲁⲟⲩⲟ ⲛ̀ⲣⲉⲙⲉⲙϩⲓⲧ ⲛ̀ⲁⲡⲁⲥ","النطق البحيري الاكاديمي (القديم)",DataContainer.acadimicBohiricPronouncation);
        setLayoutContents(context,findViewById(R.id.new_bohiric),"Ⲡⲓϫⲓⲛⲧⲁⲟⲩⲟ ⲛ̀ⲣⲉⲙⲉⲙϩⲓⲧ ⲙ̀ⲃⲉⲣⲓ","النطق البحيري الحديث (الكنسي)",DataContainer.newBohiricPronouncation);
        setLayoutContents(context,findViewById(R.id.late_bohiric),"Ⲡⲓϫⲓⲛⲧⲁⲟⲩⲟ ⲛ̀ⲣⲉⲙⲉⲙϩⲓⲧ ⲙ̀ⲡⲓϧⲁⲉ̀ ","النطق البحيري المتاخر (المتوارث)",DataContainer.lateBohiricPronouncation);

        if(!DataContainer.bohiricLetterModuleList.get(position).getComment().equals("-")){
            findViewById(R.id.comment_layout).setVisibility(View.VISIBLE);
            findViewById(R.id.comment_viewline).setVisibility(View.VISIBLE);
            findViewById(R.id.expandable_layout).setVisibility(View.VISIBLE);
        }
        expand_coolapse((ExpandableLayout) findViewById(R.id.expandable_comment),findViewById(R.id.comment_layout),(ImageView) findViewById(R.id.expand_image1));
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
        TextView copticTitleText= view.findViewById(R.id.coptic_title);
        TextView translatedTitleText= view.findViewById(R.id.translated_title);
        copticTitleText.setText(copticTitle);
        translatedTitleText.setText(title);

        ProunouncationAdapter adapter= new ProunouncationAdapter(context,list);
        ListView listView = view.findViewById(R.id.general_list);
        listView.setAdapter(adapter);
        listView.setEnabled(false);
        expand_coolapse((ExpandableLayout) view.findViewById(R.id.expandable_layout),view.findViewById(R.id.button_layout),(ImageView) view.findViewById(R.id.expand_image));

    }

    private void expand_coolapse(final ExpandableLayout expandableLayout, final View expand_button, final ImageView image){
        final ScrollView scrollView=findViewById(R.id.scrollview);

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
                    scrollView.smoothScrollTo(0,scrollView.getScrollY()+120);
                }
            }
        });
    }
}
