package ch.ivy.addon.portalkit.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.constant.IvyCacheIdentifier;
import ch.ivy.addon.portalkit.enums.PortalVariable;
import ch.ivyteam.ivy.application.ActivityState;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.application.app.IApplicationRepository;
import ch.ivyteam.ivy.data.cache.IDataCache;
import ch.ivyteam.ivy.data.cache.IDataCacheEntry;
import ch.ivyteam.ivy.data.cache.IDataCacheGroup;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.exec.Sudo;

public class IvyCacheService {
  
  private static IvyCacheService instance;

  private static final int SESSION_CACHE_TIMEOUT_MAX_MINUTES = 1440; // amount of minutes in 1 day
  private static final int SESSION_CACHE_TIMEOUT_MIN_MINITES = 5;
  private IvyCacheService() {}

  public static IvyCacheService getInstance() {
    if (instance == null) {
      synchronized (IvyCacheService.class) {
        if (instance == null) {
          instance = new IvyCacheService();
        }
      }
    }
    return instance;
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
    sessionCache().setEntry(groupIdentifier, identifier,
        getSessionCacheTimeout(), value);
  }

  /**
   * Invalidate a group in session with the groupIdentifier
   * @param groupIdentifier
   */
  public void invalidateSessionGroup(String groupIdentifier) {
    IDataCacheGroup group = sessionCache().getGroup(groupIdentifier);
    if (group != null) {
      sessionCache().invalidateGroup(group);
    }
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
   * Add or update an object to application cache based on groupName and entryName
   * @param groupName
   * @param entryName
   * @param value
   */
  public void setApplicationCache(String groupName, String entryName, Object value) {
    applicationCache().setEntry(groupName, entryName,
        SESSION_CACHE_TIMEOUT_MAX_MINUTES, value);
  }

  /**
   * Get an application cache based on groupName and entryName
   * @param groupName
   * @param entryName
   * @return value
   */
  public Object getApplicationCache(String groupName, String entryName) {
    IDataCacheEntry entry = applicationCache().getEntry(groupName, entryName);
    if (entry != null && entry.isValid()) {
      return entry.getValue();
    }
    return null;
  }

  public void invalidateApplicationCacheByGroupName(String groupName) {
    IDataCacheGroup cacheGroup = applicationCache().getGroup(groupName);
    if(cacheGroup != null) {
      applicationCache().invalidateGroup(cacheGroup);
      Ivy.log().info("CLEAR APPLICATION CACHE - GROUP {0}", groupName);
    }
  }

  /**
   * This method will invalidate global configuration cache store in application cache
   * @param cacheGroupName
   */
  //TODO: after switch to application scope for all configuration, we don't need this method, just clear cache on current app
  @SuppressWarnings("restriction")
  public void invalidateApplicationCacheForAllAvailableApplications(String cacheGroupName) {
    try {
      Sudo.call(() -> {
        var cacheMgr = ch.ivyteam.ivy.data.cache.restricted.IDataCacheManager.instance();
        List<IApplication> ivyApplications = IApplicationRepository.instance().all();
        ivyApplications.forEach(app -> {
          if(isActive(app)) {
            IDataCache cache = cacheMgr.getDataCache(app);
            if (cache != null) {
              IDataCacheGroup wsGroupName = cache.getGroup(cacheGroupName);
              if (wsGroupName != null){
                Ivy.log().info("CLEAR APPLICATION CACHE - GROUP {0} ON APPLICATION {1}", cacheGroupName, app.getName());
                wsGroupName.invalidateAllEntries();
              }
            }
          }
        });
        return Void.class;
      });
    } catch (Exception e) {
      Ivy.log().error(e);
    }
  }

  public void cacheLogoutPage(String logoutUrl) {
    setSessionCache(IvyCacheIdentifier.LOGOUT_PAGE_CACHE_GROUP_NAME, IvyCacheIdentifier.LOGOUT_PAGE_CACHE_ENTRY_NAME, logoutUrl);
  }

  public String getLogoutPageFromCache() {
    Optional<Object> result = getSessionCacheValue(IvyCacheIdentifier.LOGOUT_PAGE_CACHE_GROUP_NAME, IvyCacheIdentifier.LOGOUT_PAGE_CACHE_ENTRY_NAME);
    if (!result.isEmpty()) {
      return String.valueOf(result.get());
    }

    return StringUtils.EMPTY;
  }

  private IDataCache sessionCache() {
    return Ivy.datacache().getSessionCache();
  }

  private IDataCache applicationCache() {
    return Ivy.datacache().getAppCache();
  }

  private boolean isActive(IApplication ivyApplication) {
    return ivyApplication.getActivityState() == ActivityState.ACTIVE;
  }

  private static int getSessionCacheTimeout() {
    // If cannot parse the number from variable, set its value to the default
    // value
    int result = NumberUtils.toInt(
        Ivy.var().get(PortalVariable.SESSION_CACHE_TIMEOUT.key),
        SESSION_CACHE_TIMEOUT_MAX_MINUTES);

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

  /**
   * Invalidate all session cache entries of Portal
   * 
   */
  public void invalidatePortalSessionCacheEntries() {
    IvyCacheIdentifier.getAllPortalCacheIdentifiers()
        .forEach(entry -> invalidateSessionGroup(entry));
  }
}