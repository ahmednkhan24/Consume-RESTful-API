import org.json.JSONException;
import org.json.JSONObject;

public class Crypto {

    private double ask;
    private double bid;
    private double last;
    private double high;
    private double low;

        private void parseData(String apiResponse) {
        try {
            JSONObject obj = new JSONObject(apiResponse);

            if (obj.has("ask")) {
                //double ask = obj.getDouble("ask");
                System.out.println("ASK");
            }

            if (obj.has("bid")) {
                System.out.println("BID");
            }

            if (obj.has("last")) {
                System.out.println("LAST");
            }

            if (obj.has("high")) {
                System.out.println("HIGH");
            }

            if (obj.has("low")) {
                System.out.println("LOW");
            }

            if (obj.has("open")) {
                System.out.println("OPEN");
            }

            if (obj.has("averages")) {
                System.out.println("AVERAGES");
            }

            if (obj.has("volume")) {
                System.out.println("VOLUME");
            }

            if (obj.has("changes")) {
                System.out.println("CHANGES");
            }

            if (obj.has("volume_percent")) {
                System.out.println("VOLUME_PERCENT");
            }

            if (obj.has("timestamp")) {
                System.out.println("TIMESTAMP");
            }

            if (obj.has("display_timestamp")) {
                System.out.println("DISPLAY_TIMESTAMP");
            }

        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
