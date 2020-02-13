package ch.ivy.addon.portalkit.datamodel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;

import ch.ivy.addon.portalkit.dto.UserDTO;
import ch.ivy.addon.portalkit.ivydata.bo.IvyAbsence;
import ch.ivyteam.ivy.environment.Ivy;

public class AbsenceLazyDataModel extends LazyDataModel<IvyAbsence> {
  private static final long serialVersionUID = 1L;
  private List<IvyAbsence> datasource;

  public AbsenceLazyDataModel() {
    datasource = new ArrayList<>();
    for (int i = 0; i < 10000; i++) {
      IvyAbsence x = new IvyAbsence();
      x.setUser(new UserDTO(Ivy.session().getSessionUser()));
      x.setFrom(new Date());
      x.setUntil(new Date());
      x.setComment("Test");
      datasource.add(x);
    }
    
    Ivy.log().error("SIZE OF DATASOURCE {0}", datasource.size());
  }

  @Override
  public List<IvyAbsence> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
//    List<IvyAbsence> data = new ArrayList<>();

    Ivy.log().error("load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters)");
    // rowCount
    int dataSize = datasource.size();

    // paginate
    if (dataSize > pageSize) {
      try {
        Ivy.log().error("RETURN HERE WITH FIRST {0} AND PAGE SIZE {1}", first, pageSize);
        return datasource.subList(first, first + pageSize);
      } catch (IndexOutOfBoundsException e) {
        return datasource.subList(first, first + (dataSize % pageSize));
      }
    } else {
      Ivy.log().error("RETURN ALL");
      return datasource;
    }
  }
 
}
