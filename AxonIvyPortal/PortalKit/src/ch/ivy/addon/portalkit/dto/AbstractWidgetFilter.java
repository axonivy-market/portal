package ch.ivy.addon.portalkit.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public abstract class AbstractWidgetFilter implements Serializable {

  private static final long serialVersionUID = 5426556996000160855L;
  protected List<String> categories;
  protected List<String> states;

  public AbstractWidgetFilter() {}

  public List<String> getCategories() {
    return categories;
  }

  public void setCategories(List<String> categories) {
    this.categories = categories;
  }

  public List<String> getStates() {
    return states;
  }

  public void setStates(List<String> states) {
    this.states = states;
  }
}
