package ch.addon.portal.generic.menu;

import ch.ivy.addon.portalkit.enums.MenuKind;
import ch.ivy.addon.portalkit.enums.Protocol;

public class SubMenuItem {
  protected MenuKind menuKind;
  protected String link;
  protected String icon;
  protected String label;

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

  public String getExternalLink() {
    return correctProcessLink(link);
  }

  private String correctProcessLink(String link) {
    if (!hasProtocol(link)) {
      link = Protocol.HTTP.getValue() + link;
    }
    return link;
  }

  private boolean hasProtocol(String link) {
    String linkInLowerCase = link.toLowerCase();
    return linkInLowerCase.startsWith(Protocol.HTTP.getValue())
        || linkInLowerCase.startsWith(Protocol.HTTPS.getValue()) || linkInLowerCase.startsWith("/");
  }
}
