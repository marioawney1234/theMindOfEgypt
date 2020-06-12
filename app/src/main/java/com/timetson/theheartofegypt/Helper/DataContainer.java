package com.timetson.theheartofegypt.Helper;

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
}
