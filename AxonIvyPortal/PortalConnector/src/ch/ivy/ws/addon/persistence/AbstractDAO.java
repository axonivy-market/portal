package ch.ivy.ws.addon.persistence;

public abstract class AbstractDAO {

  private static final String DEFAULT_PERSISTENCE_IDENTIFIER = "PortalIvySystemDatabase";

  public AbstractDAO() {
  }

  protected String getPersistenceIdentifier() {
    return DEFAULT_PERSISTENCE_IDENTIFIER;
  }

}
