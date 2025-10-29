package ch.ivy.addon.portalkit.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.axonivy.portal.components.enums.MenuKind;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import ch.ivy.addon.portalkit.configuration.AbstractConfiguration;
import ch.ivy.addon.portalkit.util.LanguageUtils;
import ch.ivy.addon.portalkit.util.LanguageUtils.NameResult;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class UserMenu extends AbstractConfiguration implements Serializable {

  private static final long serialVersionUID = 2765618265355842745L;

  @Deprecated(since = "10.0", forRemoval = true)
  @JsonProperty(access = Access.WRITE_ONLY)
  private String title;
  private List<DisplayName> titles;

  private String url;

  private MenuKind menuKind;

  private List<String> permissions;

  private Map<String, String> params;

  public String getTitle() {
    return LanguageUtils.getLocalizedName(titles, title);
  }

  public void setTitle(String title) {
    NameResult nameResult = LanguageUtils.collectMultilingualNames(titles, title);
    this.titles = nameResult.names();
    this.title = nameResult.name();
  }

  public List<DisplayName> getTitles() {
    return titles;
  }

  public void setTitles(List<DisplayName> titles) {
    this.titles = titles;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public MenuKind getMenuKind() {
    return menuKind;
  }

  public void setMenuKind(MenuKind menuKind) {
    this.menuKind = menuKind;
  }

  public List<String> getPermissions() {
    return permissions;
  }

  public void setPermissions(List<String> permissions) {
    this.permissions = permissions;
  }

  public Map<String, String> getParams() {
    return params;
  }

  public void setParams(Map<String, String> params) {
    this.params = params;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    UserMenu other = (UserMenu) obj;
    if (getId() == null) {
      if (other.getId() != null)
        return false;
    } else if (!getId().equals(other.getId()))
      return false;
    return true;
  }
}
