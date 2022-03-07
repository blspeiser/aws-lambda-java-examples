package io.cambium.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import io.cambium.persistence.LookupsRepository;
import io.cambium.persistence.entities.LookupEntity;
import io.cambium.rest.LookupsController;
import io.cambium.types.requests.GetLookupsRequest;
import io.cambium.types.responses.GetLookupsResponse;

/**
 * LookupsTest.
 *
 * @author Baruch Speiser, Cambium.
 */
@SpringBootTest
public class LookupsTest {
  @Autowired
  LookupsController controller;
  @Autowired
  LookupsRepository repository;

  /**
   * Create data in the in-memory database. 
   * @throws SQLException
   */
  public void init() throws SQLException {
    LookupEntity[] lookups = {
        new LookupEntity(1L, "colors", "red",   "FF0000"),
        new LookupEntity(2L, "colors", "green", "00FF00"),
        new LookupEntity(3L, "colors", "blue",  "0000FF"),
        new LookupEntity(4L, "colors", "white", "FFFFFF"),
        new LookupEntity(5L, "colors", "black", "000000"),
        new LookupEntity(6L, "pets", "cat",    "Felis catus"),
        new LookupEntity(7L, "pets", "dog",    "Canis lupus familiaris"),
        new LookupEntity(8L, "pets", "rabbit", "Oryctolagus cuniculus domesticus")
    };
    for(LookupEntity lookup : lookups) {
      repository.save(lookup);
    }
  }
  
  @Test
  public void testLookups() throws SQLException {
    init();
  
    GetLookupsRequest request = new GetLookupsRequest("colors");
    GetLookupsResponse response = controller.getLookups(request);
    assertEquals(5, response.lookups.size());
    
    request = new GetLookupsRequest("pets");
    response = controller.getLookups(request);
    assertEquals(3, response.lookups.size());
   
    request = new GetLookupsRequest("nonexistent");
    response = controller.getLookups(request);
    assertEquals(0, response.lookups.size());   
  }
  
}
