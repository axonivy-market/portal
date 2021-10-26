package ch.ivy.addon.portalkit.util;

import java.util.Locale;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.IUser;

public class Locales {
	public Locale getCurrentLocale(){
	  return IvyExecutor.executeAsSystem(() -> {
	    IUser user = Ivy.session().getSessionUser();
      Locale emailLanguage = user.getLanguage();
      if (emailLanguage != null) {
        return emailLanguage;
      } else {
        return Ivy.wf().getApplication().getDefaultEMailLanguage();
      }
	  });
	}
}
