package ch.ivy.addon.portalkit.taskfilter;

import java.util.ArrayList;
import java.util.List;

import ch.ivy.addon.portalkit.casefilter.CaseFilter;
import ch.ivy.addon.portalkit.filter.AbstractFilterData;
import ch.ivyteam.ivy.environment.Ivy;

import com.fasterxml.jackson.annotation.JsonIgnore;

@SuppressWarnings("rawtypes")
public class TaskAnalysisFilterData extends AbstractFilterData {
  private List<TaskFilter> taskFilters = new ArrayList<>();
  private List<CaseFilter> caseFilters = new ArrayList<>();

  public List<TaskFilter> getTaskFilters() {
    return taskFilters;
  }

  public void setTaskFilters(List<TaskFilter> taskFilters) {
    this.taskFilters = taskFilters;
  }

  public List<CaseFilter> getCaseFilters() {
    return caseFilters;
  }

  public void setCaseFilters(List<CaseFilter> caseFilters) {
    this.caseFilters = caseFilters;
  }

  @Override
  public void setFilters(List filters) {
    throw new UnsupportedOperationException();
  }

  @Override
  @JsonIgnore
  public List getFilters() {
    Ivy.log().warn("==getFilters===");
    throw new UnsupportedOperationException();
  }

  @Override
  @JsonIgnore
  public String getKeyword() {
    Ivy.log().warn("==getKeyword===");
    return super.getKeyword();
  }

  
}
