package com.timetson.theheartofegypt.widgets;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.timetson.theheartofegypt.R;
import com.timetson.theheartofegypt.Splash;
import com.timetson.theheartofegypt.helpers.WordsHelper;
import com.timetson.theheartofegypt.helpers.localeHelper;
import com.timetson.theheartofegypt.modules.DataContainer;


public class WordOfTheDayAppWidgetProvider extends AppWidgetProvider {
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
            // Get word of the day.
            String[] wordsList = new WordsHelper(context).getWord();

            // Choose auxiliary title language.
            String languageCode = DataContainer.LanguageCode;
            //int titleId = "en".equals(languageCode) ? R.string.string_word_title_en : R.string.string_word_title_ar;
            //String title = context.getResources().getString(titleId);
            String title = localeHelper.getLocalizedResources(context, languageCode).getString(R.string.string_word_title);
            // Setup the new widget view.
            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_word_of_the_day);
            remoteViews.setTextViewText(R.id.word_title, title);

            remoteViews.setTextViewText(R.id.word_coptic, wordsList[0]);
            remoteViews.setTextViewText(R.id.word_arabic, wordsList[1]);
            remoteViews.setTextViewText(R.id.word_english, wordsList[2]);

            // Set intent to launch app when widget is clicked
            Intent intent = new Intent(context, Splash.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
            remoteViews.setOnClickPendingIntent(R.id.widget_root, pendingIntent);

            // Notify the AppWidgetManager to update widget
            appWidgetManager.updateAppWidget(appWidgetId, remoteViews);
        }
    }
}
