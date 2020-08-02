package com.timetson.theheartofegypt.modules;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.List;

public class DataContainer {

    //public data needed in different places in the APP
    public static List<LetterModule> bohiricLetterModuleList = null;
    public static List<LetterModule> sahidicLetterModuleList = null;
    public static List<PronounceModule> generalBohiricPronouncation = null;
    public static List<PronounceModule> acadimicBohiricPronouncation = null;
    public static List<PronounceModule> lateBohiricPronouncation = null;
    public static List<PronounceModule> newBohiricPronouncation = null;
    public static List<PronounceModule> SahidicPronounciation = null;
    public static MediaPlayer audioPlayer = new MediaPlayer();
    public static String LanguageCode = "ar";
    public static String copticCalenderCode = "1";

    //media player to play sound allover the APP
    public static void playSound(Context context, String file_name) {
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

    //AdMob intialization and load
    public static void AdmobLoad(Activity activity, Context context, int view_id) {
        MobileAds.initialize(context, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        AdView mAdView = activity.findViewById(view_id);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

}
