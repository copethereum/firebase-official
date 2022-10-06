package tests.api.coindesk;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import org.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.testng.annotations.Test;

import common.core.TestProperties;
import common.core.functions.CoreFunctions;

public class ApiBtcPriceFluctuation extends CoreFunctions {

    @Test(priority = 1)
    public void apiCoinDeskBtcPrice() throws Exception {

        double sumOfValues = 0;
        double sumOfResponses = 0;
        String time = TestProperties.dynamic.getProperty("time");

        Integer requestTime = Integer.parseInt(time);
        Integer RequestTime = 30;
        Integer iteration = requestTime / RequestTime;

        try {

            ArrayList<Double> btcArrayPrices = new ArrayList<>();
            ArrayList<Long> responseTimeArray = new ArrayList<>();

            for(int j = 0; j < iteration; j++) {

                String url = "https://api.coindesk.com/v1/bpi/currentprice.json";

                Connection.Response response = null;
                Connection request = jsoupConnection(url, Method.GET).timeout(30000);

                long startTime = 0;

                try {
                    startTime = System.currentTimeMillis();
                    response = request.execute();

                } catch(IOException e) {
                    if(e.getMessage().contains("connect timed out")) {
                        System.out.println("Request timed out after 30 seconds!");
                    }
                }

                long currentTime = System.currentTimeMillis();
                long responseTime = currentTime - startTime;

                responseTimeArray.add(responseTime);

                res = jsoupConnection(url, Method.GET).execute();

                String jsonString = res.body();
                JSONObject bpiDataCollection = new JSONObject(jsonString).getJSONObject("bpi");
                String price = bpiDataCollection.getJSONObject("USD").optString("rate_float");
                double btcPrice = Double.parseDouble(price);
                btcArrayPrices.add(btcPrice);

                System.out.println("*** Current Price USD is: " + price + " ***");
                System.out.println("Response time: " + responseTime + "ms" + "\tResponse code: " + response.statusCode());
                Thread.sleep(2000);

            }

            System.out.println( responseTimeArray);
            System.out.println( btcArrayPrices);

            for(double sum : responseTimeArray)
                sumOfResponses += sum;

            System.out.println("The sum of all Responses is " + sumOfResponses);

            double averageResponseTime = sumOfResponses / responseTimeArray.size();

            System.out.println("Average Response Time is " + averageResponseTime);

            System.out.println("*** Array of prices: " + btcArrayPrices + " ******" );
            System.out.println("***Number of collected BTC values is:" + btcArrayPrices.size() + " ***");
            System.out.println("*** Array of response time: " + responseTimeArray + "ms" + "******" );

            for(double sum : btcArrayPrices) {
                sumOfValues += sum;
            }
            System.out.println("The sum of all BTC values is " + sumOfValues);

            double averageBtcPrice = sumOfValues / btcArrayPrices.size();

            System.out.println("Average price of BTC in USD is " + averageBtcPrice);

            double medianValue = 0;

            if(IsEven(btcArrayPrices)) {
                int firstIndex = btcArrayPrices.size() / 2 - 1;
                int secondIndex = firstIndex + 1;
                medianValue = (btcArrayPrices.get(firstIndex) +  btcArrayPrices.get(secondIndex)) / 2;

                System.out.println("Median Value is: " + medianValue);
            } else {
                int firstIndex = btcArrayPrices.size() / 2 + 1;
                medianValue = btcArrayPrices.get(firstIndex);

                System.out.println("Median Value is: " + medianValue);
            }

        } catch(SocketTimeoutException e) {
            if(retryCount < TestProperties.getRepeatOnExceptionCount()) {
                retryCount++;
                System.out.println("SocketTimeoutException Caught, retrying >> describeContract << No: " + retryCount + " ...");
            } else {
                assert (false);
            }
        }
    }
}
