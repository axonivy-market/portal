package ch.ivy.addon.portalkit.bo;

import java.util.Optional;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.IWorkflowSession;

public class Guide {

  public static final String SHOW_GUIDE = "SHOW_GUIDE";
  private boolean isGuideShown;

  public Guide() {
    Optional<String> showGuide = Optional.of(Ivy.session()).map(IWorkflowSession::getSessionUser)
        .map(user -> user.getProperty(SHOW_GUIDE));
    isGuideShown = showGuide.isPresent() ? Boolean.parseBoolean(showGuide.get()) : true;
  }

  public boolean isGuideShown() {
    return isGuideShown;
  }
  
  public void setIsGuideShown(boolean isGuideShown) {
    this.isGuideShown = isGuideShown;
  }
  
  public void setShowGuideProperty(boolean isGuideShown) {
    this.isGuideShown = isGuideShown;
    Ivy.session().getSessionUser().setProperty(SHOW_GUIDE, String.valueOf(isGuideShown));
  }
  
  public boolean getShowGuideProperty() {
    Optional<String> showGuide = Optional.of(Ivy.session()).map(IWorkflowSession::getSessionUser)
        .map(user -> user.getProperty(SHOW_GUIDE));
    return showGuide.isPresent() ? Boolean.parseBoolean(showGuide.get()) : true;
  }
}
