package com.timetson.theheartofegypt;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.timetson.theheartofegypt.modules.DataContainer;
import com.timetson.theheartofegypt.modules.PronounceModule;

import java.util.List;

public class ProunouncationAdapter extends BaseAdapter {
    private static LayoutInflater inflater = null;
    private Context mcontext;
    private List<PronounceModule> moduleList;

    ProunouncationAdapter(Context mcontext, List<PronounceModule> moduleList) {
        this.moduleList = moduleList;
        this.mcontext = mcontext;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return moduleList.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return moduleList.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View v = View.inflate(mcontext, R.layout.item_pronouncation, null);
        TextView englishDescription = v.findViewById(R.id.english_description);
        TextView arabicDescription = v.findViewById(R.id.arabic_description);
        TextView IPA = v.findViewById(R.id.ipa);
        TextView IPaName = v.findViewById(R.id.ipa_name);
        ImageButton playButton = v.findViewById(R.id.btn_letter_sound);
        englishDescription.setText(moduleList.get(position).getEnglishDescription());
        arabicDescription.setText(moduleList.get(position).getArabicDescription());
        IPaName.setText(moduleList.get(position).getIPA_name());
        IPA.setText("  /" + moduleList.get(position).getIPA() + "/");
        if (moduleList.get(position).getAudio().contains("-")) playButton.setVisibility(View.GONE);
        playButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                DataContainer.playSound(mcontext, "letters_sounds/" + moduleList.get(position).getAudio() + ".mp3");
            }
        });
        return v;
    }

}
