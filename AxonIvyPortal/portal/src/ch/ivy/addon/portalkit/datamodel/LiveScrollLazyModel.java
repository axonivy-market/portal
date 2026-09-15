package ch.ivy.addon.portalkit.datamodel;

import java.util.List;

import jakarta.faces.context.FacesContext;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.primefaces.model.LazyDataModel;

public abstract class LiveScrollLazyModel<T> extends LazyDataModel<T> {

  private static final long serialVersionUID = -7848011352322176247L;

  public static final int DEFAULT_CHUNK_SIZE = 25;

  private static final String CHUNK_SIZE_PARAM = "chunkSize";
  private static final int MAX_CHUNK_SIZE = 100;

  private int chunkSize = DEFAULT_CHUNK_SIZE;
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

  public int getChunkSize() {
    return chunkSize;
  }

  protected void readChunkSizeFromRequest() {
    FacesContext facesContext = FacesContext.getCurrentInstance();
    if (facesContext == null) {
      return;
    }

    String reportedSize = facesContext.getExternalContext().getRequestParameterMap().get(CHUNK_SIZE_PARAM);
    if (StringUtils.isBlank(reportedSize)) {
      return;
    }
    chunkSize = Math.min(MAX_CHUNK_SIZE,
        Math.max(DEFAULT_CHUNK_SIZE, NumberUtils.toInt(reportedSize, DEFAULT_CHUNK_SIZE)));
  }

  public abstract List<T> getResults();

}