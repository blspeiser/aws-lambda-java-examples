package io.cambium.api;

import java.util.List;

import io.cambium.types.models.LookupData;

/**
 * LookupService.
 * 
 *  A simple service for retrieving a list of lookup options for a dropdown in a web application. 
 *
 * @author Baruch Speiser, Cambium.
 */
public interface LookupService {

  /**
   * Retrieve a list of lookup options. 
   * 
   * @param type  The type of lookup data you want to retrieve
   * @return the list of options available for that type, including their display name and their value 
   */
  List<LookupData> lookup(String type);

}
