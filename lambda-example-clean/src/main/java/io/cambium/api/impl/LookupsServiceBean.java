package io.cambium.api.impl;

import java.util.Collections;
import java.util.List;

import io.cambium.api.LookupsService;
import io.cambium.persistence.LookupsRepository;
import io.cambium.types.models.LookupData;
import io.cambium.utils.ObjectFactory;
import io.cambium.utils.StringUtils;

/**
 * LookupServiceBean.
 * 
 * @see io.cambium.api.LookupsService 
 * @author Baruch Speiser, Cambium.
 */
public class LookupsServiceBean implements LookupsService {
  private static final LookupsRepository repository = ObjectFactory.getLookupsRepository();

  /** @see io.cambium.api.LookupsService#lookup(String) */
  public List<LookupData> lookup(String type) {
    List<LookupData> list;
    if(StringUtils.isEmpty(type)) {
      list = Collections.emptyList();
    } else {
      list = repository.getLookups(type);
    }
    return Collections.unmodifiableList(list); 
  }

}
