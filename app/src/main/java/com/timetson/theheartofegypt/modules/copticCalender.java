package com.timetson.theheartofegypt.modules;

import java.util.Calendar;

public class copticCalender {
    public static long DAY = 0;
    public static long MONTH = 0;
    public static long YEAR = 0;
    public static String[] months = {"Ⲑⲱⲟⲩⲧ", "Ⲡⲁⲱⲡⲉ", "Ϩⲁⲑⲱⲣ", "Ⲭⲟⲓⲁⲕ", "Ⲧⲱⲃⲓ", "Ⲙ̀ϣⲓⲣ", "Ⲡⲁⲣⲉⲙϩⲁⲧ", "Ⲡⲁⲣⲙⲟⲩⲧⲉ", "Ⲡⲁϣⲟⲛⲥ", "Ⲡⲁⲱⲛⲓ", "Ⲉⲡⲏⲡ", "Ⲙⲉⲥⲟⲩⲣⲏ", "ⲕⲟⲩϫⲓ"};

    public static void days_count() {
        Calendar rightNow = Calendar.getInstance();
        long offset = rightNow.get(Calendar.ZONE_OFFSET) + rightNow.get(Calendar.DST_OFFSET);
        long timesInMillis = rightNow.getTimeInMillis() + offset;
        /////////////////////////
        //long totaldays = (System.currentTimeMillis()/86400000)-5367;
        long totaldays = (timesInMillis / 86400000) - 5367;
        ///////////////////
        long years;
        if (((totaldays % 1461) / 365) > 2)
            years = 6226 + ((totaldays / 1461) * 4) + (((totaldays - 1) % 1461) / 365);
        else
            years = 6226 + ((totaldays / 1461) * 4) + ((totaldays % 1461) / 365);
        ///////////////////
        long days4years = totaldays % 1461;
        if (days4years > 364) {
            days4years -= 365;
            if (days4years > 364) {
                days4years -= 365;
                if (days4years > 365) {
                    days4years -= 366;
                }
            }
        }
        YEAR = years;
        MONTH = (days4years / 30);
        DAY = days4years - (MONTH * 30) + 1;
    }

    public static long dayOfYear() {
        days_count();
        return ((MONTH - 1) * 30) + DAY;
    }

    public static String get_calender() {
        days_count();
        return DAY + " " + months[(int) MONTH] + " " + YEAR;
    }
}
