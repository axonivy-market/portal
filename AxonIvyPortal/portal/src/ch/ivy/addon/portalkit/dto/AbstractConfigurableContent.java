package ch.ivy.addon.portalkit.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import ch.ivy.addon.portalkit.dto.widget.AbstractWidget;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public abstract class AbstractConfigurableContent implements Serializable {

  private static final long serialVersionUID = 2460098610486547347L;

  protected String id;
  protected AbstractWidgetFilter filters;
  protected List<? extends AbstractWidget> widgets;
  protected boolean changed;

  public AbstractConfigurableContent() {}

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  /**
   * Add @JsonSerialize annotation and refer to sub class
   * e.g: @JsonSerialize(as = SubClass.class)
   * @return filters
   */
  public abstract AbstractWidgetFilter getFilters();

  /**
   * Add @JsonDeserialize annotation and refer to sub class
   * e.g: @JsonDeserialize(as = SubClass.class)
   * @param filters 
   */
  public abstract void setFilters(AbstractWidgetFilter filters);

  public List<? extends AbstractWidget> getWidgets() {
    return widgets;
  }

  public void setWidgets(List<? extends AbstractWidget> widgets) {
    this.widgets = widgets;
  }

  public boolean isChanged() {
    return changed;
  }

  public void setChanged(boolean changed) {
    this.changed = changed;
  }

}
