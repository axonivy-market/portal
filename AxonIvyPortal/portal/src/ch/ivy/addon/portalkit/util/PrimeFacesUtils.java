package ch.ivy.addon.portalkit.util;

import org.primefaces.PrimeFaces;

public class PrimeFacesUtils {
  private PrimeFacesUtils() {}
  
  public static void executeScript(String statement) {
    PrimeFaces.current().executeScript(statement);
  }
}
