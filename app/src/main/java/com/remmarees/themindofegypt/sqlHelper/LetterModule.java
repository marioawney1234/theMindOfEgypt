package com.remmarees.themindofegypt.sqlHelper;

import androidx.annotation.NonNull;

/**
 * Created by rifo on 10/11/16.
 */
public class LetterModule {
    private String letter;
    private String capital;
    private String name;
    private int type;

    public LetterModule(String letter, String capital, String name, int type) {
        this.letter = letter;
        this.capital = capital;
        this.name = name;
        this.type = type;
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

    @NonNull
    @Override
    public String toString() {
        return this.capital+" "+ this.letter+ "   "+this.name;
    }
}
