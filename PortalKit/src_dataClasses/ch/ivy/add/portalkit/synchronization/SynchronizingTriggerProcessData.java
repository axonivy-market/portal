package ch.ivy.add.portalkit.synchronization;

/**
 */
@SuppressWarnings("all")
@javax.annotation.Generated(comments="This is the java file of the ivy data class SynchronizingTriggerProcessData", value={"ch.ivyteam.ivy.scripting.streamInOut.IvyScriptJavaClassBuilder"})
public class SynchronizingTriggerProcessData extends ch.ivyteam.ivy.scripting.objects.CompositeObject
{
  /** SerialVersionUID */
  private static final long serialVersionUID = 2430600966180806380L;

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

  private transient java.lang.String key;

  /**
   * Gets the field key.
   * @return the value of the field key; may be null.
   */
  public java.lang.String getKey()
  {
    return key;
  }

  /**
   * Sets the field key.
   * @param _key the new value of the field key.
   */
  public void setKey(java.lang.String _key)
  {
    key = _key;
  }

  private transient java.lang.String keyPrefix;

  /**
   * Gets the field keyPrefix.
   * @return the value of the field keyPrefix; may be null.
   */
  public java.lang.String getKeyPrefix()
  {
    return keyPrefix;
  }

  /**
   * Sets the field keyPrefix.
   * @param _keyPrefix the new value of the field keyPrefix.
   */
  public void setKeyPrefix(java.lang.String _keyPrefix)
  {
    keyPrefix = _keyPrefix;
  }

  private transient ch.ivyteam.ivy.scripting.objects.List<java.lang.String> keys;

  /**
   * Gets the field keys.
   * @return the value of the field keys; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<java.lang.String> getKeys()
  {
    return keys;
  }

  /**
   * Sets the field keys.
   * @param _keys the new value of the field keys.
   */
  public void setKeys(ch.ivyteam.ivy.scripting.objects.List<java.lang.String> _keys)
  {
    keys = _keys;
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

}
