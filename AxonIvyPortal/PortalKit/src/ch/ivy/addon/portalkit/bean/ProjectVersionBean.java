package ch.ivy.addon.portalkit.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import ch.ivy.addon.portalkit.bo.RemoteLibrary;
import ch.ivy.addon.portalkit.datacollecting.factory.LibraryServiceFactory;
import ch.ivy.addon.portalkit.datacollecting.service.AbstractLibraryService;
import ch.ivy.addon.portalkit.enums.PortalLibrary;
import ch.ivy.addon.portalkit.util.LibraryUtils;
import ch.ivyteam.ivy.application.ILibrary;
import ch.ivyteam.ivy.environment.Ivy;

@ManagedBean
@ViewScoped
public class ProjectVersionBean implements Serializable {

  private String engineVersion;
  private String portalVersion;
  private Map<String, List<RemoteLibrary>> projectLibraries;

  @SuppressWarnings("restriction")
  @PostConstruct
  private void init() {
    engineVersion = ch.ivyteam.ivy.Advisor.getAdvisor().getVersion().toString();
    ILibrary portalLibrary = LibraryUtils.findReleasedLibrary(Ivy.wf().getApplication(), PortalLibrary.PORTAL_KIT.getValue());
    portalVersion = portalLibrary.getQualifiedVersion().toString();
    projectLibraries = retrieveProjectLibraries();
  }

  private Map<String, List<RemoteLibrary>> retrieveProjectLibraries() {
    AbstractLibraryService service = LibraryServiceFactory.getLibraryService();
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
