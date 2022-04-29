package ch.ivy.addon.portalkit.dto.dashboard;

import static ch.ivy.addon.portalkit.enums.ProcessWidgetMode.IMAGE_MODE;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ImageProcessDashboardWidget extends SingleProcessDashboardWidget {

  private static final long serialVersionUID = 1L;

  public ImageProcessDashboardWidget(ImageProcessDashboardWidget widget) {
    super(widget);
    setDisplayMode(IMAGE_MODE);
  }

  public ImageProcessDashboardWidget(ProcessDashboardWidget widget) {
    super(widget);
    setDisplayMode(IMAGE_MODE);
  }

  public ImageProcessDashboardWidget() {
    super();
    setDisplayMode(IMAGE_MODE);
  }
}
