package ch.ivy.addon.portalkit.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import ch.ivy.addon.portalkit.bo.Guide;
import ch.ivy.addon.portalkit.bo.GuidePool;
import ch.ivyteam.ivy.environment.Ivy;

@ManagedBean
@ViewScoped
public class GuideBean implements Serializable {

  private static final long serialVersionUID = 2420978041410219023L;
  private boolean isGuideShown;
  private boolean dontShowAgain;
  private Guide guide;
  
  @PostConstruct
  public void init() {
    guide = GuidePool.instance().guide(Ivy.session().getSessionUserName());
    guide.readShowGuideProperty();
    isGuideShown = guide.isGuideShown();
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
  }

}
