package ch.ivy.addon.portalkit.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class PortalHomeBean implements Serializable {
  private static final long serialVersionUID = 1L;

  private boolean showAllTask;
  private String styleClass;
  public static final String DEFAULT_STYLECLASS = "Wid50 Fleft";
  public static final String FULL_SCREEN_STYLECLASS = "Wid100 Fleft";
  
  @PostConstruct
  public void init(){
    styleClass = DEFAULT_STYLECLASS;
  }
  
  public void showFullTaskList(){
    if(styleClass.equalsIgnoreCase(DEFAULT_STYLECLASS)){
      styleClass = FULL_SCREEN_STYLECLASS;
    }else {
      styleClass = DEFAULT_STYLECLASS;
    }
    showAllTask = !showAllTask;
  }

  public boolean getShowAllTask() {
    return showAllTask;
  }

  public void setShowAllTask(boolean showAllTask) {
    this.showAllTask = showAllTask;
  }

  public String getStyleClass() {
    return styleClass;
  }

  public void setStyleClass(String styleClass) {
    this.styleClass = styleClass;
  }

}
