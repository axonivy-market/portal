package portalmigration.version112.datamodel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.primefaces.model.SortOrder;

import ch.ivyteam.ivy.jsf.primefaces.legazy.LazyDataModel7;
import ch.ivyteam.ivy.workflow.ITask;
import portalmigration.version112.searchcriteria.DashboardTaskSearchCriteria;

public class DashboardTaskLazyDataModel extends LazyDataModel7<ITask> {

  private static final long serialVersionUID = -6615871274830927272L;

  private DashboardTaskSearchCriteria criteria;
  private List<ITask> tasks;

  public DashboardTaskLazyDataModel() {
    criteria = new DashboardTaskSearchCriteria();
    tasks = new ArrayList<>();
  }

  @Override
  public List<ITask> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
    return null;
  }

  @Override
  public ITask getRowData(String rowKey) {
    for (ITask task : tasks) {
      if (task.getId() == Long.valueOf(rowKey)) {
        return task;
      }
    }
    return null;
  }

  @Override
  public String getRowKey(ITask task) {
    return String.valueOf(task.getId());
  }

  /**
   * @hidden
   */
  @Override
  public void setRowIndex(int index) {
    super.setRowIndex(index);
  }

  /**
   * @hidden
   */
  @Override
  public ITask getRowData() {
    return super.getRowData();
  }

  /**
   * @hidden
   */
  @Override
  public boolean isRowAvailable() {
    return super.isRowAvailable();
  }

  public DashboardTaskSearchCriteria getCriteria() {
    return criteria;
  }

  public void setCriteria(DashboardTaskSearchCriteria criteria) {
    this.criteria = criteria;
  }

  public boolean getCanWorkOn() {
    return criteria.getCanWorkOn();
  }

  public void setCanWorkOn(boolean canWorkOn) {
    criteria.setCanWorkOn(canWorkOn);
  }

}