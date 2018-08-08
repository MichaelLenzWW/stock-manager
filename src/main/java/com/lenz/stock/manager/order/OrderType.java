package com.lenz.stock.manager.order;

/**
 * 
 * @author Michael Lenz
 *
 */
public enum OrderType {

  SHARE("SH", "Share"),
  STOCK_OPTION("STOCK_OPTION", "Stock Option");

  private String name;

  private String description;

  private OrderType(final String name, final String description) {
    this.name = name;
    this.description = description;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

}