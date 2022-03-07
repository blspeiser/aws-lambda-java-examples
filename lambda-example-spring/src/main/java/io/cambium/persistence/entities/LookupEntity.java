package io.cambium.persistence.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * LookupEntity.
 * 
 *  An entity for storing lookup data that is independent of the domain model. 
 *
 * @author Baruch Speiser, Cambium.
 */
@Entity
public class LookupEntity {
  @Id
  private Long id;
  private String type;
  private String name;
  private String value;
  
  /** Default constructor. */
  public LookupEntity() {
    this(null, null, null, null);
  }
  
  /**
   * Full constructor. 
   * @param id
   * @param type
   * @param name
   * @param value
   */
  public LookupEntity(Long id, String type, String name, String value) {
    this.id = id;
    this.type = type;
    this.name = name;
    this.value = value;
  }
  
  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
  }
  public String getType() {
    return type;
  }
  public void setType(String type) {
    this.type = type;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getValue() {
    return value;
  }
  public void setValue(String value) {
    this.value = value;
  }

}
