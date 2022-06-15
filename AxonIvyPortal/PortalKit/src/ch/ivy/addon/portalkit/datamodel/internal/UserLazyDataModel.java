package ch.ivy.addon.portalkit.datamodel.internal;

import java.util.List;
import java.util.Map;

import org.primefaces.PrimeFaces;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import ch.ivy.addon.portalkit.dto.UserDTO;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.process.call.SubProcessCall;

@SuppressWarnings("deprecation")
public class UserLazyDataModel extends LazyDataModel<UserDTO> {

  private static final long serialVersionUID = -6160701869558198527L;
  
  private IApplication application;

  @Override
  public List<UserDTO> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
    @SuppressWarnings("unchecked")
    var users = (List<UserDTO>) SubProcessCall.withPath("Ivy Data Processes/SecurityService")
                  .withStartName("findUsers")
                  .withParam("application", application)
                  .withParam("query", filters.getOrDefault("name", ""))
                  .withParam("startIndex", first)
                  .withParam("count", pageSize)
                  .call()
                  .get("users");
    var rowCount = 0;
    if (users.size() >= pageSize) {
      rowCount = first + pageSize + 1;
    } else {
      rowCount = first + users.size();
    }
    setRowCount(rowCount);
    PrimeFaces.current().executeScript("PF('user-in-app').cfg.totalSize = " + rowCount);
    return users;
  }
  
  public IApplication getApplication() {
    return application;
  }
  
  public void setApplication(IApplication application) {
    this.application = application;
  }
}
