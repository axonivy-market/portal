package ch.ivy.addon.portalkit.converter;

import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.dto.dashboard.process.DashboardProcess;

@FacesConverter("processSelectionConverter")
public class ProcessSelectionConverter implements Converter {
  private static final String UNIQUE_CONVERTER_IDENTIFIER = ProcessSelectionConverter.class.getName();
  private static final String KEY_DELIMITER = ":::";
  private static final String MAP_KEY_TEMPLATE =
      UNIQUE_CONVERTER_IDENTIFIER + KEY_DELIMITER + "%s" + KEY_DELIMITER + "%s";

  /**
   * {@inheritDoc}<br />
   * <b>This implementation:</b><br />
   * Returns the item's hash code or the default no selection value.
   */
  @Override
  public String getAsString(FacesContext context, UIComponent component, Object item) {
    if (item != null && !isEmptyString(item) && !isEmptyProcess((DashboardProcess) item)) {
      String hash = String.valueOf(item.hashCode());
      Map<String, Object> viewMap = getViewMap(context);
      String mapKey = String.format(MAP_KEY_TEMPLATE, component.getId(), hash);
      viewMap.put(mapKey, item);
      return hash;
    }
    return "";
  }

  /**
   * {@inheritDoc}<br />
   * <b>This implementation:</b><br />
   * Returns the item that corresponds the the given selected value.
   */
  @Override
  public Object getAsObject(FacesContext context, UIComponent component, String selectedvalue) {
    if (selectedvalue != null && selectedvalue.length() > 0) {
      Map<String, Object> viewMap = getViewMap(context);
      String mapKey = String.format(MAP_KEY_TEMPLATE, component.getId(), selectedvalue);

      Object item = viewMap.get(mapKey);
      if (item != null && !isEmptyProcess((DashboardProcess) item)) {
        return item;
      }
    }
    return null;
  }

  private boolean isEmptyProcess(DashboardProcess process) {
    return StringUtils.isBlank(process.getName()) && StringUtils.isBlank(process.getId());
  }

  private boolean isEmptyString(Object item) {
    return String.class.isAssignableFrom(item.getClass()) && "".equals(item);
  }

  private Map<String, Object> getViewMap(FacesContext context) {
    UIViewRoot viewRoot = context.getViewRoot();
    return viewRoot.getViewMap();
  }
}
