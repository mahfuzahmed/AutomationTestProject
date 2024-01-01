package helper;


import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

public class Utilities {



    public static String getBase64EncodedString(String plainString) {
        try {
            return Base64.getEncoder().encodeToString(plainString.getBytes());
        } catch (Exception e) {

        }
        return null;
    }

    /**
     * @param string
     * @return string with case-altered characters
     */
    public static String getCaseAlteredString(String string) {

        try {
            int length = string.length();

            for (int i = 0; i < length; i++) {
                Character c = string.charAt(i);
                if (Character.isLowerCase(c)) {
                    string = string.substring(0, i) + Character.toUpperCase(c) + string.substring(i + 1);
                } else if (Character.isUpperCase(c)) {
                    string = string.substring(0, i) + Character.toLowerCase(c) + string.substring(i + 1);
                }
            }
            return string;
        } catch (Exception e) {

        }
        return null;
    }

    public static boolean isTime(String time) {
        try {
            return Pattern.compile("(((0?[1-9]|1[012])(:[0-5][0-9])?am)|((0?[0-9]|1[012])(:[0-5][0-9])?pm))").matcher(time).find();
        } catch (Exception e) {

        }
        return false;
    }

    public static boolean hasMonthInString(String month) {
        try {
            return Pattern.compile("(Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec)[.]*").matcher(month).find();
        } catch (Exception e) {

        }
        return false;
    }

    public static boolean isNumber(String number) {
        try {
            return Pattern.compile("[0-9]+").matcher(number).find();
        } catch (Exception e) {

        }
        return false;
    }

    public static boolean isPercentage(String percentage) {
        try {
            return Pattern.compile("[0-9]+%").matcher(percentage).find();
        } catch (Exception e) {

        }
        return false;
    }

    public static boolean isTemperature(String temperature) {
        try {
            return Pattern.compile("[0-9]+°(C|F)").matcher(temperature).find();
        } catch (Exception e) {

        }
        return false;
    }

    public static long daysDifference(String startDate, String endDate) {
        try {
            SimpleDateFormat myFormat = new SimpleDateFormat("MMM dd");
            Date _startDate = myFormat.parse(startDate);
            Date _endDate = myFormat.parse(endDate);
            long diff = _endDate.getTime() - _startDate.getTime();
            return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
        } catch (Exception e) {

            return 999999;
        }
    }

