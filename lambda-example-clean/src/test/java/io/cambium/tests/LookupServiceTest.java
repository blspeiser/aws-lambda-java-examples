package io.cambium.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import io.cambium.api.impl.LookupsServiceBean;
import io.cambium.persistence.LookupsRepository;
import io.cambium.types.models.LookupData;

public class LookupServiceTest {

  @Test
  public void testService() {
    LookupsServiceBean service = new LookupsServiceBean(new LookupsRepository() {
      @Override
      public List<LookupData> getLookups(String type) {
        if(!"colors".equals(type)) return Collections.emptyList();
        return Arrays.asList(
            new LookupData("colors", "red", "FF0000"),
            new LookupData("colors", "green", "00FF00"),
            new LookupData("colors", "blue", "0000FF"));
      }
    });
    
    assertNotNull(service.lookup(null));
    assertNotNull(service.lookup(""));
    assertNotNull(service.lookup("kjghf"));
    assertEquals(0, service.lookup(null).size());
    assertEquals(0, service.lookup("").size());
    assertEquals(0, service.lookup("654").size());
    List<LookupData> list = service.lookup("colors");
    assertEquals(3, list.size());
    try {
      list.clear();
      fail("List should be unmodifiable");
    } catch(UnsupportedOperationException expected) {
      //ignore
    }
    
    
  }
  
}
