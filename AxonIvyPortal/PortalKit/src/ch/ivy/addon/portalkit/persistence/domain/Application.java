package ch.ivy.addon.portalkit.persistence.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class Application extends BusinessEntity {

  private String displayName;
  private String menuIcon;
  private Integer menuOrdinal;
  private String name;
  @JsonInclude(value = Include.NON_NULL)
  private String link;
  
  /**
   * @deprecated not used anymore
   */
  @Deprecated(since = "9.2", forRemoval = true)
  private Long serverId;
  
  /**
   * @deprecated not used anymore
   */
  @Deprecated(since = "9.2", forRemoval = true)
  private boolean embedInFrame = true;

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

  /**
   * Not used anymore
   * @return serverId
   */
  @Deprecated(since = "9.2", forRemoval = true)
  public Long getServerId() {
    return serverId;
  }
  
  /**
   * Not used anymore
   * @param serverId
   */
  @Deprecated(since = "9.2", forRemoval = true)
  public void setServerId(Long serverId) {
    this.serverId = serverId;
  }

  /**
   * Not used anymore
   * @return embedInFrame
   */
  @Deprecated(since = "9.2", forRemoval = true)
  public boolean isEmbedInFrame() {
    return embedInFrame;
  }

  
  /**
   * Not used anymore
   * @param embedInFrame
   */
  @Deprecated(since = "9.2", forRemoval = true)
  public void setEmbedInFrame(boolean embedInFrame) {
    this.embedInFrame = embedInFrame;
  }

  @Override
  public String toString() {
    return String.format("Application {displayName=%s, menuIcon=%s, menuOrdinal=%s, name=%s, link=%s, serverId=%s, id=%s, embedInFrame=%s}", 
      displayName, menuIcon, menuOrdinal, name, link, serverId, id, embedInFrame);
  }
}
