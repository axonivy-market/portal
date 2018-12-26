package ch.ivy.addon.portalkit.support;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.persistence.domain.Application;
import ch.ivy.addon.portalkit.service.ApplicationService;
import ch.ivy.addon.portalkit.service.GlobalSettingService;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.data.cache.IDataCache;
import ch.ivyteam.ivy.data.cache.IDataCacheEntry;
import ch.ivyteam.ivy.data.cache.IDataCacheGroup;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.server.ServerFactory;

public final class DataCache {
  public static final String GLOBAL_SETTING_GROUP = "Global Settings Group";
  public static final String LOGOUT_PAGE_GROUP = "Logout Page Group";
  public static final String LOGOUT_PAGE_INDENTIFIER = "Logout Page Id";
  
  public static Object getGlobalSettingFromCache(String attributeName){
    IDataCacheEntry entry = Ivy.datacache().getAppCache().getEntry(GLOBAL_SETTING_GROUP, attributeName);
    return entry == null ? null : entry.getValue();
  }
  
  public static void cacheGlobalSetting(String name, Object value){
    Ivy.datacache().getAppCache().setEntry(GLOBAL_SETTING_GROUP, name, value);
  }
  
  public static void invalidateGlobalSettingCache(){
    IDataCacheGroup wsGroupNameCurrentApp = Ivy.datacache().getAppCache().getGroup(GLOBAL_SETTING_GROUP);
    if (wsGroupNameCurrentApp != null){
      Ivy.log().info("CLEAR GET APPS WS CACHE CURRENT APP");
      Ivy.datacache().getAppCache().invalidateGroup(wsGroupNameCurrentApp );
    }
    invalidateGlobalSettingOnApp(IApplication.PORTAL_APPLICATION_NAME);
    ApplicationService service = new ApplicationService();
    List<Application> findAllIvyApplications = service.findAllIvyApplications();
    findAllIvyApplications.forEach(application -> invalidateGlobalSettingOnApp(application.getName()));
  }

  private static void invalidateGlobalSettingOnApp(String applicationName) {
    try {
      ServerFactory.getServer().getSecurityManager().executeAsSystem(() ->{
        IApplication findApplication = ServerFactory.getServer().getApplicationConfigurationManager().findApplication(applicationName);
        if (findApplication != null) {
          IDataCache cache = (IDataCache) findApplication .getAdapter(IDataCache.class);
          if (cache != null) {
            IDataCacheGroup wsGroupName = cache.getGroup(GLOBAL_SETTING_GROUP);
            if (wsGroupName != null){
              Ivy.log().info("CLEAR GLOBAL SETTING CACHE : {0} on application {1}", GLOBAL_SETTING_GROUP, applicationName);
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

  public static String getGlobalSettingValueAsString(String attributeName){
    Object attribute = getGlobalSettingFromCache(attributeName);
    if (attribute == null){
      String attributeValue = getValueFromDB(attributeName);
      cacheGlobalSetting(attributeName, attributeValue);
      return attributeValue;      
    }
    return String.valueOf(attribute);
  }
  
  public static Boolean getGlobalSettingValueAsBoolean(String attributeName){
    Object attribute = getGlobalSettingFromCache(attributeName);
    if (attribute == null){
      String attributeValue = getValueFromDB(attributeName);
      cacheGlobalSetting(attributeName, attributeValue);
      return Boolean.valueOf(attributeValue);      
    }
    return Boolean.valueOf((String)attribute);
  }

  private static String getValueFromDB(String attributeName) {
    GlobalSettingService globalSettingSerive = new GlobalSettingService();
    String attributeValue = globalSettingSerive.findGlobalSettingValue(attributeName);
    return attributeValue;
  }
  
  public static void cacheLogoutPage(String logoutUrl){
    Ivy.datacache().getSessionCache().setEntry(LOGOUT_PAGE_GROUP, LOGOUT_PAGE_INDENTIFIER, logoutUrl);
  }
  
  public static String getLogoutPageFromCache(){
    IDataCacheEntry entry = Ivy.datacache().getSessionCache().getEntry(LOGOUT_PAGE_GROUP, LOGOUT_PAGE_INDENTIFIER);
    return entry == null ? StringUtils.EMPTY : String.valueOf(entry.getValue());
  }
}
