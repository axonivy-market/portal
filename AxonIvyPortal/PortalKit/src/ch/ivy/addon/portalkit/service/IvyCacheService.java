package ch.ivy.addon.portalkit.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.constant.IvyCacheIdentifier;
import ch.ivyteam.ivy.application.ActivityState;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.data.cache.IDataCache;
import ch.ivyteam.ivy.data.cache.IDataCacheEntry;
import ch.ivyteam.ivy.data.cache.IDataCacheGroup;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.server.ServerFactory;

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
    Objects.requireNonNull(groupIdentifier, "groupIdentifier cann't be null");
    Objects.requireNonNull(identifier, "identifier cann't be null");
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
    Objects.requireNonNull(groupIdentifier, "groupIdentifier cann't be null");
    Objects.requireNonNull(identifier, "identifier cann't be null");
    sessionCache().setEntry(groupIdentifier, identifier, value);
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
    Objects.requireNonNull(groupIdentifier, "groupIdentifier cann't be null");
    Objects.requireNonNull(identifier, "identifier cann't be null");
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
    applicationCache().setEntry(groupName, entryName, value);
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
  
  /**
   * Get all global settings in application cache
   * @return global settings in cache
   */
  public List<IDataCacheEntry> getAllGlobalSettingsFromCache() {
    IDataCacheGroup group = applicationCache().getGroup(IvyCacheIdentifier.GLOBAL_SETTING_CACHE_GROUP_NAME);
    if (group != null && CollectionUtils.isNotEmpty(group.getEntries())){
      return group.getEntries();
    }
    return null;
  }
  
  /**
   * Get a global setting from application cache with attributeName
   * @param attributeName
   * @return value of global setting
   */
  public Object getGlobalSettingFromCache(String attributeName) {
    return getApplicationCache(IvyCacheIdentifier.GLOBAL_SETTING_CACHE_GROUP_NAME, attributeName);
  }

  /**
   * Add or update a setting to application cache by name of setting
   * @param name
   * @param value
   */
  public void cacheGlobalSetting(String name, Object value) {
    setApplicationCache(IvyCacheIdentifier.GLOBAL_SETTING_CACHE_GROUP_NAME, name, value);
  }

  /**
   * Invalidate cache of global settings
   */
  public void invalidateGlobalSettingCache() {
    invalidateApplicationCacheByGroupName(IvyCacheIdentifier.GLOBAL_SETTING_CACHE_GROUP_NAME);
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
  public void invalidateApplicationCacheForAllAvailableApplications(String cacheGroupName) {
    try {
      ServerFactory.getServer().getSecurityManager().executeAsSystem(() -> {
        List<IApplication> ivyApplications = ServerFactory.getServer().getApplicationConfigurationManager().getApplications();
        ivyApplications.forEach(app -> {
          if(isActive(app)) {
            IDataCache cache = app.getAdapter(IDataCache.class);
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
    if (result.isPresent() && !result.isEmpty()) {
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
}
