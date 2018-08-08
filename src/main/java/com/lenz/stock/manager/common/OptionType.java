package com.lenz.stock.manager.common;

/**
 * 
 * @author Michael Lenz
 *
 */
public enum OptionType {

  PUT("P", "Put"),
  CALL("C", "Call");

  private String name;

  private String description;

  private OptionType(final String name, final String description) {
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