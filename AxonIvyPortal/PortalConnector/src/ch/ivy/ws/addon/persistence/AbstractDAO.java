package ch.ivy.ws.addon.persistence;

import javax.persistence.EntityManager;

import ch.ivyteam.ivy.environment.Ivy;


public abstract class AbstractDAO {

  private static final String DEFAULT_PERSISTENCE_IDENTIFIER = "PortalIvySystemDatabase";

  public AbstractDAO() {
  }

  protected String getPersistenceIdentifier() {
    return DEFAULT_PERSISTENCE_IDENTIFIER;
  }

  /**
   * Get Entity Manager
   * @return singleton EntityManager
   */
  protected EntityManager getEM() {
    return Ivy.persistence().get(getPersistenceIdentifier()).createEntityManager();
  }

}
