package ch.ivy.addon.portalkit.service.exception;

public class GetPortalConnectorApplicationException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public GetPortalConnectorApplicationException() {
    super();
  }

  public GetPortalConnectorApplicationException(String message, Throwable cause, boolean enableSuppression,
      boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }

  public GetPortalConnectorApplicationException(String message, Throwable cause) {
    super(message, cause);
  }

  public GetPortalConnectorApplicationException(String message) {
    super(message);
  }

  public GetPortalConnectorApplicationException(Throwable cause) {
    super(cause);
  }

}
