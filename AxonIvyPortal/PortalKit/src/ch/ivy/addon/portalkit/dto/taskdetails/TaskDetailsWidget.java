package ch.ivy.addon.portalkit.dto.taskdetails;

import java.io.Serializable;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import ch.ivy.addon.portalkit.constant.TaskDetailsWidgetType;
import ch.ivy.addon.portalkit.dto.WidgetLayout;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({@Type(value = TaskDetailsInformationWidget.class, name = TaskDetailsWidgetType.INFORMATION),
    @Type(value = TaskDetailsHistoryWidget.class, name = TaskDetailsWidgetType.HISTORY),
    @Type(value = TaskDetailsDocumentWidget.class, name = TaskDetailsWidgetType.DOCUMENT),
    @Type(value = TaskDetailsCustomWidget.class, name = TaskDetailsWidgetType.CUSTOM)})
public abstract class TaskDetailsWidget implements Serializable {

  private static final long serialVersionUID = 5533038246959235330L;

  @JsonIgnore
  protected String id;

  protected String type;
  protected WidgetLayout layout;

  public TaskDetailsWidget() {}

  public String getId() {
    if (id == null) {
      id = UUID.randomUUID().toString();
    }
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

  public WidgetLayout getLayout() {
    return layout;
  }

  public void setLayout(WidgetLayout layout) {
    this.layout = layout;
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
