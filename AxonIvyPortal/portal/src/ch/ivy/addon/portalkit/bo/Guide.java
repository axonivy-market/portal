package ch.ivy.addon.portalkit.bo;

import java.util.Optional;

import ch.ivy.addon.portalkit.constant.UserProperty;
import ch.ivy.addon.portalkit.enums.GlobalVariable;
import ch.ivy.addon.portalkit.service.GlobalSettingService;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.IWorkflowSession;

public class Guide {

  private boolean isGuideShown;
  private GlobalSettingService globalSettingService;

  public Guide() {
    globalSettingService = new GlobalSettingService();
    readShowGuideProperty();
  }

  public boolean isGuideShown() {
    return isGuideShown;
  }
  
  public void setIsGuideShown(boolean isGuideShown) {
    this.isGuideShown = isGuideShown;
  }
  
  public void setShowGuideProperty(boolean isGuideShown) {
    this.isGuideShown = isGuideShown;
    Ivy.session().getSessionUser().setProperty(UserProperty.SHOW_GUIDE, String.valueOf(isGuideShown));
  }
  
  public void readShowGuideProperty() {
    boolean showGuideSetting =
        Boolean.parseBoolean(globalSettingService.findGlobalSettingValue(GlobalVariable.SHOW_USER_GUIDE));
    if (!showGuideSetting) {
      isGuideShown = false;
      return;
    }
    
    Optional<String> showGuide = Optional.of(Ivy.session()).map(IWorkflowSession::getSessionUser)
        .map(user -> user.getProperty(UserProperty.SHOW_GUIDE));
    isGuideShown = showGuide.isPresent() ? Boolean.parseBoolean(showGuide.get()) : true;
  }
}
