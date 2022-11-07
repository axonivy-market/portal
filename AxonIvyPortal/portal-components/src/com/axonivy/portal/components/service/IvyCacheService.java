package com.axonivy.portal.components.service;

import java.util.Objects;
import java.util.Optional;

import com.axonivy.portal.components.constant.IvyCacheIdentifier;

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

  /**
   * Get an application cache based on groupIdentifier and identifier
   * 
   * @param groupIdentifier
   * @param identifier
   * @return setting
   */
  public Object getApplicationCache(String groupIdentifier, String identifier) {
    IDataCacheEntry entry = applicationCache().getEntry(groupIdentifier, identifier);
    if (entry != null && entry.isValid()) {
      return entry.getValue();
    }
    return null;
  }

  /**
   * Get a global setting from application cache with attributeName
   * 
   * @param attributeName
   * @return value of global setting
   */
  public Object getGlobalSettingFromCache(String attributeName) {
    return getApplicationCache(IvyCacheIdentifier.GLOBAL_SETTING_CACHE_GROUP_NAME, attributeName);
  }

  private IDataCache sessionCache() {
    return Ivy.datacache().getSessionCache();
  }

  private IDataCache applicationCache() {
    return Ivy.datacache().getAppCache();
  }
}
