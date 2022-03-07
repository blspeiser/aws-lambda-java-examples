package io.cambium.api.impl;

import java.util.Collections;
import java.util.List;

import io.cambium.api.LookupService;
import io.cambium.persistence.LookupsRepository;
import io.cambium.persistence.impl.LookupsRepositoryBean;
import io.cambium.types.models.LookupData;
import io.cambium.utils.StringUtils;

/**
 * LookupServiceBean.
 * 
 * @see io.cambium.api.LookupService 
 * @author Baruch Speiser, Cambium.
 */
public class LookupServiceBean implements LookupService {
  private static final LookupsRepository repository = new LookupsRepositoryBean();

  /** @see io.cambium.api.LookupService#lookup(String) */
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
