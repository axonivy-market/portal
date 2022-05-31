package ch.ivy.addon.portalkit.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.component.tabview.TabView;
import org.primefaces.event.ReorderEvent;
import org.primefaces.event.TabChangeEvent;

import ch.addon.portal.generic.userprofile.homepage.HomepageUtils;
import ch.ivy.addon.portalkit.configuration.Application;
import ch.ivy.addon.portalkit.jsf.Attrs;

@ManagedBean
@ViewScoped
public class AdminSettingBean implements Serializable {

  private static final long serialVersionUID = 1506137118077215366L;

  public void onTabChange(TabChangeEvent<Object> tabChangeEvent) {
    if (tabChangeEvent.getComponent() instanceof TabView) {
      TabView tabView = (TabView) tabChangeEvent.getComponent();
      Integer activeTabIndex = tabView.getChildren().indexOf(tabChangeEvent.getTab());

      IvyComponentLogicCaller<String> adminSettingTabChange = new IvyComponentLogicCaller<>();
      String componentId = Attrs.currentContext().getBuildInAttribute("clientId");
      adminSettingTabChange.invokeComponentLogic(componentId, "#{logic.onTabChange}", new Object[] {activeTabIndex});
    }
  }

  public void onApplicationReorder(ReorderEvent reorderEvent) {
    int fromIndex = reorderEvent.getFromIndex();
    int toIndex = reorderEvent.getToIndex();
    List<Application> applicationList = Attrs.currentContext().getAttribute("#{data.applicationList}", List.class);

    Application selectedApp = applicationList.remove(fromIndex);
    applicationList.add(toIndex, selectedApp);

    for (int i = 0; i < applicationList.size(); i++) {
      applicationList.get(i).setMenuOrdinal(i);
    }

    IvyComponentLogicCaller<String> applicationReorder = new IvyComponentLogicCaller<>();
    String componentId = Attrs.currentContext().getBuildInAttribute("clientId");
    applicationReorder.invokeComponentLogic(componentId, "#{logic.onApplicationReorder}",
        new Object[] {applicationList, selectedApp});
  }

  public boolean isShowLegacyUI() {
    return HomepageUtils.isShowLegacyUI();
  }
}
