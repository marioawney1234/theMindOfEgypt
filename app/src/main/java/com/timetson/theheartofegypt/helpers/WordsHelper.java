package com.timetson.theheartofegypt.helpers;

import android.content.Context;
import android.util.Log;

import com.timetson.theheartofegypt.modules.copticCalender;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class WordsHelper {

    private final Context mContext;

    public WordsHelper(Context context) {
        this.mContext = context;
    }

    public String[] getWord() {
        int today = (int) (copticCalender.dayOfYear() - 1) % 256;
        String[] list = {"", "", ""};
        String jsonLetters = loadAssetTextAsString(mContext, "databases/ϩⲁⲛcⲁϫⲓ.json");
        try {
            JSONObject jsonObj = new JSONObject(jsonLetters);
            JSONArray Words = jsonObj.getJSONArray("ⲛⲓⲥⲁϫⲓ");
            JSONObject j = Words.getJSONObject(today);
            list[0] = j.getString("coptic");
            list[1] = j.getString("arabic");
            list[2] = j.getString("english");
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
