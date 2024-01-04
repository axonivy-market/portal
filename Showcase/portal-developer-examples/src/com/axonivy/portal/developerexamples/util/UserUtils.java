package com.axonivy.portal.developerexamples.util;

import java.util.Calendar;

import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.developerexamples.dto.UserProperty;

import ch.ivyteam.ivy.security.ISecurityContext;
import ch.ivyteam.ivy.security.IUser;
import ch.ivyteam.ivy.security.exec.Sudo;

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

}
