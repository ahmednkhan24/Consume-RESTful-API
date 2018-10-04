import org.json.JSONException;
import org.json.JSONObject;

public class Crypto {

    private double ask;
    private double bid;
    private double last;
    private double high;
    private double low;
    private double openDay;
    private double volume;
    private double dpriceChange;
    private double wpriceChange;
    private double mpriceChange;
    private double ypriceChange;
    private String timestamp;

        public void parseData(String apiResponse) {
        try {
            JSONObject obj = new JSONObject(apiResponse);

            if (obj.has("ask")) {
                ask = obj.getDouble("ask");

                //TODO delete this print statement
                System.out.println("Ask: " + ask);
            }

            if (obj.has("bid")) {
                bid = obj.getDouble("bid");

                //TODO delete this print statement
                System.out.println("Bid: " + bid);
            }

            if (obj.has("last")) {
                last = obj.getDouble("last");

                //TODO delete this print statement
                System.out.println("Last: " + last);
            }

            // High price for the day
            if (obj.has("high")) {
                high = obj.getDouble("high");

                //TODO delete this print statement
                System.out.println("High: " + high);
            }

            // Low price for the day
            if (obj.has("low")) {
                low = obj.getDouble("low");

                //TODO delete this print statement
                System.out.println("Low: " + low);
            }

            // Opening prices for hour, day, week, and year for the
            // crypto currency that is currently selected
            if (obj.has("open")) {

                // TODO: We probably only want the opening price for the day not the other one's
                openDay = obj.getJSONObject("open").getDouble("day");

                double openWeek = obj.getJSONObject("open").getDouble("week");
                double openMonth = obj.getJSONObject("open").getDouble("month");
                double openYear = obj.getJSONObject("open").getDouble("year");

                //TODO delete this print statement
                System.out.println("OPEN -> DAY: " + openDay + "  Week: " + openWeek + "  Month: " + openMonth + "  Year: " + openYear);
            }

            // Average prices for day, week, and month for the
            // crypto currency that is currently selected
            if (obj.has("averages")) {
                double avgDay = obj.getJSONObject("averages").getDouble("day");
                double avgWeek = obj.getJSONObject("averages").getDouble("week");
                double avgMonth = obj.getJSONObject("averages").getDouble("month");

                //TODO delete this print statement
                System.out.println("Average -> DAY: " + avgDay + "  Week: " + avgWeek + "  Month: " + avgMonth);

            }

            // Volume of the crypto currency
            if (obj.has("volume")) {
                volume = obj.getDouble("volume");

                //TODO delete this print statement
                System.out.println("Volume: " + volume);
            }

            // Changes in price for the selected crypto currency from the
            // given day, week, month, and year.
            if (obj.has("changes")) {
                dpriceChange = obj.getJSONObject("changes").getJSONObject("price").getDouble("day");
                wpriceChange = obj.getJSONObject("changes").getJSONObject("price").getDouble("week");
                mpriceChange = obj.getJSONObject("changes").getJSONObject("price").getDouble("month");
                ypriceChange = obj.getJSONObject("changes").getJSONObject("price").getDouble("year");


                //TODO delete this print statement
                System.out.println("Changes -> DAY: " + dpriceChange + "  Week: " + wpriceChange + "  Month: " + mpriceChange + "  Year: " + ypriceChange);
            }

            // TODO we don't need this.....I think

            if (obj.has("volume_percent")) {
                //System.out.println("VOLUME_PERCENT");
            }

            // TODO we don't need this.....I think
            if (obj.has("timestamp")) {
               //System.out.println("TIMESTAMP");
            }

            // Display time for when data was pulled
            if (obj.has("display_timestamp")) {
                timestamp = obj.getString("display_timestamp");

                //TODO delete this print statement
                System.out.println("Timestamp: " + timestamp);
            }

        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
