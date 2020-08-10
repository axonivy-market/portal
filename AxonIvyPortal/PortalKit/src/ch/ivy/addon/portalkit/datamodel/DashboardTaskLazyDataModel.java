package ch.ivy.addon.portalkit.datamodel;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import ch.ivy.addon.portalkit.ivydata.searchcriteria.DashboardTaskSearchCriteria;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.ITask;
import ch.ivyteam.ivy.workflow.query.TaskQuery;

public class DashboardTaskLazyDataModel extends LazyDataModel<ITask> {

  private static final long serialVersionUID = -6615871274830927272L;

  private DashboardTaskSearchCriteria criteria;
  private List<ITask> data;
  
  @Override
  public List<ITask> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
    TaskQuery query = criteria.buildQuery();
    data = Ivy.wf().getTaskQueryExecutor().getResults(query);
    setRowCount(data.size());
    return data;
  }

  public DashboardTaskSearchCriteria getCriteria() {
    return criteria;
  }
  
  public void setCriteria(DashboardTaskSearchCriteria criteria) {
    this.criteria = criteria;
  }
}
