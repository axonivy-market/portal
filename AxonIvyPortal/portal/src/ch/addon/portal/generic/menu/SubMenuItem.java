package ch.addon.portal.generic.menu;

import ch.ivy.addon.portalkit.enums.MenuKind;
import ch.ivy.addon.portalkit.enums.Protocol;

public class SubMenuItem {
  protected MenuKind menuKind;
  protected String link;
  protected String icon;
  protected String label;
  protected String name;
  
  public MenuKind getMenuKind() {
    return menuKind;
  }

  public void setMenuKind(MenuKind menuKind) {
    this.menuKind = menuKind;
  }

  public String getLink() {
    return link;
  }

  public void setLink(String link) {
    this.link = link;
  }

  public String getIcon() {
    return icon;
  }

  public void setIcon(String icon) {
    this.icon = icon;
  }

  public String getLabel() {
    return label;
  }
  
  public void setLabel(String label) {
    this.label = label;
  }
  
  public String getName() {
    return name;
  }
  
  public void setName(String name) {
    this.name = name;
  }

  public String buildLink() {
    return correctProcessLink(link);
  }

  private String correctProcessLink(String link) {
    String processLink = link;
    if (processLink != null && !hasProtocolOrIsARelativeLink(processLink)) {
      processLink = Protocol.HTTP.getValue() + processLink;
    }
    return processLink;
  }

  private boolean hasProtocolOrIsARelativeLink(String link) {
    String linkInLowerCase = link.toLowerCase();
    return linkInLowerCase.startsWith(Protocol.HTTP.getValue())
        || linkInLowerCase.startsWith(Protocol.HTTPS.getValue()) || linkInLowerCase.startsWith("/");
  }
}
