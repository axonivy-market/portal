package ch.ivy.addon.portalkit.util;

import java.util.Locale;
import java.util.concurrent.Callable;

import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.IUser;
import ch.ivyteam.ivy.server.ServerFactory;

public class Locales {
	public Locale getCurrentLocale(){
		try {
			  return ServerFactory.getServer().getSecurityManager().executeAsSystem(new Callable<Locale>() {
				@Override
        public Locale call() throws Exception {
					IUser user = Ivy.session().getSessionUser();
					Locale emailLanguage = user.getEMailLanguage();
					if (emailLanguage != null) {
						return emailLanguage;
					} else {
						return IApplication.current().getDefaultEMailLanguage();
					}
				}
			});
			  
		} catch (Exception e) {
			Ivy.log().error(e);
		}
		return null;
	}
}
