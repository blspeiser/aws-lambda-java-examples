package io.cambium.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * DatabaseUtils.
 *
 * @author Baruch Speiser, Cambium.
 */
public class DatabaseUtils {

  /** @param conn Safely close the database connection. */
  public static void close(Connection conn) {
    if(null == conn) return;
    try {
      conn.close();
    } catch(Exception ignore) {
      // don't care
    }
  }

  /** @param stmt Safely close the database statement. */
  public static void close(Statement stmt) {
    if(null == stmt) return;
    try {
      stmt.close();
    } catch(Exception ignore) {
      // don't care
    }
  }

  /** @param rs Safely close the result set. */
  public static void close(ResultSet rs) {
    if(null == rs) return;
    try {
      rs.close();
    } catch(Exception ignore) {
      // don't care
    }
  }

  
}
