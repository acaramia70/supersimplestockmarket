package com.jpmorgan.supersimplestoremarket;

import java.util.Date;

/**
 * Trade bean
 * 
 * @author acaramia *
 */
public class Trade {
  
  private String indicator;
  private Integer quantity;
  private Double price;
  private Date timestamp;
  
  public Trade(String indicator, Integer quantity, Double price) {
    this.indicator = indicator;
    this.quantity = quantity;
    this.price = price;
    this.timestamp = new Date();
  }

  public String getType() {
    return indicator;
  }

  public void setIndicator(String indicator) {
    this.indicator = indicator;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }
  
  public Date getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(Date timestamp) {
    this.timestamp = timestamp;
  }

}
