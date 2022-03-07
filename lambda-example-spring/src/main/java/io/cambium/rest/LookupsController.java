package io.cambium.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import io.cambium.api.LookupService;
import io.cambium.types.models.LookupData;
import io.cambium.types.requests.GetLookupsRequest;
import io.cambium.types.responses.GetLookupsResponse;

/**
 * LookupsController.
 * 
 *  REST interface for an API that returns a list of options for a dropdown in a web application. 
 *
 * @author Baruch Speiser, Cambium.
 */
@RestController
@EnableWebMvc
@RequestMapping(path="/api/lookups")
public class LookupsController {
  private static final Logger log = LoggerFactory.getLogger(LookupsController.class);
  
  @Autowired
  private LookupService service;
  
  @GetMapping
  public GetLookupsResponse getLookups(GetLookupsRequest request) {
    try {
      List<LookupData> lookups = service.lookup(request.type);
      return new GetLookupsResponse(lookups);
    } catch(RuntimeException e) {
      //Log it before we throw it upwards.  
      log.error("Error during lookup", e);
      throw e;
    }  
  }
  
  
  
}
