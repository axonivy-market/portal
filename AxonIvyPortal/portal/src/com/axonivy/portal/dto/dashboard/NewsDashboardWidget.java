package com.axonivy.portal.dto.dashboard;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.axonivy.portal.dto.News;
import com.axonivy.portal.service.NewsService;
import com.fasterxml.jackson.annotation.JsonIgnore;

import ch.ivy.addon.portalkit.dto.WidgetLayout;
import ch.ivy.addon.portalkit.dto.dashboard.DashboardWidget;
import ch.ivy.addon.portalkit.enums.DashboardWidgetType;
import ch.ivy.addon.portalkit.util.LanguageUtils;
import ch.ivy.addon.portalkit.util.LanguageUtils.NameResult;

public class NewsDashboardWidget extends DashboardWidget implements Serializable {

  private static final long serialVersionUID = -5650954020648136966L;
  @JsonIgnore
  private List<News> newsList;
  private boolean showFullscreenMode;

  public void buildDataFirstTime() {
    newsList = NewsService.getInstance().findAll();
  }

  @Override
  public void resetWidgetFilters() {}

  @Override
  public DashboardWidgetType getType() {
    return DashboardWidgetType.NEWS;
  }

  public static NewsDashboardWidget buildDefaultWidget(String widgetId, String widgetName) {
    var widget = new NewsDashboardWidget();
    widget.setId(widgetId);
    NameResult nameResult = LanguageUtils.collectMultilingualNames(new ArrayList<>(), widgetName);
    widget.setNames(nameResult.names());
    widget.setLayout(new WidgetLayout());
    widget.getLayout().setWidth(4);
    widget.getLayout().setHeight(6);
    widget.getLayout().setAxisX(0);
    widget.getLayout().setAxisY(0);
    widget.setShowFullscreenMode(true);
    return widget;
  }

  public List<News> getNewsList() {
    return newsList;
  }

  public void setNewsList(List<News> newsList) {
    this.newsList = newsList;
  }

  @Override
  public void cancelUserFilter() {}

  public void setShowFullscreenMode(boolean showFullscreenMode) {
    this.showFullscreenMode = showFullscreenMode;
  }

  public boolean isShowFullscreenMode() {
    return showFullscreenMode;
  }
}
