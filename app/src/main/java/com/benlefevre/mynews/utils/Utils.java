package com.benlefevre.mynews.utils;

import com.google.android.material.checkbox.MaterialCheckBox;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Utils {

    /**
     * Convert a given String date in the correct date format to display it.
     * @param initialDate the given date
     * @return the formatted String date according to it's initial format
     */
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
                && !initialDate.matches("\\d{4}-\\d{2}-\\d{2}\\w\\d{2}:\\d{2}:\\d{2}\\S\\d{4}") && !initialDate.matches("\\d{4}-\\d{2}-\\d{2}\\w\\d{2}:\\d{2}:\\d{2}\\w$"))
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

    /**
     * Reduces the title lenght to display it correctly
     * @param initialTitle a given title
     * @return formatted title
     */
    public static String convertTitleForDisplay(String initialTitle) {
        if (initialTitle.length() > 60) {
            String formattedTitle = initialTitle.substring(0, 60).trim();
            formattedTitle += "...";
            return formattedTitle;
        } else
            return initialTitle;
    }

    /**
     * Convert String date to the http request needed format
     * @param initialDate a given date
     * @return formatted String date for request
     */
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

    /**
     * Sets a String to filter queries in http request according to the MaterialCheckbox checked
     * @return the string needed to filter queries
     */
    public static String configureFilterQueries(MaterialCheckBox cb1, MaterialCheckBox cb2, MaterialCheckBox cb3, MaterialCheckBox cb4, MaterialCheckBox cb5, MaterialCheckBox cb6) {
        String filterQuery = "news_desk:(";
        if (cb1.isChecked())
            filterQuery += "\"Arts\" ";
        if (cb2.isChecked())
            filterQuery += "\"Business\" ";
        if (cb3.isChecked())
            filterQuery += "\"Politics\" ";
        if (cb4.isChecked())
            filterQuery += "\"Sports\" ";
        if (cb5.isChecked())
            filterQuery += "\"Entrepreneurs\" ";
        if (cb6.isChecked())
            filterQuery += "\"Travel\" ";
        filterQuery += ")";
        return filterQuery;
    }


    /**
     * Convert a given title to a short title used to save in SharedPreferences
     * @param initialtitle a given title
     * @return a short title ad ID according to the initialTitle.length()
     */
    public static String convertTitleToId(String initialtitle) {
        if (initialtitle.length() >= 20) {
            String substring = initialtitle.substring(0, 20);
            return substring;
        } else
            return initialtitle;
    }
}
