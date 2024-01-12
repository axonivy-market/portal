package com.axonivy.portal.userexamples.util;

import java.util.Locale;

import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.userexamples.service.LanguageService;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.IUser;
import ch.ivyteam.ivy.security.exec.Sudo;
import ch.ivyteam.ivy.workflow.IWorkflowSession;

public class UserUtils {
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

  private static IWorkflowSession getIvySession() {
    return Ivy.session();
  }
}
