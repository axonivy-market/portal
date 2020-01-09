package ch.ivy.addon.portalkit.datamodel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import ch.ivy.addon.portalkit.bo.ExpressProcess;
import ch.ivy.addon.portalkit.service.ExpressProcessService;

public class ExpressProcessLazyDataModel extends LazyDataModel<ExpressProcess> {

  private static final long serialVersionUID = 1727177020671848233L;
  private static final String EXPRESS_TYPE = "AHWF";

  protected List<ExpressProcess> data;
  private ExpressProcessService expressProcessService;

  public ExpressProcessLazyDataModel() {
    super();
    data = new ArrayList<ExpressProcess>();
    expressProcessService = new ExpressProcessService();
  }

  public ExpressProcessLazyDataModel(List<ExpressProcess> data) {
    this.data = data;
  }

  @Override
  public List<ExpressProcess> load(int first, int pageSize, String sortField, SortOrder sortOrder,
      Map<String, Object> filters) {
    if (first == 0) {
      updateRowCount();
    }

    List<ExpressProcess> expressProcessesList = expressProcessService.findReadyToExecuteProcessOrderByName(first, pageSize, EXPRESS_TYPE);
    expressProcessesList.forEach(express -> {
      if (!data.contains(express)) {
        data.add(express);
      }
    });

    return expressProcessesList;
  }
  
  public void updateRowCount() {
    this.setRowCount((int) expressProcessService.totalCounts());
  }

  @Override
  public Object getRowKey(ExpressProcess expressProcess) {
    return expressProcess.getProcessName();
  }

  @Override
  public ExpressProcess getRowData(String rowKey) {
    for (ExpressProcess expressProcess : data) {
      if (expressProcess.getProcessName().equals(rowKey))
        return expressProcess;
    }
    return null;
  }

  @Override
  public void setRowIndex(int rowIndex) {

    if (rowIndex == -1 || getPageSize() == 0) {
      super.setRowIndex(-1);
    } else
      super.setRowIndex(rowIndex % getPageSize());
  }

}
