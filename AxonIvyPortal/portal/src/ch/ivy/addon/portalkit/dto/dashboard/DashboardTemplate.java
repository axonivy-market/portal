package ch.ivy.addon.portalkit.dto.dashboard;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import ch.ivy.addon.portalkit.configuration.AbstractConfiguration;
import ch.ivy.addon.portalkit.dto.DisplayName;
import ch.ivy.addon.portalkit.util.LanguageUtils;
import ch.ivy.addon.portalkit.util.LanguageUtils.NameResult;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class DashboardTemplate extends AbstractConfiguration implements Serializable {

  private static final long serialVersionUID = 6785925200522522200L;
  private List<DisplayName> titles;
  private List<DisplayName> descriptions;
  private String icon;
  private Dashboard dashboard;

  @JsonIgnore
  public String getTitle() {
    return LanguageUtils.getLocalizedName(titles);
  }

  public void setTitle(String title) {
    NameResult nameResult = LanguageUtils.collectMultilingualNames(titles, title);
    this.titles = nameResult.names();
  }

  public List<DisplayName> getTitles() {
    return titles;
  }

  public void setTitles(List<DisplayName> titles) {
    this.titles = titles;
  }

  @JsonIgnore
  public String getDescription() {
    return LanguageUtils.getLocalizedName(descriptions);
  }

  public void setDescription(String description) {
    NameResult nameResult = LanguageUtils.collectMultilingualNames(descriptions, description);
    this.descriptions = nameResult.names();
  }

  public List<DisplayName> getDescriptions() {
    return descriptions;
  }

  public void setDescriptions(List<DisplayName> descriptions) {
    this.descriptions = descriptions;
  }

  public String getIcon() {
    return icon;
  }

  public void setIcon(String icon) {
    this.icon = icon;
  }

  public Dashboard getDashboard() {
    return dashboard;
  }

  public void setDashboard(Dashboard dashboard) {
    this.dashboard = dashboard;
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
    Dashboard other = (Dashboard) obj;
    if (getId() == null) {
      if (other.getId() != null)
        return false;
    } else if (!getId().equals(other.getId()))
      return false;
    return true;
  }
}
