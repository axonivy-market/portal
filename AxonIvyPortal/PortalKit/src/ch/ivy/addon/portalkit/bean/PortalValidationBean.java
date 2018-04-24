package ch.ivy.addon.portalkit.bean;

import static ch.ivy.addon.portalkit.enums.IvyDefaultApplication.DESIGNER;
import static ch.ivy.addon.portalkit.enums.IvyDefaultApplication.SYSTEM;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import ch.ivy.addon.portalkit.datacollecting.service.SingleAppLibraryService;
import ch.ivy.addon.portalkit.enums.PortalLibrary;
import ch.ivy.addon.portalkit.util.LibraryUtils;
import ch.ivyteam.ivy.application.ILibrary;
import ch.ivyteam.ivy.environment.Ivy;

@ManagedBean
@SessionScoped
public class PortalValidationBean {
  private List<String> errorMessages;

  @PostConstruct
  public void init() {
    errorMessages = new ArrayList<>();
    validate();
  }

  private void validate() {
    checkCompatibility();
  }

  private boolean checkCompatibility() {
    SingleAppLibraryService service = new SingleAppLibraryService();
    boolean compatibilityOk = true;
    String portalKitVersion = "";
    String portalConnectorVersion = "";

    ILibrary portalKit = LibraryUtils.findReleasedLibrary(Ivy.wf().getApplication(), PortalLibrary.PORTAL_KIT.getValue());
    ILibrary portalConnector = service.findLibraryFromApp(SYSTEM.getValue(), PortalLibrary.PORTAL_CONNECTOR.getValue());
    // for designer
    if (portalConnector == null) {
      portalConnector = service.findLibraryFromApp(DESIGNER.getValue(), PortalLibrary.PORTAL_CONNECTOR.getValue());
    }

    if (portalKit != null && portalConnector != null) {
      portalKitVersion = portalKit.getQualifiedVersion().toString();
      portalConnectorVersion = portalConnector.getQualifiedVersion().toString();
      compatibilityOk = portalConnectorVersion.equals(portalKitVersion);
    }

    if (!compatibilityOk) {
      List<Object> params = Arrays.asList(portalConnectorVersion, portalKitVersion);
      errorMessages.add(Ivy.cms().co("/errors/compatibility/message", params));
    }
    return compatibilityOk;
  }

  public List<String> getErrorMessages() {
    return errorMessages;
  }

  public void setErrorMessages(List<String> errorMessages) {
    this.errorMessages = errorMessages;
  }

}
