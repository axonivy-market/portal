package ch.ivy.addon.portalkit.bean;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import ch.ivy.addon.portalkit.bo.RemoteLibrary;
import ch.ivy.addon.portalkit.datacollecting.factory.AbstractLibraryFactory;
import ch.ivy.addon.portalkit.datacollecting.factory.FactoryCreator;
import ch.ivy.addon.portalkit.datacollecting.service.AbstractLibraryService;
import ch.ivyteam.ivy.application.ILibrary;
import ch.ivyteam.ivy.environment.Ivy;

@ManagedBean
@ViewScoped
public class ProjectVersionBean {

  private final static String PORTAL_KIT_LIBRARY = "ch.ivyteam.ivy.project.portal:portalKit";
  
  private String engineVersion;
  private String portalVersion;
  private Map<String, List<RemoteLibrary>> projectLibraries;

  @SuppressWarnings("restriction")
  @PostConstruct
  private void init() {
    engineVersion = ch.ivyteam.ivy.Advisor.getAdvisor().getVersion().toString();
    ILibrary portalLibrary = Ivy.wf().getApplication().findReleasedLibrary(PORTAL_KIT_LIBRARY);
    portalVersion = portalLibrary.getQualifiedVersion().toString();
    projectLibraries = retrieveProjectLibraries();
  }

  private Map<String, List<RemoteLibrary>> retrieveProjectLibraries() {
    AbstractLibraryFactory factory = FactoryCreator.getLibraryFactory();
    AbstractLibraryService service = factory.createService();
    return service.collectLibraries();
  }

  public String getEngineVersion() {
    return engineVersion;
  }

  public String getPortalVersion() {
    return portalVersion;
  }

  public Map<String, List<RemoteLibrary>> getProjectLibraries() {
    return projectLibraries;
  }
}
