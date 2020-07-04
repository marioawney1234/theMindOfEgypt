package com.timetson.theheartofegypt.modules;

import androidx.annotation.NonNull;

public class LetterModule {
    private String letter;
    private String capital;
    private String name;
    private int type;
    private String comment;
    private String englishComment;

    public LetterModule(String letter, String capital, String name, int type, String comment, String englishComment) {
        this.letter = letter;
        this.capital = capital;
        this.name = name;
        this.type = type;
        this.comment = comment;
        this.englishComment = englishComment;
    }

    public String getLetter() {
        return letter;
    }

    public String getCapital() {
        return capital;
    }

    public String getName() {
        return name;
    }

    public int getType() {
        return type;
    }

    public String getComment() {
        return comment;
    }
    public String getEnglishComment(){return englishComment;}

    @NonNull
    @Override
    public String toString() {
        return this.capital + " " + this.letter + "   " + this.name;
    }
}
