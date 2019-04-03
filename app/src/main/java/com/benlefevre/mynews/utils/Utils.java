package com.benlefevre.mynews.utils;

import com.google.android.material.checkbox.MaterialCheckBox;

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
        if (initialDate.matches("\\d/\\d/\\d{4}$") || initialDate.matches("\\d/\\d{2}/\\d{4}$") || initialDate.matches("\\d{2}/\\d/\\d{4}$")) {
            try {
                initial = convertFormat.parse(initialDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            initialDate = convertFormat.format(initial);
            return initialDate;
        }
        if (!initialDate.matches("\\d{4}-\\d{2}-\\d{2}\\w\\d{2}:\\d{2}:\\d{2}-\\d{2}:\\d{2}$") && !initialDate.matches("\\d{4}-\\d{2}-\\d{2}$")
                && !initialDate.matches("\\d{4}-\\d{2}-\\d{2}\\w\\d{2}:\\d{2}:\\d{2}\\S\\d{4}"))
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

    public static String configureFilterQueries(MaterialCheckBox cb1, MaterialCheckBox cb2, MaterialCheckBox cb3, MaterialCheckBox cb4, MaterialCheckBox cb5, MaterialCheckBox cb6) {
        String filterQuery = "(";
        if (cb1.isChecked())
            filterQuery += "\"arts\" ";
        if (cb2.isChecked())
            filterQuery += "\"business\" ";
        if (cb3.isChecked())
            filterQuery += "\"politics\" ";
        if (cb4.isChecked())
            filterQuery += "\"sport\" ";
        if (cb5.isChecked())
            filterQuery += "\"entrepreneurs\" ";
        if (cb6.isChecked())
            filterQuery += "\"travel\" ";
        filterQuery += ")";
        return filterQuery;
    }

    public static String convertDateForQuery(String initialDate) {
        SimpleDateFormat initialFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        SimpleDateFormat formattedFormat = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
        if (initialDate.matches("\\d{2}/\\d{2}/\\d{4}$")) {
            try {
                Date date = initialFormat.parse(initialDate);
                initialDate = formattedFormat.format(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return initialDate;
    }
}
