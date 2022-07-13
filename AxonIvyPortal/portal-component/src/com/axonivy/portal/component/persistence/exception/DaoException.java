package com.axonivy.portal.component.persistence.exception;

/**
 * This exception is thrown when an error occurs while accessing a database through any extending
 * data access object.
 */
public class DaoException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  /**
   * Constructs a {@link DaoException} with the specified detail message.
   * <p>
   * Note that the detail message associated with {@code cause} is <i>not</i> automatically
   * incorporated in this {@link DaoException} detail message.
   * 
   * @param message the detail message (which is saved for later retrieval by the
   *        {@link #getMessage()} method).
   */
  public DaoException(String message) {
    super(message);
  }

  /**
   * Constructs a {@link DaoException} with the specified detail message and cause.
   * <p>
   * Note that the detail message associated with {@code cause} is <i>not</i> automatically
   * incorporated in this {@link DaoException DaoException's} detail message.
   * 
   * @param message the detail message (which is saved for later retrieval by the
   *        {@link #getMessage()} method).
   * @param cause the cause (which is saved for later retrieval by the {@link #getCause()} method).
   *        (A <tt>null</tt> value is permitted, and indicates that the cause is nonexistent or
   *        unknown.)
   */
  public DaoException(String message, Throwable cause) {
    super(message, cause);
  }

}
