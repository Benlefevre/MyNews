package com.benlefevre.mynews.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Utils {

    public static String convertDateForDisplay(String initialDate) {
        SimpleDateFormat initialFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        SimpleDateFormat convertFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        Date initial = null;
        if (initialDate.matches("\\d{2}/\\d{2}/\\d{4}$"))
            return initialDate;
        if (initialDate.matches("\\d/\\d/\\d{4}$") || initialDate.matches("\\d/\\d{2}/\\d{4}$") || initialDate.matches("\\d{2}/\\d/\\d{4}$")){
            try {
                initial = convertFormat.parse(initialDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }initialDate = convertFormat.format(initial);
            return initialDate;
        }
        if (!initialDate.matches("\\d{4}-\\d{2}-\\d{2}\\w\\d{2}:\\d{2}:\\d{2}-\\d{2}:\\d{2}$") && !initialDate.matches("\\d{4}-\\d{2}-\\d{2}$"))
            return "This is not a correct date";
        else {
            try {
                initial = initialFormat.parse(initialDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            initialDate = convertFormat.format(initial);
            return initialDate;
        }
    }

    public static String convertTitleForDisplay(String initialTitle) {
        if (initialTitle.length() > 60) {
            String formattedTitle = initialTitle.substring(0, 60).trim();
            formattedTitle += "...";
            return formattedTitle;
        } else
            return initialTitle;
    }
}
