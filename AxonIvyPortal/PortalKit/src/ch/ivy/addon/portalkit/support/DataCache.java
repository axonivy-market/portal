package ch.ivy.addon.portalkit.support;

import ch.ivyteam.ivy.data.cache.IDataCacheEntry;
import ch.ivyteam.ivy.data.cache.IDataCacheGroup;
import ch.ivyteam.ivy.environment.Ivy;

public final class DataCache {
  public static final String GLOBAL_SETTING_GROUP = "Global Settings";
  
  public static Object getGlobalSetting(String name){
    final IDataCacheEntry entry = Ivy.datacache().getEnvironmentCache().getEntry(DataCache.GLOBAL_SETTING_GROUP, name);
    return entry == null ? null : entry.getValue();
  }
  
  public static void cacheGlobalSetting(String name, Object value){
    Ivy.datacache().getEnvironmentCache().setEntry(GLOBAL_SETTING_GROUP, name, value);
  }
  
  public static void invalidateGlobalSettingCache(){
    IDataCacheGroup wsGroupName = Ivy.datacache().getEnvironmentCache().getGroup(GLOBAL_SETTING_GROUP);
    if (wsGroupName != null){
      Ivy.log().debug("CLEAR GLOBAL SETTING CACHE");
      Ivy.datacache().getAppCache().invalidateGroup(wsGroupName );
    }
  }
}
