package io.cambium.persistence;

import java.util.List;

import io.cambium.types.models.LookupData;

/**
 * LookupRepository.
 * 
 *  A repository for working with lookup data.  
 *
 * @author Baruch Speiser, Cambium.
 */
public interface LookupsRepository {

  /**
   * Gets lookup data by type. 
   * 
   * @param type
   * @return list of lookups, or empty if not found
   * @throws some sort of runtime exception if there is a problem connecting to the database. 
   */
  public List<LookupData> getLookups(String type);
  
}
