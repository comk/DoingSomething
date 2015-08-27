package com.mayhub.doingsomething.util;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by daihai on 2015/8/21.
 */
public class DateUtils {

    public static final long dayMills = 1000*60*60*24;

    public static String getFormattedDate(long date){

        int duration = subDay(System.currentTimeMillis(), date);
        if(Math.abs(duration) < 1){
            return "今天 " + DateUtils.getTimeString(date);
        }else if(Math.abs(duration) < 2){
            return "昨天 " + DateUtils.getTimeString(date);
        }else{
            return getDateTimeString(date);
        }
    }

    public static String getDateTimeString(long timemills){
        return DateFormat.getDateTimeInstance().format(new Date(timemills));
    }

    public static String getTimeString(long timemills){
        return DateFormat.getTimeInstance().format(new Date(timemills));
    }

    public static boolean isLeapYear(int year){
        if(year % 4 == 0 && year % 100 != 0){
            return true;
        }else if(year % 400 == 0){
            return true;
        }else {
            return false;
        }
    }

    /**
     * 获取俩个时间之前的相隔的天数
     *
     * @param startTime
     * @param endTime
     * @return
     */
    public static int subDay(long startTime, long endTime) {
        Calendar can1 = calendarThreadLocal.get();

        can1.setTimeInMillis(startTime);
        int dayOfYear1 = can1.get(Calendar.DAY_OF_YEAR);
        int year1 = can1.get(Calendar.YEAR);

        can1.setTimeInMillis(endTime);
        int dayOfYear2 = can1.get(Calendar.DAY_OF_YEAR);
        int year2 = can1.get(Calendar.YEAR);

        long durationMills = Math.abs(startTime - endTime);
        int days = (int) (durationMills/dayMills);
        if(days > 2){
            return days;
        }else{
            if(year1 == year2) {
                return dayOfYear1 - dayOfYear2;
            }else{
                return dayOfYear1 - dayOfYear2 + (isLeapYear(dayOfYear2) ? 366 : 365);
            }
        }
    }

    /**
     * Calendar objects are rather expensive: for heavy usage it's a good idea to use a single instance per thread
     * instead of calling Calendar.getInstance() multiple times. Calendar.getInstance() creates a new instance each
     * time.
     */
    public static final class DefaultCalendarThreadLocal extends ThreadLocal<Calendar> {
        @Override
        protected Calendar initialValue() {
            return Calendar.getInstance();
        }
    }

    private static ThreadLocal<Calendar> calendarThreadLocal = new DefaultCalendarThreadLocal();

    public static long getTimeForDay(int year, int month, int day) {
        return getTimeForDay(calendarThreadLocal.get(), year, month, day);
    }

    /** @param calendar helper object needed for conversion */
    public static long getTimeForDay(Calendar calendar, int year, int month, int day) {
        calendar.clear();
        calendar.set(year, month - 1, day);
        return calendar.getTimeInMillis();
    }

    /** Sets hour, minutes, seconds and milliseconds to the given values. Leaves date info untouched. */
    public static void setTime(Calendar calendar, int hourOfDay, int minute, int second, int millisecond) {
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);
        calendar.set(Calendar.MILLISECOND, millisecond);
    }

    /** Readable yyyyMMdd int representation of a day, which is also sortable. */
    public static int getDayAsReadableInt(long time) {
        Calendar cal = calendarThreadLocal.get();
        cal.setTimeInMillis(time);
        return getDayAsReadableInt(cal);
    }

    /** Readable yyyyMMdd representation of a day, which is also sortable. */
    public static int getDayAsReadableInt(Calendar calendar) {
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH) + 1;
        int year = calendar.get(Calendar.YEAR);
        return year * 10000 + month * 100 + day;
    }

    /** Returns midnight of the given day. */
    public static long getTimeFromDayReadableInt(int day) {
        return getTimeFromDayReadableInt(calendarThreadLocal.get(), day, 0);
    }

    /** @param calendar helper object needed for conversion */
    public static long getTimeFromDayReadableInt(Calendar calendar, int readableDay, int hour) {
        int day = readableDay % 100;
        int month = readableDay / 100 % 100;
        int year = readableDay / 10000;

        calendar.clear(); // We don't set all fields, so we should clear the calendar first
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.YEAR, year);

        return calendar.getTimeInMillis();
    }

    public static int getDayDifferenceOfReadableInts(int dayOfBroadcast1, int dayOfBroadcast2) {
        long time1 = getTimeFromDayReadableInt(dayOfBroadcast1);
        long time2 = getTimeFromDayReadableInt(dayOfBroadcast2);

        // Don't use getDayDifference(time1, time2) here, it's wrong for some days.
        // Do float calculation and rounding at the end to cover daylight saving stuff etc.
        float daysFloat = (time2 - time1) / 1000 / 60 / 60 / 24f;
        return Math.round(daysFloat);
    }

    public static int getDayDifference(long time1, long time2) {
        return (int) ((time2 - time1) / 1000 / 60 / 60 / 24);
    }

    public static long addDays(long time, int days) {
        Calendar calendar = calendarThreadLocal.get();
        calendar.setTimeInMillis(time);
        calendar.add(Calendar.DAY_OF_YEAR, days);
        return calendar.getTimeInMillis();
    }

    public static void addDays(Calendar calendar, int days) {
        calendar.add(Calendar.DAY_OF_YEAR, days);
    }
}
