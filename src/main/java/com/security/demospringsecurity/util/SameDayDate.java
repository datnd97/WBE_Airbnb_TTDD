package com.security.demospringsecurity.util;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

public class SameDayDate {
    public static boolean sameDay(String d1,String d2) throws ParseException {
        Date date1 = StringToDate.parsingDate(d1);
        Date date2 = StringToDate.parsingDate(d2);
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(date1);
        cal2.setTime(date2);
        Boolean sameDay = true;
       if(cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR) &&
               cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)) {
           return true;
       }
       return false;
    }
}
