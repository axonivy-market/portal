package com.axonivy.portal.developerexamples.service;

import java.util.Objects;

import ch.ivyteam.ivy.data.cache.IDataCache;
import ch.ivyteam.ivy.data.cache.IDataCacheEntry;
import ch.ivyteam.ivy.data.cache.IDataCacheGroup;
import ch.ivyteam.ivy.environment.Ivy;

public class IvyCacheService {

  private IvyCacheService() {}

  public static IvyCacheService newInstance() {
    return new IvyCacheService();
  }

  private void verifyIdentifier(String groupIdentifier, String identifier) {
    Objects.requireNonNull(groupIdentifier, "groupIdentifier can't be null");
    Objects.requireNonNull(identifier, "identifier can't be null");
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


  /**
   * Invalidate the data of entry in session cache entry with key is groupIdentifier and identifier
   * @param groupIdentifier
   * @param identifier
   */
  public void invalidateSessionEntry(String groupIdentifier, String identifier) {
    verifyIdentifier(groupIdentifier, identifier);
    IDataCacheGroup group = sessionCache().getGroup(groupIdentifier);
    if (group != null) {
      IDataCacheEntry entry = sessionCache().getEntry(groupIdentifier, identifier);
      if (entry != null) {
        sessionCache().invalidateEntry(group, entry);
      }
    }
  }

  private IDataCache sessionCache() {
    return Ivy.datacache().getSessionCache();
  }
}
