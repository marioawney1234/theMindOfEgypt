package com.timetson.theheartofegypt.helpers;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;

import androidx.annotation.NonNull;

import java.util.Locale;

public class localeHelper {
    @NonNull
    public static Resources getLocalizedResources(Context context, String desiredLanguage) {
        Locale locale = new Locale(desiredLanguage);
        Configuration conf = context.getResources().getConfiguration();
        conf = new Configuration(conf);
        conf.setLocale(locale);
        Context localizedContext = context.createConfigurationContext(conf);
        return localizedContext.getResources();
    }
}
