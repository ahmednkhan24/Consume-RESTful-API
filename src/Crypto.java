/*
 * Crypto.java
 * Object class to model all possible crypto-currencies received by an API
 *
 * Crypto's that are supported in this application:
 *      BitCoin  - BTC
 *      LiteCoin - LTC
 *
 *
 * Currencies that are supported in this application:
 *      US Dollar         - USD
 *      Euro              - EUR
 *      British Pound     - GBP
 *      Australian Dollar - AUD
 *      Japanese Yen      - JPY
 *      Chinese Yuan      - CNY
 *      Saudi Riyal       - SAR
 */

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Crypto {
    private String rawJson;
    private String priceSymbol;
    private String timestamp;
    private String display_timestamp;
    private double ask;
    private double bid;
    private double last;
    private double high;
    private double low;
    private double volume;
    private double volumePercent;
    private DateData open;
    private DateData averages;
    private DateData priceChanges;
    private DateData percentChanges;
    private ArrayList <HistoricalData> dailyHistory;
    private ArrayList <HistoricalData> monthlyHistory;
    private ArrayList <HistoricalData> alltimeHistory;


    public Crypto(String priceSymbol) {
        this.priceSymbol = priceSymbol;

        ask = bid = last = high = low = volume = volumePercent = 0;
        open = new DateData();
        averages = new DateData();
        priceChanges = new DateData();
        percentChanges = new DateData();
        dailyHistory = new ArrayList<HistoricalData>();
        monthlyHistory = new ArrayList<HistoricalData>();
        alltimeHistory = new ArrayList<HistoricalData>();

        //TODO DELETE ME LATTER
        updateData();
        updateDailyHistory();
        updateMonthlyHistory();
        updateAllTimeHistory();
    }

    // getters
    public String getRawJson() {
        return rawJson;
    }

    public String getPriceSymbol() {
        return priceSymbol;
    }

    public String getDisplay_timestamp() {
        return display_timestamp;
    }

    public double getAsk() {
        return ask;
    }

    public double getBid() {
        return bid;
    }

    public double getLast() {
        return last;
    }

    public double getHigh() {
        return high;
    }

    public double getLow() {
        return low;
    }

    public double getVolume() {
        return volume;
    }

    public double getVolumePercent() {
        return volumePercent;
    }

    public DateData getOpen() {
        return open;
    }

    public DateData getAverages() {
        return averages;
    }

    public DateData getPriceChanges() {
        return priceChanges;
    }

    public DateData getPercentChanges() {
        return percentChanges;
    }

    // setters
    public void setRawJson(String rawJson) {
        this.rawJson = rawJson;
    }

    public void setPriceSymbol(String priceSymbol) {
        this.priceSymbol = priceSymbol.toUpperCase();
        updateData();
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public void setDisplay_timestamp(String display_timestamp) {
        this.display_timestamp = display_timestamp;
    }

    public void setAsk(double ask) {
        this.ask = ask;
    }

    public void setBid(double bid) {
        this.bid = bid;
    }

    public void setLast(double last) {
        this.last = last;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    public void setLow(double low) {
        this.low = low;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public void setVolumePercent(double volumePercent) {
        this.volumePercent = volumePercent;
    }

    private void updateData() {
        // create the url for the endpoint and call the api using it
        String endpoint = "https://apiv2.bitcoinaverage.com/indices/global/ticker/" + getPriceSymbol();
        String apiResponse = Api.fetch(endpoint);

        // parse the JSON data received from the api
        try {
            JSONObject obj = new JSONObject(apiResponse);
            setRawJson(apiResponse);

            if (obj.has("ask"))
                setAsk(obj.getDouble("ask"));
            if (obj.has("bid"))
                setBid(obj.getDouble("bid"));
            if (obj.has("last"))
                setLast(obj.getDouble("last"));
            if (obj.has("high"))
                setHigh(obj.getDouble("high"));
            if (obj.has("low"))
                setLow(obj.getDouble("low"));
            if (obj.has("volume"))
                setVolume(obj.getDouble("volume"));
            if (obj.has("volume_percent"))
                setVolumePercent(obj.getDouble("volume_percent"));
            if (obj.has("timestamp"))
                setTimestamp(Double.toString(obj.getDouble("timestamp")));
            if (obj.has("display_timestamp"))
                setDisplay_timestamp(obj.getString("display_timestamp"));
            if (obj.has("open"))
                open.updateData(obj.getJSONObject("open"));
            if (obj.has("averages"))
                averages.updateData(obj.getJSONObject("averages"));
            if (obj.has("changes")) {
                JSONObject changes = obj.getJSONObject("changes");

                if (changes.has("price"))
                    priceChanges.updateData(changes.getJSONObject("price"));
                if (changes.has("percent"))
                    percentChanges.updateData(changes.getJSONObject("percent"));
            }
        }
        catch (JSONException e) {
            System.out.println("JSONException: Crypto.parseData()");
            e.printStackTrace();
        }
    }

    public void updateDailyHistory(){
        // create the url for the endpoint and call the api using it
        String endpoint = "https://apiv2.bitcoinaverage.com/indices/global/history/" + getPriceSymbol() + "?period=daily&?format=json";
        String apiResponse = Api.fetch(endpoint);

        try {
            dailyHistory.clear(); //removes all old stuff from arraylist

            JSONArray arr = new JSONArray(apiResponse);

            for (int i = 0; i < arr.length(); ++i) {
                JSONObject rec = arr.getJSONObject(i);

                if(rec.has("time") && rec.has("average")){

                    // Get avg and time from obj into local variables
                    String time = rec.getString("time");
                    double avg = rec.getDouble("average");

                    // Add to dailyHistory arraylist
                    dailyHistory.add(new HistoricalData(time, avg));
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateMonthlyHistory(){
        // create the url for the endpoint and call the api using it
        String endpoint = "https://apiv2.bitcoinaverage.com/indices/global/history/" + getPriceSymbol() + "?period=monthly&?format=json";
        String apiResponse = Api.fetch(endpoint);

        try {
            monthlyHistory.clear(); //removes all old stuff from arraylist

            JSONArray arr = new JSONArray(apiResponse);

            for (int i = 0; i < arr.length(); ++i) {
                JSONObject rec = arr.getJSONObject(i);

                if(rec.has("time") && rec.has("average") && rec.has("low") && rec.has("open") && rec.has("high")){

                    // Get data from obj into local variables
                    String time = rec.getString("time");
                    double avg = rec.getDouble("average");
                    double low = rec.getDouble("low");
                    double high = rec.getDouble("high");
                    double open = rec.getDouble("open");


                    // Add to dailyHistory arraylist
                    monthlyHistory.add(new HistoricalData(time, avg, low, high, open));
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateAllTimeHistory(){
        // create the url for the endpoint and call the api using it
        String endpoint = "https://apiv2.bitcoinaverage.com/indices/global/history/" + getPriceSymbol() + "?period=alltime&?format=json";
        String apiResponse = Api.fetch(endpoint);

        try {
            alltimeHistory.clear(); //removes all old stuff from arraylist

            JSONArray arr = new JSONArray(apiResponse);

            for (int i = 0; i < arr.length(); ++i) {
                JSONObject rec = arr.getJSONObject(i);

                if(rec.has("time") && rec.has("average") && rec.has("volume")){

                    // Get avg and time from obj into local variables
                    String time = rec.getString("time");
                    double avg = rec.getDouble("average");
                    double volume = rec.getDouble("volume");

                    // Add to dailyHistory arraylist
                    alltimeHistory.add(new HistoricalData(time, avg, volume));
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Price Symbol: " + getPriceSymbol());
        sb.append("\n");
        sb.append("Timestamp: " + getDisplay_timestamp());
        sb.append("\n");
        sb.append("Ask: " + getAsk());
        sb.append("\n");
        sb.append("Bid: " + getBid());
        sb.append("\n");
        sb.append("Last: " + getLast());
        sb.append("\n");
        sb.append("High: " + getHigh());
        sb.append("\n");
        sb.append("Low: " + getLow());
        sb.append("\n");
        sb.append("Volume: " + getVolume());
        sb.append("\n");
        sb.append("Volume Percent: " + getVolumePercent());
        sb.append("\n");
        sb.append("Open:");
        sb.append("\n");
        sb.append(getOpen().toString());
        sb.append("\n");
        sb.append("Averages:");
        sb.append("\n");
        sb.append(getAverages().toString());
        sb.append("\n");
        sb.append("Price Changes:");
        sb.append("\n");
        sb.append(getPriceChanges().toString());
        sb.append("\n");
        sb.append("Percent Changes:");
        sb.append("\n");
        sb.append(getPercentChanges().toString());

        return sb.toString();
    }
}
