package ch.ivy.ws.addon.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import ch.ivy.ws.addon.WSException;
import ch.ivy.ws.addon.bo.IsAliveServiceResult;
import ch.ivy.ws.addon.types.IvyApplication;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.server.IServer;
import ch.ivyteam.ivy.server.ServerFactory;

/**
 * Default implementation for the isAlive service
 * @author ril
 *
 */
@Deprecated
public class IsAliveServiceImpl extends AbstractService implements IIsAliveService {

	private static String SYSTEM_APP_NAME = "System";
	
	@Override
	public IsAliveServiceResult isActive(final List<String> apps) throws WSException {
		try{
			return ServerFactory.getServer().getSecurityManager().executeAsSystem(new Callable<IsAliveServiceResult>() {
				@Override
				public IsAliveServiceResult call() throws Exception {
					IsAliveServiceResult result = new IsAliveServiceResult();
					
					if (apps == null || apps.size() == 0) {
					  IServer server = ch.ivyteam.ivy.server.ServerFactory.getServer();
						
					  for(IApplication i : server.getApplicationConfigurationManager().getApplications()){
						if (i.getName().equals(SYSTEM_APP_NAME) == false && apps != null) {
					      apps.add(i.getName());
						}
					  }				
					}				
					
					List<IvyApplication> ivyApps = new ArrayList<IvyApplication>();
          if (apps != null) {
            for (String name : apps) {
              IvyApplication app = new IvyApplication();
              app = isApplicationActive(name);
              ivyApps.add(app);
            }
          }
					result.setApps(ivyApps);
					
					return result;
				}
			});
		} catch (Exception e){
			throw new WSException(10006, e);
		}
		
	}

}
