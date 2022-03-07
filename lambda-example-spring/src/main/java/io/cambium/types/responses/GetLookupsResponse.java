package io.cambium.types.responses;

import java.util.List;

import io.cambium.types.models.LookupData;

/**
 * GetLookupsResponse.
 * 
 *  DTO for receiving lookups. 
 * 
 * @author Baruch Speiser, Cambium.
 */
public class GetLookupsResponse {
  public final List<LookupData> lookups;

  /** @param lookups */
  public GetLookupsResponse(List<LookupData> lookups) {
    this.lookups = lookups;
  }

}
