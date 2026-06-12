package ch.ivy.addon.portalkit.dto.dashboard;

import static ch.ivy.addon.portalkit.constant.DashboardConfigurationPrefix.CMS;

import java.io.Serializable;
import java.text.DecimalFormat;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Strings;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import ch.ivy.addon.portalkit.util.PortalCustomFieldUtils;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.custom.field.ICustomFields;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties({"format", "filterType", "filterListOptions", "dateFilterFrom", "dateFilterTo", "userFilter",
    "userFilterList", "userFilterFrom", "userFilterTo", "userDateFilterFrom", "userDateFilterTo",
    "userFilterListOptions"})
public class ColumnModel extends AbstractColumn implements Serializable {

  private static final long serialVersionUID = -4315469062114036720L;

  @Override
  public String getHeaderText() {
    if (getHeader() == null || Strings.CS.equals(getHeader(), CMS + getDefaultHeaderCMS())) {
      return Ivy.cms().co(getDefaultHeaderCMS());
    }
    return getHeader();
  }

  @JsonIgnore
  @SuppressWarnings("unused")
  public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {}
  
  @JsonIgnore
  public boolean canQuickSearch() {
    return false;
  }

  @JsonIgnore
  public boolean canFilter() {
    return true;
  }
  
  public String displayStringFieldContent(ICustomFields customFields) {
    if (Boolean.TRUE.equals(this.hasCmsValues)) {
      return PortalCustomFieldUtils.getDisplayValueByField(customFields, field);
    }
    return customFields.stringField(field).getOrNull();
  }
  
  /**
   * This is fix for input Japanese yen throw exception with pattern: \u00A5###,###.###
   * Reason: The pattern is being stored as the literal 6-character escape sequence \u00A5 (backslash + u + 0 + 0 + A + 5) instead of the actual yen character ¥. 
   * This happens when someone types \u00A5 in a text input field — the UI form doesn't interpret Unicode escapes, it stores them verbatim.
   * When new DecimalFormat(pattern) receives this 16-character string, it parses it character by character looking for the start of the number pattern (the first 0 or #)
   * @param pattern
   * @return resolved pattern
   */
  private String resolveUnicodeEscapes(String pattern) {
    StringBuffer sb = new StringBuffer();
    java.util.regex.Matcher m = java.util.regex.Pattern
        .compile("\\\\u([0-9A-Fa-f]{4})")
        .matcher(pattern);
    while (m.find()) {
        m.appendReplacement(sb, String.valueOf((char) Integer.parseInt(m.group(1), 16)));
    }
    m.appendTail(sb);
    return sb.toString();
}
  protected Object displayNumberWithPattern(ICustomFields customFields) {
    Number value = customFields.numberField(field).getOrNull();
    if (value == null) {
      return null;
    }

    java.util.Locale locale = Ivy.session().getFormattingLocale();
    try {
      if (StringUtils.isNotBlank(pattern)) {
        String resolvedPattern = resolveUnicodeEscapes(pattern);
        return new DecimalFormat(resolvedPattern, new java.text.DecimalFormatSymbols(locale)).format(value);
      }
      return java.text.NumberFormat.getNumberInstance(locale).format(value);
    } catch (IllegalArgumentException e) {
      return value; // fall back to raw number
    }
  }
}
