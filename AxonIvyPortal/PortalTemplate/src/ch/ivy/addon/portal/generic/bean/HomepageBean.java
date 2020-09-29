package ch.ivy.addon.portal.generic.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ch.addon.portal.generic.userprofile.homepage.Homepage;
import ch.addon.portal.generic.userprofile.homepage.HomepageUtils;
import ch.ivy.addon.portalkit.constant.UserProperty;
import ch.ivyteam.ivy.environment.Ivy;

@ManagedBean
@ViewScoped
public class HomepageBean implements Serializable {

  private static final long serialVersionUID = -3017414322269539791L;

  private Homepage homepage;
  private ObjectMapper mapper;
  
  @PostConstruct
  public void init() {
    mapper = new ObjectMapper();
  }
  
  public void saveHomepage(Homepage homepage) throws JsonProcessingException {
    Ivy.session().getSessionUser().setProperty(UserProperty.HOMEPAGE, mapper.writeValueAsString(homepage));
  }
  
  public Homepage readHomepage() throws JsonMappingException, JsonProcessingException {
    if (homepage == null) {
      homepage = HomepageUtils.getHomepage();
    }
    return homepage;
  }
}
