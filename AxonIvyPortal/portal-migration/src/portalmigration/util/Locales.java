package portalmigration.util;

import java.util.Locale;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.IUser;

public class Locales {
  public Locale getCurrentLocale(){
    return IvyExecutor.executeAsSystem(() -> {
      IUser user = Ivy.session().getSessionUser();
      Locale emailLanguage = user.getEMailLanguage();
      if (emailLanguage != null) {
        return emailLanguage;
      } else {
        return Ivy.wf().getApplication().getDefaultEMailLanguage();
      }
    });
  }
}
