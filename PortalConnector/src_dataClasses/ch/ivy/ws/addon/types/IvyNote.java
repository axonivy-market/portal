package ch.ivy.ws.addon.types;

/**
 */
@SuppressWarnings("all")
@javax.annotation.Generated(comments="This is the java file of the ivy data class IvyNote", value={"ch.ivyteam.ivy.scripting.streamInOut.IvyScriptJavaClassBuilder"})
public class IvyNote extends ch.ivyteam.ivy.scripting.objects.CompositeObject
{
  /** SerialVersionUID */
  private static final long serialVersionUID = -8974123174633243867L;

  /**
   * ID of the note
   */
  private transient java.lang.Long id;

  /**
   * Gets the field id.
   * @return the value of the field id; may be null.
   */
  public java.lang.Long getId()
  {
    return id;
  }

  /**
   * Sets the field id.
   * @param _id the new value of the field id.
   */
  public void setId(java.lang.Long _id)
  {
    id = _id;
  }

  /**
   * Message
   */
  private transient java.lang.String message;

  /**
   * Gets the field message.
   * @return the value of the field message; may be null.
   */
  public java.lang.String getMessage()
  {
    return message;
  }

  /**
   * Sets the field message.
   * @param _message the new value of the field message.
   */
  public void setMessage(java.lang.String _message)
  {
    message = _message;
  }

  /**
   * Creation timetstamp
   */
  private transient java.util.Date creationTimestamp;

  /**
   * Gets the field creationTimestamp.
   * @return the value of the field creationTimestamp; may be null.
   */
  public java.util.Date getCreationTimestamp()
  {
    return creationTimestamp;
  }

  /**
   * Sets the field creationTimestamp.
   * @param _creationTimestamp the new value of the field creationTimestamp.
   */
  public void setCreationTimestamp(java.util.Date _creationTimestamp)
  {
    creationTimestamp = _creationTimestamp;
  }

  /**
   * Creator username
   */
  private transient java.lang.String creatorUserName;

  /**
   * Gets the field creatorUserName.
   * @return the value of the field creatorUserName; may be null.
   */
  public java.lang.String getCreatorUserName()
  {
    return creatorUserName;
  }

  /**
   * Sets the field creatorUserName.
   * @param _creatorUserName the new value of the field creatorUserName.
   */
  public void setCreatorUserName(java.lang.String _creatorUserName)
  {
    creatorUserName = _creatorUserName;
  }

  /**
   * Creator fullname
   */
  private transient java.lang.String creatorFullName;

  /**
   * Gets the field creatorFullName.
   * @return the value of the field creatorFullName; may be null.
   */
  public java.lang.String getCreatorFullName()
  {
    return creatorFullName;
  }

  /**
   * Sets the field creatorFullName.
   * @param _creatorFullName the new value of the field creatorFullName.
   */
  public void setCreatorFullName(java.lang.String _creatorFullName)
  {
    creatorFullName = _creatorFullName;
  }

}
