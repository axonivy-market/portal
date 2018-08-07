package ch.ivy.add.portalkit.synchronization;

/**
 */
@SuppressWarnings("all")
@javax.annotation.Generated(comments="This is the java file of the ivy data class AddOrUpdateWSCallingData", value={"ch.ivyteam.ivy.scripting.streamInOut.IvyScriptJavaClassBuilder"})
public class AddOrUpdateWSCallingData extends ch.ivyteam.ivy.scripting.objects.CompositeObject
{
  /** SerialVersionUID */
  private static final long serialVersionUID = 3396648518468096772L;

  private ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.CustomPropertyPair> customPropertyPairs;

  /**
   * Gets the field customPropertyPairs.
   * @return the value of the field customPropertyPairs; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.CustomPropertyPair> getCustomPropertyPairs()
  {
    return customPropertyPairs;
  }

  /**
   * Sets the field customPropertyPairs.
   * @param _customPropertyPairs the new value of the field customPropertyPairs.
   */
  public void setCustomPropertyPairs(ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.CustomPropertyPair> _customPropertyPairs)
  {
    customPropertyPairs = _customPropertyPairs;
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

  private transient ch.ivy.ws.addon.ReturnedStatus returnedStatus;

  /**
   * Gets the field returnedStatus.
   * @return the value of the field returnedStatus; may be null.
   */
  public ch.ivy.ws.addon.ReturnedStatus getReturnedStatus()
  {
    return returnedStatus;
  }

  /**
   * Sets the field returnedStatus.
   * @param _returnedStatus the new value of the field returnedStatus.
   */
  public void setReturnedStatus(ch.ivy.ws.addon.ReturnedStatus _returnedStatus)
  {
    returnedStatus = _returnedStatus;
  }

  private java.lang.String serverURL;

  /**
   * Gets the field serverURL.
   * @return the value of the field serverURL; may be null.
   */
  public java.lang.String getServerURL()
  {
    return serverURL;
  }

  /**
   * Sets the field serverURL.
   * @param _serverURL the new value of the field serverURL.
   */
  public void setServerURL(java.lang.String _serverURL)
  {
    serverURL = _serverURL;
  }

  private java.lang.Integer retryTime;

  /**
   * Gets the field retryTime.
   * @return the value of the field retryTime; may be null.
   */
  public java.lang.Integer getRetryTime()
  {
    return retryTime;
  }

  /**
   * Sets the field retryTime.
   * @param _retryTime the new value of the field retryTime.
   */
  public void setRetryTime(java.lang.Integer _retryTime)
  {
    retryTime = _retryTime;
  }

  private ch.ivy.addon.portalkit.enums.WSAuthenticationType authenticationType;

  /**
   * Gets the field authenticationType.
   * @return the value of the field authenticationType; may be null.
   */
  public ch.ivy.addon.portalkit.enums.WSAuthenticationType getAuthenticationType()
  {
    return authenticationType;
  }

  /**
   * Sets the field authenticationType.
   * @param _authenticationType the new value of the field authenticationType.
   */
  public void setAuthenticationType(ch.ivy.addon.portalkit.enums.WSAuthenticationType _authenticationType)
  {
    authenticationType = _authenticationType;
  }

  private java.lang.String username;

  /**
   * Gets the field username.
   * @return the value of the field username; may be null.
   */
  public java.lang.String getUsername()
  {
    return username;
  }

  /**
   * Sets the field username.
   * @param _username the new value of the field username.
   */
  public void setUsername(java.lang.String _username)
  {
    username = _username;
  }

  private java.lang.String password;

  /**
   * Gets the field password.
   * @return the value of the field password; may be null.
   */
  public java.lang.String getPassword()
  {
    return password;
  }

  /**
   * Sets the field password.
   * @param _password the new value of the field password.
   */
  public void setPassword(java.lang.String _password)
  {
    password = _password;
  }

  private java.lang.String host;

  /**
   * Gets the field host.
   * @return the value of the field host; may be null.
   */
  public java.lang.String getHost()
  {
    return host;
  }

  /**
   * Sets the field host.
   * @param _host the new value of the field host.
   */
  public void setHost(java.lang.String _host)
  {
    host = _host;
  }

  private java.lang.String domain;

  /**
   * Gets the field domain.
   * @return the value of the field domain; may be null.
   */
  public java.lang.String getDomain()
  {
    return domain;
  }

  /**
   * Sets the field domain.
   * @param _domain the new value of the field domain.
   */
  public void setDomain(java.lang.String _domain)
  {
    domain = _domain;
  }

}
