package com.axonivy.portal.components.service;

import java.util.Objects;
import java.util.Optional;

import org.apache.commons.lang.math.NumberUtils;

import com.axonivy.portal.components.enums.PortalVariable;

import ch.ivyteam.ivy.data.cache.IDataCache;
import ch.ivyteam.ivy.data.cache.IDataCacheEntry;
import ch.ivyteam.ivy.data.cache.IDataCacheGroup;
import ch.ivyteam.ivy.environment.Ivy;

public class IvyCacheService {

  public static final int MAX_TIMEOUT = 86400; // amount of seconds in 1 day
  private static final int MIN_TIMEOUT = 300; // amount of seconds in 5 minutes

  private IvyCacheService() {}
  
  public static IvyCacheService getInstance() {
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

  /**
   * Add or update an object to session cache with key is groupIdentifier and identifier
   * @param groupIdentifier
   * @param identifier
   * @param value
   */
  public void setSessionCache(String groupIdentifier, String identifier, Object value) {
    verifyIdentifier(groupIdentifier, identifier);
    sessionCache().setEntry(groupIdentifier, identifier,
        getSessionCacheTimeout(), value);
  }

  private void verifyIdentifier(String groupIdentifier, String identifier) {
    Objects.requireNonNull(groupIdentifier, "groupIdentifier can't be null");
    Objects.requireNonNull(identifier, "identifier can't be null");
  }

  private IDataCache sessionCache() {
    return Ivy.datacache().getSessionCache();
  }

  public int getSessionCacheTimeout() {
    // If cannot parse the number from variable, set its value to the default value
    int result = NumberUtils.toInt(
        Ivy.var().get(PortalVariable.SESSION_CACHE_TIMEOUT.key), MAX_TIMEOUT);

    // If the session timeout is equals to infinity amount (-1), return it directly
    if (result == NumberUtils.INTEGER_MINUS_ONE) {
      return result;
    }

    // If timeout value in the variable is default max value or less than
    // default min value, return the default value
    if (result > MAX_TIMEOUT || result < MIN_TIMEOUT) {
      return MAX_TIMEOUT;
    }

    return result;
  }
}
