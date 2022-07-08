package com.axonivy.portal.component.persistence.variable;

public final class PropertyKey {
  public static final String PORTAL_PROPERTY_START = "AxonIvyPortal";
  public static final String ENTITY_PROPERTY_KEY_PREFIX = PORTAL_PROPERTY_START + ".%s";
  public static final String ENTITY_PROPERTY_KEY = ENTITY_PROPERTY_KEY_PREFIX + ".%d";
  public static final String ENTITY_INCREMENT_ID_KEY = PORTAL_PROPERTY_START + ".IncrementId";
  
  private PropertyKey() {}
  
}
