package portalmigration.version112.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import portalmigration.version112.enums.DashboardWidgetType;
import portalmigration.version112.util.LanguageUtils;
import portalmigration.version112.util.LanguageUtils.NameResult;

public class NewsDashboardWidget extends DashboardWidget implements Serializable {

  private static final long serialVersionUID = -5650954020648136966L;
  @JsonIgnore
  private List<News> newsList;

  public void buildDataFirstTime() {
    // In this version, we don't need to migrate news widget, so skip it
    newsList = new ArrayList<>();
  }

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
    return widget;
  }

  public List<News> getNewsList() {
    return newsList;
  }

  public void setNewsList(List<News> newsList) {
    this.newsList = newsList;
  }
}
