package ch.ivy.addon.portalkit.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.log4j.Priority;

import ch.ivy.ws.addon.WsException;
import ch.ivyteam.ivy.environment.Ivy;

/**
 * This class handles errors by logging and storing the errors to FacesContext so that in the GUI, we can display the
 * errors
 */
public class ErrorHandler {

  public static void addError(Priority priority, WsException wsException) {
    Ivy.log().log(priority, "Web service error with: \r\n- User text: {0} \r\n- Help text: {1} \r\n- Error code: {2}",
        wsException.getUserText(), wsException.getHelpText(), wsException.getErrorCode());
    storeError(wsException.getUserText());
  }

  private static void storeError(String message) {
	if (FacesContext.getCurrentInstance() != null) {
      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, message));
	}
  }

}
