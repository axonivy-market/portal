package ch.ivy.addon.portalkit.converter;

import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

/**
 * Converter that can handle POJOs. Can handle each type of object.
 * 
 * Attention: If a no selection item is provided its item value must be ''. An empty string ('') is
 * not allowed as choosable item value (it will always be converted to {@code null}).
 * 
 * Attention: If the POJOs must match each other (e.g. in a dropdown) over multiple views it is
 * required that the POJOs implement the hasCode method properly.
 */
@FacesConverter("pojoConverter")
public class PojoConverter implements Converter {
  private static final String UNIQUE_CONVERTER_IDENTIFIER = PojoConverter.class.getName();
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
    if (item != null && !isEmptyString(item)) {
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

      return item;
    }
    return null;
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
