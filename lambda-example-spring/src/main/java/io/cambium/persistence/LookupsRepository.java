package io.cambium.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import io.cambium.persistence.entities.LookupEntity;

/**
 * LookupRepository.
 * 
 *  A repository for working with lookup data.  
 *
 * @author Baruch Speiser, Cambium.
 */
public interface LookupsRepository extends JpaRepository<LookupEntity, Long> {

  /**
   * Gets lookup data by type. 
   * 
   * @param type
   * @return list of lookups
   */
  List<LookupEntity> findAllByType(String type);
  
}
