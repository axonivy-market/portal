package com.axonivy.portal.components.service;

import java.util.Objects;
import java.util.Optional;

import ch.ivyteam.ivy.data.cache.IDataCache;
import ch.ivyteam.ivy.data.cache.IDataCacheEntry;
import ch.ivyteam.ivy.environment.Ivy;

public class IvyCacheService {

  private IvyCacheService() {}
  
  public static IvyCacheService newInstance() {
    return new IvyCacheService();
  }
  
  /**
   * Get value of a session cache based on groupIdentifier and identifier
   * Will return empty if this key doesn't exist in session or invalid any more
   * @param groupIdentifier
   * @param identifier
   * @return value of cache
   */
  public Optional<Object> getSessionCacheValue(String groupIdentifier, String identifier) {
    verifyIdentifier(groupIdentifier, identifier);

    IDataCacheEntry cacheEntry = sessionCache().getEntry(groupIdentifier, identifier);
    if (cacheEntry != null && cacheEntry.isValid()) {
      return Optional.ofNullable(cacheEntry.getValue());
    }
    return Optional.empty();
  }

  /**
   * Add or update an object to session cache with key is groupIdentifier and identifier
   * @param groupIdentifier
   * @param identifier
   * @param value
   */
  public void setSessionCache(String groupIdentifier, String identifier, Object value) {
    verifyIdentifier(groupIdentifier, identifier);
    sessionCache().setEntry(groupIdentifier, identifier, value);
  }

  private void verifyIdentifier(String groupIdentifier, String identifier) {
    Objects.requireNonNull(groupIdentifier, "groupIdentifier can't be null");
    Objects.requireNonNull(identifier, "identifier can't be null");
  }

  private IDataCache sessionCache() {
    return Ivy.datacache().getSessionCache();
  }
}
