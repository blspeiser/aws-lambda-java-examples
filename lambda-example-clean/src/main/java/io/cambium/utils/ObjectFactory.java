package io.cambium.utils;

import io.cambium.api.LookupsService;
import io.cambium.api.impl.LookupsServiceBean;
import io.cambium.persistence.LookupsRepository;
import io.cambium.persistence.impl.LookupsRepositoryBean;

/**
 * ObjectFactory.
 * 
 *  A centralized location to retrieve implementation classes. 
 *
 * @author Baruch Speiser, Cambium.
 */
public class ObjectFactory {
  private static LookupsService lookupsService = null; 
  private static LookupsRepository lookupsRepository = null;

  /** @return LookupsService implementation. */
  public static synchronized LookupsService getLookupsService() {
    if(null == lookupsService) lookupsService = new LookupsServiceBean(); 
    return lookupsService;
  }

  /** @return LookupsRepository implementation. */  
  public static synchronized LookupsRepository getLookupsRepository() {
    if(null == lookupsRepository) lookupsRepository = new LookupsRepositoryBean();
    return lookupsRepository;
  }



}
