package com.axonivy.portal.developerexamples.util;

import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.developerexamples.configuration.PortalConstants;
import com.axonivy.portal.developerexamples.dto.UserDTO;
import com.axonivy.portal.developerexamples.dto.UserProperty;
import com.axonivy.portal.developerexamples.service.LanguageService;

import ch.ivyteam.ivy.language.LanguageManager;
import ch.ivyteam.ivy.process.call.SubProcessCall;
import ch.ivyteam.ivy.security.ISecurityContext;
import ch.ivyteam.ivy.security.IUser;
import ch.ivyteam.ivy.security.exec.Sudo;

public class UserUtils {

  public static String getUserLanguage() {
    String userLanguage = LanguageService.newInstance().getIvyLanguageOfUser().getUserLanguage();
    String systemLanguage = LanguageManager.instance().configurator(ISecurityContext.current()).content().toString();
    return StringUtils.defaultIfBlank(userLanguage, systemLanguage);
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
  @SuppressWarnings("unchecked")
  public static List<UserDTO> findUsers(String query, int startIndex, int count, List<String> fromRoles,
      List<String> excludedUsernames) {
    return Sudo.get(() -> {
      return SubProcessCall.withPath(PortalConstants.SECURITY_SERVICE_CALLABLE).withStartName("findUsers")
          .withParam("query", query).withParam("startIndex", startIndex).withParam("count", count)
          .withParam("fromRoles", fromRoles).withParam("excludedUsernames", excludedUsernames).call()
          .get("users", List.class);
    });
  }

  public static IUser findUserByUsername(String username) {
    return Sudo.get(() -> {
      return ISecurityContext.current().users().find(username);
    });
  }

}
