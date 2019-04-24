package ch.ivy.addon.portalkit.service;


import ch.ivy.addon.portalkit.bo.PortalProperty;

public class PortalPropertyService extends BusinessDataService<PortalProperty> {
  private static final String FIElD_KEY = "key";
  private static PortalPropertyService INSTANCE;

  private PortalPropertyService() {}

  public static final PortalPropertyService getInstance() {
    if (INSTANCE == null) {
      INSTANCE = new PortalPropertyService();
    }
    return INSTANCE;
  }

  public void updateFirstPropertyByKey(String key, String value) {
    PortalProperty property = repo().search(getType()).textField(FIElD_KEY).isEqualToIgnoringCase(key).execute().getFirst();
    if (property == null) {
      property = new PortalProperty(key, value);
    } else {
      property.setValue(value);
    }
    save(property);
  }

  public PortalProperty findFirstByKey(String key) {
    return repo().search(getType()).textField(FIElD_KEY).isEqualToIgnoringCase(key).execute().getFirst();
  }
  
  @Override
  public Class<PortalProperty> getType() {
    return PortalProperty.class;
  }
}
