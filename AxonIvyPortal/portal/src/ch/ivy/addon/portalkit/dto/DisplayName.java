package ch.ivy.addon.portalkit.dto;

import java.util.Locale;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class DisplayName {

  private Locale locale;
  private String value;

  public Locale getLocale() {
    return locale;
  }

  public void setLocale(Locale locale) {
    this.locale = locale;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof DisplayName)) {
      return false;
    }
    DisplayName other = (DisplayName) obj;
    EqualsBuilder builder = new EqualsBuilder();
    builder.append(value, other.getValue());
    builder.append(locale, other.locale);
    return builder.isEquals();
  }

  @Override
  public int hashCode() {
    HashCodeBuilder builder = new HashCodeBuilder();
    builder.append(getValue());
    builder.append(getLocale());
    return builder.hashCode();
  }
}
