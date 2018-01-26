package ch.ivy.addon.portalkit.util;

public class NumberUtils {
  
  /**
   * Answers <code>true</code> iff the given number is 'not a number'
   * (i.e., is a <code>Float</code> or <code>Double</code> containing
   * one of the predefined constant values representing <code>NaN</code>).
   *
   * @param number
   * @return boolean
   */
  public static boolean isNaN(Number number) {
    if (number instanceof Double && ((Double)number).isNaN())
      return true;
    if (number instanceof Float && ((Float)number).isNaN())
      return true;
    return false;
  }
}
