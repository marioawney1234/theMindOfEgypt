package com.remmarees.themindofegypt.sqlHelper;

public class PronounceModule {
    int id;
    String letter;
    String IPA;
    String type;
    String arabicDescription;
    String englishDescription;
    String IPA_name;
    String audio;

    public PronounceModule(int id, String letter, String IPA, String type, String arabicDescription, String englishDescription, String IPA_name ,String audio) {
        this.id = id;
        this.letter = letter;
        this.IPA = IPA;
        this.audio= audio;
        this.type = type;
        this.arabicDescription = arabicDescription;
        this.englishDescription = englishDescription;
        this.IPA_name = IPA_name;
    }

    public int getId() {
        return id;
    }

    public String getLetter() {
        return letter;
    }

    public String getIPA() {
        return IPA;
    }

    public String getAudio() {
        return audio;
    }

    public String getArabicDescription() {
        return arabicDescription;
    }

    public String getEnglishDescription() {
        return englishDescription;
    }

    public String getIPA_name() {
        return IPA_name;
    }

    public void setId(int id) {
        this.id = id;
    }
}
