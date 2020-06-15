package ch.ivy.addon.portalkit.dto;

import java.io.Serializable;

import ch.ivy.addon.portalkit.enums.DashboardWidgetType;

public class DashboardWidget implements Serializable {

  private static final long serialVersionUID = 4580715578128184706L;
  
  private String name;
  private DashboardWidgetType type;
  private int posX;
  private int posY;
  private int width;
  private int height;

  public DashboardWidget(String name, DashboardWidgetType type, int posX, int posY, int width, int height) {
    this.name = name;
    this.type = type;
    this.posX = posX;
    this.posY = posY;
    this.width = width;
    this.height = height;
  }
  
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public DashboardWidgetType getType() {
    return type;
  }
  
  public void setType(DashboardWidgetType type) {
    this.type = type;
  }

  public int getPosX() {
    return posX;
  }

  public void setPosX(int posX) {
    this.posX = posX;
  }

  public int getPosY() {
    return posY;
  }

  public void setPosY(int posY) {
    this.posY = posY;
  }
 
  public int getWidth() {
    return width;
  }
  
  public void setWidth(int width) {
    this.width = width;
  }
  
  public int getHeight() {
    return height;
  }
  
  public void setHeight(int height) {
    this.height = height;
  }
}
