package io.cambium.utils;

/**
 * StringUtils.
 *
 * @author Baruch Speiser, Cambium.
 */
public class StringUtils {

  /**
   * Test a string to see if it is empty of meaningful content. 
   * 
   * @param s
   * @return  true if the string is null or blank, 
   *          false if it contains at least one non-whitespace character. 
   */
  public static boolean isEmpty(String s) {
    return (null == s) || s.isEmpty() || s.trim().isEmpty();
  }
  
  
}
