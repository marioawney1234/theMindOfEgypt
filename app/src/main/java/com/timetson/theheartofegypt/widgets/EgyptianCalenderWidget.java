package com.timetson.theheartofegypt.widgets;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.provider.AlarmClock;
import android.widget.RemoteViews;

import androidx.preference.PreferenceManager;

import com.timetson.theheartofegypt.R;
import com.timetson.theheartofegypt.modules.copticCalender;

import java.util.Calendar;

/**
 * Implementation of App Widget functionality.
 */
public class EgyptianCalenderWidget extends AppWidgetProvider {


    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        // coptic calender
        String egyptianCalenderText = copticCalender.get_calender(PreferenceManager.getDefaultSharedPreferences(context).getString("coptic_calender_code", "1"));
        String[] copticDaysNames = context.getResources().getStringArray(R.array.days_of_week);
        String todayName = copticDaysNames[Calendar.getInstance().get(Calendar.DAY_OF_WEEK)];

        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_egyptian_calender);
        views.setTextViewText(R.id.egyptian_calender_text, egyptianCalenderText);
        views.setTextViewText(R.id.today_name, todayName);

        // open alarms application
        Intent mClockIntent = new Intent(AlarmClock.ACTION_SHOW_ALARMS);
        mClockIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent pendingIntent;
        pendingIntent = PendingIntent.getActivity(context, 0, mClockIntent, 0);
        views.setOnClickPendingIntent(R.id.time_mow, pendingIntent);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
/*
    private static Intent getAlarmPackage(Context context) {
        PackageManager manager = context.getPackageManager();
        Intent intent = new Intent(Intent.ACTION_MAIN)
                .addCategory(Intent.CATEGORY_LAUNCHER);

        try {
            ComponentName c = new ComponentName("com.sec.android.app.clockpackage",
                    "com.android.deskclock.AlarmClock");
            manager.getActivityInfo(c, PackageManager.GET_META_DATA);
            intent.setComponent(c);
        } catch (PackageManager.NameNotFoundException nf) {
            Log.d("TAG", "Caught name not found exception!");
        }

        return intent;
    }
 */
}

