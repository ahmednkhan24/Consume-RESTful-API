/*
REFERENCES USED

    BitCoin API that I've been using
    https://apiv2.bitcoinaverage.com/

    BitCoin API that I haven't used yet - this is what Alex sent
    https://www.cryptocompare.com/api/#-api-data-price-

    News API used
    https://newsapi.org/

    news api key = f313ec1010e145cab7730034d0c6baf5

    Calling an API in Java
    https://www.mkyong.com/webservices/jax-rs/restfull-java-client-with-java-net-url/

    Make JSON readable
    http://jsonmate.com/

    Importing JSON jar to project
    https://stackoverflow.com/questions/8997598/importing-json-into-an-eclipse-project

    Using Java JSON Object
    https://stackoverflow.com/questions/2591098/how-to-parse-json-in-java

    Jersey JARS
    https://jersey.github.io/download.html

    Convert input stream to string in Java
    http://roufid.com/5-ways-convert-inputstream-string-java/
*/

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Crypto bitCoinUS = new Crypto("BTCUSD");
        System.out.println(bitCoinUS.toString());
        System.out.println();

//        List<HistoricalData> daily = bitCoinUS.getDailyHistory();
//        for (HistoricalData d : daily)
//            System.out.println(d.toString());
//        System.out.println(daily.size());
//
//        List<HistoricalData> monthly = bitCoinUS.getMonthlyHistory();
//        for (HistoricalData d : monthly)
//            System.out.println(d.toString());
//        System.out.println(monthly.size());
//
//        List<HistoricalData> alltime = bitCoinUS.getAllTimeHistory();
//        for (HistoricalData d : alltime)
//            System.out.println(d.toString());
//        System.out.println(alltime.size());
//
//        List<NewsData> articles = bitCoinUS.relevantArticles();
//        for (NewsData a : articles)
//            System.out.println(a.toString());
//        System.out.println(articles.size());
    }
}
