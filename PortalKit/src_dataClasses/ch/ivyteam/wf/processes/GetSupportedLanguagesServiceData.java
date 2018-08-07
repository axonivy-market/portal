package ch.ivyteam.wf.processes;

/**
 */
@SuppressWarnings("all")
@javax.annotation.Generated(comments="This is the java file of the ivy data class GetSupportedLanguagesServiceData", value={"ch.ivyteam.ivy.scripting.streamInOut.IvyScriptJavaClassBuilder"})
public class GetSupportedLanguagesServiceData extends ch.ivyteam.ivy.scripting.objects.CompositeObject
{
  /** SerialVersionUID */
  private static final long serialVersionUID = 9086775132995243600L;

  private transient java.lang.String applicationName;

  /**
   * Gets the field applicationName.
   * @return the value of the field applicationName; may be null.
   */
  public java.lang.String getApplicationName()
  {
    return applicationName;
  }

  /**
   * Sets the field applicationName.
   * @param _applicationName the new value of the field applicationName.
   */
  public void setApplicationName(java.lang.String _applicationName)
  {
    applicationName = _applicationName;
  }

  private transient java.lang.String endpoint;

  /**
   * Gets the field endpoint.
   * @return the value of the field endpoint; may be null.
   */
  public java.lang.String getEndpoint()
  {
    return endpoint;
  }

  /**
   * Sets the field endpoint.
   * @param _endpoint the new value of the field endpoint.
   */
  public void setEndpoint(java.lang.String _endpoint)
  {
    endpoint = _endpoint;
  }

  private transient ch.ivy.addon.portalkit.persistence.domain.Server server;

  /**
   * Gets the field server.
   * @return the value of the field server; may be null.
   */
  public ch.ivy.addon.portalkit.persistence.domain.Server getServer()
  {
    return server;
  }

  /**
   * Sets the field server.
   * @param _server the new value of the field server.
   */
  public void setServer(ch.ivy.addon.portalkit.persistence.domain.Server _server)
  {
    server = _server;
  }

  private transient ch.ivyteam.ivy.scripting.objects.List<java.lang.String> supportedLanguages;

  /**
   * Gets the field supportedLanguages.
   * @return the value of the field supportedLanguages; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<java.lang.String> getSupportedLanguages()
  {
    return supportedLanguages;
  }

  /**
   * Sets the field supportedLanguages.
   * @param _supportedLanguages the new value of the field supportedLanguages.
   */
  public void setSupportedLanguages(ch.ivyteam.ivy.scripting.objects.List<java.lang.String> _supportedLanguages)
  {
    supportedLanguages = _supportedLanguages;
  }

}
