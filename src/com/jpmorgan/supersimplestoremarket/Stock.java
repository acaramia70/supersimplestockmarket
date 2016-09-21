package com.jpmorgan.supersimplestoremarket;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jpmorgan.supersimplestoremarket.util.Constants;

/**
 * Manage stock operations
 * 
 * @author acaramia *
 */
public class Stock {
  
  private String symbol;
  private String type;
  private Double lastDividend;
  private Double fixedDividend;
  private Double parValue;
  private List<Trade> trades;
  
  public Stock(String symbol, String type, Double lastDividend, Double fixedDividend, Double parValue) {
    this.symbol = symbol;
    this.type = type;
    this.lastDividend = lastDividend;
    this.fixedDividend = fixedDividend;
    this.parValue = parValue;
    this.trades = new ArrayList<Trade>();
  }
    
  public String getSymbol() {
    return symbol;
  }

  public void setSymbol(String symbol) {
    this.symbol = symbol;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Double getLastDividend() {
    return lastDividend;
  }

  public void setLastDividend(Double lastDividend) {
    this.lastDividend = lastDividend;
  }

  public Double getFixedDividend() {
    return fixedDividend;
  }

  public void setFixedDividend(Double fixedDividend) {
    this.fixedDividend = fixedDividend;
  }

  public Double getParValue() {
    return parValue;
  }

  public void setParValue(Double parValue) {
    this.parValue = parValue;
  }

  public List<Trade> getTrades() {
    return trades;
  }

  public void setTrades(List<Trade> trades) {
    this.trades = trades;
  }

  /**
   * Calculate the dividend yield given a price in input
   * 
   * @param price The price for calculating the dividend yield
   * @return The dividend yield
   */
  public Double dividend(Double price) {
    if (this.getType().equals(Constants.STOCKTYPECOMMON))
      return this.getLastDividend()/price;
    else if (this.getType().equals(Constants.STOCKTYPEPREFERRED))
      return this.getFixedDividend()*this.getParValue()/price;
    else
      return 0.0;
  }
  
  /**
   * Calculate P/E Ratio given a price in input
   * 
   * @param price The price for calculating the P/E ratio
   * @return The P/E Ratio
   */
  public Double PERatio(Double price) {
    return price/this.getLastDividend();
  }

  /**
   * Create trade for buying stocks
   * 
   * @param quantity The stock quantity
   * @param price The stock price
   */
  public void buy(Integer quantity, Double price) {
    Trade trade = new Trade(Constants.SELLINDICATOR, quantity, price);
    trades.add(trade);
  }

  /**
   * Create trade for selling stocks
   * 
   * @param quantity The stock quantity
   * @param price The stock price
   */
  public void sell(Integer quantity, Double price) {
    Trade trade = new Trade(Constants.SELLINDICATOR, quantity, price);
    trades.add(trade);   
  }
  
  /**
   * Return the last trade price
   * 
   * @return The last trade price
   */
  public Double getPrice() {
    if (this.trades.size() > 0) {
      return this.trades.get(trades.size()-1).getPrice();
    } else {
      return 0.0;
    }
  }
  
  /**
   * Calculate the Volume Weighted Stock Price in past minutes
   * 
   * @param minutes The minutes of trades to consider
   * @return The Volume Weighted Stock Price
   */
  public Double getVolumeWeightedStockPrice(Integer minutes) {
    Date now = new Date();
    Date startTime = new Date(now.getTime() - (minutes * 60 * 1000));
    Double volumeWeigthedStockPrice = 0.0;
    Integer quantitySum = 0;
    for (Trade trade: trades) {
      if (!trade.getTimestamp().before(startTime)) {
        quantitySum += trade.getQuantity();
        volumeWeigthedStockPrice += trade.getPrice() * trade.getQuantity();
      }
    }
    return volumeWeigthedStockPrice/quantitySum;
  }
}
