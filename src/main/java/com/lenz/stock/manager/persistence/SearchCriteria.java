package com.lenz.stock.manager.persistence;

/**
 * 
 * @author Michael Lenz
 *
 */
public class SearchCriteria {

  private String key;
  private String operation;
  private Object value;

  /**
   * Construct a {@link SearchCriteria}.
   * 
   * @param key
   * @param operation
   * @param value
   */
  public SearchCriteria(final String key, final String operation, final Object value) {
    this.key = key;
    this.operation = operation;
    this.value = value;
  }

  /**
   * Getter for the operation.
   * 
   * @return the operation
   */

  public String getOperation() {
    return operation;
  }

  /**
   * Setter for the operation.
   * 
   * @param operation
   *          the operation to set
   */
  public void setOperation(final String operation) {
    this.operation = operation;
  }

  /**
   * Getter for the key.
   * 
   * @return the key
   */

  public String getKey() {
    return key;
  }

  /**
   * Setter for the key.
   * 
   * @param key
   *          the key to set
   */
  public void setKey(final String key) {
    this.key = key;
  }

  /**
   * Getter for the value.
   * 
   * @return the value
   */

  public Object getValue() {
    return value;
  }

  /**
   * Setter for the value.
   * 
   * @param value
   *          the value to set
   */
  public void setValue(final Object value) {
    this.value = value;
  }
}