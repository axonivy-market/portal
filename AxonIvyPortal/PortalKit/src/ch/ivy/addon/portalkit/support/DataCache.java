package ch.ivy.addon.portalkit.support;

import java.util.List;

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
    ApplicationService service = new ApplicationService();
    final List<Application> findAllIvyApplications = service.findAllIvyApplications();
    findAllIvyApplications.forEach(application -> {
      try {
        ServerFactory.getServer().getSecurityManager().executeAsSystem(() ->{
          IApplication findApplication = ServerFactory.getServer().getApplicationConfigurationManager().findApplication(application.getName());
          IDataCache cache = (IDataCache) findApplication .getAdapter(IDataCache.class);
          IDataCacheGroup wsGroupName = cache.getGroup(GLOBAL_SETTING_GROUP);
          if (wsGroupName != null){
            Ivy.log().debug("CLEAR GLOBAL SETTING CACHE");
            Ivy.datacache().getAppCache().invalidateGroup(wsGroupName );
          }
          return null;
        });
      } catch (Exception e) {
        // TODO Auto-generated catch block
        Ivy.log().error(e);
      }
      
    });
  }
  
  public static Object getGlobalSettingValue(String attributeName){
    Object attribute = getGlobalSettingFromCache(attributeName);
    if (attribute == null){
      GlobalSettingService globalSettingSerive = new GlobalSettingService();
      String attributeValue = globalSettingSerive.findGlobalSettingValue(attributeName);
      cacheGlobalSetting(attributeName, attributeValue);
      return attributeValue;      
    }
    return attribute;
  }
}
