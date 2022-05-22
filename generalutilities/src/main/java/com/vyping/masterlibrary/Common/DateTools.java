package com.vyping.masterlibrary.Common;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Build;
import android.text.format.DateFormat;

import androidx.annotation.IntDef;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
import java.util.TimeZone;

public class DateTools {

    public static final int TIME_BEFORE = 0, TIME_EQUALS = 1, TIME_AFTER = 2;

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({TIME_BEFORE, TIME_EQUALS, TIME_AFTER})
    public @interface TypeClock {
    }

    /**
     * -------- Time to String Section
     */

    public Calendar setCalendar(long timeMillis) {

        Calendar calendar = Calendar.getInstance(Locale.getDefault());
        calendar.setTimeInMillis(timeMillis);

        return calendar;
    }

    public String getTime(@NonNull String Format) {

        Calendar calendar = Calendar.getInstance(Locale.getDefault());

        return DateFormat.format(Format, calendar).toString();
    }

    public String getTime(String Format, Object Time) {

        long longTime = new MyNumbers().objectToLong(Time);

        return getTime(Format, longTime);
    }

    public String getTime(String Format, long Time) {

        Calendar calendar = Calendar.getInstance(Locale.getDefault());
        calendar.setTimeInMillis(Time);

        return DateFormat.format(Format, calendar).toString();
    }

    public long getMillisLong() {

        Locale locale = Locale.getDefault();
        Calendar calendar = Calendar.getInstance(locale);

        return getMillisLong(calendar);
    }

    public long getMillisLong(@NonNull Calendar calendar) {

        return calendar.getTimeInMillis();
    }

    public String getMillisString() {

        Locale locale = Locale.getDefault();
        Calendar calendar = Calendar.getInstance(locale);

        return getMillisString(calendar);
    }

    public String getMillisString(@NonNull Calendar calendar) {

        return String.valueOf(calendar.getTimeInMillis());
    }

