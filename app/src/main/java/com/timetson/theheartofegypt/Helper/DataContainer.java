package com.timetson.theheartofegypt.Helper;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;

import java.util.List;

public class DataContainer {
    public static List<LetterModule> bohiricLetterModuleList = null;
    public static List<LetterModule> sahidicLetterModuleList = null;
    public static List<PronounceModule> generalBohiricPronouncation = null;
    public static List<PronounceModule> acadimicBohiricPronouncation = null;
    public static List<PronounceModule> lateBohiricPronouncation = null;
    public static List<PronounceModule> newBohiricPronouncation = null;
    public static List<PronounceModule> SahidicPronouncation = null;
    public static int letter_sound = 0;
    public static MediaPlayer audioPlayer = new MediaPlayer();

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
}
