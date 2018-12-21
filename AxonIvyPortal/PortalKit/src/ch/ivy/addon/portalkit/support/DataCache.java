package ch.ivy.addon.portalkit.support;

import ch.ivy.addon.portalkit.service.GlobalSettingService;
import ch.ivyteam.ivy.data.cache.IDataCacheEntry;
import ch.ivyteam.ivy.data.cache.IDataCacheGroup;
import ch.ivyteam.ivy.environment.Ivy;

public final class DataCache {
  public static final String GLOBAL_SETTING_GROUP = "Global Settings";
  
  public static Object getGlobalSettingFromCache(String attributeName){
    final IDataCacheEntry entry = Ivy.datacache().getSessionCache().getEntry(DataCache.GLOBAL_SETTING_GROUP, attributeName);
    return entry == null ? null : entry.getValue();
  }
  
  public static void cacheGlobalSetting(String name, Object value){
    Ivy.datacache().getSessionCache().setEntry(GLOBAL_SETTING_GROUP, name, value);
  }
  
  public static void invalidateGlobalSettingCache(){
    IDataCacheGroup wsGroupName = Ivy.datacache().getSessionCache().getGroup(GLOBAL_SETTING_GROUP);
    if (wsGroupName != null){
      Ivy.log().debug("CLEAR GLOBAL SETTING CACHE");
      Ivy.datacache().getSessionCache().invalidateGroup(wsGroupName );
    }
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
