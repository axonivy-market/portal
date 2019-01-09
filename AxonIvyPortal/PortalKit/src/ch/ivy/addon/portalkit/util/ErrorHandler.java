package ch.ivy.addon.portalkit.util;

import org.apache.log4j.Priority;

import ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException;
import ch.ivyteam.ivy.environment.Ivy;

/**
 * This class handles errors by logging and storing the errors to FacesContext so that in the GUI, we can display the
 * errors
 */
public class ErrorHandler {

  private ErrorHandler() {}

  public static void addError(Priority priority, PortalIvyDataException error) {
    Ivy.log().log(priority, error.toString());
  }

}
