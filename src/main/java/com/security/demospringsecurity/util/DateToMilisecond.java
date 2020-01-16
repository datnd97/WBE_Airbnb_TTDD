package com.security.demospringsecurity.util;

import java.text.ParseException;
import java.util.Date;

public class DateToMilisecond {
    public static int totalDay(String date1,String date2) throws ParseException {
        Date currentDate1 = StringToDate.parsingDate(date1);
        Date currentDate2 = StringToDate.parsingDate(date2);
        Long offset = currentDate2.getTime() - currentDate1.getTime();
        int totalDays = Math.round(offset / 1000 / 60 / 60 / 24);
        return totalDays;
    }
}
