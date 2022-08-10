package ch.ivy.addon.portalkit.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import ch.ivy.addon.portalkit.enums.PortalLibrary;
import ch.ivy.addon.portalkit.ivydata.factory.LibraryServiceFactory;
import ch.ivy.addon.portalkit.ivydata.service.ILibraryService;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.application.ILibrary;
import ch.ivyteam.ivy.application.ReleaseState;
import ch.ivyteam.ivy.environment.Ivy;

@ManagedBean
@ViewScoped
public class ProjectVersionBean implements Serializable {

  private static final long serialVersionUID = -2148042793400166168L;
  private String engineVersion;
  private String portalVersion;
  private Map<String, List<ILibrary>> projectLibraries;

  private Map<String, List<ILibrary>> retrieveProjectLibraries() {
    ILibraryService service = LibraryServiceFactory.getLibraryService();
    return service.collectLibraries();
  }

  public String getEngineVersion() {
    return engineVersion;
  }

  public String getPortalVersion() {
    return portalVersion;
  }

  public Map<String, List<ILibrary>> getProjectLibraries() {
    return projectLibraries;
  }
  
  @SuppressWarnings("restriction")
  public void loadProjectVesion() {
    engineVersion = ch.ivyteam.ivy.Advisor.getAdvisor().getVersion().toString();
    ILibrary portalLibrary = IApplication.current().findReleasedLibrary(PortalLibrary.PORTAL.getValue());
    portalVersion = portalLibrary.getQualifiedVersion().toString();
    projectLibraries = retrieveProjectLibraries();
  }
  
  public String translateReleaseState(ReleaseState state) {
    return Ivy.cms().co(String.format("/ch.ivy.addon.portalkit.ui.jsf/Enums/ReleaseState/%s", state.toString()));
  }
}
