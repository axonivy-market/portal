package ch.ivy.ws.addon.service;

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
import ch.ivy.ws.addon.util.LibraryUtils;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.application.ILibrary;
import ch.ivyteam.ivy.server.ServerFactory;

public class LibraryServiceImpl implements ILibraryService {

  private final static String PORTAL_STYLE_LIBRARY = "ch.ivyteam.ivy.project.portal:portalStyle";
  private final static String PORTAL_KIT_LIBRARY = "ch.ivyteam.ivy.project.portal:portalKit";
  private final static String PORTAL_TEMPLATE_LIBRARY = "ch.ivyteam.ivy.project.portal:portalTemplate";
  private final static String PORTAL_CONNECTOR_LIBRARY = "ch.ivyteam.ivy.project.system:portalConnector";
  private final static String SELF_SERVICE_LIBRARY = "ch.ivyteam.ivy.project.portal:selfService";
  private final static String EXPRESS_LIBRARY = "ch.ivyteam.ivy.project.portal:axonIvyExpress";

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
          IApplication application =
              ServerFactory.getServer().getApplicationConfigurationManager().findApplication(app);
          if (application != null) {
            ivyLibraries.addAll(application.getLibraries().stream()
                .filter(lib -> !portalLibraryStrings.contains(lib.getId()))
                .map(lib -> IvyLibraryTransformer.transform(lib, app)).collect(Collectors.toList()));
          }
        } catch (Exception e) {
          List<Object> userText = new ArrayList<Object>();
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
          IApplication application =
              ServerFactory.getServer().getApplicationConfigurationManager().findApplication(appName);
          if (application != null) {
            ILibrary library = LibraryUtils.findReleasedLibrary(application, libraryId);
            if (library != null) {
              result.setLibrary(IvyLibraryTransformer.transform(library, appName));
            } else {
              List<Object> userText = new ArrayList<Object>();
              userText.add(libraryId);
              errors.add(new WSException(WSErrorType.WARNING, 10054, userText, null));
            }
          } else {
            List<Object> userText = new ArrayList<Object>();
            userText.add(appName);
            errors.add(new WSException(WSErrorType.WARNING, 10054, userText, null));
          }
          result.setErrors(errors);
          return result;
        }
      });
    } catch (Exception e) {
      List<Object> userTextParams = new ArrayList<Object>();
      throw new WSException(WSErrorType.WARNING, 10054, e, userTextParams, null);
    }
  }

}
