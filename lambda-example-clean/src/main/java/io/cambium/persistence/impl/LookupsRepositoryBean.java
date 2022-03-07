package io.cambium.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import io.cambium.persistence.LookupsRepository;
import io.cambium.types.models.LookupData;
import io.cambium.utils.DatabaseUtils;

/**
 * LookupRepositoryBean.
 * 
 *  SQL-based implementation for managing lookup data. 
 *  
 *  (Yes, it includes a lot of boilerplate SQL management code, 
 *   but that isn't the point right now.)   
 * 
 * @see io.cambium.persistence.LookupsRepository
 * @author Baruch Speiser, Cambium.
 */
public class LookupsRepositoryBean implements LookupsRepository {
  private final DataSource ds = DataSourceFactory.createDataSource();
  
  /** @see io.cambium.persistence.LookupsRepository */
  public List<LookupData> getLookups(String type) {
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    try {
      conn = ds.getConnection();
      stmt = conn.prepareStatement("SELECT NAME, CODE FROM LOOKUPS WHERE TYPE = ?");
      stmt.setString(1, type);
      rs = stmt.executeQuery();
      List<LookupData> list = new ArrayList<LookupData>();
      while(rs.next()) {
        LookupData lookup = new LookupData();
        lookup.type   = type;
        lookup.name   = rs.getString("NAME");
        lookup.value  = rs.getString("CODE");
        list.add(lookup);
      }
      return list;
    } catch(Exception e) {
      throw new RuntimeException("Unexpected error", e);
    } finally {
      DatabaseUtils.close(rs);
      DatabaseUtils.close(stmt);
      DatabaseUtils.close(conn);
    }
  }
}
