package portalmigration.version112.dto;

import com.fasterxml.jackson.annotation.JsonInclude;


@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ImageProcessDashboardWidget extends SingleProcessDashboardWidget {

  private static final long serialVersionUID = 1L;

  public ImageProcessDashboardWidget(ImageProcessDashboardWidget widget) {
    super(widget);
  }

  public ImageProcessDashboardWidget(ProcessDashboardWidget widget) {
    super(widget);
  }

  public ImageProcessDashboardWidget() {
    super();
  }
}
