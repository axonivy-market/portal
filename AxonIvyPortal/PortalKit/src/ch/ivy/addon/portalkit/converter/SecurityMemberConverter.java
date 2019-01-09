package ch.ivy.addon.portalkit.converter;

import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import ch.ivyteam.ivy.security.ISecurityMember;

@FacesConverter("securityMemberConverter")
public class SecurityMemberConverter implements Converter {
  private static final String KEY_DELIMITER = ":::";
  private static final String MAP_KEY_TEMPLATE = "%s" + KEY_DELIMITER + "%s" + KEY_DELIMITER
      + "%s";

  /**
   * {@inheritDoc}<br />
   * <b>This implementation:</b><br />
   * Returns the item's hash code or the default no selection value.
   */
  @Override
  public String getAsString(FacesContext context, UIComponent component, Object item) {
    if (item != null && !isEmptyString(item)) {
      ISecurityMember member = (ISecurityMember) item;
      String hash = String.valueOf(item.hashCode());
      Map<String, Object> viewMap = getViewMap(context);
      String mapKey = String.format(MAP_KEY_TEMPLATE, member.isUser() ? "User" : "Role", component.getId(), hash);
      viewMap.put(mapKey, item);
      return mapKey;
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
      return viewMap.get(selectedvalue);
    }
    return null;
  }

  private boolean isEmptyString(Object item) {
    return String.class.isAssignableFrom(item.getClass()) && "".equals(item);
  }

  private Map<String, Object> getViewMap(FacesContext context) {
    UIViewRoot viewRoot = context.getViewRoot();
    return viewRoot.getViewMap();
  }
}
