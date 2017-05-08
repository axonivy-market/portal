package gawfs;

/**
 */
@SuppressWarnings("all")
@javax.annotation.Generated(comments="This is the java file of the ivy data class Workflow", value={"ch.ivyteam.ivy.scripting.streamInOut.IvyScriptJavaClassBuilder"})
@javax.persistence.Entity
@javax.persistence.Table(name="processRepository")
public class Workflow extends ch.ivyteam.ivy.scripting.objects.CompositeObject
{
  /** SerialVersionUID */
  private static final long serialVersionUID = 1479855008896716361L;

  /**
   * Identifier
   */
  @javax.persistence.Id
  @javax.persistence.GeneratedValue
  private java.lang.Integer id;

  /**
   * Gets the field id.
   * @return the value of the field id; may be null.
   */
  public java.lang.Integer getId()
  {
    return id;
  }

  /**
   * Sets the field id.
   * @param _id the new value of the field id.
   */
  public void setId(java.lang.Integer _id)
  {
    id = _id;
  }

  private java.lang.String processName;

  /**
   * Gets the field processName.
   * @return the value of the field processName; may be null.
   */
  public java.lang.String getProcessName()
  {
    return processName;
  }

  /**
   * Sets the field processName.
   * @param _processName the new value of the field processName.
   */
  public void setProcessName(java.lang.String _processName)
  {
    processName = _processName;
  }

  private java.lang.String processDescription;

  /**
   * Gets the field processDescription.
   * @return the value of the field processDescription; may be null.
   */
  public java.lang.String getProcessDescription()
  {
    return processDescription;
  }

  /**
   * Sets the field processDescription.
   * @param _processDescription the new value of the field processDescription.
   */
  public void setProcessDescription(java.lang.String _processDescription)
  {
    processDescription = _processDescription;
  }

  private java.lang.String processType;

  /**
   * Gets the field processType.
   * @return the value of the field processType; may be null.
   */
  public java.lang.String getProcessType()
  {
    return processType;
  }

  /**
   * Sets the field processType.
   * @param _processType the new value of the field processType.
   */
  public void setProcessType(java.lang.String _processType)
  {
    processType = _processType;
  }

  private java.lang.String processPermission;

  /**
   * Gets the field processPermission.
   * @return the value of the field processPermission; may be null.
   */
  public java.lang.String getProcessPermission()
  {
    return processPermission;
  }

  /**
   * Sets the field processPermission.
   * @param _processPermission the new value of the field processPermission.
   */
  public void setProcessPermission(java.lang.String _processPermission)
  {
    processPermission = _processPermission;
  }

  private java.lang.String processOwner;

  /**
   * Gets the field processOwner.
   * @return the value of the field processOwner; may be null.
   */
  public java.lang.String getProcessOwner()
  {
    return processOwner;
  }

  /**
   * Sets the field processOwner.
   * @param _processOwner the new value of the field processOwner.
   */
  public void setProcessOwner(java.lang.String _processOwner)
  {
    processOwner = _processOwner;
  }

}