    public static String getPreviousDay() {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").format(addDay(-1));
        } catch (Exception e) {

            return null;
        }
    }

    public static String getCurrentDay() {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        } catch (Exception e) {

            return null;
        }
    }

    public static String getCurrentWeekRange() {
        try {
            return new SimpleDateFormat("MM/dd/yyyy").format(addDay(-7)) +
                    " - " + new SimpleDateFormat("MM/dd/yyyy").format(new Date());
        } catch (Exception e) {

            return null;
        }
    }

    public static String getLastWeekRange() {
        try {
            return new SimpleDateFormat("MM/dd/yyyy").format(addDay(-13)) +
                    " - " + new SimpleDateFormat("MM/dd/yyyy").format(addDay(-7));
        } catch (Exception e) {

            return null;
        }
    }

    public static String getCurrentMonthRange() {
        try {
            return new SimpleDateFormat("MM/dd/yyyy").format(getFirstDateOfMonth(new Date())) +
                    " - " + new SimpleDateFormat("MM/dd/yyyy").format(getLastDateOfMonth(new Date()));
        } catch (Exception e) {

            return null;
        }
    }

    public static String getLastMonthRange() {
        try {
            return new SimpleDateFormat("MM/dd/yyyy").format(getFirstDateOfMonth(addDay(-31))) +
                    " - " + new SimpleDateFormat("MM/dd/yyyy").format(getLastDateOfMonth(addDay(-31)));
        } catch (Exception e) {

            return null;
        }
    }

    /**
     * CSV Formatted data:
     * <p>
     * <p>
     * Data on 7/22/2019,Start Date,Start date - Today,Diff betwn two consecutive start dates,End Date,End date - Today,Diff betwn two consecutive end dates,Start date - End date
     * Current Year,1/22/2018,-546,,7/22/2019,0,,-546
     * Last Year,1/23/2017,-910,-364,7/22/2018,-365,-365,-545
     * 2nd last year,1/23/2016,-1276,-366,7/22/2017,-730,-365,-546
     * 3rd last year,1/23/2015,-1641,-365,7/22/2016,-1095,-365,-546
     * 4th last year,1/24/2014,-2005,-364,7/23/2015,-1460,-365,-545
     * 5th last year,1/24/2013,-2370,-365,7/23/2014,-1825,-365,-545
     * 6th last year,1/24/2012,-2736,-366,7/23/2013,-2190,-365,-546
     * 7th last year,1/24/2011,-3101,-365,7/23/2012,-2555,-365,-546
     * 8th last year,1/25/2010,-3465,-364,7/24/2011,-2920,-365,-545
     * <p>
     * for now 546 and 545 days are used to test the year range
     */

    public static String getCurrentYearRange() {
        try {
            return new SimpleDateFormat("MM/dd/yyyy").format(addDay(-546)) +
                    " - " + new SimpleDateFormat("MM/dd/yyyy").format(new Date());
        } catch (Exception e) {

            return null;
        }
    }

    public static String getLastYearRange() {
        try {
            return new SimpleDateFormat("MM/dd/yyyy").format(addDay(-(545 + 365))) +
                    " - " + new SimpleDateFormat("MM/dd/yyyy").format(addDay(-365));
        } catch (Exception e) {

            return null;
        }
    }

    public static String getLastMonth() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -1);
        return new SimpleDateFormat("MMMM, yyyy").format(cal.getTime());
    }

    private static Date addDay(int days) {
        final Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, days);
        return cal.getTime();
    }

    private static Date getFirstDateOfMonth(Date date) {
        final Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
        return cal.getTime();
    }

    private static Date getLastDateOfMonth(Date date) {
        final Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        return cal.getTime();
    }

    public static List<String> getSortedList(List<String> list) {
        try {
            List<String> sortedList = new ArrayList<String>(list);
            Collections.sort(sortedList);
            return sortedList;
        } catch (Exception e) {

        }
        return null;
    }

    public static List<String> getReverseSortedList(List<String> list) {
        try {
            List<String> reverseSortedList = new ArrayList<String>(list);
            reverseSortedList.sort(Collections.<String>reverseOrder());
            return reverseSortedList;
        } catch (Exception e) {

        }
        return null;
    }

    public static String getCurrentMonthYear() {
        try {
            Calendar cal = Calendar.getInstance();
            return new SimpleDateFormat("MMM yyyy").format(cal.getTime());
        } catch (Exception e) {

        }
        return "";
    }

    public static String getCurrentDayOfMonth() {
        try {
            Calendar cal = Calendar.getInstance();
            return new SimpleDateFormat("d").format(cal.getTime());
        } catch (Exception e) {

        }
        return "";
    }

    public static String getNextDayOfMonth() {
        try {
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DATE, 1);
            return new SimpleDateFormat("d").format(cal.getTime());
        } catch (Exception e) {

        }
        return "";
    }

    public static String getCurrentDate() {
        try {
            return new SimpleDateFormat("yyyy/MM/dd").format(new Date());
        } catch (Exception e) {

            return null;
        }
    }

    public static String convert24hrTo12hr(String date24hr) {
        try {
            SimpleDateFormat _24HourSDF = new SimpleDateFormat("HH:mm");
            SimpleDateFormat _12HourSDF = new SimpleDateFormat("h:mm a");
            Date _24HourDt = _24HourSDF.parse(date24hr);
            return _12HourSDF.format(_24HourDt);
        } catch (Exception e) {

            return null;
        }
    }
}
