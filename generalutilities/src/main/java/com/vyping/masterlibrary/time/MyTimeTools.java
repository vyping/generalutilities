package com.vyping.masterlibrary.time;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

import android.os.Build;
import android.text.format.DateFormat;

import androidx.annotation.IntDef;
import androidx.annotation.NonNull;

import com.vyping.masterlibrary.Common.MyNumbers;
import com.vyping.masterlibrary.Common.MyString;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Calendar;
import java.util.Locale;

public class MyTimeTools {

    public static final int TIME_BEFORE = 0, TIME_EQUALS = 1, TIME_AFTER = 2;

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({TIME_BEFORE, TIME_EQUALS, TIME_AFTER})
    public @interface TypeClock {
    }


    // ----- Add Days - Operators ----- //

    public Calendar addDays(int days) {

        Calendar calendar = new MyTime().getCalendar();

        return addDays(calendar, days);
    }

    public Calendar addDays(String days) {

        Calendar calendar = new MyTime().getCalendar();

        return addDays(calendar, days);
    }

    public Calendar addDays(@NonNull Calendar calendar, int days) {

        calendar.add(Calendar.DAY_OF_YEAR, days);

        return calendar;
    }

    public Calendar addDays(@NonNull Calendar calendar, String days) {

        boolean isNumber = new MyNumbers().isNumber(days);

        if (isNumber) {

            int Days = new MyNumbers().objectToInteger(days);

            return addDays(calendar, Days);

        } else {

            return calendar;
        }
    }

    public Calendar addDays(long instant, int days) {

        Calendar calendar = new MyTime().getCalendar(instant);

        return addDays(calendar, days);
    }

    public Calendar addDays(long instant, String days) {

        Calendar calendar = new MyTime().getCalendar(instant);

        return addDays(calendar, days);
    }

    public Calendar addDays(String instant, int days) {

        Calendar calendar = new MyTime().getCalendar(instant);

        return addDays(calendar, days);
    }

    public Calendar addDays(String instant, String days) {

        Calendar calendar = new MyTime().getCalendar(instant);

        return addDays(calendar, days);
    }

    public Calendar addDays(String date, String format, int days) {

        Calendar calendar = new MyTime().getCalendar(date, format);

        return addDays(calendar, days);
    }

    public Calendar addDays(String date, String format, String days) {

        Calendar calendar = new MyTime().getCalendar(date, format);

        return addDays(calendar, days);
    }

    public void addDays(int days, AddDayInterface interfase) {

        Calendar calendar = new MyTime().getCalendar();

        addDays(calendar, days, interfase);
    }

    public void addDays(String days, AddDayInterface interfase) {

        Calendar calendar = new MyTime().getCalendar();

        addDays(calendar, days, interfase);
    }

    public void addDays(@NonNull Calendar calendar, int days, @NonNull AddDayInterface interfase) {

        calendar.add(Calendar.DAY_OF_YEAR, days);

        long millis = calendar.getTimeInMillis();
        String day = new MyTime().getDayOfMonthOnString(calendar);
        String month = new MyTime().getMonthOnString(calendar);
        String year = new MyTime().getYearOnString(calendar);

        interfase.AddDay(calendar, millis, day, month, year);
    }

    public void addDays(@NonNull Calendar calendar, String days, AddDayInterface interfase) {

        boolean isNumber = new MyNumbers().isNumber(days);

        if (isNumber) {

            int Days = new MyNumbers().objectToInteger(days);

            addDays(calendar, Days,  interfase);

        } else {

          interfase.Error("Los Dias indicados no son un número");
        }
    }

    public void addDays(long instant, int days, @NonNull AddDayInterface interfase) {

        Calendar calendar = new MyTime().getCalendar(instant);

        addDays(calendar, days,  interfase);
    }

    public void addDays(long instant, String days, AddDayInterface interfase) {

        Calendar calendar = new MyTime().getCalendar(instant);

        addDays(calendar, days,  interfase);
    }

    public void addDays(String instant, int days, @NonNull AddDayInterface interfase) {

        Calendar calendar = new MyTime().getCalendar(instant);

        addDays(calendar, days,  interfase);
    }

    public void addDays(String instant, String days, AddDayInterface interfase) {

        Calendar calendar = new MyTime().getCalendar(instant);

        addDays(calendar, days,  interfase);
    }

    public void addDays(String date, String format, int days, @NonNull AddDayInterface interfase) {

        Calendar calendar = new MyTime().getCalendar(date, format);

        addDays(calendar, days,  interfase);
    }

    public void addDays(String date, String format, String days, AddDayInterface interfase) {

        Calendar calendar = new MyTime().getCalendar(date, format);

        addDays(calendar, days,  interfase);
    }


    //----- Interface - Section-----//

    public interface AddDayInterface {

        void AddDay(Calendar Calendar, long millis, String day, String month, String year);
        void Error(String error);
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

        Calendar calendar = new MyTime().getCalendar(time);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);

        Calendar otherCalendar = new MyTime().getCalendar(otherTime);
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

        return new MyString().formatDigits(prevMonth, 2);
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

        Calendar compareCalendar = new MyTime().getCalendar(stringDate);
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

        Calendar compareCalendar = new MyTime().getCalendar(date);
        int compareMonth = compareCalendar.get(Calendar.MONTH) + 1;

        Calendar todayCalendar = Calendar.getInstance(Locale.getDefault());
        int todayMonth = todayCalendar.get(Calendar.MONTH) + 1;

        if (compareMonth == todayMonth) {

            return TRUE;

        } else {

            return FALSE;
        }
    }



    public String InstantToTimeToString(String Instant) {

        Calendar calendar = Calendar.getInstance(Locale.getDefault());
        calendar.setTimeInMillis(Long.parseLong(Instant));
        String instante = DateFormat.format("MMM dd - HH:mm", calendar).toString();

        return new MyString().firstLetterUpperCase(instante);
    }

    public String intsToStringDate(int day, int month, int year) {

        String Day = completeDigitsDate(day);
        String Month = completeDigitsDate(month + 1);
        String Year = convertTwoDigitsYear(year);

        return Day + "-" + Month + "-" + Year;
    }


    /**
     * -------- Request Section
     */

    public boolean requestAuthByDate(long otherTime, long rankHours) {

        long thisTime = new MyTime().getMillis();
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
