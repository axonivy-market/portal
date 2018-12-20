package ch.ivy.addon.portalkit.webservice;

import ch.ivyteam.ivy.data.cache.IDataCacheGroup;
import ch.ivyteam.ivy.environment.Ivy;

public final class WebserviceCache {
  public static final String GET_APPLICATIONS_WS_GROUP_NAME = "getApplications";
  public static final Integer WS_CACHE_LIFETIME = 3600;  
  private static final WebserviceCache instance = new WebserviceCache();
  
  private WebserviceCache(){}
  
  public static WebserviceCache getInstance() {
    return instance;
  }
  
  public void invalidateGetApplicationsWSCache(){
    IDataCacheGroup wsGroupName = Ivy.datacache().getEnvironmentCache().getGroup(GET_APPLICATIONS_WS_GROUP_NAME);
    if (wsGroupName != null){
      Ivy.log().debug("CLEAR GET APPS WS CACHE");
      Ivy.datacache().getAppCache().invalidateGroup(wsGroupName );
    }
  }
}
