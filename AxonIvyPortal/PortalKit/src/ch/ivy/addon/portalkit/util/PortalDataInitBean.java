package ch.ivy.addon.portalkit.util;

import ch.ivy.addon.portalkit.service.UserService;
import ch.ivyteam.ivy.process.eventstart.AbstractProcessStartEventBean;
import ch.ivyteam.ivy.process.eventstart.IProcessStartEventBeanRuntime;

public class PortalDataInitBean extends AbstractProcessStartEventBean {
	
	public PortalDataInitBean() {
		super("PortalDataInitBean", "Preload time-consuming data of portal");
	}
	
	@Override
	public void initialize(IProcessStartEventBeanRuntime eventRuntime, String configuration) {
		super.initialize(eventRuntime, configuration);
		getEventBeanRuntime().setPollTimeInterval(0);
		loadPortalUsersToCache();
	}
	
	private void loadPortalUsersToCache() {
		UserService userService = new UserService();
		userService.findByUserName("SYSTEM"); //get any name to init cache
	}

}
