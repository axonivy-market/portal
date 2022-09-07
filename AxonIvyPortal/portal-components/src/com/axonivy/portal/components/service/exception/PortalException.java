package com.axonivy.portal.components.service.exception;

public class PortalException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public PortalException(String message, Throwable cause) {
    super(message, cause);
  }

  public PortalException(String message) {
    super(message);
  }

  public PortalException(Throwable cause) {
    super(cause);
  }

}
