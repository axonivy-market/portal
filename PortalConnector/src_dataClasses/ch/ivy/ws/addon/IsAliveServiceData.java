package ch.ivy.ws.addon;

/**
 */
@SuppressWarnings("all")
@javax.annotation.Generated(comments="This is the java file of the ivy data class IsAliveServiceData", value={"ch.ivyteam.ivy.scripting.streamInOut.IvyScriptJavaClassBuilder"})
public class IsAliveServiceData extends ch.ivyteam.ivy.scripting.objects.CompositeObject
{
  /** SerialVersionUID */
  private static final long serialVersionUID = 3493915495653948478L;

  private transient java.lang.Boolean isAlive;

  /**
   * Gets the field isAlive.
   * @return the value of the field isAlive; may be null.
   */
  public java.lang.Boolean getIsAlive()
  {
    return isAlive;
  }

  /**
   * Sets the field isAlive.
   * @param _isAlive the new value of the field isAlive.
   */
  public void setIsAlive(java.lang.Boolean _isAlive)
  {
    isAlive = _isAlive;
  }

  private transient ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.types.IvyApplication> applicationList;

  /**
   * Gets the field applicationList.
   * @return the value of the field applicationList; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.types.IvyApplication> getApplicationList()
  {
    return applicationList;
  }

  /**
   * Sets the field applicationList.
   * @param _applicationList the new value of the field applicationList.
   */
  public void setApplicationList(ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.types.IvyApplication> _applicationList)
  {
    applicationList = _applicationList;
  }

  private transient ch.ivyteam.ivy.scripting.objects.List<java.lang.String> apps;

  /**
   * Gets the field apps.
   * @return the value of the field apps; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<java.lang.String> getApps()
  {
    return apps;
  }

  /**
   * Sets the field apps.
   * @param _apps the new value of the field apps.
   */
  public void setApps(ch.ivyteam.ivy.scripting.objects.List<java.lang.String> _apps)
  {
    apps = _apps;
  }

  private transient ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.WSException> errors;

  /**
   * Gets the field errors.
   * @return the value of the field errors; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.WSException> getErrors()
  {
    return errors;
  }

  /**
   * Sets the field errors.
   * @param _errors the new value of the field errors.
   */
  public void setErrors(ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.WSException> _errors)
  {
    errors = _errors;
  }

}
