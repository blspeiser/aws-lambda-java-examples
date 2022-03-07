package io.cambium.api.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import io.cambium.api.LookupService;
import io.cambium.persistence.LookupsRepository;
import io.cambium.persistence.entities.LookupEntity;
import io.cambium.types.models.LookupData;

/**
 * LookupServiceBean.
 * 
 * @see io.cambium.api.LookupService 
 * @author Baruch Speiser, Cambium.
 */
@Service
public class LookupServiceBean implements LookupService {
  @Autowired
  LookupsRepository repository;
  
  /** @see io.cambium.api.LookupService#lookup(String) */
  public List<LookupData> lookup(String type) {
    List<LookupData> list;
    if(StringUtils.hasText(type)) {
      //Convert hydrated objects to pojos
      List<LookupEntity> data = repository.findAllByType(type);
      list = new ArrayList<>(data.size());
      for(LookupEntity lookup : data) {
        list.add(new LookupData(
            lookup.getType(), 
            lookup.getName(),
            lookup.getValue()));
      }
    } else {
      list = Collections.emptyList();
    }
    return Collections.unmodifiableList(list); 
  }

}
