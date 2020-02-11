package ch.ivy.addon.portalkit.datamodel;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import ch.ivy.addon.portalkit.ivydata.bo.IvyAbsence;

public class AbsenceLazyDataModel extends LazyDataModel<IvyAbsence> {
  private static final long serialVersionUID = 1L;
  private List<IvyAbsence> datasource;

  public AbsenceLazyDataModel(List<IvyAbsence> allAbcenses) {
    this.datasource = allAbcenses;
  }

  @Override
  public List<IvyAbsence> load(int first, int pageSize, String sortField, SortOrder sortOrder,
      Map<String, Object> filters) {
    /*
    List<IvyAbsence> data = new ArrayList<>();

    // filter
    for (IvyAbsence absence : datasource) {
      boolean match = true;

      if (filters != null) {
        for (Object object : filters.values()) {
          Ivy.log().error("object type : {0}", object.getClass().getTypeName());
          try {
            if (object instanceof FilterMeta) {
              FilterMeta meta = (FilterMeta)object;
              String filterField = meta.getColumn().getField();
              Object filterValue = meta.getFilterValue();
              Ivy.log().error("GO HERE WITH field {0}, value {1}", filterField, filterValue);
              String fieldValue = String.valueOf(absence.getClass().getField(filterField).get(absence));

              if (filterValue == null || fieldValue.startsWith(filterValue.toString())) {
                match = true;
              } else {
                match = false;
                break;
              }
            }
          } catch (Exception e) {
            match = false;
          }
        }
      }

      if (match) {
        data.add(absence);
      }
    }
    */

    // rowCount
    int dataSize = datasource.size();
    this.setRowCount(dataSize);

    // paginate
    if (dataSize > pageSize) {
      try {
        return datasource.subList(first, first + pageSize);
      } catch (IndexOutOfBoundsException e) {
        return datasource.subList(first, first + (dataSize % pageSize));
      }
    } else {
      return datasource;
    }
  }

}
