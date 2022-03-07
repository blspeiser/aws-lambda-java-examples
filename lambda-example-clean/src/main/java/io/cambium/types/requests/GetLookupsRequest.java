package io.cambium.types.requests;

/**
 * GetLookupsRequest.
 * 
 *  DTO for requesting lookups. 
 *
 * @author Baruch Speiser, Cambium.
 */
public class GetLookupsRequest {
  public final String type;

  /** Default constructor. */
  public GetLookupsRequest() {
    this(null);
  }

  /** @param type */
  public GetLookupsRequest(String type) {
    this.type = type;
  }  
}
