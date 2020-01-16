package com.security.demospringsecurity.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateParser {
    public static String dateParser(Date dt, String format) {

        String formatDate = "null";

        if (format.equals("yyyy/MM/dd")) {
            //：2016-7-6
            formatDate = DateFormat.getDateInstance().format(dt);
            return formatDate;
        }


//      //Year：2016 month:7 day:6

//
        if (format.equals("yyyy/MM/dd HH:mm:ss")) {
          //：2016-07-06 09:39:58
            DateFormat dFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"); //HH表示24小时制；
            formatDate = dFormat.format(dt);
            return formatDate;
        }



        return formatDate;

    }
}
