package ch.ivy.addon.portalkit.dto.dashboard;

import static ch.ivy.addon.portalkit.constant.DashboardConfigurationPrefix.CMS;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.regex.Pattern;

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
   * Resolves literal Unicode escape sequences (e.g. "\\u00A5") into their corresponding characters (e.g. "¥").
   * This is needed when a user types the escape sequence into a text input, because the UI stores it verbatim.
   */
  private static final Pattern UNICODE_ESCAPE_PATTERN =
      Pattern.compile("\\\\u([0-9A-Fa-f]{4})");

  private String resolveUnicodeEscapes(String pattern) {
    StringBuffer sb = new StringBuffer();
    java.util.regex.Matcher m = UNICODE_ESCAPE_PATTERN.matcher(pattern);
    while (m.find()) {
      String replacement = String.valueOf((char) Integer.parseInt(m.group(1), 16));
      m.appendReplacement(sb, java.util.regex.Matcher.quoteReplacement(replacement));
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
