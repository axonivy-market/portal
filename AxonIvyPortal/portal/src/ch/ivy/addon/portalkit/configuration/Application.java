package ch.ivy.addon.portalkit.configuration;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Application extends AbstractConfiguration {

  private String displayName;
  private String menuIcon;
  private Integer menuOrdinal;
  private String name;
  private String link;

  public Application() {
    setIsPublic(true);
  }

  public String getDisplayName() {
    return displayName;
  }

  public void setDisplayName(String displayName) {
    this.displayName = displayName;
  }

  public String getMenuIcon() {
    return menuIcon;
  }

  public void setMenuIcon(String menuIcon) {
    this.menuIcon = menuIcon;
  }

  public Integer getMenuOrdinal() {
    return menuOrdinal;
  }

  public void setMenuOrdinal(Integer menuOrdinal) {
    this.menuOrdinal = menuOrdinal;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getLink() {
    return link;
  }

  public void setLink(String link) {
    this.link = link;
  }

  @Override
  public String toString() {
    return String.format(
        "Application {displayName=%s, menuIcon=%s, menuOrdinal=%s, name=%s, link=%s, id=%s}", displayName, menuIcon,
        menuOrdinal, name, link, getId());
  }
}
