package ch.ivy.addon.portalkit.service;

import java.util.Objects;
import java.util.Optional;

import ch.ivyteam.ivy.data.cache.IDataCache;
import ch.ivyteam.ivy.data.cache.IDataCacheEntry;
import ch.ivyteam.ivy.data.cache.IDataCacheGroup;
import ch.ivyteam.ivy.environment.Ivy;

public class IvyCacheService {

  private IvyCacheService() {}
  
  public static IvyCacheService newInstance() {
    return new IvyCacheService();
  }
  
  public Optional<Object> getSessionCacheValue(String groupIdentifier, String identifier) {
    IDataCacheEntry cacheEntry = getSessionCache(groupIdentifier, identifier);
    if (cacheEntry != null && cacheEntry.isValid()) {
      return Optional.ofNullable(cacheEntry.getValue());
    }
    return Optional.empty();
  }
  
  public IDataCacheEntry getSessionCache(String groupIdentifier, String identifier) {
    Objects.requireNonNull(groupIdentifier, "groupIdentifier shouldn't be null");
    Objects.requireNonNull(identifier, "identifier shouldn't be null");
    return sessionCache().getEntry(groupIdentifier, identifier);
  }
  
  public void setSessionCache(String groupIdentifier, String identifier, Object value) {
    Objects.requireNonNull(groupIdentifier, "groupIdentifier shouldn't be null");
    Objects.requireNonNull(identifier, "identifier shouldn't be null");
    sessionCache().setEntry(groupIdentifier, identifier, value);
  }
  
  public void invalidateGroup(String groupIdentifier) {
    IDataCacheGroup group = sessionCache().getGroup(groupIdentifier);
    if (group != null) {
      sessionCache().invalidateGroup(group);
    }
  }
  
  private IDataCache sessionCache() {
    return Ivy.datacache().getSessionCache();
  }
}
