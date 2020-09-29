package ch.addon.portal.generic.userprofile.homepage;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ch.ivy.addon.portalkit.constant.UserProperty;
import ch.ivyteam.ivy.environment.Ivy;

public class HomepageUtils {

  public static Homepage getHomepage() throws JsonMappingException, JsonProcessingException {
    String homepageProperty = Ivy.session().getSessionUser().getProperty(UserProperty.HOMEPAGE);
    if (StringUtils.isBlank(homepageProperty)) {
      return null;
    }
    
    ObjectMapper mapper = new ObjectMapper();
    return mapper.readValue(homepageProperty, Homepage.class);
  }
}
