package ch.ivy.add.portalkit;

/**
 */
@SuppressWarnings("all")
@javax.annotation.Generated(comments="This is the java file of the ivy data class SynchronizeServerConfigurationData", value={"ch.ivyteam.ivy.scripting.streamInOut.IvyScriptJavaClassBuilder"})
public class SynchronizeServerConfigurationData extends ch.ivyteam.ivy.scripting.objects.CompositeObject
{
  /** SerialVersionUID */
  private static final long serialVersionUID = 411289643734797178L;

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

  private transient ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.addon.CustomPropertyPair> customPropertyPairs;

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

}
