package com.app.githubapp.utils;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.widget.Toast;
import com.app.githubapp.R;
import com.app.githubapp.widget.styleabletoast.StyleableToast;
import org.joda.time.DateTime;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Created by Sagar Shimpi on 26/8/18.
 */

public class Utils {

    public static String NO_INTERNET_TITLE = "No Internet Connection";
    public static String UNPROPER_RESPONSE = "Unable to get proper response from server. Please, Try Again.";

    public static void showErrorToast(Context context, String message) {
        StyleableToast styleableToast = new StyleableToast.Builder(context).text(message).textColor(Color.WHITE)
                .duration(Toast.LENGTH_SHORT).cornerRadius(23)
                .backgroundColor(Color.RED).build();
        styleableToast.show();
    }

    public static void showLongToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    public static String getCreatedDateFromPeriod(String period) {
        switch (period) {
            case "action_today":
                return new DateTime().minusDays(1).toString("yyyy-MM-dd");
            case "action_this_week":
                return new DateTime().minusDays(7).toString("yyyy-MM-dd");
            case "action_this_month":
                return new DateTime().minusMonths(1).toString("yyyy-MM-dd");
            case "action_this_year":
                return new DateTime().minusYears(1).toString("yyyy-MM-dd");
            default:
                return "";
        }
    }

    public static String getPeriodNameFromPeriod(String period) {
        switch (period) {
            case "action_today":
                return "today";
            case "action_this_week":
                return "last week";
            case "action_this_month":
                return "last month";
            case "action_this_year":
                return "last year";
            default:
                return "";
        }
    }

    public static String getNewsTimeStr(@NonNull Context context, @NonNull Date date){
        long subTime = System.currentTimeMillis() - date.getTime();
        final double MILLIS_LIMIT = 1000.0f;
        final double SECONDS_LIMIT = 60 * MILLIS_LIMIT;
        final double MINUTES_LIMIT = 60 * SECONDS_LIMIT;
        final double HOURS_LIMIT = 24 * MINUTES_LIMIT;
        final double DAYS_LIMIT = 30 * HOURS_LIMIT;
        if(subTime < MILLIS_LIMIT){
            return context.getString(R.string.just_now);
        } else if(subTime < SECONDS_LIMIT){
            return Math.round(subTime / MILLIS_LIMIT) + " " + context.getString(R.string.seconds_ago);
        } else if(subTime < MINUTES_LIMIT){
            return Math.round(subTime / SECONDS_LIMIT) + " " + context.getString(R.string.minutes_ago);
        } else if(subTime < HOURS_LIMIT){
            return Math.round(subTime / MINUTES_LIMIT) + " " + context.getString(R.string.hours_ago);
        } else if(subTime < DAYS_LIMIT){
            return Math.round(subTime / HOURS_LIMIT) + " " + context.getString(R.string.days_ago);
        } else
            return getDateStr(date);
    }

    public static String getDateStr(@NonNull Date date){
        Locale locale = getLocale("en");
        String regex = DATE_REGEX_MAP.containsKey(locale) ? DATE_REGEX_MAP.get(locale) : "yyyy-MM-dd";
        SimpleDateFormat format = new SimpleDateFormat(regex, locale);
        return format.format(date);
    }

    public static Locale getLocale(String language) {
        Locale locale;
        String[] array = language.split("-");
        if (array.length > 1) {
            String country =  array[1].replaceFirst("r", "");
            locale = new Locale(array[0], country);
        } else {
            locale = new Locale(language);
        }
        return locale;
    }

    private final static Map<Locale, String> DATE_REGEX_MAP = new HashMap<>();
    static {
        DATE_REGEX_MAP.put(Locale.CHINA, "yyyy-MM-dd");
        DATE_REGEX_MAP.put(Locale.TAIWAN, "yyyy-MM-dd");
        DATE_REGEX_MAP.put(Locale.ENGLISH, "MMM d, yyyy");
        DATE_REGEX_MAP.put(Locale.GERMAN, "dd.MM.yyyy");
        DATE_REGEX_MAP.put(Locale.GERMANY, "dd.MM.yyyy");
    }
}
