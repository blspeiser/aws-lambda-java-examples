package io.cambium.types.models;

/**
 * LookupData.
 *
 *  A simple class containing lookup data for a dropdown in a web application. 
 *
 * @author Baruch Speiser, Cambium.
 */
public class LookupData {
  public String type;
  public String name;
  public String value;
  
  /** Default constructor. */
  public LookupData() {
    this(null, null, null);
  }

  /**
   * Full constructor.
   * @param type
   * @param name
   * @param value
   */
  public LookupData(String type, String name, String value) {
    this.type = type;
    this.name = name;
    this.value = value;
  }
  
}