    public long getMillisLongFromServer(@NonNull Context context) {

        LocationManager locMan = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return locMan.getLastKnownLocation(LocationManager.NETWORK_PROVIDER).getTime();

        } else {

            return 0L;
        }
    }

    public String getMillisStringFromServer(@NonNull Context context) {

        LocationManager locMan = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return String.valueOf(locMan.getLastKnownLocation(LocationManager.NETWORK_PROVIDER).getTime());

        } else {

            return "0";
        }
    }

    public String InstantToTimeToString(String Instant) {

        Calendar calendar = Calendar.getInstance(Locale.getDefault());
        calendar.setTimeInMillis(Long.parseLong(Instant));
        String instante = DateFormat.format("MMM dd - HH:mm", calendar).toString();

        return new MyStrings().firstLetterUpperCase(instante);
    }

    public String intsToStringDate(int day, int month, int year) {

        String Day = completeDigitsDate(day);
        String Month = completeDigitsDate(month + 1);
        String Year = convertTwoDigitsYear(year);

        return Day + "-" + Month + "-" + Year;
    }

    public int daysOfMonth(long instant) {

        String Instant = String.valueOf(instant);

        Calendar calendar = Calendar.getInstance(Locale.getDefault());
        calendar.setTimeInMillis(Long.parseLong(Instant));

        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    public int dayOfWeekInteger(int day, int month, int year) {

        Calendar calendar = Calendar.getInstance(Locale.getDefault());
        calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.YEAR, year);

        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    public int dayOfWeekInteger(String day, String month, String year) {

        int Day = Integer.parseInt(day);
        int Month = Integer.parseInt(month);
        int Year = Integer.parseInt(year);

        return dayOfWeekInteger(Day, Month, Year);
    }

    public String dayOfWeekString(int day, int month, int year) {

        String[] weekdays = new DateFormatSymbols(Locale.getDefault()).getWeekdays();
        int dayOfWeek = dayOfWeekInteger(day, month, year);

        return weekdays[dayOfWeek];
    }

    public String dayOfWeekString(String day, String month, String year) {

        int Day = Integer.parseInt(day);
        int Month = Integer.parseInt(month);
        int Year = Integer.parseInt(year);

        return dayOfWeekString(Day, Month, Year);
    }


    public int mothOfYearInteger(int day, int month, int year) {

        Calendar calendar = Calendar.getInstance(Locale.getDefault());
        calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.YEAR, year);

        return calendar.get(Calendar.MONTH);
    }

    public int mothOfYearInteger(String day, String month, String year) {

        int Day = Integer.parseInt(day);
        int Month = Integer.parseInt(month);
        int Year = Integer.parseInt(year);

        return mothOfYearInteger(Day, Month, Year);
    }

    public String mothOfYearString(int day, int month, int year) {

        String[] months = new DateFormatSymbols(Locale.getDefault()).getMonths();
        int monthOfYear = mothOfYearInteger(day, month, year);
        String Month = months[monthOfYear];

        return Month.substring(0, 1).toUpperCase() + Month.substring(1);
    }

    public String mothOfYearString(String day, String month, String year) {

        int Day = Integer.parseInt(day);
        int Month = Integer.parseInt(month);
        int Year = Integer.parseInt(year);

        return mothOfYearString(Day, Month, Year);
    }

    public String mothOfYearString(long time) {

        String[] months = new DateFormatSymbols(Locale.getDefault()).getMonths();

        Calendar calendar = Calendar.getInstance(Locale.getDefault());
        calendar.setTimeInMillis(time);
        int monthOfYear = calendar.get(Calendar.MONTH);

        return months[monthOfYear];
    }

    public String mothOfYearString(@NonNull Calendar calendar) {

        int Day = calendar.get(Calendar.DAY_OF_MONTH);
        int Month = calendar.get(Calendar.MONTH) + 1;
        int Year = calendar.get(Calendar.YEAR);
        new LogCat("Month", Month);
        return mothOfYearString(Day, Month, Year);
    }

    public String selectedDateToTag(@NonNull Calendar date) {

        String day = completeDigitsDate(date.get(Calendar.DAY_OF_MONTH));
        String month = completeDigitsDate(date.get(Calendar.MONTH) + 1);
        String year = convertTwoDigitsYear(date.get(Calendar.YEAR));

        return day + "-" + month + "-" + year;
    }

    public String selectedDateToView(Calendar date) {

        String dayOfWeek = new MyStrings().firstLetterUpperCase(dayOfWeek(date));
        String day = completeDigitsDate(date.get(Calendar.DAY_OF_MONTH));
        String textMonth = convertToTextMonth(date.get(Calendar.MONTH));

        return dayOfWeek + " " + day + " de " + textMonth;
    }

    public Calendar getCalendarFromString(String stringDate) {

        try {

            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yy", Locale.getDefault());
            Date date = Objects.requireNonNull(dateFormat.parse(stringDate));
            calendar.setTime(date);

            return dateFormat.getCalendar();

        } catch (ParseException e) {

            e.printStackTrace();

            return null;
        }
    }

    public String getMonthFromString(String stringDate) {

        Calendar calendar = getCalendarFromString(stringDate);

        return completeDigitsDate(calendar.get(Calendar.MONTH) + 1);
    }

    public String getYearFromString(String stringDate) {

        Calendar calendar = getCalendarFromString(stringDate);

        return String.valueOf(calendar.get(Calendar.YEAR));
    }

    public String setStartHourForServer(long time, int delay) {

        Calendar calendar = setCalendar(time);
        calendar.add(Calendar.MINUTE, delay);
        String year = String.valueOf(calendar.get(Calendar.YEAR));
        String day = completeDigitsDate(calendar.get(Calendar.DAY_OF_MONTH));
        String month = completeDigitsDate(calendar.get(Calendar.MONTH) + 1);
        String hour = completeDigitsDate(calendar.get(Calendar.HOUR_OF_DAY));
        String minutes = completeDigitsDate(calendar.get(Calendar.MINUTE));

        return year + "-" + month + "-" + day + "T" + hour + ":" + minutes + ":00-05:00";
    }

    public long setStartStampForServer(long time, int delay) {

        Calendar calendar = setCalendar(time);
        calendar.add(Calendar.MINUTE, delay);

        return calendar.getTimeInMillis();
    }

    public long getStampFromServer(String stringDate) {

        try {

            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd hh:mm:ss Z yyyy", Locale.ENGLISH);
            dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
            Date date = Objects.requireNonNull(dateFormat.parse(stringDate));
            calendar.setTime(date);

            return calendar.getTimeInMillis();

        } catch (ParseException e) {

            e.printStackTrace();

            return 0L;
        }
    }

    public String getTimeFromServer(String stringDate) {

        try {

            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd hh:mm:ss Z yyyy", Locale.ENGLISH);
            dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
            Date date = Objects.requireNonNull(dateFormat.parse(stringDate));
            calendar.setTime(date);

            String hour = completeDigitsDate(calendar.get(Calendar.HOUR_OF_DAY));
            String minutes = completeDigitsDate(calendar.get(Calendar.MINUTE));
            String seconds = completeDigitsDate(calendar.get(Calendar.SECOND));

            return hour + ":" + minutes + ":" + seconds;

        } catch (ParseException e) {

            e.printStackTrace();

            return "";
        }
    }


    /**
     * -------- Comparators Section
     */

    public String timeSince(long startTime) {

        Calendar endCalendar = Calendar.getInstance(Locale.getDefault());
        long endTime = endCalendar.getTimeInMillis();

        return timeSince(startTime, endTime);
    }

    public String timeSince(long startTime, long endTime) {

        Calendar startCalendar = Calendar.getInstance(Locale.getDefault());
        startCalendar.setTimeInMillis(startTime);

        Calendar endCalendar = Calendar.getInstance(Locale.getDefault());
        endCalendar.setTimeInMillis(endTime);

        long millis = endCalendar.getTimeInMillis() - startCalendar.getTimeInMillis();
        int hours = (int) (millis / (1000 * 60 * 60));
        int minutes = (int) ((millis / (1000 * 60)) % 60);
        String Hours = setHoursUnits(hours);
        String Minutes = setMinutesUnits(minutes);

        return Hours + ", " + Minutes;
    }

    public int roundedHoursSince(long startTime, long endTime) {

        Calendar startCalendar = Calendar.getInstance(Locale.getDefault());
        startCalendar.setTimeInMillis(startTime);

        Calendar endCalendar = Calendar.getInstance(Locale.getDefault());
        endCalendar.setTimeInMillis(endTime);

        long millis = endCalendar.getTimeInMillis() - startCalendar.getTimeInMillis();
        int difHours = (int) (millis / (1000 * 60 * 60));
        int difMinutes = (int) ((millis / (1000 * 60)) % 60);

        if (difMinutes == 0) {

            return difHours;

        } else {

            return difHours + 1;
        }
    }

    public boolean isSameMonth(long time, long otherTime) {

        Calendar calendar = setCalendar(time);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);

        Calendar otherCalendar = setCalendar(otherTime);
        int otherMonth = otherCalendar.get(Calendar.MONTH);
        int otherYear = otherCalendar.get(Calendar.YEAR);

        if (month == otherMonth && year == otherYear) {

            return TRUE;

        } else {

            return FALSE;
        }
    }

    public boolean isThisMonth(String month, String year) {

        Locale locale = Locale.getDefault();
        Calendar currentCalendar = Calendar.getInstance(locale);
        int currentMonth = currentCalendar.get(Calendar.MONTH);
        int currentYear = currentCalendar.get(Calendar.YEAR);
        int Month = Integer.parseInt(month) - 1;
        int Year = Integer.parseInt(year);

        if (Month == currentMonth && Year == currentYear) {

            return TRUE;

        } else {

            return FALSE;
        }
    }

    public String previousMonth(long time) {

        Locale locale = Locale.getDefault();
        Calendar calendar = Calendar.getInstance(locale);
        calendar.setTimeInMillis(time);
        int prevMonth = calendar.get(Calendar.MONTH);

        return new MyStrings().formatDigits(prevMonth, 2);
    }

    public boolean comparateWhitToday(Calendar otherCalendar, long otherTime) {

        Calendar calendar = Calendar.getInstance(Locale.getDefault());
        long today = calendar.getTimeInMillis(), other;
        boolean Return = FALSE;

        if (otherCalendar != null && otherTime != 0L) {

            other = otherCalendar.getTimeInMillis();

            if (other >= otherTime) {

                Return = TRUE;

            } else {

                Return = FALSE;
            }

        } else {

            if (otherCalendar != null) {

                other = otherCalendar.getTimeInMillis();

                if (other >= today) {

                    Return = TRUE;
                }

            } else if (otherTime != 0L) {

                if (otherTime >= today) {

                    Return = TRUE;
                }
            }
        }

        return Return;
    }

    public boolean isBehindComparedToToday(@NonNull Calendar calendar) {

        Calendar refCalendar = Calendar.getInstance(Locale.getDefault());
        refCalendar.add(Calendar.DATE, -1);
        refCalendar.set(Calendar.HOUR_OF_DAY, 0);
        refCalendar.set(Calendar.MINUTE, 0);
        refCalendar.set(Calendar.SECOND, 0);

        long refInstant = refCalendar.getTimeInMillis();
        long instant = calendar.getTimeInMillis();

        if (refInstant > instant) {

            return TRUE;

        } else {

            return FALSE;
        }
    }

    public int comparateWithToday(long otherTime) {

        Calendar todayCalendar = Calendar.getInstance(Locale.getDefault());
        long todayTime = todayCalendar.getTimeInMillis();

        if (otherTime > todayTime) {

            return TIME_AFTER;

        } else if (otherTime == todayTime) {

            return TIME_EQUALS;

        } else {

            return TIME_BEFORE;
        }
    }

    public int comparateWithToday(String otherTime) {

        long OtherTime = Long.parseLong(otherTime);

        return comparateWithToday(OtherTime);
    }

    public int comparateWithToday(int day, int month, int year) {

        Calendar calendar = Calendar.getInstance(Locale.getDefault());
        calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.YEAR, year);

        long timeOnMilis = calendar.getTimeInMillis();

        return comparateWithToday(timeOnMilis);
    }

    public int comparateWithToday(String day, String month, String year) {

        int Day = Integer.parseInt(day);
        int Month = Integer.parseInt(month);
        int Year = Integer.parseInt(year);

        return comparateWithToday(Day, Month, Year);
    }

    public boolean isBeforeToday(String stringDate) {

        boolean isBeforeToday = FALSE;

        Calendar currentCalendar = Calendar.getInstance(Locale.getDefault());
        int currentYear = currentCalendar.get(Calendar.YEAR);
        int currentMonth = currentCalendar.get(Calendar.MONTH);
        int currentDay = currentCalendar.get(Calendar.DAY_OF_MONTH);

        Calendar compareCalendar = getCalendarFromString(stringDate);
        int compareYear = compareCalendar.get(Calendar.YEAR);
        int compareMonth = compareCalendar.get(Calendar.MONTH);
        int compareDay = compareCalendar.get(Calendar.DAY_OF_MONTH);

        if (currentYear > compareYear) {

            isBeforeToday = TRUE;

        } else if (currentYear == compareYear) {

            if (currentMonth > compareMonth) {

                isBeforeToday = TRUE;

            } else if (currentMonth == compareMonth) {

                if (currentDay > compareDay) {

                    isBeforeToday = TRUE;
                }
            }
        }

        return isBeforeToday;
    }


    public boolean comparateWhitThisMoment(Long time, int positiveDelay, int negativeDelay) {

        Calendar calendar = Calendar.getInstance(Locale.getDefault());
        long thisMoment = calendar.getTimeInMillis();
        long minLimit = time - 1000L * 60 * negativeDelay;
        long maxLimit = time + 1000L * 60 * positiveDelay;

        if (minLimit <= thisMoment && maxLimit >= thisMoment) {

            return TRUE;

        } else {

            return FALSE;
        }
    }

    public boolean comparateTwoInstants(long instant, long limitInstant) {

        if (limitInstant >= instant) {

            return TRUE;

        } else {

            return FALSE;
        }
    }

    public boolean isAvailible(long instant, long instDelay, long reference, long refDelay) {

        if (instant == 0L) {

            Calendar calInstant = Calendar.getInstance(Locale.getDefault());
            instant = calInstant.getTimeInMillis();
        }

        if (reference == 0L) {

            Calendar calReference = Calendar.getInstance(Locale.getDefault());
            reference = calReference.getTimeInMillis();
        }

        if ((instant + instDelay) <= (reference + refDelay)) {

            return TRUE;

        } else {

            return FALSE;
        }
    }

    public boolean comparateWhitMidNight(long Moment) {

        long midNightMoment = timeInThisMidNight();

        if (Moment == 0L) {

            Calendar calendar = Calendar.getInstance(Locale.getDefault());
            Moment = calendar.getTimeInMillis();
        }

        if (Moment > midNightMoment) {

            return TRUE;

        } else {

            return FALSE;
        }
    }

    public boolean comparateTwoInstantWhitMidNight(long instant, long limitInstant) {

        long midNightMoment = timeInThisMidNight(limitInstant);

        if (limitInstant == 0L) {

            Calendar calendar = Calendar.getInstance(Locale.getDefault());
            instant = calendar.getTimeInMillis();
        }

        if (midNightMoment > instant) {

            return TRUE;

        } else {

            return FALSE;
        }
    }

    public boolean belongsToThisMonth(String date) {

        Calendar compareCalendar = getCalendarFromString(date);
        int compareMonth = compareCalendar.get(Calendar.MONTH) + 1;

        Calendar todayCalendar = Calendar.getInstance(Locale.getDefault());
        int todayMonth = todayCalendar.get(Calendar.MONTH) + 1;

        if (compareMonth == todayMonth) {

            return TRUE;

        } else {

            return FALSE;
        }
    }


    /**
     * -------- Request Section
     */

    public boolean requestAuthByDate(long otherTime, long rankHours) {

        long thisTime = getMillisLong();
        long rankTime = otherTime + rankHours * 60 * 60 * 1000;

        if (rankTime >= thisTime) {

            return TRUE;

        } else {

            return FALSE;
        }
    }

    public String requestDiferenceFormTime(long lessTime, long heighterTime) {

        long milliseconds = heighterTime - lessTime;
        int minutes = (int) ((milliseconds / (1000 * 60)) % 60);
        int hours = (int) ((milliseconds / (1000 * 60 * 60)) % 24);

        if (hours != 0 && minutes != 0) {

            return hours + " h, " + minutes + " min";

        } else {

            if (hours != 0) {

                return hours + " h";

            } else {

                return minutes + " min";
            }
        }
    }


    public long timeInThisMidNight() {

        Locale locale = Locale.getDefault();
        Calendar calendar = Calendar.getInstance(locale);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        return calendar.getTimeInMillis();
    }

    public long timeInThisMidNight(long moment) {

        Locale locale = Locale.getDefault();
        Calendar calendar = Calendar.getInstance(locale);
        calendar.setTimeInMillis(moment);
        calendar.add(Calendar.DATE, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        return calendar.getTimeInMillis();
    }


    public int getNumberOfMonths(long Time) {

        long thisMoment = timeInThisMidNight();
        long diference = Time - thisMoment;
        long months = 30 * 24 * 60 * 60 * 1000L;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {

            return Math.toIntExact(diference / months);

        } else {

            return Integer.parseInt(String.valueOf(diference / months));
        }
    }


    /**
     * -------- Tools Section
     */

    public Calendar stringTimeToCalendar(@NonNull String Time) {

        Calendar Return = Calendar.getInstance(Locale.getDefault());

        if (!Time.equals("")) {

            Calendar calendar = Calendar.getInstance(Locale.getDefault());
            String day = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
            String month = String.valueOf(calendar.get(Calendar.MONTH));
            String year = String.valueOf(calendar.get(Calendar.YEAR));

            try {

                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm", Locale.getDefault());
                calendar.setTime(Objects.requireNonNull(dateFormat.parse(day + "-" + month + "-" + year + " " + Time)));

                Return = calendar;

            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        return Return;
    }

    public Calendar readStringDate(@NonNull final String Date) {

        Calendar Return = Calendar.getInstance(Locale.getDefault());

        if (!Date.equals("")) {

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yy", Locale.getDefault());

            try {

                Calendar calendar = Calendar.getInstance();
                java.util.Date date = dateFormat.parse(Date);

                if (date != null) {

                    calendar.setTime(date);
                }

                Return = calendar;

            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        return Return;
    }

    public Calendar readStringTime(@NonNull final String Date) {

        Calendar Return = Calendar.getInstance(Locale.getDefault());

        if (!Date.equals("")) {

            SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm", Locale.getDefault());

            try {

                Calendar calendar = Calendar.getInstance();
                java.util.Date date = dateFormat.parse(Date);

                if (date != null) {

                    calendar.setTime(date);
                }

                Return = calendar;

            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        return Return;
    }

    public String convertTwoDigitsYear(int Year) {

        String year = String.valueOf(Year);

        new LogCat("Year", Year, "year", year);

        if (year.length() == 4) {

            new LogCat("year", year.substring(2, 4));

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

    public String convertToTextMonth(int Month) {

        String textMonth = null;

        switch (Month) {

            case 0:
                textMonth = "Enero";
                break;

            case 1:
                textMonth = "Febrero";
                break;

            case 2:
                textMonth = "Marzo";
                break;

            case 3:
                textMonth = "Abril";
                break;

            case 4:
                textMonth = "Mayo";
                break;

            case 5:
                textMonth = "Junio";
                break;

            case 6:
                textMonth = "Julio";
                break;

            case 7:
                textMonth = "Agosto";
                break;

            case 8:
                textMonth = "Septiembre";
                break;

            case 9:
                textMonth = "Octubre";
                break;

            case 10:
                textMonth = "Noviembre";
                break;

            case 11:
                textMonth = "Diciembre";
                break;
        }

        return textMonth;
    }

    public String dayOfWeek(@NonNull Calendar calendar) {

        return calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault());
    }


    /**
     * -------- Tools Section
     */


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
