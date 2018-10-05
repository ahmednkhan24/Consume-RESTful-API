/*
 * DateData.java
 * Object class to model all possible date data values received by an API
 */

import org.json.JSONException;
import org.json.JSONObject;

public class DateData {
    // member variables
    private double hour;
    private double day;
    private double week;
    private double month;
    private double month_3;
    private double month_6;
    private double year;
    private boolean isInDepth;

    // constructors
    public DateData() {
        isInDepth = false;
        hour = day = week = month = month_3 = month_6 = year = -1;
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

    public double getYear() {
        return year;
    }

    public boolean isInDepth() {
        return isInDepth;
    }

    // setters
    public void setHour(double hour) {
        this.hour = hour;
    }

    public void setDay(double day) {
        this.day = day;
    }

    public void setWeek(double week) {
        this.week = week;
    }

    public void setMonth(double month) {
        this.month = month;
    }

    public void setMonth_3(double month_3) {
        this.month_3 = month_3;
    }

    public void setMonth_6(double month_6) {
        this.month_6 = month_6;
    }

    public void setYear(double year) {
        this.year = year;
    }

    public void setInDepth(boolean inDepth) {
        this.isInDepth = inDepth;
    }

    /*
     * A DateData object can either be in-depth or not in-depth.
     *
     * A non in-depth DateData object ONLY has: day, week, month
     * An in-depth DateData object contains at least one additional data member, i.e. hour, month_3, month6, year
     */
    public void updateData(JSONObject obj) {
        try {
            setInDepth(false);

            if (obj.has("day")) {
                setDay(obj.getDouble("day"));
                setWeek(obj.getDouble("week"));
                setMonth(obj.getDouble("month"));
            }

            if (obj.has("hour")) {
                setInDepth(true);

                setHour(obj.getDouble("hour"));
                setMonth_3(obj.getDouble("month_3"));
                setMonth_6(obj.getDouble("month_6"));
                setYear(obj.getDouble("year"));

            }
        }
        catch (JSONException e) {
            System.out.println("JSONException: DateData.updateData()");
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        if (isInDepth()) {
            sb.append("Hour: " + getHour());
            sb.append("\n");
            sb.append("Day: " + getDay());
            sb.append("\n");
            sb.append("Week: " + getWeek());
            sb.append("\n");
            sb.append("Month: " + getMonth());
            sb.append("\n");
            sb.append("Month_3: " + getMonth_3());
            sb.append("\n");
            sb.append("Month_6: " + getMonth_6());
            sb.append("\n");
            sb.append("Year: " + getYear());
        }
        else {
            sb.append("Day: " + getDay());
            sb.append("\n");
            sb.append("Week: " + getWeek());
            sb.append("\n");
            sb.append("Month: " + getMonth());
        }

        return sb.toString();
    }
}
