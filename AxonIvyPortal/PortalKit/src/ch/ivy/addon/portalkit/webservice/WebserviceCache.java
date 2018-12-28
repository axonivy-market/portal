package ch.ivy.addon.portalkit.webservice;

import ch.ivy.addon.portalkit.persistence.variable.PortalCacheConstants;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.data.cache.IDataCache;
import ch.ivyteam.ivy.data.cache.IDataCacheGroup;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.server.ServerFactory;

public final class WebserviceCache {
  private static final WebserviceCache instance = new WebserviceCache();
  
  private WebserviceCache(){}
  
  public static WebserviceCache getInstance() {
    return instance;
  }
  
  public void invalidateGetApplicationsWSCache(){
    IDataCacheGroup wsGroupNameCurrentApp = Ivy.datacache().getAppCache().getGroup(PortalCacheConstants.GET_APPLICATIONS_WS_CACHE_GROUP_NAME);
    if (wsGroupNameCurrentApp != null){
      Ivy.log().info("CLEAR GET APPS WS CACHE CURRENT APP");
      Ivy.datacache().getAppCache().invalidateGroup(wsGroupNameCurrentApp );
    }
    invalidateGetApplicationCacheOnApp(IApplication.PORTAL_APPLICATION_NAME);
  }
  
  public static void invalidateGetApplicationCacheOnApp(String applicationName) {
    try {
      ServerFactory.getServer().getSecurityManager().executeAsSystem(() ->{
        IApplication findApplication = ServerFactory.getServer().getApplicationConfigurationManager().findApplication(applicationName);
        if (findApplication != null) {
          IDataCache cache = (IDataCache) findApplication .getAdapter(IDataCache.class);
          if (cache != null) {
            IDataCacheGroup wsGroupName = cache.getGroup(PortalCacheConstants.GET_APPLICATIONS_WS_CACHE_GROUP_NAME); 
            if (wsGroupName != null){
              Ivy.log().info("CLEAR GLOBAL SETTING CACHE : {0} on application {1}", PortalCacheConstants.GET_APPLICATIONS_WS_CACHE_GROUP_NAME, applicationName);
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
}
