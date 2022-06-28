package com.vyping.masterlibrary.time;

import static com.vyping.masterlibrary.time.Definitions.TIME_AFTER;
import static com.vyping.masterlibrary.time.Definitions.TIME_BEFORE;
import static com.vyping.masterlibrary.time.Definitions.TIME_EQUALS;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

import android.os.Build;
import android.text.format.DateFormat;
import android.text.format.DateUtils;

import androidx.annotation.NonNull;

import com.vyping.masterlibrary.Common.LogCat;
import com.vyping.masterlibrary.Common.MyString;

import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class Comparator {

    private final MyTime myTime;
    private final Calendar otherCalendar;


    /**
     * -------- Comparators Section
     */

    public Comparator(@NonNull MyTime myTime, long otherMillis) {

        this.myTime = myTime;
        this.otherCalendar = Calendar.getInstance(Locale.getDefault());
        this.otherCalendar.setTimeInMillis(otherMillis);
    }

    public Comparator(@NonNull MyTime myTime, Calendar otherCalendar) {

        this.myTime = myTime;
        this.otherCalendar = otherCalendar;
    }

    public long getDeltaYears() {

       return otherCalendar.get(Calendar.YEAR) - myTime.calendar.get(Calendar.YEAR);
    }

    public long getDeltaMonths() {

        long diffInMillies = otherCalendar.getTimeInMillis() - myTime.calendar.getTimeInMillis();
        long diffOnDays = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);

        return diffOnDays / 30;
    }

    public long getDeltaDays() {

        long diffInMillies = otherCalendar.getTimeInMillis() - myTime.calendar.getTimeInMillis();

        return TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
    }

    public long getDeltaHours() {

        long diffInMillies = otherCalendar.getTimeInMillis() - myTime.calendar.getTimeInMillis();

        return  TimeUnit.HOURS.convert(diffInMillies, TimeUnit.MILLISECONDS);
    }

    public long getDeltaMinutes() {

        long diffInMillies = otherCalendar.getTimeInMillis() - myTime.calendar.getTimeInMillis();

        return TimeUnit.MINUTES.convert(diffInMillies, TimeUnit.MILLISECONDS);
    }

    public long getDeltaSeconds() {

        long diffInMillies = otherCalendar.getTimeInMillis() - myTime.calendar.getTimeInMillis();

        return TimeUnit.SECONDS.convert(diffInMillies, TimeUnit.MILLISECONDS);
    }

    public long getDeltaMilliseconds() {

        long diffInMillies = otherCalendar.getTimeInMillis() - myTime.calendar.getTimeInMillis();

        return TimeUnit.MILLISECONDS.convert(diffInMillies, TimeUnit.MILLISECONDS);
    }




    public String timeSince(long startTime, long endTime) {

        Calendar startCalendar = Calendar.getInstance(Locale.getDefault());
        startCalendar.setTimeInMillis(startTime);

        Calendar endCalendar = Calendar.getInstance(Locale.getDefault());
        endCalendar.setTimeInMillis(endTime);

        long millis = endCalendar.getTimeInMillis() - startCalendar.getTimeInMillis();
        int hours = (int) (millis / (1000 * 60 * 60));
        int minutes = (int) ((millis / (1000 * 60)) % 60);
        String Hours = myTime.setHoursUnits(hours);
        String Minutes = myTime.setMinutesUnits(minutes);

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

        int month = myTime.calendar.get(Calendar.MONTH);
        int year = myTime.calendar.get(Calendar.YEAR);

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

        int currentYear = myTime.calendar.get(Calendar.YEAR);
        int currentMonth = myTime.calendar.get(Calendar.MONTH);
        int currentDay = myTime.calendar.get(Calendar.DAY_OF_MONTH);

        int compareYear = otherCalendar.get(Calendar.YEAR);
        int compareMonth = otherCalendar.get(Calendar.MONTH);
        int compareDay = otherCalendar.get(Calendar.DAY_OF_MONTH);

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

        long midNightMoment = myTime.midNight().getTimestamp();

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

    public boolean belongsToThisMonth(String date) {

        int compareMonth = otherCalendar.get(Calendar.MONTH) + 1;

        int todayMonth = myTime.calendar.get(Calendar.MONTH) + 1;

        if (compareMonth == todayMonth) {

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

    public int getNumberOfMonths() {

        long thisMoment = myTime.calendar.getTimeInMillis();
        long diference = otherCalendar.getTimeInMillis() - thisMoment;
        long months = 30 * 24 * 60 * 60 * 1000L;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {

            return Math.toIntExact(diference / months);

        } else {

            return Integer.parseInt(String.valueOf(diference / months));
        }
    }
}
