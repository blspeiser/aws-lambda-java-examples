package io.cambium.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;

import io.cambium.aws.lambdas.GetLookupsLambda;
import io.cambium.persistence.impl.DataSourceFactory;
import io.cambium.types.requests.GetLookupsRequest;
import io.cambium.types.responses.GetLookupsResponse;
import io.cambium.utils.DatabaseUtils;

/**
 * LookupsTest.
 *
 * @author Baruch Speiser, Cambium.
 */
public class LookupsTest {

  /**
   * Create data in the in-memory database. 
   * @throws SQLException
   */
  public void init() throws SQLException {
    DataSource ds = DataSourceFactory.createDataSource();
    Connection conn = ds.getConnection();
    Statement stmt = conn.createStatement();
    stmt.execute("DROP ALL OBJECTS");
    stmt.execute("CREATE TABLE LOOKUPS (TYPE VARCHAR(32) NOT NULL, NAME VARCHAR(32) NOT NULL, CODE VARCHAR(128) NOT NULL);");
    stmt.execute("INSERT INTO LOOKUPS (TYPE, NAME, CODE) VALUES ('colors', 'red',   'FF0000')");
    stmt.execute("INSERT INTO LOOKUPS (TYPE, NAME, CODE) VALUES ('colors', 'green', '00FF00')");
    stmt.execute("INSERT INTO LOOKUPS (TYPE, NAME, CODE) VALUES ('colors', 'blue',  '0000FF')");
    stmt.execute("INSERT INTO LOOKUPS (TYPE, NAME, CODE) VALUES ('colors', 'white', 'FFFFFF')");
    stmt.execute("INSERT INTO LOOKUPS (TYPE, NAME, CODE) VALUES ('colors', 'black', '000000')");
    stmt.execute("INSERT INTO LOOKUPS (TYPE, NAME, CODE) VALUES ('pets', 'cat',    'Felis catus')");
    stmt.execute("INSERT INTO LOOKUPS (TYPE, NAME, CODE) VALUES ('pets', 'dog',    'Canis lupus familiaris')");
    stmt.execute("INSERT INTO LOOKUPS (TYPE, NAME, CODE) VALUES ('pets', 'rabbit', 'Oryctolagus cuniculus domesticus')");
    DatabaseUtils.close(conn, stmt);
  }
  
  @Test
  public void testLookups() throws SQLException {
    init();
    
    GetLookupsLambda lambda = new GetLookupsLambda();
    GetLookupsRequest request = new GetLookupsRequest("colors");
    GetLookupsResponse response = lambda.handleRequest(request, null);
    assertEquals(5, response.lookups.size());
    
    request = new GetLookupsRequest("pets");
    response = lambda.handleRequest(request, null);
    assertEquals(3, response.lookups.size());
   
    request = new GetLookupsRequest("nonexistent");
    response = lambda.handleRequest(request, null);
    assertEquals(0, response.lookups.size());   
  }
  
}
