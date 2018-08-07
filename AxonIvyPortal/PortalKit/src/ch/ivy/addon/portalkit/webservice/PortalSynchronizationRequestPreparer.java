package ch.ivy.addon.portalkit.webservice;

import ch.ivy.addon.portalkit.persistence.converter.BusinessEntityConverter;
import ch.ivy.addon.portalkit.persistence.domain.BusinessEntity;
import ch.ivy.addon.portalkit.persistence.variable.PropertyKey;
import ch.ivy.addon.portalkit.service.PortalDataService;
import ch.ivy.ws.addon.CustomPropertyPair;
import ch.ivyteam.util.Property;

/**
 * This class if provide method to prepare request data to call Synchronization
 * Portal Data web service
 */
public class PortalSynchronizationRequestPreparer {

  public CustomPropertyPair convertPortalEntityToRequestData(BusinessEntity entity) {
    String jsonValue = BusinessEntityConverter.entityToJsonValue(entity);
    return newCustomPropertyPairInstance(entity.getPropertyKey(), jsonValue);
  }

  public CustomPropertyPair convertPropertyToRequestData(Property property) {
    String key = property.getName();
    String value = property.getValue();
    return newCustomPropertyPairInstance(key, value);
  }

  public CustomPropertyPair getIncrementIdPropertyPair() {
    String key = PropertyKey.ENTITY_INCREMENT_ID_KEY;
    PortalDataService portalDataService = new PortalDataService();
    long value = portalDataService.getPortalIncrementIdValue();
    return newCustomPropertyPairInstance(key, String.valueOf(value));
  }

  private CustomPropertyPair newCustomPropertyPairInstance(String propertyKey, String jsonValue) {
    CustomPropertyPair customPropertyPair = new CustomPropertyPair();
    customPropertyPair.setKey(propertyKey);
    customPropertyPair.setValue(jsonValue);
    return customPropertyPair;
  }

}
