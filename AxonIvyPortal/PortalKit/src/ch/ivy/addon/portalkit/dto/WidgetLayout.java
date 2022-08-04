package ch.ivy.addon.portalkit.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class WidgetLayout implements Serializable {

  private static final long serialVersionUID = 1734727544534129141L;

  private String id;
  private String styleClass;
  private String style;

  @JsonProperty("w")
  private int width;
  @JsonProperty("h")
  private int height;
  @JsonProperty("x")
  private int axisX;
  @JsonProperty("y")
  private int axisY;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getStyleClass() {
    return styleClass;
  }

  public void setStyleClass(String styleClass) {
    this.styleClass = styleClass;
  }

  public String getStyle() {
    return style;
  }

  public void setStyle(String style) {
    this.style = style;
  }

  public int getAxisX() {
    return axisX;
  }

  public void setAxisX(int axisX) {
    this.axisX = axisX;
  }

  public int getAxisY() {
    return axisY;
  }

  public void setAxisY(int axisY) {
    this.axisY = axisY;
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
