package ch.ivy.addon.portal.chat;

public class PortalChatException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public PortalChatException() {
    super();
  }

  public PortalChatException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }

  public PortalChatException(String message, Throwable cause) {
    super(message, cause);
  }

  public PortalChatException(String message) {
    super(message);
  }

  public PortalChatException(Throwable cause) {
    super(cause);
  }

}
