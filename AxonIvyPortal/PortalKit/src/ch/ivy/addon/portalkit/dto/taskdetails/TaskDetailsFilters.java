package ch.ivy.addon.portalkit.dto.taskdetails;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

import ch.ivy.addon.portalkit.dto.AbstractWidgetFilter;

public class TaskDetailsFilters extends AbstractWidgetFilter {

  private static final long serialVersionUID = -3200596568431055148L;

  public TaskDetailsFilters() {}

  @Override
  @JsonSetter("taskCategories")
  public void setCategories(List<String> categories) {
    super.setCategories(categories);
  }

  @Override
  @JsonGetter("taskCategories")
  public List<String> getCategories() {
    return super.getCategories();
  }

  @Override
  @JsonGetter("taskStates")
  public List<String> getStates() {
    return this.states;
  }

  @Override
  @JsonSetter("taskStates")
  public void setStates(List<String> taskStates) {
    this.states = taskStates;
  }


}
