package com.vyping.masterlibrary.time;

import android.text.format.DateFormat;

import androidx.annotation.NonNull;

import com.vyping.masterlibrary.Common.MyNumbers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class MyTime extends MyTimeTools {

    public static final String FORMAT_HOUR_01 = "HH:mm", FORMAT_HOUR_02 = "HH:mm:ss", FORMAT_HOUR_03 = "HH:mm a", FORMAT_HOUR_04 = "HH:mm:ss a";

    public static final String FORMAT_DATE_01 = "dd-MM-yy", FORMAT_DATE_02 = "dd-MM-yyyy", FORMAT_DATE_03 = "dd-MMM-yy", FORMAT_DATE_04 = "dd-MMM-yyyy", FORMAT_DATE_05 = "dd-MMMM-yy", FORMAT_DATE_06 = "dd-MMMM-yyyy";
    public static final String FORMAT_DATE_07 = "dd/MM/yy", FORMAT_DATE_08 = "dd/MM/yyyy", FORMAT_DATE_09 = "dd/MMM/yy", FORMAT_DATE_10 = "dd/MMM/yyyy", FORMAT_DATE_11 = "dd/MMMM/yy", FORMAT_DATE_12 = "dd/MMMM/yyyy";
    public static final String FORMAT_DATE_13 = "EEE dd 'de' MMM", FORMAT_DATE_14 = "EEE dd 'de' MMMM", FORMAT_DATE_15 = "EEEE dd 'de' MMM", FORMAT_DATE_16 = "EEEE dd 'de' MMMM";
    public static final String FORMAT_DATE_17 = "MMM dd 'del' yy", FORMAT_DATE_18 = "MMMM dd 'del' yy", FORMAT_DATE_19 = "MMM dd 'del' yyyy", FORMAT_DATE_20 = "MMMM dd 'del' yyyy";
    public static final String FORMAT_DATE_21 = "dd 'de' MMM 'del' yy", FORMAT_DATE_22 = "dd 'de' MMMM 'del' yy", FORMAT_DATE_23 = "dd 'de' MMM 'del' yyyy", FORMAT_DATE_24 = "dd 'de' MMMM 'del' yyyy";

    public static final String FORMAT_TIME_01 = "dd-MM-yy HH:mm", FORMAT_TIME_02 = "dd-MM-yyyy HH:mm", FORMAT_TIME_03 = "dd-MMM-yy HH:mm", FORMAT_TIME_04 = "dd-MMM-yyyy HH:mm", FORMAT_TIME_05 = "dd-MMMM-yy HH:mm", FORMAT_TIME_06 = "dd-MMMM-yyyy HH:mm";
    public static final String FORMAT_TIME_07 = "dd 'de' MMM 'a las' HH:mm", FORMAT_TIME_08 = "dd 'de' MMMM 'a las' HH:mm", FORMAT_TIME_09 = "dd 'de' MMM 'del' yyyy 'a las' HH:mm", FORMAT_TIME_10 = "dd 'de' MMMM 'del' yyyy 'a las' HH:mm";
    public static final String FORMAT_TIME_11 = "MMM dd 'del' yyyy 'a las' HH:mm", FORMAT_TIME_12 = "MMMM dd 'del' yyyy 'a las' HH:mm";

    /*----- Calendar - Methods -----*/

    public Calendar getCalendar() {

        return Calendar.getInstance(Locale.getDefault());
    }

    public Calendar getCalendar(long timeMillis) {

        Calendar calendar = Calendar.getInstance(Locale.getDefault());
        calendar.setTimeInMillis(timeMillis);

        return calendar;
    }

    public Calendar getCalendar(String timeMillis) {

        boolean isNumber = new MyNumbers().isNumber(timeMillis);

        if (isNumber) {

            long TimeMillis = new MyNumbers().objectToLong(timeMillis);

            return getCalendar(TimeMillis);

        } else {

            return getCalendar();
        }
    }

    public Calendar getCalendar(int day, int month, int year) {

        Calendar calendar = Calendar.getInstance(Locale.getDefault());
        calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.YEAR, year);

        return calendar;
    }

    public Calendar getCalendar(String day, String month, String year) {

        int Day = Integer.parseInt(day);
        int Month = Integer.parseInt(month);
        int Year = Integer.parseInt(year);

        return getCalendar(Day, Month, Year);
    }

    public Calendar getCalendar(String date, String format) {

        Calendar calendar = Calendar.getInstance();

        try {

            SimpleDateFormat dateFormat = new SimpleDateFormat(format, Locale.getDefault());
            Date Date = Objects.requireNonNull(dateFormat.parse(date));
            calendar.setTime(Date);

            return dateFormat.getCalendar();

        } catch (ParseException e) {

            e.printStackTrace();

            return calendar;
        }
    }


    /*----- Time - Methods -----*/
    public String getTime(@NonNull String Format) {

        Calendar calendar = getCalendar();
        long timeMillis = calendar.getTimeInMillis();

        return getTime(Format, timeMillis);
    }

    public String getTime(String Format, Calendar calendar) {

        return DateFormat.format(Format, calendar).toString();
    }

    public String getTime(String Format, long timeMillis) {

        Calendar calendar = getCalendar(timeMillis);

        return DateFormat.format(Format, calendar).toString();
    }

    public String getTime(String Format, String timeMillis) {

        long TimeMillis = Long.parseLong(timeMillis);

        return getTime(Format, TimeMillis);
    }

    public String getTime(String Format, Object Time) {

        long timeMillis = new MyNumbers().objectToLong(Time);

        return getTime(Format, timeMillis);
    }


    /*----- Millis - Methods -----*/

    public long getMillis() {

        Calendar calendar = getCalendar();

        return getMillis(calendar);
    }

    public long getMillis(@NonNull Calendar calendar) {

        return calendar.getTimeInMillis();
    }

    public long getMillis(int day, int month, int year) {

        Calendar calendar = getCalendar(day, month, year);

        return getMillis(calendar);
    }

    public long getMillis(String day, String month, String year) {

        Calendar calendar = getCalendar(day, month, year);

        return getMillis(calendar);
    }

    public long getMillis(String date, String format) {

        Calendar calendar = getCalendar(date, format);

        return getMillis(calendar);
    }

    public String getMillisOnString() {

        Calendar calendar = getCalendar();

        return getMillisOnString(calendar);
    }

    public String getMillisOnString(@NonNull Calendar calendar) {

        long millis = calendar.getTimeInMillis();

        return String.valueOf(millis);
    }

    public String getMillisOnString(int day, int month, int year) {

        Calendar calendar = getCalendar(day, month, year);

        return getMillisOnString(calendar);
    }

    public String getMillisOnString(String day, String month, String year) {

        Calendar calendar = getCalendar(day, month, year);

        return getMillisOnString(calendar);
    }

    public String getMillisOnString(String date, String format) {

        Calendar calendar = getCalendar(date, format);

        return getMillisOnString(calendar);
    }


    /*----- Max Days of Month - Methods -----*/

    public int getMaxDaysOfMonth() {

        Calendar calendar = getCalendar();

        return getMaxDaysOfMonth(calendar);
    }

    public int getMaxDaysOfMonth(@NonNull Calendar calendar) {

        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    public int getMaxDaysOfMonth(long instant) {

        Calendar calendar = getCalendar(instant);

        return getMaxDaysOfMonth(calendar);
    }

    public int getMaxDaysOfMonth(String instant) {

        Calendar calendar = getCalendar(instant);

        return getMaxDaysOfMonth(calendar);
    }

    public int getMaxDaysOfMonth(int day, int month, int year) {

        Calendar calendar = getCalendar(day, month, year);

        return getMaxDaysOfMonth(calendar);
    }

    public int getMaxDaysOfMonth(String day, String month, String year) {

        Calendar calendar = getCalendar(day, month, year);

        return getMaxDaysOfMonth(calendar);
    }

    public int getMaxDaysOfMonth(String date, String format) {

        Calendar calendar = getCalendar(date, format);

        return getMaxDaysOfMonth(calendar);
    }

    public String getMaxDaysOfMonthOnString() {

        Calendar calendar = getCalendar();

        return getMaxDaysOfMonthOnString(calendar);
    }

    public String getMaxDaysOfMonthOnString(@NonNull Calendar calendar) {

        int maxDayOfMOnth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        return String.valueOf(maxDayOfMOnth);
    }

    public String getMaxDaysOfMonthOnString(long instant) {

        Calendar calendar = getCalendar(instant);

        return getMaxDaysOfMonthOnString(calendar);
    }

    public String getMaxDaysOfMonthOnString(String instant) {

        Calendar calendar = getCalendar(instant);

        return getMaxDaysOfMonthOnString(calendar);
    }

    public String getMaxDaysOfMonthOnString(int day, int month, int year) {

        Calendar calendar = getCalendar(day, month, year);

        return getMaxDaysOfMonthOnString(calendar);
    }

    public String getMaxDaysOfMonthOnString(String day, String month, String year) {

        Calendar calendar = getCalendar(day, month, year);

        return getMaxDaysOfMonthOnString(calendar);
    }

    public String getMaxDaysOfMonthOnString(String date, String format) {

        Calendar calendar = getCalendar(date, format);

        return getMaxDaysOfMonthOnString(calendar);
    }


    /*----- Day Name - Methods -----*/

    public String getDayName() {

        Calendar calendar = getCalendar();

        return getDayName(calendar);
    }

    public String getDayName(@NonNull Calendar calendar) {

        return calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault());
    }

    public String getDayName(long instant) {

        Calendar calendar = getCalendar(instant);

        return getDayName(calendar);
    }

    public String getDayName(String instant) {

        Calendar calendar = getCalendar(instant);

        return getDayName(calendar);
    }

    public String getDayName(int day, int month, int year) {

        Calendar calendar = getCalendar(day, month, year);

        return getDayName(calendar);
    }

    public String getDayName(String day, String month, String year) {

        Calendar calendar = getCalendar(day, month, year);

        return getDayName(calendar);
    }

    public String getDayName(String date, String format) {

        Calendar calendar = getCalendar(date, format);

        return getDayName(calendar);
    }


    /*----- Day of Week - Methods -----*/

    public int getDayOfWeek() {

        Calendar calendar = getCalendar();

        return getDayOfWeek(calendar);
    }

    public int getDayOfWeek(@NonNull Calendar calendar) {

        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    public int getDayOfWeek(long instant) {

        Calendar calendar = getCalendar(instant);

        return getDayOfWeek(calendar);
    }

    public int getDayOfWeek(String instant) {

        Calendar calendar = getCalendar(instant);

        return getDayOfWeek(calendar);
    }

    public int getDayOfWeek(int day, int month, int year) {

        Calendar calendar = getCalendar(day, month, year);

        return getDayOfWeek(calendar);
    }

    public int getDayOfWeek(String day, String month, String year) {

        Calendar calendar = getCalendar(day, month, year);

        return getDayOfWeek(calendar);
    }

    public int getDayOfWeek(String date, String format) {

        Calendar calendar = getCalendar(date, format);

        return getDayOfWeek(calendar);
    }

    public String getDayOfWeekOnString() {

        Calendar calendar = getCalendar();

        return getDayOfWeekOnString(calendar);
    }

    public String getDayOfWeekOnString(@NonNull Calendar calendar) {

        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

        return completeDigitsDate(dayOfWeek);
    }

    public String getDayOfWeekOnString(long instant) {

        Calendar calendar = getCalendar(instant);

        return getDayOfWeekOnString(calendar);
    }

    public String getDayOfWeekOnString(String instant) {

        Calendar calendar = getCalendar(instant);

        return getDayOfWeekOnString(calendar);
    }

    public String getDayOfWeekOnString(int day, int month, int year) {

        Calendar calendar = getCalendar(day, month, year);

        return getDayOfWeekOnString(calendar);
    }

    public String getDayOfWeekOnString(String day, String month, String year) {

        Calendar calendar = getCalendar(day, month, year);

        return getDayOfWeekOnString(calendar);
    }

    public String getDayOfWeekOnString(String date, String format) {

        Calendar calendar = getCalendar(date, format);

        return getDayOfWeekOnString(calendar);
    }


    /*----- Day of Month - Methods -----*/

    public int getDayOfMonth() {

        Calendar calendar = getCalendar();

        return getDayOfMonth(calendar);
    }

    public int getDayOfMonth(@NonNull Calendar calendar) {

        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    public int getDayOfMonth(long instant) {

        Calendar calendar = getCalendar(instant);

        return getDayOfMonth(calendar);
    }

    public int getDayOfMonth(String instant) {

        Calendar calendar = getCalendar(instant);

        return getDayOfMonth(calendar);
    }

    public int getDayOfMonth(int day, int month, int year) {

        Calendar calendar = getCalendar(day, month, year);

        return getDayOfMonth(calendar);
    }

    public int getDayOfMonth(String day, String month, String year) {

        Calendar calendar = getCalendar(day, month, year);

        return getDayOfMonth(calendar);
    }

    public int getDayOfMonth(String date, String format) {

        Calendar calendar = getCalendar(date, format);

        return getDayOfMonth(calendar);
    }

    public String getDayOfMonthOnString() {

        Calendar calendar = getCalendar();

        return getDayOfMonthOnString(calendar);
    }

    public String getDayOfMonthOnString(@NonNull Calendar calendar) {

        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        return completeDigitsDate(dayOfMonth);
    }

    public String getDayOfMonthOnString(long instant) {

        Calendar calendar = getCalendar(instant);

        return getDayOfMonthOnString(calendar);
    }

    public String getDayOfMonthOnString(String instant) {

        Calendar calendar = getCalendar(instant);

        return getDayOfMonthOnString(calendar);
    }

    public String getDayOfMonthOnString(int day, int month, int year) {

        Calendar calendar = getCalendar(day, month, year);

        return getDayOfMonthOnString(calendar);
    }

    public String getDayOfMonthOnString(String day, String month, String year) {

        Calendar calendar = getCalendar(day, month, year);

        return getDayOfMonthOnString(calendar);
    }

    public String getDayOfMonthOnString(String date, String format) {

        Calendar calendar = getCalendar(date, format);

        return getDayOfMonthOnString(calendar);
    }


    /*----- Month Name - Methods -----*/

    public String getMonthName() {

        Calendar calendar = getCalendar();

        return getMonthName(calendar);
    }

    public String getMonthName(@NonNull Calendar calendar) {

        return calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
    }

    public String getMonthName(long instant) {

        Calendar calendar = getCalendar(instant);

        return getMonthName(calendar);
    }

    public String getMonthName(String instant) {

        Calendar calendar = getCalendar(instant);

        return getMonthName(calendar);
    }

    public String getMonthName(int day, int month, int year) {

        Calendar calendar = getCalendar(day, month, year);

        return getMonthName(calendar);
    }

    public String getMonthName(String day, String month, String year) {

        Calendar calendar = getCalendar(day, month, year);

        return getMonthName(calendar);
    }

    public String getMonthName(String date, String format) {

        Calendar calendar = getCalendar(date, format);

        return getMonthName(calendar);
    }


    /*----- Month - Methods -----*/

    public int getMonth() {

        Calendar calendar = getCalendar();

        return getMonth(calendar);
    }

    public int getMonth(@NonNull Calendar calendar) {

        return calendar.get(Calendar.MONTH) + 1;
    }

    public int getMonth(long instant) {

        Calendar calendar = getCalendar(instant);

        return getMonth(calendar);
    }

    public int getMonth(String instant) {

        Calendar calendar = getCalendar(instant);

        return getMonth(calendar);
    }

    public int getMonth(int day, int month, int year) {

        Calendar calendar = getCalendar(day, month, year);

        return getMonth(calendar);
    }

    public int getMonth(String day, String month, String year) {

        Calendar calendar = getCalendar(day, month, year);

        return getMonth(calendar);
    }

    public int getMonth(String date, String format) {

        Calendar calendar = getCalendar(date, format);

        return getMonth(calendar);
    }

    public String getMonthOnString() {

        Calendar calendar = getCalendar();

        return getMonthOnString(calendar);
    }

    public String getMonthOnString(@NonNull final Calendar calendar2) {

        int month = getMonth(calendar2);

        return completeDigitsDate(month);
    }

    public String getMonthOnString(long instant) {

        Calendar calendar = getCalendar(instant);

        return getMonthOnString(calendar);
    }

    public String getMonthOnString(String instant) {

        Calendar calendar = getCalendar(instant);

        return getMonthOnString(calendar);
    }

    public String getMonthOnString(int day, int month, int year) {

        Calendar calendar = getCalendar(day, month, year);

        return getMonthOnString(calendar);
    }

    public String getMonthOnString(String day, String month, String year) {

        Calendar calendar = getCalendar(day, month, year);

        return getMonthOnString(calendar);
    }

    public String getMonthOnString(String date, String format) {

        Calendar calendar = getCalendar(date, format);

        return getMonthOnString(calendar);
    }


    /*-----  Year - Methods -----*/

    public int getYear() {

        Calendar calendar = getCalendar();

        return getYear(calendar);
    }

    public int getYear(@NonNull Calendar calendar) {

        return calendar.get(Calendar.YEAR);
    }

    public int getYear(long instant) {

        Calendar calendar = getCalendar(instant);

        return getYear(calendar);
    }

    public int getYear(String instant) {

        Calendar calendar = getCalendar(instant);

        return getYear(calendar);
    }

    public int getYear(int day, int month, int year) {

        Calendar calendar = getCalendar(day, month, year);

        return getYear(calendar);
    }

    public int getYear(String day, String month, String year) {

        Calendar calendar = getCalendar(day, month, year);

        return getYear(calendar);
    }

    public int getYear(String date, String format) {

        Calendar calendar = getCalendar(date, format);

        return getYear(calendar);
    }

    public String getYearOnString() {

        Calendar calendar = getCalendar();

        return getYearOnString(calendar);
    }

    public String getYearOnString(@NonNull Calendar calendar) {

        int year = calendar.get(Calendar.YEAR);

        return String.valueOf(year);
    }

    public String getYearOnString(long instant) {

        Calendar calendar = getCalendar(instant);

        return getYearOnString(calendar);
    }

    public String getYearOnString(String instant) {

        Calendar calendar = getCalendar(instant);

        return getYearOnString(calendar);
    }

    public String getYearOnString(int day, int month, int year) {

        Calendar calendar = getCalendar(day, month, year);

        return getYearOnString(calendar);
    }

    public String getYearOnString(String day, String month, String year) {

        Calendar calendar = getCalendar(day, month, year);

        return getYearOnString(calendar);
    }

    public String getYearOnString(String date, String format) {

        Calendar calendar = getCalendar(date, format);

        return getYearOnString(calendar);
    }

    public String getYearShortOnString() {

        Calendar calendar = getCalendar();

        return getYearShortOnString(calendar);
    }

    public String getYearShortOnString(@NonNull Calendar calendar) {

        int year = calendar.get(Calendar.YEAR);

        return convertTwoDigitsYear(year);
    }

    public String getYearShortOnString(long instant) {

        Calendar calendar = getCalendar(instant);

        return getYearShortOnString(calendar);
    }

    public String getYearShortOnString(String instant) {

        Calendar calendar = getCalendar(instant);

        return getYearShortOnString(calendar);
    }

    public String getYearShortOnString(int day, int month, int year) {

        Calendar calendar = getCalendar(day, month, year);

        return getYearShortOnString(calendar);
    }

    public String getYearShortOnString(String day, String month, String year) {

        Calendar calendar = getCalendar(day, month, year);

        return getYearShortOnString(calendar);
    }

    public String getYearShortOnString(String date, String format) {

        Calendar calendar = getCalendar(date, format);

        return getYearShortOnString(calendar);
    }
}
