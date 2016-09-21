package com.jpmorgan.supersimplestoremarket;

import java.util.HashMap;

import com.jpmorgan.supersimplestoremarket.util.Constants;
import com.jpmorgan.supersimplestoremarket.util.Functions;

public class SuperSimpleStockMarket 
{  

    public static void main( String[] args) {
        try {
            String help = "Syntax:\n" +
                          "java TestMain <price> <minutes>\n" +
                          "<price> : price for calculating dividend yield and P/E ratio\n" +
                          "<minutes> : minutes for calculating Volume Weighted Stock Price";
            if (args.length < 2) {
              System.out.println(help);
              return;
            }
            Double price = new Double(args[0]);
            Integer minutes = new Integer(args[1]);
            HashMap<String, Stock> stockMap = new HashMap<String, Stock>();
            stockMap.put("TEA", new Stock("TEA", Constants.STOCKTYPECOMMON, 0.0, 0.0, 100.0));
            stockMap.put("POP", new Stock("POP", Constants.STOCKTYPECOMMON, 8.0, 0.0, 100.0));
            stockMap.put("ALE", new Stock("ALE", Constants.STOCKTYPECOMMON, 23.0, 0.0, 60.0));
            stockMap.put("GIN", new Stock("GIN", Constants.STOCKTYPEPREFERRED, 8.0, 0.2, 100.0));
            stockMap.put("JOE", new Stock("JOE", Constants.STOCKTYPECOMMON, 13.0, 0.0, 250.0));
            for (Stock stock: stockMap.values()) {
              System.out.println(stock.getSymbol() + " dividend yield: " + stock.dividend(price));
              System.out.println(stock.getSymbol() + " P/E Ratio: " + stock.PERatio(price));
              for (int i=1; i <= 10; i++) {
                double random = 0.0; 
                while (random == 0.0) {
                  random = Math.random();
                }
                random = 10*random;
                stock.buy(i, random);
                System.out.println( stock.getSymbol() + " buy " + i + " shares at $" + random);
                random = 0.0; 
                while (random == 0.0) {
                  random = Math.random();
                }
                random = 10*random;
                stock.sell(i, random);
                System.out.println(stock.getSymbol() + " sell " + i + " shares at $" + random);
              }
              System.out.println(stock.getSymbol() + " last price: $" + stock.getPrice());
              System.out.println(stock.getSymbol() + " Volume Weighted Stock Price in past " + minutes + "minutes: $" + stock.getVolumeWeightedStockPrice(minutes));
            }
            Double GBCEallShareIndex = Functions.getGBCEallShareIndex(stockMap);
            System.out.println( "GBCE All Share Index: " + GBCEallShareIndex);
      } catch (Exception e) {
        System.err.println(e.getMessage());
      }
    }
}
