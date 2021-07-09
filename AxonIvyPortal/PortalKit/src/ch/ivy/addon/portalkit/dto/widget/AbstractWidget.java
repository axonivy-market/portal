package ch.ivy.addon.portalkit.dto.widget;

import java.io.Serializable;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import ch.ivy.addon.portalkit.constant.WidgetType;
import ch.ivy.addon.portalkit.dto.WidgetLayout;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type", visible = false)
@JsonSubTypes({@Type(value = InformationWidget.class, name = WidgetType.INFORMATION),
    @Type(value = HistoryWidget.class, name = WidgetType.HISTORY),
    @Type(value = DocumentWidget.class, name = WidgetType.DOCUMENT),
    @Type(value = RelatedTaskWidget.class, name = WidgetType.RELATED_TASK),
    @Type(value = TechnicalCaseWidget.class, name = WidgetType.TECHINCAL_CASE),
    @Type(value = CustomWidget.class, name = WidgetType.CUSTOM)
})
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public abstract class AbstractWidget implements Serializable {

  private static final long serialVersionUID = -6401247494366299573L;

  protected String id;
  @JsonIgnore
  protected String type;
  protected WidgetLayout layout;

  public AbstractWidget() {}
  
  public AbstractWidget(String id, String type, WidgetLayout layout) {
    this.id = id;
    this.type = type;
    this.layout = layout;
  }

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
    AbstractWidget other = (AbstractWidget) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    return true;
  }
}
