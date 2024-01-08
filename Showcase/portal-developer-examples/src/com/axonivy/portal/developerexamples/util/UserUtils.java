package com.axonivy.portal.developerexamples.util;

import java.util.Calendar;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.developerexamples.dto.UserProperty;
import com.axonivy.portal.developerexamples.service.LanguageService;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.ISecurityContext;
import ch.ivyteam.ivy.security.IUser;
import ch.ivyteam.ivy.security.exec.Sudo;
import ch.ivyteam.ivy.workflow.IWorkflowSession;

public class UserUtils {

  public static IUser findUserByUsername(String username) {
    return Sudo.get(() -> {
      return ISecurityContext.current().users().find(username);
    });
  }

  public static boolean isValidPasswordResetToken(String token, IUser user) {
    if (user != null && StringUtils.isNotBlank(token)) {
      String tokenInDb = user.getProperty(UserProperty.RESET_PASSWORD_TOKEN);
      String tokenExpiryInDb = user.getProperty(UserProperty.RESET_PASSWORD_TOKEN_EXPIRY);
      long expiryTime = StringUtils.isNotBlank(tokenExpiryInDb) ? Long.valueOf(tokenExpiryInDb) : 0l;
      long currentTime = Calendar.getInstance().getTimeInMillis();
      return token.equals(tokenInDb) && currentTime < expiryTime;
    }
    return false;
  }

  private static IWorkflowSession getIvySession() {
    return Ivy.session();
  }

  public static void setLanguage() {
    Sudo.get(() -> {
      IUser sessionUser = getIvySession().getSessionUser();
      Locale defaultContentLocale = LanguageService.newInstance().getDefaultEmailLanguage();
      Locale defaultFormattingLocale = LanguageService.newInstance().getDefaultFormattingLanguage();

      Locale contentLocale =
          sessionUser.getLanguage() != null && StringUtils.isNotBlank(sessionUser.getLanguage().toString())
              ? sessionUser.getLanguage()
              : defaultContentLocale;
      Locale formattingLocale = sessionUser.getFormattingLanguage() != null
          && StringUtils.isNotBlank(sessionUser.getFormattingLanguage().toString())
              ? sessionUser.getFormattingLanguage()
              : defaultFormattingLocale;

      getIvySession().setContentLocale(contentLocale);
      getIvySession().setFormattingLocale(formattingLocale);
      return null;
    });
  }

}
