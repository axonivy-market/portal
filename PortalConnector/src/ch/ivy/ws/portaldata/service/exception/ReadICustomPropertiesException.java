package ch.ivy.ws.portaldata.service.exception;

public class ReadICustomPropertiesException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public ReadICustomPropertiesException() {
    super();
  }

  public ReadICustomPropertiesException(String message, Throwable cause, boolean enableSuppression,
      boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }

  public ReadICustomPropertiesException(String message, Throwable cause) {
    super(message, cause);
  }

  public ReadICustomPropertiesException(String message) {
    super(message);
  }

  public ReadICustomPropertiesException(Throwable cause) {
    super(cause);
  }

}
