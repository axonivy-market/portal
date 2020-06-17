package ch.ivy.addon.portalkit.dto;

import java.io.Serializable;

public class DashboardWidget implements Serializable {

  private static final long serialVersionUID = 4580715578128184706L;
  
  private String id;
  private String name;
  private int x;
  private int y;
  private int width;
  private int height;
  
  public DashboardWidget() {
  }

  public DashboardWidget(String id, String name, int x, int y, int width, int height) {
    this.id = id;
    this.name = name;
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
  }
  
  public String getId() {
    return id;
  }
  
  public void setId(String id) {
    this.id = id;
  }
  
  public String getName() {
    return name;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  public int getX() {
    return x;
  }

  public void setX(int x) {
    this.x = x;
  }

  public int gety() {
    return y;
  }

  public void setY(int y) {
    this.y = y;
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
