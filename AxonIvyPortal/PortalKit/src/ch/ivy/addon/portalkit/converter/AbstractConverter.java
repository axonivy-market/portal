package ch.ivy.addon.portalkit.converter;

import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

public abstract class AbstractConverter<T> implements Converter {

  protected static final String KEY_DELIMITER = ":::";

  /**
   * {@inheritDoc}<br />
   * <b>This implementation:</b><br />
   * Returns the item's hash code or the default no selection value.
   */
  @Override
  public String getAsString(FacesContext context, UIComponent component, Object item) {
    if (item != null && !isEmptyString(item) && !isEmptyObject(item)) {
      Map<String, Object> viewMap = getViewMap(context);
      String hash = String.valueOf(item.hashCode());
      String mapKey = String.format(getMapKeyTemplate(), component.getId(), hash);
      viewMap.put(mapKey, item);

      return hash;
    }
    return "";
  }

  @Override
  public Object getAsObject(FacesContext context, UIComponent component, String selectedValue) {
    if (selectedValue != null && selectedValue.length() > 0) {
      String mapKey = String.format(getMapKeyTemplate(), component.getId(), selectedValue);
      Map<String, Object> viewMap = getViewMap(context);
      var item = viewMap.get(mapKey);
      if (item == null) {
        return createNewObject(selectedValue);
      } else if (isEmptyObject(item)) {
        return null;
      }
      return item;
    }
    return null;
  }

  private String getMapKeyTemplate() {
    return getType().getClass().getName() + KEY_DELIMITER + "%s" + KEY_DELIMITER + "%s";
  }

  protected boolean isEmptyString(Object item) {
    return String.class.isAssignableFrom(item.getClass()) && "".equals(item);
  }

  protected Map<String, Object> getViewMap(FacesContext context) {
    UIViewRoot viewRoot = context.getViewRoot();
    return viewRoot.getViewMap();
  }

  protected abstract Class<T> getType();

  protected abstract boolean isEmptyObject(Object item);

  protected abstract Object createNewObject(String selectedValue);
}
