package ch.ivy.addon.portalkit.bo;

import java.util.Locale;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ch.ivyteam.ivy.environment.Ivy;


/**
 * Use the new model {@link ch.ivy.addon.portalkit.configuration.Announcement}
 *
 */
@Deprecated
public class Announcement {

  private String id;
  private String language;
  private String value;
  @JsonIgnore
  private String languageDisplayName;

  public Announcement() {}

  public Announcement(String language, String value) {
    this.language = language;
    this.value = value;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getLanguage() {
    return language;
  }

  public void setLanguage(String language) {
    this.language = language;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public String getLanguageDisplayName() {
    if (StringUtils.isEmpty(languageDisplayName)) {
      Locale displayedLocale = Locale.forLanguageTag(language);
      languageDisplayName = displayedLocale.getDisplayName(Ivy.session().getContentLocale());
    }
    return languageDisplayName;
  }

}
