package ch.ivy.ws.protaldata;

/**
 */
@SuppressWarnings("all")
@javax.annotation.Generated(comments="This is the java file of the ivy data class PortalDataServiceData", value={"ch.ivyteam.ivy.scripting.streamInOut.IvyScriptJavaClassBuilder"})
public class PortalDataServiceData extends ch.ivyteam.ivy.scripting.objects.CompositeObject
{
  /** SerialVersionUID */
  private static final long serialVersionUID = -8727589069642679294L;

  private transient ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.portaldata.model.CustomPropertyPair> customPropertyPairs;

  /**
   * Gets the field customPropertyPairs.
   * @return the value of the field customPropertyPairs; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.portaldata.model.CustomPropertyPair> getCustomPropertyPairs()
  {
    return customPropertyPairs;
  }

  /**
   * Sets the field customPropertyPairs.
   * @param _customPropertyPairs the new value of the field customPropertyPairs.
   */
  public void setCustomPropertyPairs(ch.ivyteam.ivy.scripting.objects.List<ch.ivy.ws.portaldata.model.CustomPropertyPair> _customPropertyPairs)
  {
    customPropertyPairs = _customPropertyPairs;
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

  private transient java.lang.String propertyKeyToBeDelete;

  /**
   * Gets the field propertyKeyToBeDelete.
   * @return the value of the field propertyKeyToBeDelete; may be null.
   */
  public java.lang.String getPropertyKeyToBeDelete()
  {
    return propertyKeyToBeDelete;
  }

  /**
   * Sets the field propertyKeyToBeDelete.
   * @param _propertyKeyToBeDelete the new value of the field propertyKeyToBeDelete.
   */
  public void setPropertyKeyToBeDelete(java.lang.String _propertyKeyToBeDelete)
  {
    propertyKeyToBeDelete = _propertyKeyToBeDelete;
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

  private transient ch.ivyteam.ivy.scripting.objects.List<java.lang.String> keyPrefixesToBeDeleted;

  /**
   * Gets the field keyPrefixesToBeDeleted.
   * @return the value of the field keyPrefixesToBeDeleted; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<java.lang.String> getKeyPrefixesToBeDeleted()
  {
    return keyPrefixesToBeDeleted;
  }

  /**
   * Sets the field keyPrefixesToBeDeleted.
   * @param _keyPrefixesToBeDeleted the new value of the field keyPrefixesToBeDeleted.
   */
  public void setKeyPrefixesToBeDeleted(ch.ivyteam.ivy.scripting.objects.List<java.lang.String> _keyPrefixesToBeDeleted)
  {
    keyPrefixesToBeDeleted = _keyPrefixesToBeDeleted;
  }

  private transient ch.ivyteam.ivy.scripting.objects.List<java.lang.String> propertyKeysToBeDeleted;

  /**
   * Gets the field propertyKeysToBeDeleted.
   * @return the value of the field propertyKeysToBeDeleted; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<java.lang.String> getPropertyKeysToBeDeleted()
  {
    return propertyKeysToBeDeleted;
  }

  /**
   * Sets the field propertyKeysToBeDeleted.
   * @param _propertyKeysToBeDeleted the new value of the field propertyKeysToBeDeleted.
   */
  public void setPropertyKeysToBeDeleted(ch.ivyteam.ivy.scripting.objects.List<java.lang.String> _propertyKeysToBeDeleted)
  {
    propertyKeysToBeDeleted = _propertyKeysToBeDeleted;
  }

}
