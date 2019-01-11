package ch.ivy.addon.portalkit.ivydata.dao;

public abstract class AbstractDao {

  private static final String DEFAULT_PERSISTENCE_IDENTIFIER = "PortalIvySystemDatabase";

  public AbstractDao() {
  }

  protected String getPersistenceIdentifier() {
    return DEFAULT_PERSISTENCE_IDENTIFIER;
  }

}
