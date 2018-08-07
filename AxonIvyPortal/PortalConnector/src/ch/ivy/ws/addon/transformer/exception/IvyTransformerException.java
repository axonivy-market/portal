package ch.ivy.ws.addon.transformer.exception;

public class IvyTransformerException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public IvyTransformerException() {
    super();
  }

  public IvyTransformerException(String message, Throwable cause, boolean enableSuppression,
      boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }

  public IvyTransformerException(String message, Throwable cause) {
    super(message, cause);
  }

  public IvyTransformerException(String message) {
    super(message);
  }

  public IvyTransformerException(Throwable cause) {
    super(cause);
  }

}
