package ch.ivy.addon.portalkit.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.constant.IvyCacheIdentifier;
import ch.ivy.addon.portalkit.constant.PortalConstants;
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
   * Add or update an object to application cache based on groupIdentifier and identifier
   * @param groupIdentifier 
   * @param identifier 
   * @param value
   */
  public void setApplicationCache(String groupIdentifier, String identifier, Object value) {
    applicationCache().setEntry(groupIdentifier, identifier, value);
  }

  /**
   * Get an application cache based on groupIdentifier and identifier
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
   * Get all global settings in application cache
   * @return global settings in cache
   */
  public List<IDataCacheEntry> getAllGlobalSettingsFromCache() {
    IDataCacheGroup group = applicationCache().getGroup(IvyCacheIdentifier.GLOBAL_SETTING_CACHE_GROUP_NAME);
    if (!Objects.isNull(group) && CollectionUtils.isNotEmpty(group.getEntries())) {
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
    IDataCacheGroup groupNameCurrentApp = applicationCache().getGroup(IvyCacheIdentifier.GLOBAL_SETTING_CACHE_GROUP_NAME);
    if (groupNameCurrentApp != null) {
      Ivy.log().info("CLEAR GET APPS WS CACHE CURRENT APP");
      applicationCache().invalidateGroup(groupNameCurrentApp);
    }
    invalidateGlobalSettingOnApp(PortalConstants.PORTAL_APPLICATION_NAME);
  }

  public void invalidateCacheGroupOfAllPortalApps(String groupName) {
    List<IApplication> apps = ServerService.getInstance().getApplicationsRelatedToPortal();
    apps.stream()
        .map(app -> app.getAdapter(IDataCache.class))
        .filter(Objects::nonNull)
        .map(dataCache -> dataCache.getGroup(groupName))
        .filter(Objects::nonNull).forEach(cacheGroup -> cacheGroup.invalidateAllEntries());
    Ivy.log().info("CLEAR CACHE GROUP {0} OF ALL ALLICATIONS RELATED TO PORTAL", groupName);
  }

  /**
   * Invalidate application cache based on application name
   * @param applicationName
   */
  public void invalidateGlobalSettingOnApp(String applicationName) {
    try {
      ServerFactory.getServer().getSecurityManager().executeAsSystem(() -> {
        IApplication findApplication = ServerFactory.getServer().getApplicationConfigurationManager().findApplication(applicationName);
        if (findApplication != null) {
          IDataCache cache = findApplication .getAdapter(IDataCache.class);
          if (cache != null) {
            IDataCacheGroup wsGroupName = cache.getGroup(IvyCacheIdentifier.GLOBAL_SETTING_CACHE_GROUP_NAME);
            if (wsGroupName != null) {
              Ivy.log().info("CLEAR GLOBAL SETTING CACHE : {0} on application {1}", IvyCacheIdentifier.GLOBAL_SETTING_CACHE_GROUP_NAME, applicationName);
              wsGroupName.invalidateAllEntries();
            }
          }
        }
        return null;
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
}
