/*
REFERENCES USED

    BitCoin API that I've been using
    https://apiv2.bitcoinaverage.com/

    BitCoin API that I haven't used yet - this is what Alex sent
    https://www.cryptocompare.com/api/#-api-data-price-

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

public class Main {

    public static void main(String[] args) {

        Crypto coinData_obj = new Crypto();

        String coinData_string = Api.fetch("https://apiv2.bitcoinaverage.com/indices/global/ticker/BTCUSD");
        //System.out.println(coinData_string);

        coinData_obj.parseData(coinData_string);

        //System.out.println(Api.fetch("https://apiv2.bitcoinaverage.com/symbols/indices/ticker"));
    }
}
