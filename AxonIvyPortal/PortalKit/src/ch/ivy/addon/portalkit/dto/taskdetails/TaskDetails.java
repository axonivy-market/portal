package ch.ivy.addon.portalkit.dto.taskdetails;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import ch.ivy.addon.portalkit.dto.AbstractWidgetFilter;
import ch.ivy.addon.portalkit.dto.AbstractConfigurableContent;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class TaskDetails extends AbstractConfigurableContent implements Serializable {

  private static final long serialVersionUID = -8424800742022240884L;

  public TaskDetails() {
    super();
  }

  @Override
  @JsonSerialize(as = TaskDetailsFilters.class)
  public TaskDetailsFilters getFilters() {
    return (TaskDetailsFilters) this.filters;
  }

  @Override
  @JsonDeserialize(as = TaskDetailsFilters.class)
  public void setFilters(AbstractWidgetFilter filters) {
    this.filters = filters;
  }

}
