package ch.ivy.addon.portalkit.webservice;

import java.util.List;

import ch.ivy.addon.portalkit.persistence.domain.Application;
import ch.ivy.addon.portalkit.service.ApplicationService;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.data.cache.IDataCache;
import ch.ivyteam.ivy.data.cache.IDataCacheGroup;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.server.ServerFactory;

public final class WebserviceCache {
  public static final String GET_APPLICATIONS_WS_GROUP_NAME = "getApplications";
  public static final Integer WS_CACHE_LIFETIME = 3600;  
  private static final WebserviceCache instance = new WebserviceCache();
  
  private WebserviceCache(){}
  
  public static WebserviceCache getInstance() {
    return instance;
  }
  
  public void invalidateGetApplicationsWSCache(){
    IDataCacheGroup wsGroupNameCurrentApp = Ivy.datacache().getAppCache().getGroup(GET_APPLICATIONS_WS_GROUP_NAME);
    if (wsGroupNameCurrentApp != null){
      Ivy.log().info("CLEAR GET APPS WS CACHE CURRENT APP");
      Ivy.datacache().getAppCache().invalidateGroup(wsGroupNameCurrentApp );
    }
    ApplicationService service = new ApplicationService();
    List<Application> findAllIvyApplications = service.findAllIvyApplications();
    findAllIvyApplications.forEach(application -> {
      try {
        ServerFactory.getServer().getSecurityManager().executeAsSystem(() ->{
          IApplication findApplication = ServerFactory.getServer().getApplicationConfigurationManager().findApplication(application.getName());
          IDataCache cache = (IDataCache) findApplication .getAdapter(IDataCache.class);
          IDataCacheGroup wsGroupName = cache.getGroup(GET_APPLICATIONS_WS_GROUP_NAME);
          if (wsGroupName != null){
            Ivy.log().info("CLEAR GLOBAL SETTING CACHE : {0} for application: {1}", GET_APPLICATIONS_WS_GROUP_NAME, application.getName());
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
}
