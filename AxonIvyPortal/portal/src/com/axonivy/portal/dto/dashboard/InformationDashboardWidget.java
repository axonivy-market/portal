package com.axonivy.portal.dto.dashboard;

import java.util.ArrayList;
import java.util.List;

import com.axonivy.portal.dto.InformationDTO;

import ch.ivy.addon.portalkit.dto.WidgetLayout;
import ch.ivy.addon.portalkit.dto.dashboard.DashboardWidget;
import ch.ivy.addon.portalkit.enums.DashboardWidgetType;
import ch.ivy.addon.portalkit.util.LanguageUtils;
import ch.ivy.addon.portalkit.util.LanguageUtils.NameResult;

public class InformationDashboardWidget extends DashboardWidget{

  private static final long serialVersionUID = -4330932580998493468L;

  private List<InformationDTO> informations;

  private boolean showFullscreenMode;


  public static InformationDashboardWidget buildDefaultWidget(String widgetId, String widgetName) {
    InformationDashboardWidget widget = new InformationDashboardWidget();
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
    return DashboardWidgetType.INFORMATION;
  }

  public List<InformationDTO> getInformations() {
    return informations;
  }

  public void setInformations(List<InformationDTO> informations) {
    this.informations = informations;
  }

  public boolean isShowFullscreenMode() {
    return showFullscreenMode;
  }

  public void setShowFullscreenMode(boolean showFullscreenMode) {
    this.showFullscreenMode = showFullscreenMode;
  }

}
