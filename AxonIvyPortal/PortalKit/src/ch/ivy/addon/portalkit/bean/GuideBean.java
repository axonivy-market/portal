package ch.ivy.addon.portalkit.bean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import ch.ivy.addon.portalkit.bo.Guide;
import ch.ivy.addon.portalkit.bo.GuidePool;
import ch.ivyteam.ivy.environment.Ivy;

@ManagedBean
@ViewScoped
public class GuideBean {

  private boolean isGuideShown;
  private boolean dontShowAgain;
  private Guide guide;
  
  @PostConstruct
  public void init() {
    guide = GuidePool.instance().guide(Ivy.session().getSessionUserName());
    guide.readShowGuideProperty();
    isGuideShown = guide.isGuideShown();
    Ivy.log().warn("isGuideShown: " + isGuideShown);
  }
  
  public boolean getIsGuideShown() {
    return isGuideShown;
  }
  
  public void updateToLoadRealData() {
    this.isGuideShown = false;
    guide.setIsGuideShown(false);
  }
  
  public boolean getDontShowAgain() {
    return dontShowAgain;
  }

  public void setDontShowAgain(boolean dontShowAgain) {
    this.dontShowAgain = dontShowAgain;
    guide.setShowGuideProperty(!dontShowAgain);
    Ivy.log().warn("setDontShowAgain - isGuideShown: " + !dontShowAgain);
  }

}
