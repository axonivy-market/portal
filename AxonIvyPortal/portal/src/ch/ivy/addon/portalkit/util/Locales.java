package ch.ivy.addon.portalkit.util;

import java.util.Locale;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.language.LanguageConfigurator;
import ch.ivyteam.ivy.security.ISecurityContext;
import ch.ivyteam.ivy.security.IUser;

public class Locales {
	public Locale getCurrentLocale(){
	  return IvyExecutor.executeAsSystem(() -> {
	    IUser user = Ivy.session().getSessionUser();
	    Locale userLanguage = user.getLanguage();
	    if (userLanguage != null) {
	      return userLanguage;
	    }
	    return new LanguageConfigurator(ISecurityContext.current()).content();
	  });
	}
}
