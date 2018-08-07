package ch.ivy.addon.portalkit.service.exception;

public class ApplicationNotFoundException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public ApplicationNotFoundException() {
    super();
  }

  public ApplicationNotFoundException(String message, Throwable cause, boolean enableSuppression,
      boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }

  public ApplicationNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }

  public ApplicationNotFoundException(String message) {
    super(message);
  }

  public ApplicationNotFoundException(Throwable cause) {
    super(cause);
  }

}
