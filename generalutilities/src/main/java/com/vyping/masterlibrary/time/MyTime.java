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

public class MyTime extends Definitions {

    public Calendar calendar;


    // ----- Setup ----- //

    public MyTime() {

        calendar = Calendar.getInstance(Locale.getDefault());
    }

    public MyTime(Calendar calendar) {

        this.calendar = calendar;
    }

    public MyTime(Object object) {

        long timestamp = new MyNumbers().objectToLong(object);
        calendar = Calendar.getInstance(Locale.getDefault());

        if (timestamp != 0L) {

            calendar.setTimeInMillis(timestamp);
        }
    }

    public MyTime(long timestamp) {

        calendar = Calendar.getInstance(Locale.getDefault());

        if (timestamp != 0L) {

            calendar.setTimeInMillis(timestamp);
        }
    }

    public MyTime(String timestamp) {

        calendar = Calendar.getInstance(Locale.getDefault());

        boolean isNumber = new MyNumbers().isNumber(timestamp);

        if (isNumber) {

            long Timestamp = new MyNumbers().objectToLong(timestamp);

            if (Timestamp != 0L) {

                calendar.setTimeInMillis(Timestamp);
            }
        }
    }

    public MyTime(int day, int month, int year) {

        calendar = Calendar.getInstance(Locale.getDefault());
        calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.YEAR, year);
    }

    public MyTime(String day, String month, String year) {

        int Day = Integer.parseInt(day);
        int Month = Integer.parseInt(month);
        int Year = Integer.parseInt(year);

        calendar = Calendar.getInstance(Locale.getDefault());
        calendar.set(Calendar.DAY_OF_MONTH, Day);
        calendar.set(Calendar.MONTH, Month - 1);
        calendar.set(Calendar.YEAR, Year);
    }

    public MyTime(String date, String format) {

        calendar = Calendar.getInstance(Locale.getDefault());

        try {

            SimpleDateFormat dateFormat = new SimpleDateFormat(format, Locale.getDefault());
            Date Date = Objects.requireNonNull(dateFormat.parse(date));
            calendar.setTime(Date);

        } catch (ParseException ignored) {}
    }


    // ----- Add - Methods ----- //

    public MyTime addValueToField(int field, int value) {

        calendar.add(field, value);

        return this;
    }

    public MyTime addYears(int years) {

        calendar.add(Calendar.YEAR, years);

        return this;
    }

    public MyTime addMonths(int months) {

        calendar.add(Calendar.MONTH, months);

        return this;
    }

    public MyTime addDays(int days) {

        calendar.add(Calendar.DAY_OF_YEAR, days);

        return this;
    }

    public MyTime addHours(int hour) {

        calendar.add(Calendar.HOUR, hour);

        return this;
    }

    public MyTime addMinutes(int minute) {

        calendar.add(Calendar.MINUTE, minute);

        return this;
    }

    public MyTime addSeconds(int seconds) {

        calendar.add(Calendar.SECOND, seconds);

        return this;
    }

    public MyTime addMillis(long millis) {

        int timestamp = new MyNumbers().longToInteger(millis);

        calendar.add(Calendar.MILLISECOND, timestamp);

        return this;
    }


    // ----- Get Basics - Methods ----- //

    public Calendar getCalendar() {

        return calendar;
    }

    public int getYear() {

        return calendar.get(Calendar.YEAR);
    }

    public int getMonth() {

        return calendar.get(Calendar.MONTH) + 1;
    }

    public int getDay() {

        return calendar.get(Calendar.DAY_OF_YEAR);
    }

    public int getHour() {

        return calendar.get(Calendar.HOUR);
    }

    public int getMinute() {

        return calendar.get(Calendar.MINUTE);
    }

    public int getSecond() {

        return calendar.get(Calendar.SECOND);
    }

    public long getTimestamp() {

        return calendar.getTimeInMillis();
    }

    public String getTime(String format) {

        return DateFormat.format(format, calendar).toString();
    }


    // ----- Get Counter - Methods ----- //

    public int getMaxDaysOfMonth() {

        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    public int getDayOfMonth() {

        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    public int getDayOfWeek() {

        return calendar.get(Calendar.DAY_OF_WEEK);
    }


    // ----- Get Names - Methods ----- //

    public String getYearShort() {

        int year = calendar.get(Calendar.YEAR);

        return convertTwoDigitsYear(year);
    }

    public String getMonthName() {

        return calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
    }

    public String getDayName() {

        return calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault());
    }


    // ----- Functions ----- //

    public MyTime midNight() {

        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        return this;
    }

    public Comparator compare(@NonNull Calendar otherCalendar) {

        return new Comparator(this, otherCalendar);
    }

    public Comparator compare(long otherTimestamp) {

        return new Comparator(this, otherTimestamp);
    }


    // ----- Tools ----- //

    public String convertTwoDigitsYear(int Year) {

        String year = String.valueOf(Year);

        if (year.length() == 4) {

            return year.substring(2, 4);

        } else {

            return year;
        }
    }

    public String completeDigitsDate(int date) {

        if (date < 10) {

            return "0" + date;

        } else {

            return String.valueOf(date);
        }
    }

    public String setHoursUnits(int hours) {

        if (hours == 0) {

            return hours + " h";

        } else {

            return hours + " hrs";
        }
    }

    public String setMinutesUnits(int minutes) {

        if (minutes == 0) {

            return minutes + " min";

        } else {

            return minutes + " mins";
        }
    }
}
