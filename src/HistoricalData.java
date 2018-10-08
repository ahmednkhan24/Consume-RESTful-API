/*
 * HistoricalData.java
 * Object class to model all possible historical data values received by an API
 */

public class HistoricalData {
    /*
        Daily
        "time": "2018-10-07 15:03:00",
        "average": 6557.02

        Monthly
        "low": 7287.23,
        "open": 7315.80,
        "average": 7300.24,
        "time": "2018-09-03 20:00:00",
        "high": 7315.80

        Alltime
        "open": "",
        "time": "2010-07-17 00:00:00",
        "volume": 20.0,
        "low": "",
        "average": 0.05,
        "high": ""
     */
    private String type;
    private String time;
    private double volume;
    private double low;
    private double high;
    private double open;
    private double average;

    // daily
    public HistoricalData(String time, double average) {
        this.type    = "DAILY";
        this.time    = time;
        this.average = average;
    }

    // monthly
    public HistoricalData(String time, double average, double low, double high, double open) {
        this.type    = "MONTHLY";
        this.low     = low;
        this.high    = high;
        this.open    = open;
        this.time    = time;
        this.average = average;
    }

    // all time
    public HistoricalData(String time, double average, double volume) {
        this.type    = "ALLTIME";
        this.time    = time;
        this.volume  = volume;
        this.average = average;
    }

    // getters
    public String getType() {
        return type;
    }

    public String getTime() {
        return time;
    }

    public double getVolume() {
        return volume;
    }

    public double getLow() {
        return low;
    }

    public double getHigh() {
        return high;
    }

    public double getOpen() {
        return open;
    }

    public double getAverage() {
        return average;
    }

    // setters
    public void setType(String type) {
        this.type = type;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public void setLow(double low) {
        this.low = low;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    public void setOpen(double open) {
        this.open = open;
    }

    public void setAverage(double average) {
        this.average = average;
    }
}
