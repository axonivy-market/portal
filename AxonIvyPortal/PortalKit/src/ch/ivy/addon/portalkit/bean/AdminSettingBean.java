package ch.ivy.addon.portalkit.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.component.tabview.TabView;
import org.primefaces.event.TabChangeEvent;

@ManagedBean
@ViewScoped
public class AdminSettingBean {
  public void onTabChange(TabChangeEvent tabChangeEvent) {
    if (tabChangeEvent.getComponent() instanceof TabView) {
      TabView tabView = (TabView) tabChangeEvent.getComponent();
      Integer activeTabIndex = tabView.getChildren().indexOf(tabChangeEvent.getTab());
      
      IvyComponentLogicCaller<String> substitueTabChange = new IvyComponentLogicCaller<>();
      substitueTabChange.invokeComponentLogic("adminui:adminTabView", "#{logic.onTabChange}", new Object[] { activeTabIndex });
    }
  }
}
