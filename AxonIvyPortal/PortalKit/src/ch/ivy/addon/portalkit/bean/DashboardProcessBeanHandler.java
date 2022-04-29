package ch.ivy.addon.portalkit.bean;

import ch.ivy.addon.portalkit.dto.dashboard.ProcessDashboardWidget;

public interface DashboardProcessBeanHandler<T extends ProcessDashboardWidget> {

  void preRender(T widget);

  void onChangeDisplayMode();

  void preview();
}
