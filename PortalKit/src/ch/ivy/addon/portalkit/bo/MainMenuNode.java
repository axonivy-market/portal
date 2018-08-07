package ch.ivy.addon.portalkit.bo;

import ch.ivy.addon.portalkit.enums.MenuKind;

public class MainMenuNode {

  private String value;
  private String icon;
  private MenuKind menuKind;
  private String url;
  
  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public String getIcon() {
    return icon;
  }

  public void setIcon(String icon) {
    this.icon = icon;
  }

  public MenuKind getMenuKind() {
    return menuKind;
  }

  public void setMenuKind(MenuKind menuKind) {
    this.menuKind = menuKind;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }
}
