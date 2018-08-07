package ch.ivy.ws.addon.types;

/**
 */
@SuppressWarnings("all")
@javax.annotation.Generated(comments="This is the java file of the ivy data class IvyDocument", value={"ch.ivyteam.ivy.scripting.streamInOut.IvyScriptJavaClassBuilder"})
public class IvyDocument extends ch.ivyteam.ivy.scripting.objects.CompositeObject
{
  /** SerialVersionUID */
  private static final long serialVersionUID = 8014170194703322638L;

  private transient java.lang.String contentType;

  /**
   * Gets the field contentType.
   * @return the value of the field contentType; may be null.
   */
  public java.lang.String getContentType()
  {
    return contentType;
  }

  /**
   * Sets the field contentType.
   * @param _contentType the new value of the field contentType.
   */
  public void setContentType(java.lang.String _contentType)
  {
    contentType = _contentType;
  }

  private transient ch.ivyteam.ivy.workflow.document.ModificationInfo creation;

  /**
   * Gets the field creation.
   * @return the value of the field creation; may be null.
   */
  public ch.ivyteam.ivy.workflow.document.ModificationInfo getCreation()
  {
    return creation;
  }

  /**
   * Sets the field creation.
   * @param _creation the new value of the field creation.
   */
  public void setCreation(ch.ivyteam.ivy.workflow.document.ModificationInfo _creation)
  {
    creation = _creation;
  }

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

  private transient ch.ivyteam.ivy.workflow.document.ModificationInfo lastModification;

  /**
   * Gets the field lastModification.
   * @return the value of the field lastModification; may be null.
   */
  public ch.ivyteam.ivy.workflow.document.ModificationInfo getLastModification()
  {
    return lastModification;
  }

  /**
   * Sets the field lastModification.
   * @param _lastModification the new value of the field lastModification.
   */
  public void setLastModification(ch.ivyteam.ivy.workflow.document.ModificationInfo _lastModification)
  {
    lastModification = _lastModification;
  }

  private transient java.lang.String name;

  /**
   * Gets the field name.
   * @return the value of the field name; may be null.
   */
  public java.lang.String getName()
  {
    return name;
  }

  /**
   * Sets the field name.
   * @param _name the new value of the field name.
   */
  public void setName(java.lang.String _name)
  {
    name = _name;
  }

  private transient ch.ivyteam.ivy.workflow.document.Path path;

  /**
   * Gets the field path.
   * @return the value of the field path; may be null.
   */
  public ch.ivyteam.ivy.workflow.document.Path getPath()
  {
    return path;
  }

  /**
   * Sets the field path.
   * @param _path the new value of the field path.
   */
  public void setPath(ch.ivyteam.ivy.workflow.document.Path _path)
  {
    path = _path;
  }

  private transient java.lang.Long size;

  /**
   * Gets the field size.
   * @return the value of the field size; may be null.
   */
  public java.lang.Long getSize()
  {
    return size;
  }

  /**
   * Sets the field size.
   * @param _size the new value of the field size.
   */
  public void setSize(java.lang.Long _size)
  {
    size = _size;
  }

}
