package ch.ivy.add.portalkit;

/**
 */
@SuppressWarnings("all")
@javax.annotation.Generated(comments="This is the java file of the ivy data class AbstractSynchronizingConfigurationData", value={"ch.ivyteam.ivy.scripting.streamInOut.IvyScriptJavaClassBuilder"})
public class AbstractSynchronizingConfigurationData extends ch.ivyteam.ivy.scripting.objects.CompositeObject
{
  /** SerialVersionUID */
  private static final long serialVersionUID = 8987156366030258460L;

  private transient ch.ivyteam.ivy.scripting.objects.List<ch.ivy.addon.portalkit.persistence.domain.BusinessEntity> businessEntities;

  /**
   * Gets the field businessEntities.
   * @return the value of the field businessEntities; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<ch.ivy.addon.portalkit.persistence.domain.BusinessEntity> getBusinessEntities()
  {
    return businessEntities;
  }

  /**
   * Sets the field businessEntities.
   * @param _businessEntities the new value of the field businessEntities.
   */
  public void setBusinessEntities(ch.ivyteam.ivy.scripting.objects.List<ch.ivy.addon.portalkit.persistence.domain.BusinessEntity> _businessEntities)
  {
    businessEntities = _businessEntities;
  }

  private transient ch.ivy.addon.portalkit.persistence.domain.BusinessEntity businessEntity;

  /**
   * Gets the field businessEntity.
   * @return the value of the field businessEntity; may be null.
   */
  public ch.ivy.addon.portalkit.persistence.domain.BusinessEntity getBusinessEntity()
  {
    return businessEntity;
  }

  /**
   * Sets the field businessEntity.
   * @param _businessEntity the new value of the field businessEntity.
   */
  public void setBusinessEntity(ch.ivy.addon.portalkit.persistence.domain.BusinessEntity _businessEntity)
  {
    businessEntity = _businessEntity;
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

  private transient java.lang.String propertyKeyToBeDeleted;

  /**
   * Gets the field propertyKeyToBeDeleted.
   * @return the value of the field propertyKeyToBeDeleted; may be null.
   */
  public java.lang.String getPropertyKeyToBeDeleted()
  {
    return propertyKeyToBeDeleted;
  }

  /**
   * Sets the field propertyKeyToBeDeleted.
   * @param _propertyKeyToBeDeleted the new value of the field propertyKeyToBeDeleted.
   */
  public void setPropertyKeyToBeDeleted(java.lang.String _propertyKeyToBeDeleted)
  {
    propertyKeyToBeDeleted = _propertyKeyToBeDeleted;
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
