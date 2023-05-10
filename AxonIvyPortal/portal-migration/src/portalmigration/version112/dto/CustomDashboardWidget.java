package portalmigration.version112.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import portalmigration.version112.enums.DashboardWidgetType;

public class CustomDashboardWidget extends DashboardWidget {
  private static final long serialVersionUID = 6901163427361921809L;

  private DashboardCustomWidgetData data;
  private String info;

  @Override
  public DashboardWidgetType getType() {
    return DashboardWidgetType.CUSTOM;
  }

  public DashboardCustomWidgetData getData() {
    return data;
  }

  public void setData(DashboardCustomWidgetData data) {
    this.data = data;
  }

  public String getInfo() {
    return info;
  }

  public void setInfo(String info) {
    this.info = info;
  }
  
  @JsonIgnore
  public static CustomDashboardWidget buildDefaultWidget(String id, String name) {
    CustomDashboardWidget result = new CustomDashboardWidget();
    result.setId(id);
    result.setName(name);
    result.setLayout(new WidgetLayout());
    result.getLayout().setWidth(10);
    result.getLayout().setHeight(6);
    result.getLayout().setAxisX(0);
    result.getLayout().setAxisY(0);
    result.setData(new DashboardCustomWidgetData());
    return result;
  }
}