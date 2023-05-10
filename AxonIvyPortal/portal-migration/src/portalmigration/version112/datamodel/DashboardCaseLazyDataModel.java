package portalmigration.version112.datamodel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.primefaces.model.SortOrder;

import ch.ivyteam.ivy.jsf.primefaces.legazy.LazyDataModel7;
import ch.ivyteam.ivy.workflow.ICase;
import portalmigration.version112.searchcriteria.DashboardCaseSearchCriteria;

public class DashboardCaseLazyDataModel extends LazyDataModel7<ICase> {

  private static final long serialVersionUID = -6615871274830927272L;

  private DashboardCaseSearchCriteria criteria;
  private List<ICase> cases;

  public DashboardCaseLazyDataModel() {
    criteria = new DashboardCaseSearchCriteria();
    cases = new ArrayList<>();
  }

  @Override
  public List<ICase> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
    return null;
  }

  @Override
  public ICase getRowData(String rowKey) {
    for (ICase caze : cases) {
      if (caze.getId() == Long.valueOf(rowKey)) {
        return caze;
      }
    }
    return null;
  }

  @Override
  public String getRowKey(ICase caze) {
    return String.valueOf(caze.getId());
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
  public ICase getRowData() {
    return super.getRowData();
  }

  /**
   * @hidden
   */
  @Override
  public boolean isRowAvailable() {
    return super.isRowAvailable();
  }

  public DashboardCaseSearchCriteria getCriteria() {
    return criteria;
  }

  public void setCriteria(DashboardCaseSearchCriteria criteria) {
    this.criteria = criteria;
  }
}
