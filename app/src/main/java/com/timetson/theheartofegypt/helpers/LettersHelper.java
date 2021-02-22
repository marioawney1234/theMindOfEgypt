package com.timetson.theheartofegypt.helpers;

import android.content.Context;
import android.util.Log;

import com.timetson.theheartofegypt.modules.LetterModule;
import com.timetson.theheartofegypt.modules.PronounceModule;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class LettersHelper {

    private static final String ASSETS_PATH = "databases";
    private final Context mContext;


    public LettersHelper(Context context) {
        this.mContext = context;
    }

    public List<LetterModule> getBohiricLetters() {
        LetterModule module;
        List<LetterModule> list = new ArrayList<>();
        ///////////load Json ///////////
        String jsonLetters = loadAssetTextAsString(mContext, "databases/ⲣⲉⲙⲉⲙϩⲓⲧ_ⲁⲗⲫⲁⲃⲏⲧⲟⲛ.json");

        try {
            JSONObject jsonObj = new JSONObject(jsonLetters);

            // Getting JSON Array node
            JSONArray Letters = jsonObj.getJSONArray("letters");
            for (int i = 0; i < Letters.length(); i++) {
                JSONObject j = Letters.getJSONObject(i);
                module = new LetterModule(j.getString("letter"), j.getString("capital"), j.getString("name"), j.getInt("type"), "-", "-");
                list.add(module);
            }
        } catch (final JSONException e) {
            Log.e("TAG", "Json parsing error: " + e.getMessage());

        }
        return list;
    }

    public List<PronounceModule> getBohiricPronouncation(String letter, String type) {
        PronounceModule module;
        List<PronounceModule> list = new ArrayList<>();

        String jsonLetters = loadAssetTextAsString(mContext, "databases/ⲣⲉⲙⲉⲙϩⲓⲧ_ϫⲓⲛⲧⲟⲁⲟⲩⲟ.json");
        try {
            JSONObject jsonObj = new JSONObject(jsonLetters);

            // Getting JSON Array node
            JSONArray Letters = jsonObj.getJSONArray(letter + "_" + type);
            for (int i = 0; i < Letters.length(); i++) {
                JSONObject j = Letters.getJSONObject(i);
                module = new PronounceModule(0, letter, j.getString("IPA"), "g", j.getString("arabic_description"), j.getString("english_description"), j.getString("letter_name"), j.getString("Audio"));
                list.add(module);
            }
        } catch (final JSONException e) {
            Log.e("TAG", "Json parsing error: " + e.getMessage());

        }
        return list;
    }

    ///////////////////////////////////////////////////////////////////////

    public List<LetterModule> getSahidicLetters() {
        LetterModule module;
        List<LetterModule> list = new ArrayList<>();
        ///////////load Json ///////////
        String jsonLetters = loadAssetTextAsString(mContext, "databases/ⲣⲉⲙⲙⲁⲣⲏⲥ_ⲁⲗⲫⲁⲃⲏⲧⲟⲛ.json");

        try {
            JSONObject jsonObj = new JSONObject(jsonLetters);

            // Getting JSON Array node
            JSONArray Letters = jsonObj.getJSONArray("letters");
            for (int i = 0; i < Letters.length(); i++) {
                JSONObject j = Letters.getJSONObject(i);
                module = new LetterModule(j.getString("letter"), j.getString("capital"), j.getString("name"), 1, "-", "-");
                list.add(module);
            }
        } catch (final JSONException e) {
            Log.e("TAG", "Json parsing error: " + e.getMessage());

        }
        return list;
    }

    public List<PronounceModule> getSahidicPronouncation(String letter) {
        PronounceModule module;
        List<PronounceModule> list = new ArrayList<>();

        String jsonLetters = loadAssetTextAsString(mContext, "databases/ⲣⲉⲙⲙⲁⲣⲏⲥ_ϫⲓⲛⲧⲟⲁⲟⲩⲟ.json");
        try {
            JSONObject jsonObj = new JSONObject(jsonLetters);

            // Getting JSON Array node
            JSONArray Letters = jsonObj.getJSONArray(letter);
            for (int i = 0; i < Letters.length(); i++) {
                JSONObject j = Letters.getJSONObject(i);
                module = new PronounceModule(0, letter, j.getString("IPA"), "g", j.getString("arabic_description"), j.getString("english_description"), j.getString("letter_name"), j.getString("Audio"));
                list.add(module);
            }
        } catch (final JSONException e) {
            Log.e("TAG", "Json parsing error: " + e.getMessage());

        }
        return list;
    }

    //load file from Assets to String
    private String loadAssetTextAsString(Context context, String name) {
        BufferedReader in = null;
        try {
            StringBuilder buf = new StringBuilder();
            InputStream is = context.getAssets().open(name);
            in = new BufferedReader(new InputStreamReader(is));

            String str;
            boolean isFirst = true;
            while ((str = in.readLine()) != null) {
                if (isFirst)
                    isFirst = false;
                else
                    buf.append('\n');
                buf.append(str);
            }
            return buf.toString();
        } catch (IOException e) {
            Log.e("TAG", "Error opening asset " + name);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    Log.e("TAG", "Error closing asset " + name);
                }
            }
        }

        return null;
    }
}
