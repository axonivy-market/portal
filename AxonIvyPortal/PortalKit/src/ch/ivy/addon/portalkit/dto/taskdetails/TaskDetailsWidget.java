package ch.ivy.addon.portalkit.dto.taskdetails;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import ch.ivy.addon.portalkit.constant.TaskDetailsWidgetType;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({@Type(value = TaskDetailsInformationWidget.class, name = TaskDetailsWidgetType.INFORMATION),
    @Type(value = TaskDetailsHistoryWidget.class, name = TaskDetailsWidgetType.HISTORY),
    @Type(value = TaskDetailsDocumentWidget.class, name = TaskDetailsWidgetType.DOCUMENT),
    @Type(value = TaskDetailsCustomWidget.class, name = TaskDetailsWidgetType.CUSTOM)})
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public abstract class TaskDetailsWidget implements Serializable {

  private static final long serialVersionUID = 5533038246959235330L;
  
  protected String id;
  protected String type;
  protected String styleClass;
  protected String style;
  protected int axisX;
  protected int axisY;
  protected int width;
  protected int height;

  public TaskDetailsWidget() {}

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
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

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
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
    TaskDetailsWidget other = (TaskDetailsWidget) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    return true;
  }
}
