package com.axonivy.portal.userexamples.configuration;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.userexamples.dto.UserDTO;
import com.axonivy.portal.userexamples.service.LanguageService;

import ch.ivyteam.ivy.language.LanguageManager;
import ch.ivyteam.ivy.process.call.SubProcessCall;
import ch.ivyteam.ivy.security.ISecurityContext;
import ch.ivyteam.ivy.security.exec.Sudo;

public class UserUtils {

  public static String getUserLanguage() {
    String userLanguage = LanguageService.newInstance().getIvyLanguageOfUser().getUserLanguage();
    String systemLanguage = LanguageManager.instance().configurator(ISecurityContext.current()).content().toString();
    return StringUtils.defaultIfBlank(userLanguage, systemLanguage);
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
}
