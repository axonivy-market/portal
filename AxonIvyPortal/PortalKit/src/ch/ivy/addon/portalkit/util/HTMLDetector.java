package ch.ivy.addon.portalkit.util;

import java.util.regex.Pattern;

/**
 * Helper class to detect whether the text contains HTML or not
 */
public class HTMLDetector {
  public final static String TAG_START =
      "\\<\\w+((\\s+\\w+(\\s*\\=\\s*(?:\".*?\"|'.*?'|[^'\"\\>\\s]+))?)+\\s*|\\s*)\\>";
  public final static String TAG_END = "\\</\\w+\\>";
  public final static String TAG_SELF_CLOSING =
      "\\<\\w+((\\s+\\w+(\\s*\\=\\s*(?:\".*?\"|'.*?'|[^'\"\\>\\s]+))?)+\\s*|\\s*)/\\>";
  public final static String HTML_ENTITY = "&[a-zA-Z][a-zA-Z0-9]+;";
  public final static Pattern HTML_PATTERN = Pattern.compile("(" + TAG_START + ".*" + TAG_END + ")|("
      + TAG_SELF_CLOSING + ")|(" + HTML_ENTITY + ")", Pattern.DOTALL);

  /**
   * Will return true if s contains HTML markup tags or entities.
   *
   * @param s String to test
   * @return true if string contains HTML
   */
  public static boolean isHtml(String s) {
    boolean ret = false;
    if (s != null) {
      ret = HTML_PATTERN.matcher(s).find();
    }
    return ret;
  }

}
