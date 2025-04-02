package com.axonivy.portal.components.service;

import java.util.Objects;
import java.util.Optional;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.components.enums.PortalVariable;

import ch.ivyteam.ivy.data.cache.IDataCache;
import ch.ivyteam.ivy.data.cache.IDataCacheEntry;
import ch.ivyteam.ivy.data.cache.IDataCacheGroup;
import ch.ivyteam.ivy.environment.Ivy;

public class IvyCacheService {

  private static final int SESSION_CACHE_TIMEOUT_MAX_MINUTES = 1440; // amount of minutes in 1 day
  private static final int SESSION_CACHE_TIMEOUT_MIN_MINITES = 5;

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

  private static int getSessionCacheTimeout() {
    // If cannot find the variable, use 1 day as default timeout
    String resultStr =  Ivy.var().get(PortalVariable.SESSION_CACHE_TIMEOUT.key);
    if (StringUtils.isBlank(resultStr)) {
      return SESSION_CACHE_TIMEOUT_MAX_MINUTES;
    }

    // If cannot parse the number from variable, set its value to the default
    // value -1
    int result = NumberUtils.toInt(resultStr, NumberUtils.INTEGER_MINUS_ONE);

    // If the session timeout is equals to infinity amount (-1), return it
    // directly
    if (result == NumberUtils.FLOAT_MINUS_ONE) {
      return NumberUtils.INTEGER_MINUS_ONE;
    }

    // If timeout value in the variable is greater than 1 day, set timeout to 1
    // day
    if (result > SESSION_CACHE_TIMEOUT_MAX_MINUTES) {
      result = SESSION_CACHE_TIMEOUT_MAX_MINUTES;
    }

    // If timeout value in the variable is less than 5 minutes and different
    // from -1, set timeout to 5 minutes
    if (result < SESSION_CACHE_TIMEOUT_MIN_MINITES
        && result != NumberUtils.INTEGER_MINUS_ONE) {
      result = SESSION_CACHE_TIMEOUT_MIN_MINITES;
    }

    // return the amount of seconds
    return result * 60;
  }
}
