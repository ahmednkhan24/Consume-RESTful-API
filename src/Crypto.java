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
import java.util.Collections;
import java.util.List;

public class Crypto {
    private static final String BASE_URL = "https://apiv2.bitcoinaverage.com/indices/global/";
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
    private ArrayList <HistoricalData> allTimeHistory;


    public Crypto(String priceSymbol) {
        this.priceSymbol = priceSymbol;

        ask = bid = last = high = low = volume = volumePercent = 0;
        open = new DateData();
        averages = new DateData();
        priceChanges = new DateData();
        percentChanges = new DateData();
        dailyHistory = new ArrayList<HistoricalData>();
        monthlyHistory = new ArrayList<HistoricalData>();
        allTimeHistory = new ArrayList<HistoricalData>();

        updateData();
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

    // calls the api to receive relevant data for this crypto and updates respective fields
    private void updateData() {
        // create the url for the endpoint and call the api using it
        String endpoint = BASE_URL + "ticker/" + getPriceSymbol();
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

    /*
     * returns an immutable list of the daily per minute daily sliding window
     */
    public List<HistoricalData> getDailyHistory(){
        // create the url for the endpoint and call the api using it
        String endpoint = BASE_URL + "history/" + getPriceSymbol() + "?period=daily&?format=json";
        String apiResponse = Api.fetch(endpoint);

        try {
            // remove any previous data stored in the list
            dailyHistory.clear();

            JSONArray arr = new JSONArray(apiResponse);
            for (int i = 0; i < arr.length(); ++i) {
                JSONObject rec = arr.getJSONObject(i);
                if(rec.has("time") && rec.has("average")){
                    String time = rec.getString("time");
                    double avg = rec.getDouble("average");
                    dailyHistory.add(new HistoricalData(time, avg));
                }
            }
        }
        catch (JSONException e) {
            System.out.println("JSONException: Crypto.getDailyHistory()");
            e.printStackTrace();
        }
        return Collections.unmodifiableList(dailyHistory);
    }

    /*
     * returns an immutable list of the monthly per hour monthly sliding window
     */
    public List<HistoricalData> getMonthlyHistory(){
        // create the url for the endpoint and call the api using it
        String endpoint = BASE_URL + "history/" + getPriceSymbol() + "?period=monthly&?format=json";
        String apiResponse = Api.fetch(endpoint);

        try {
            // remove any previous data stored in the list
            monthlyHistory.clear();

            JSONArray arr = new JSONArray(apiResponse);
            for (int i = 0; i < arr.length(); ++i) {
                JSONObject rec = arr.getJSONObject(i);
                if(rec.has("time") && rec.has("average") && rec.has("low") && rec.has("open") && rec.has("high")){
                    String time = rec.getString("time");
                    double avg = rec.getDouble("average");
                    double low = rec.getDouble("low");
                    double high = rec.getDouble("high");
                    double open = rec.getDouble("open");

                    monthlyHistory.add(new HistoricalData(time, avg, low, high, open));
                }
            }
        }
        catch (JSONException e) {
            System.out.println("JSONException: Crypto.getMonthlyHistory()");
            e.printStackTrace();
        }
        return Collections.unmodifiableList(monthlyHistory);
    }

    /*
     * returns an immutable list of the alltime - per day all time history (default value)
     */
    public List<HistoricalData> getAllTimeHistory(){
        // create the url for the endpoint and call the api using it
        String endpoint = BASE_URL + "history/" + getPriceSymbol() + "?period=alltime&?format=json";
        String apiResponse = Api.fetch(endpoint);

        try {
            // remove any previous data stored in the list
            allTimeHistory.clear();

            JSONArray arr = new JSONArray(apiResponse);
            for (int i = 0; i < arr.length(); ++i) {
                JSONObject rec = arr.getJSONObject(i);
                if(rec.has("time") && rec.has("average") && rec.has("volume")){
                    String time = rec.getString("time");
                    double avg = rec.getDouble("average");
                    double volume = rec.getDouble("volume");

                    allTimeHistory.add(new HistoricalData(time, avg, volume));
                }
            }
        }
        catch (JSONException e) {
            System.out.println("JSONException: Crypto.getAllTimeHistory()");
            e.printStackTrace();
        }
        return Collections.unmodifiableList(monthlyHistory);
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
