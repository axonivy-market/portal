package com.axonivy.portal.dto.dashboard;

import java.util.ArrayList;

import com.axonivy.portal.dto.InfomationDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;

import ch.ivy.addon.portalkit.dto.WidgetLayout;
import ch.ivy.addon.portalkit.dto.dashboard.DashboardWidget;
import ch.ivy.addon.portalkit.enums.DashboardWidgetType;
import ch.ivy.addon.portalkit.util.LanguageUtils;
import ch.ivy.addon.portalkit.util.LanguageUtils.NameResult;

public class InfomationDashboardWidget extends DashboardWidget{

  private static final long serialVersionUID = -4330932580998493468L;
  @JsonIgnore
  private InfomationDTO information;
  private boolean showFullscreenMode;


  public static InfomationDashboardWidget buildDefaultWidget(String widgetId, String widgetName) {
    InfomationDashboardWidget widget = new InfomationDashboardWidget();
    widget.setId(widgetId);
    NameResult nameResult = LanguageUtils.collectMultilingualNames(new ArrayList<>(), widgetName);
    widget.setNames(nameResult.names());
    widget.setLayout(new WidgetLayout());
    widget.getLayout().setWidth(4);
    widget.getLayout().setHeight(6);
    widget.getLayout().setAxisX(0);
    widget.getLayout().setAxisY(0);
    widget.setShowFullscreenMode(false);
    return widget;
  }

  @Override
  public void resetWidgetFilters() {
    
  }

  @Override
  public void cancelUserFilter() {
  }

  @Override
  public DashboardWidgetType getType() {
    return DashboardWidgetType.INFOMATION;
  }

  public InfomationDTO getInformation() {
    return information;
  }

  public void setInformation(InfomationDTO information) {
    this.information = information;
  }

  public boolean isShowFullscreenMode() {
    return showFullscreenMode;
  }

  public void setShowFullscreenMode(boolean showFullscreenMode) {
    this.showFullscreenMode = showFullscreenMode;
  }

}
