package ch.ivy.ws.addon.service;

import static ch.ivyteam.ivy.server.ServerFactory.getServer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;

import ch.ivy.ws.addon.WSErrorType;
import ch.ivy.ws.addon.WSException;
import ch.ivy.ws.addon.bo.LibraryServiceResult;
import ch.ivy.ws.addon.transformer.IvyLibraryTransformer;
import ch.ivy.ws.addon.types.IvyLibrary;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.application.ILibrary;
import ch.ivyteam.ivy.server.ServerFactory;

public class LibraryServiceImpl implements ILibraryService {

  private static final String PORTAL_STYLE_LIBRARY = "ch.ivyteam.ivy.project.portal:portalStyle";
  private static final String PORTAL_KIT_LIBRARY = "ch.ivyteam.ivy.project.portal:portalKit";
  private static final String PORTAL_TEMPLATE_LIBRARY = "ch.ivyteam.ivy.project.portal:portalTemplate";
  private static final String PORTAL_CONNECTOR_LIBRARY = "ch.ivyteam.ivy.project.system:portalConnector";
  private static final String SELF_SERVICE_LIBRARY = "ch.ivyteam.ivy.project.portal:selfService";
  private static final String EXPRESS_LIBRARY = "ch.ivyteam.ivy.project.portal:axonIvyExpress";
  
  @Override
  public LibraryServiceResult getLibraries(List<String> apps) throws WSException {
    List<String> portalLibraryStrings =
        Arrays.asList(PORTAL_STYLE_LIBRARY, PORTAL_KIT_LIBRARY, PORTAL_TEMPLATE_LIBRARY, PORTAL_CONNECTOR_LIBRARY,
            SELF_SERVICE_LIBRARY, EXPRESS_LIBRARY);

    LibraryServiceResult result = new LibraryServiceResult();
    List<IvyLibrary> ivyLibraries = new ArrayList<>();
    List<WSException> errors = new ArrayList<>();

    if (CollectionUtils.isNotEmpty(apps)) {
      apps.forEach(app -> {
        try {
          IApplication application = getServer().getApplicationConfigurationManager().findApplication(app);
          if (application != null) {
            ivyLibraries.addAll(application.getLibraries().stream()
                .filter(lib -> !portalLibraryStrings.contains(lib.getId()))
                .map(lib -> IvyLibraryTransformer.transform(lib, app)).collect(Collectors.toList()));
          }
        } catch (Exception e) {
          List<Object> userText = new ArrayList<>();
          userText.add(app);
          errors.add(new WSException(WSErrorType.WARNING, 10054, e, userText, null));
        }
      });
    }

    result.setLibraries(ivyLibraries);
    result.setErrors(errors);
    return null;
  }

  @Override
  public LibraryServiceResult getLibrary(String libraryId, String appName) throws WSException {
    LibraryServiceResult result = new LibraryServiceResult();
    List<WSException> errors = new ArrayList<>();
    try {
      return ServerFactory.getServer().getSecurityManager().executeAsSystem(new Callable<LibraryServiceResult>() {
        @Override
        public LibraryServiceResult call() throws Exception {
          IApplication application = getServer().getApplicationConfigurationManager().findApplication(appName);
          if (application != null) {
            ILibrary library = application.findReleasedLibrary(libraryId);
            if (library != null) {
              result.setLibrary(IvyLibraryTransformer.transform(library, appName));
            } else {
              errors.add(new WSException(WSErrorType.WARNING, 10054, Arrays.asList(libraryId), null));
            }
          } else {
            errors.add(new WSException(WSErrorType.WARNING, 10054, Arrays.asList(appName), null));
          }
          result.setErrors(errors);
          return result;
        }
      });
    } catch (Exception e) {
      List<Object> userTextParams = new ArrayList<>();
      throw new WSException(WSErrorType.WARNING, 10054, e, userTextParams, null);
    }
  }

}
