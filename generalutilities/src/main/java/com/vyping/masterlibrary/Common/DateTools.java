package com.vyping.masterlibrary.Common;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

import android.text.format.DateFormat;

import androidx.annotation.NonNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
import java.util.TimeZone;

public class DateTools {

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

    public String getTime(String Format, long Time) {

        Calendar calendar = Calendar.getInstance(Locale.getDefault());
        calendar.setTimeInMillis(Time);

        return DateFormat.format(Format, calendar).toString();
    }






    public String InstantToTimeToString(String Instant) {

        Calendar calendar = Calendar.getInstance(Locale.getDefault());
        calendar.setTimeInMillis(Long.parseLong(Instant));
        String instante = DateFormat.format("MMM dd - HH:mm", calendar).toString();

        return new Strings().firstLetterUpperCase(instante);
    }

    public String intsToStringDate(int day, int month, int year) {

        String Day = completeDigitsDate(day);
        String Month = completeDigitsDate(month + 1);
        String Year = convertTwoDigitsYear(year);

        return Day + "-" + Month + "-" + Year;
    }

    public String selectedDateToTag(@NonNull Calendar date) {

        String day = completeDigitsDate(date.get(Calendar.DAY_OF_MONTH));
        String month = completeDigitsDate(date.get(Calendar.MONTH) + 1);
        String year = convertTwoDigitsYear(date.get(Calendar.YEAR));

        return day + "-" + month + "-" + year;
    }

    public String selectedDateToView(Calendar date) {

        String dayOfWeek = new Strings().firstLetterUpperCase(dayOfWeek(date));
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

    public boolean comparateWhitToday(Calendar otherCalendar, String otherTime) {

        Calendar calendar = Calendar.getInstance(Locale.getDefault());
        long today = calendar.getTimeInMillis(), other;
        long OtherTime = Long.parseLong(otherTime);

        boolean Return = FALSE;

        if (otherCalendar != null) {

            other = otherCalendar.getTimeInMillis();

            if (other >= today) {

                Return = TRUE;
            }

        } else if (OtherTime != 0L) {

            if (OtherTime >= today) {

                Return = TRUE;
            }
        }

        return Return;
    }

    public boolean comparateWhitThisMoment(Long time, int positiveDelay, int negativeDelay) {

        Calendar calendar = Calendar.getInstance(Locale.getDefault());
        long thisMoment = calendar.getTimeInMillis();
        long minLimit = time - 1000L*60*negativeDelay;
        long maxLimit = time + 1000L*60*positiveDelay;

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

        long thisTime = getThisMomentTimeStamp();
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


    public long getThisMomentTimeStamp() {

        Locale locale = Locale.getDefault();
        Calendar calendar = Calendar.getInstance(locale);

        return calendar.getTimeInMillis();
    }

    public int getNumberOfMonths(long Time) {

        long thisMoment = timeInThisMidNight();
        long diference = Time - thisMoment;
        long months = 30*24*60*60*1000L;

        return Math.toIntExact(diference / months);
    }


    /**
     *
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

            } catch (ParseException e) { e.printStackTrace(); }
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

            } catch (ParseException e) { e.printStackTrace(); }
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

            } catch (ParseException e) { e.printStackTrace(); }
        }

        return Return;
    }

    public String convertTwoDigitsYear(int Year) {

        if (Year >= 2000) {

            return completeDigitsDate(Year - 2000);

        } else {

            return String.valueOf(Year - 1900);
        }
    }

    public String completeDigitsDate(int date) {

        if (date < 10 ) {

            return "0" + date;

        } else  {

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

}
