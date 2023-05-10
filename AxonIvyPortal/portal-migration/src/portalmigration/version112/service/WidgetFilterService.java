package portalmigration.version112.service;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;

import portalmigration.version112.dto.WidgetFilterModel;
import portalmigration.version112.enums.PortalVariable;

public class WidgetFilterService extends JsonConfigurationService<WidgetFilterModel> {

  public static final String WIDGET_FILTER_KEY_PATTERN = "WIDGET_FILTER_KEY_%s_%s";
  
  private static WidgetFilterService instance;

  public static WidgetFilterService getInstance() {
    if (instance == null) {
      instance = new WidgetFilterService();
    }
    return instance;
  }

  public List<WidgetFilterModel> findFiltersByWidgetId(String widgetId) {
    return CollectionUtils.emptyIfNull(findAll()).stream()
        .filter(filter -> filter.getWidgetId().equals(widgetId))
        .collect(Collectors.toList());
  }

  @Override
  public Class<WidgetFilterModel> getType() {
    return WidgetFilterModel.class;
  }

  @Override
  public String getConfigKey() {
    return PortalVariable.WIDGET_FILTER.key;
  }
}
