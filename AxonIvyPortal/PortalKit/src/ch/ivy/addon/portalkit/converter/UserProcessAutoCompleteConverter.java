package ch.ivy.addon.portalkit.converter;

import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.persistence.domain.UserProcess;
import ch.ivy.addon.portalkit.util.UserUtils;

@FacesConverter("userProcessAutoCompleteConverter")
public class UserProcessAutoCompleteConverter implements Converter {
  private static final String UNIQUE_CONVERTER_IDENTIFIER = UserProcessAutoCompleteConverter.class.getName();
  private static final String KEY_DELIMITER = ":::";
  private static final String MAP_KEY_TEMPLATE = UNIQUE_CONVERTER_IDENTIFIER + KEY_DELIMITER + "%s" + KEY_DELIMITER
      + "%s";

  /**
   * {@inheritDoc}<br />
   * <b>This implementation:</b><br />
   * Returns the item's hash code or the default no selection value.
   */
  @Override
  public String getAsString(FacesContext context, UIComponent component, Object item) throws ConverterException {
    if (item != null && !isEmptyString(item) && !isEmptyUserProcess((UserProcess) item)) {
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
      if (item == null) {
        return new UserProcess(selectedvalue, UserUtils.getSessionUserName(), "");
      } else if (isEmptyUserProcess((UserProcess) item)) {
        return null;
      }
      return item;
    }
    return null;
  }

  private boolean isEmptyUserProcess(UserProcess userProcess) {
    return StringUtils.isBlank(userProcess.getProcessName()) && StringUtils.isBlank(userProcess.getLink());
  }

  private boolean isEmptyString(Object item) {
    boolean isEmptyString = String.class.isAssignableFrom(item.getClass()) && "".equals(item);
    return isEmptyString;
  }

  private Map<String, Object> getViewMap(FacesContext context) {
    UIViewRoot viewRoot = context.getViewRoot();
    Map<String, Object> viewMap = viewRoot.getViewMap();
    return viewMap;
  }
}
