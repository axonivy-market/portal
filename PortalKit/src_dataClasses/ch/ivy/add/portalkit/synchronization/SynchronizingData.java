package ch.ivy.add.portalkit.synchronization;

/**
 */
@SuppressWarnings("all")
@javax.annotation.Generated(comments="This is the java file of the ivy data class SynchronizingData", value={"ch.ivyteam.ivy.scripting.streamInOut.IvyScriptJavaClassBuilder"})
public class SynchronizingData extends ch.ivyteam.ivy.scripting.objects.CompositeObject
{
  /** SerialVersionUID */
  private static final long serialVersionUID = 1678328714461302248L;

  private transient ch.ivyteam.ivy.scripting.objects.List<ch.ivy.addon.portalkit.persistence.domain.Server> servers;

  /**
   * Gets the field servers.
   * @return the value of the field servers; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<ch.ivy.addon.portalkit.persistence.domain.Server> getServers()
  {
    return servers;
  }

  /**
   * Sets the field servers.
   * @param _servers the new value of the field servers.
   */
  public void setServers(ch.ivyteam.ivy.scripting.objects.List<ch.ivy.addon.portalkit.persistence.domain.Server> _servers)
  {
    servers = _servers;
  }

  private transient java.lang.Integer counter;

  /**
   * Gets the field counter.
   * @return the value of the field counter; may be null.
   */
  public java.lang.Integer getCounter()
  {
    return counter;
  }

  /**
   * Sets the field counter.
   * @param _counter the new value of the field counter.
   */
  public void setCounter(java.lang.Integer _counter)
  {
    counter = _counter;
  }

  private transient java.lang.String keyToBeDeleted;

  /**
   * Gets the field keyToBeDeleted.
   * @return the value of the field keyToBeDeleted; may be null.
   */
  public java.lang.String getKeyToBeDeleted()
  {
    return keyToBeDeleted;
  }

  /**
   * Sets the field keyToBeDeleted.
   * @param _keyToBeDeleted the new value of the field keyToBeDeleted.
   */
  public void setKeyToBeDeleted(java.lang.String _keyToBeDeleted)
  {
    keyToBeDeleted = _keyToBeDeleted;
  }

  private transient java.lang.String keyPrefixToBeDeleted;

  /**
   * Gets the field keyPrefixToBeDeleted.
   * @return the value of the field keyPrefixToBeDeleted; may be null.
   */
  public java.lang.String getKeyPrefixToBeDeleted()
  {
    return keyPrefixToBeDeleted;
  }

  /**
   * Sets the field keyPrefixToBeDeleted.
   * @param _keyPrefixToBeDeleted the new value of the field keyPrefixToBeDeleted.
   */
  public void setKeyPrefixToBeDeleted(java.lang.String _keyPrefixToBeDeleted)
  {
    keyPrefixToBeDeleted = _keyPrefixToBeDeleted;
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

  private transient ch.ivyteam.ivy.scripting.objects.List<java.lang.String> keysToBeDeleted;

  /**
   * Gets the field keysToBeDeleted.
   * @return the value of the field keysToBeDeleted; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<java.lang.String> getKeysToBeDeleted()
  {
    return keysToBeDeleted;
  }

  /**
   * Sets the field keysToBeDeleted.
   * @param _keysToBeDeleted the new value of the field keysToBeDeleted.
   */
  public void setKeysToBeDeleted(ch.ivyteam.ivy.scripting.objects.List<java.lang.String> _keysToBeDeleted)
  {
    keysToBeDeleted = _keysToBeDeleted;
  }

}
