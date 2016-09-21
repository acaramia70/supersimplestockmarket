package com.jpmorgan.supersimplestoremarket.util;

import java.util.Map;

import com.jpmorgan.supersimplestoremarket.Stock;

public class Functions {

  /**
   * Calculate the GBCE All Share Index for all stocks
   * 
   * @param stocks The stock map
   * @return The GBCE All Share Index
   */
  public static Double getGBCEallShareIndex(Map<String, Stock> stockMap) {
    Double allShareIndex = 0.0;
    for(Stock stock: stockMap.values()) {
      allShareIndex+=stock.getPrice();
    }
    return Math.pow(allShareIndex, 1.0 / stockMap.size());
  }

}
