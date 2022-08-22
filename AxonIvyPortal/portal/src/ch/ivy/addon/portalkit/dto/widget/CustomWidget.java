package ch.ivy.addon.portalkit.dto.widget;

public class CustomWidget extends AbstractWidget {

  private static final long serialVersionUID = -1623176520422054253L;

  private CustomWidgetData data;

  public CustomWidget() {
  }

  public CustomWidgetData getData() {
    return data;
  }

  public void setData(CustomWidgetData data) {
    this.data = data;
  }
}
