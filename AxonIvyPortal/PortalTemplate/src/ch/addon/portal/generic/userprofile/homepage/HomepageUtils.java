package ch.addon.portal.generic.userprofile.homepage;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
import ch.ivy.addon.portalkit.constant.UserProperty;
import ch.ivy.addon.portalkit.enums.GlobalVariable;
import ch.ivy.addon.portalkit.service.GlobalSettingService;
import ch.ivyteam.ivy.environment.Ivy;

public class HomepageUtils {

  public static Homepage getHomepage() throws JsonMappingException, JsonProcessingException {
    String homepageUrl = Ivy.session().getSessionUser().getProperty(UserProperty.HOMEPAGE);
    if (StringUtils.isNotBlank(homepageUrl)) {
      ObjectMapper mapper = new ObjectMapper();
      return mapper.readValue(homepageUrl, Homepage.class);
    }
    
    homepageUrl = new GlobalSettingService().findGlobalSettingValue(GlobalVariable.DEFAULT_HOMEPAGE.toString());
    return StringUtils.isNotBlank(homepageUrl) ? defaultHomepage(homepageUrl) : null;
  }

  private static Homepage defaultHomepage(String homepageUrl) {
    Homepage homepage = new Homepage();
    homepage.setLink(homepageUrl);
    if (StringUtils.equals(homepageUrl, PortalNavigator.getPortalStartUrl())) {
      homepage.setType(HomepageType.DASHBOARD);
    } else {
      homepage.setType(HomepageType.CUSTOM);
    }
    return homepage;
  }
}
