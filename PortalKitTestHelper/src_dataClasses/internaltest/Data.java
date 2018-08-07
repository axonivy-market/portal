package internaltest;

/**
 */
@SuppressWarnings("all")
@javax.annotation.Generated(comments="This is the java file of the ivy data class Data", value={"ch.ivyteam.ivy.scripting.streamInOut.IvyScriptJavaClassBuilder"})
public class Data extends ch.ivyteam.ivy.scripting.objects.CompositeObject
{
  /** SerialVersionUID */
  private static final long serialVersionUID = -3846462973447918019L;

  private ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.IvyCase> cases;

  /**
   * Gets the field cases.
   * @return the value of the field cases; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.IvyCase> getCases()
  {
    return cases;
  }

  /**
   * Sets the field cases.
   * @param _cases the new value of the field cases.
   */
  public void setCases(ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.IvyCase> _cases)
  {
    cases = _cases;
  }

  private ch.ivyteam.ivy.scripting.objects.List<java.lang.String> apps;

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

  private ch.ivy.ws.addon.WsException error;

  /**
   * Gets the field error.
   * @return the value of the field error; may be null.
   */
  public ch.ivy.ws.addon.WsException getError()
  {
    return error;
  }

  /**
   * Sets the field error.
   * @param _error the new value of the field error.
   */
  public void setError(ch.ivy.ws.addon.WsException _error)
  {
    error = _error;
  }

}
