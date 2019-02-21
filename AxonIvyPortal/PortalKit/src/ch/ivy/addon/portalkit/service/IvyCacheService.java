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
  
  public void cacheEntry(String groupName, String entryName, Object value){
    applicationCache().setEntry(groupName, entryName, value);
  }
  
  public Object getValueFromCache(String groupName, String entryName){
    IDataCacheEntry entry = applicationCache().getEntry(groupName, entryName);
    return entry == null ? null : entry.getValue();
  }
  
  public String getValueFromCacheAsString(String groupName, String entryName){
    Object attribute = getValueFromCache(groupName, entryName);
    if (attribute != null){
      return String.valueOf(attribute);
    }
    return null;
  }
  
  public Boolean getValueFromCacheAsBoolean(String groupName, String entryName){
    Object attribute = getValueFromCache(groupName, entryName);
    if (attribute != null){
      return Boolean.valueOf((String)attribute);
    }
    return null;
  }
  
  public Object getGlobalSettingFromCache(String attributeName){
    IDataCacheEntry entry = applicationCache().getEntry(IvyCacheIdentifier.GLOBAL_SETTING_CACHE_GROUP_NAME, attributeName);
    return entry == null ? null : entry.getValue();
  }
  
  public List<IDataCacheEntry> getAllGlobalSettingsFromCache(){
    IDataCacheGroup group = applicationCache().getGroup(IvyCacheIdentifier.GLOBAL_SETTING_CACHE_GROUP_NAME);
    if (group != null && CollectionUtils.isNotEmpty(group.getEntries())){
      return group.getEntries();
    }
    return null;
  }
  
  public void cacheGlobalSetting(String name, Object value){
    applicationCache().setEntry(IvyCacheIdentifier.GLOBAL_SETTING_CACHE_GROUP_NAME, name, value);
  }
  
  public void invalidateGlobalSettingCache(){
    IDataCacheGroup wsGroupNameCurrentApp = applicationCache().getGroup(IvyCacheIdentifier.GLOBAL_SETTING_CACHE_GROUP_NAME);
    if (wsGroupNameCurrentApp != null){
      Ivy.log().info("CLEAR GET APPS WS CACHE CURRENT APP");
      applicationCache().invalidateGroup(wsGroupNameCurrentApp );
    }
    invalidateGlobalSettingOnApp(PortalConstants.PORTAL_APPLICATION_NAME);
  }

  public void invalidateGlobalSettingOnApp(String applicationName) {
    try {
      ServerFactory.getServer().getSecurityManager().executeAsSystem(() ->{
        IApplication findApplication = ServerFactory.getServer().getApplicationConfigurationManager().findApplication(applicationName);
        if (findApplication != null) {
          IDataCache cache = (IDataCache) findApplication .getAdapter(IDataCache.class);
          if (cache != null) {
            IDataCacheGroup wsGroupName = cache.getGroup(IvyCacheIdentifier.GLOBAL_SETTING_CACHE_GROUP_NAME);
            if (wsGroupName != null){
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
  
  public void cacheLogoutPage(String logoutUrl){
    sessionCache().setEntry(IvyCacheIdentifier.LOGOUT_PAGE_CACHE_GROUP_NAME, IvyCacheIdentifier.LOGOUT_PAGE_CACHE_ENTRY_NAME, logoutUrl);
  }
  
  public static String getLogoutPageFromCache(){
    IDataCacheEntry entry = Ivy.datacache().getSessionCache().getEntry(IvyCacheIdentifier.LOGOUT_PAGE_CACHE_GROUP_NAME, IvyCacheIdentifier.LOGOUT_PAGE_CACHE_ENTRY_NAME);
    return entry == null ? StringUtils.EMPTY : String.valueOf(entry.getValue());
  }
  
  public void invalidateUsersCache(String applicationName) {
    IDataCache cache = getCacheFromApplicationName(applicationName);
      if (cache == null) {
        return ;
      }
    IDataCacheGroup group = cache.getGroup(IvyCacheIdentifier.PORTAL_USERS_CACHE_GROUP_NAME);
    if (group != null) {
      Ivy.log().info("Invalidated users cache in application: " + applicationName);
      cache.invalidateGroup(group);
    }
  }
  
  private IDataCache getCacheFromApplicationName(String applicationName) {
    IApplication findApplication = ServerFactory.getServer().getApplicationConfigurationManager().findApplication(applicationName);
      if (findApplication == null) {
        return null;
      }
      return (IDataCache) findApplication.getAdapter(IDataCache.class);
  }
  
  private IDataCache sessionCache() {
    return Ivy.datacache().getSessionCache();
  }
  
  private IDataCache applicationCache() {
    return Ivy.datacache().getAppCache();
  }
}
