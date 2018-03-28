package ch.ivy.addon.portalkit.bean;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import ch.ivy.addon.portalkit.bo.RemoteLibrary;
import ch.ivy.addon.portalkit.mapper.RemoteLibraryMapper;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.application.ILibrary;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.process.call.SubProcessCall;
import ch.ivyteam.ivy.server.ServerFactory;

@ManagedBean
@ViewScoped
public class ProjectVersionBean {

  private final static String PORTAL_STYLE_LIBRARY = "ch.ivyteam.ivy.project.portal:portalStyle";
  private final static String PORTAL_KIT_LIBRARY = "ch.ivyteam.ivy.project.portal:portalKit";
  private final static String PORTAL_TEMPLATE_LIBRARY = "ch.ivyteam.ivy.project.portal:portalTemplate";
  private final static String PORTAL_CONNECTOR_LIBRARY = "ch.ivyteam.ivy.project.system:portalConnector";
  private final static String SELF_SERVICE_LIBRARY = "ch.ivyteam.ivy.project.portal:selfService";
  private final static String EXPRESS_LIBRARY = "ch.ivyteam.ivy.project.portal:axonIvyExpress";
  private final static String LIBRARY_SERVICE_CALLABLE = "MultiPortal/LibraryService";

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
    List<String> portalLibraryStrings =
        Arrays.asList(PORTAL_STYLE_LIBRARY, PORTAL_KIT_LIBRARY, PORTAL_TEMPLATE_LIBRARY, PORTAL_CONNECTOR_LIBRARY,
            SELF_SERVICE_LIBRARY, EXPRESS_LIBRARY);
    if (Ivy.request().getApplication().getName().equals(IApplication.PORTAL_APPLICATION_NAME)) {
      try {
        List<RemoteLibrary> libraries = ServerFactory.getServer().getSecurityManager().executeAsSystem(new Callable<List<RemoteLibrary>>() {

          @SuppressWarnings("unchecked")
          @Override
          public List<RemoteLibrary> call() throws Exception {
            return SubProcessCall.withPath(LIBRARY_SERVICE_CALLABLE).withStartName("getLibraries")
                .withParam("username", Ivy.session().getSessionUserName()).call().get("libraries", List.class);
          }
        });
        
        return libraries.stream().collect(Collectors.groupingBy(RemoteLibrary::getApplication));
      } catch (Exception e) {
        Ivy.log().error("Can't get project versions", e);
      }
    }
    return Ivy.request().getApplication().getLibraries().stream()
        .filter(lib -> !portalLibraryStrings.contains(lib.getId())).map(RemoteLibraryMapper::mapLibrary)
        .collect(Collectors.groupingBy(RemoteLibrary::getApplication));
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
