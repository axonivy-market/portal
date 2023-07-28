package ch.ivy.addon.portalkit.datamodel;

import java.util.List;

import org.primefaces.model.LazyDataModel;

public abstract class LiveScrollLazyModel<T> extends LazyDataModel<T> {

  private static final long serialVersionUID = -7848011352322176247L;
  private int rowIndex;

  @Override
  public void setRowIndex(int rowIndex) {
    int idx = rowIndex;
    if (idx >= getResults().size()) {
      idx = -1;
    }
    this.rowIndex = idx;
  }

  @Override
  public T getRowData() {
    return getResults().get(this.rowIndex);
  }

  @Override
  public boolean isRowAvailable() {
    if (getResults() == null) {
      return false;
    }

    return rowIndex >= 0 && rowIndex < getResults().size();
  }

  public abstract List<T> getResults();

}