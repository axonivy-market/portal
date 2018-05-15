package ch.ivy.ws.portaldata.service;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;

import ch.ivy.ws.addon.PortalException;
import ch.ivy.ws.addon.util.PasswordUtils;
import ch.ivy.ws.portaldata.model.CustomPropertyPair;
import ch.ivyteam.ivy.application.property.ICustomProperties;
import ch.ivyteam.ivy.application.property.ICustomProperty;
import ch.ivyteam.ivy.environment.EnvironmentNotAvailableException;
import ch.ivyteam.ivy.persistence.PersistencyException;
import ch.ivyteam.ivy.security.ISecurityManager;
import ch.ivyteam.ivy.security.SecurityManagerFactory;

/**
 * Class uses {@link ICustomProperties} to add, update, delete portal data to {@link ICustomProperty} in PortalConnector
 * Application
 */
public class PortalDataService {

  public void addOrUpdate(List<CustomPropertyPair> customPropertyPairs) throws PersistencyException, EnvironmentNotAvailableException {// NOSONAR
    executeCallableAsSystemManager(new AddOrUpdateCustomPropertiesCommand(customPropertyPairs));
  }

  public void delete(String key) throws PersistencyException, EnvironmentNotAvailableException {// NOSONAR
    executeCallableAsSystemManager(new DeleteCustomPropertiesCommand(key));
  }

  public void delete(List<String> keys) throws PersistencyException, EnvironmentNotAvailableException {// NOSONAR
    executeCallableAsSystemManager(new DeleteCustomPropertiesCommand(keys));
  }

  public void deleteByPrefix(String keyPrefix) throws PersistencyException, EnvironmentNotAvailableException {// NOSONAR
    executeCallableAsSystemManager(new DeleteCustomPropertiesByPrefixCommand(keyPrefix));
  }

  private static class AddOrUpdateCustomPropertiesCommand implements Callable<Void> {

    private List<CustomPropertyPair> customPropertyPairs;

    public AddOrUpdateCustomPropertiesCommand(List<CustomPropertyPair> customPropertyPairs) {
      this.customPropertyPairs = customPropertyPairs;
    }

    @Override
    public Void call() throws Exception {
      ICustomProperties customProperties = IvyService.getApplication().customProperties();
      for (CustomPropertyPair propertyPair : customPropertyPairs) {
        String propertyKey = propertyPair.getKey();
        String propertyValue = propertyPair.getValue();
        if (PasswordUtils.isKeyOfServer(propertyKey)) {
          PasswordUtils.createPassword(propertyValue);
          propertyValue = PasswordUtils.removePasswordInformation(propertyValue);
        }
        customProperties.property(propertyKey).setValue(propertyValue);
      }
      return null;
    }
  }

  private static class DeleteCustomPropertiesCommand implements Callable<Void> {

    private List<String> keysToBeDeleted;

    public DeleteCustomPropertiesCommand(String keyToBeDelete) {
      this.keysToBeDeleted = Arrays.asList(keyToBeDelete);
    }

    public DeleteCustomPropertiesCommand(List<String> keysToBeDelete) {
      this.keysToBeDeleted = keysToBeDelete;
    }

    @Override
    public Void call() throws Exception {
      for (String keyToBeDeleted : keysToBeDeleted) {
        ICustomProperties customProperties = IvyService.getApplication().customProperties();
        if (PasswordUtils.isKeyOfServer(keyToBeDeleted)) {
          PasswordUtils.deletePasswordRelatedTo(keyToBeDeleted);
        }
        customProperties.delete(keyToBeDeleted);
      }
      return null;
    }

  }

  private static class DeleteCustomPropertiesByPrefixCommand implements Callable<Void> {

    private String keyPrefixToBeDeleted;

    public DeleteCustomPropertiesByPrefixCommand(String keyPrefixToBeDelete) {
      this.keyPrefixToBeDeleted = keyPrefixToBeDelete;
    }

    @Override
    public Void call() throws Exception {
      ICustomProperties iCustomProperties = IvyService.getApplication().customProperties();
      List<ICustomProperty> customProperties = iCustomProperties.findAllStartingWith(keyPrefixToBeDeleted);
      for (ICustomProperty customProperty : customProperties) {
        if (PasswordUtils.isKeyOfServer(customProperty.getName())) {
          PasswordUtils.deletePasswordRelatedTo(customProperty.getName());
        }
        iCustomProperties.delete(customProperty.getName());
      }
      return null;
    }
  }

  private void executeCallableAsSystemManager(Callable<?> callable) {
    ISecurityManager securityManager = SecurityManagerFactory.getSecurityManager();
    try {
      securityManager.executeAsSystem(callable);
    } catch (Exception ex) {
      throw new PortalException(ex);
    }
  }

}
