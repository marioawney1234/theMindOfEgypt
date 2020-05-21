package com.remmarees.themindofegypt;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.remmarees.themindofegypt.sqlHelper.DataContainer;
import com.remmarees.themindofegypt.sqlHelper.PronounceModule;

import java.util.List;

public class ProunouncationAdapter extends BaseAdapter {
    private static LayoutInflater inflater = null;
    private Context mcontext;
    private List<PronounceModule> moduleList;

    ProunouncationAdapter(Context mcontext, List<PronounceModule> moduleList){
        this.moduleList=moduleList;
        this.mcontext=mcontext;
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
        View v = View.inflate(mcontext, R.layout.pronouncation_item, null);
        TextView englishDescription = v.findViewById(R.id.english_description);
        TextView arabicDescription =  v.findViewById(R.id.arabic_description);
        TextView IPA =  v.findViewById(R.id.ipa);
        TextView IPaName =  v.findViewById(R.id.ipa_name);
        ImageButton playButton =v.findViewById(R.id.btn_letter_sound);
        englishDescription.setText(moduleList.get(position).getEnglishDescription());
        arabicDescription.setText(moduleList.get(position).getArabicDescription());
        IPaName.setText(moduleList.get(position).getIPA_name());
        IPA.setText("  /"+moduleList.get(position).getIPA()+"/");
        if(moduleList.get(position).getAudio().contains("-")) playButton.setVisibility(View.GONE);
        playButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                playSound(mcontext,"letters_sounds/"+moduleList.get(position).getAudio()+".mp3");
            }
        });
        return v;
    }

    public void playSound(Context context,String file_name) {
        try {
            DataContainer.audioPlayer.stop();
            DataContainer.audioPlayer.reset();
            AssetFileDescriptor descriptor = context.getAssets().openFd(file_name);
            DataContainer.audioPlayer.setDataSource(descriptor.getFileDescriptor(), descriptor.getStartOffset(), descriptor.getLength());
            descriptor.close();

            DataContainer.audioPlayer.prepare();
            DataContainer.audioPlayer.setVolume(1f, 1f);
            DataContainer.audioPlayer.setLooping(false);
            DataContainer.audioPlayer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
