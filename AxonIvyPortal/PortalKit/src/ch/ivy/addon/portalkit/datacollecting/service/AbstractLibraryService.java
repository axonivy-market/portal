package ch.ivy.addon.portalkit.datacollecting.service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import ch.ivy.addon.portalkit.bo.RemoteLibrary;

public abstract class AbstractLibraryService {

  protected final static String PORTAL_STYLE_LIBRARY = "ch.ivyteam.ivy.project.portal:portalStyle";
  protected final static String PORTAL_KIT_LIBRARY = "ch.ivyteam.ivy.project.portal:portalKit";
  protected final static String PORTAL_TEMPLATE_LIBRARY = "ch.ivyteam.ivy.project.portal:portalTemplate";
  protected final static String PORTAL_CONNECTOR_LIBRARY = "ch.ivyteam.ivy.project.system:portalConnector";
  protected final static String SELF_SERVICE_LIBRARY = "ch.ivyteam.ivy.project.portal:selfService";
  protected final static String EXPRESS_LIBRARY = "ch.ivyteam.ivy.project.portal:axonIvyExpress";
  
  protected final List<String> portalLibraryStrings =
      Arrays.asList(PORTAL_STYLE_LIBRARY, PORTAL_KIT_LIBRARY, PORTAL_TEMPLATE_LIBRARY, PORTAL_CONNECTOR_LIBRARY,
          SELF_SERVICE_LIBRARY, EXPRESS_LIBRARY);
  
  public abstract Map<String, List<RemoteLibrary>> collectLibraries();
}
