/*
    DateData.java

    Object class to model all possible date data values received by an API
 */

public class DateData {
    // member variables
    private double hour;
    private double day;
    private double week;
    private double month;
    private double month_3;
    private double month_6;
    private int year;

    // constructors
    public DateData() {
        hour = day = week = month = month_3 = month_6 = year = -1;
    }

    public DateData(double day, double week, double month) {
        this.day   = day;
        this.week  = week;
        this.month = month;

        hour = month_3 = month_6 = year = -1;
    }

    public DateData(double hour, double day, double week, double month, double month_3, double month_6, int year) {
        this.hour    = hour;
        this.day     = day;
        this.week    = week;
        this.month   = month;
        this.month_3 = month_3;
        this.month_6 = month_6;
        this.year    = year;
    }

    // getters
    public double getHour() {
        return hour;
    }

    public double getDay() {
        return day;
    }

    public double getWeek() {
        return week;
    }

    public double getMonth() {
        return month;
    }

    public double getMonth_3() {
        return month_3;
    }

    public double getMonth_6() {
        return month_6;
    }

    public int getYear() {
        return year;
    }
}
