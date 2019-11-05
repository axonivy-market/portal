package ch.internalsupport;

import static ch.ivy.addon.portalkit.constant.CustomFields.CUSTOM_VARCHAR_FIELD1;
import static ch.ivy.addon.portalkit.constant.CustomFields.CUSTOM_VARCHAR_FIELD5;

import java.util.Arrays;
import java.util.List;

import ch.ivy.addon.portalkit.datamodel.TaskLazyDataModel;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.query.TaskQuery;

public class CustomizedTaskLazyDataModel extends TaskLazyDataModel {

  private static final long serialVersionUID = 7996851327481047161L;

  public CustomizedTaskLazyDataModel() {
    super();
  }

  @Override
  protected void initFilterContainer() {
    filterContainer = new CustomizedTaskFilterContainer();
  }

  @Override
  public void extendSort(TaskQuery taskQuery) {
    if ("customVarCharField5".equalsIgnoreCase(criteria.getSortField())) {
      if (criteria.isSortDescending()) {
        taskQuery.orderBy().customField().stringField(CUSTOM_VARCHAR_FIELD5).descending();
      } else {
        taskQuery.orderBy().customField().stringField(CUSTOM_VARCHAR_FIELD5);
      }
    } else if ("customVarCharField1".equalsIgnoreCase(criteria.getSortField())) {
      if (criteria.isSortDescending()) {
        taskQuery.orderBy().customField().stringField(CUSTOM_VARCHAR_FIELD1).descending();
      } else {
        taskQuery.orderBy().customField().stringField(CUSTOM_VARCHAR_FIELD1);
      }
    }
  }

  @Override
  protected List<String> getDefaultColumns() {
    return Arrays.asList("PRIORITY", "NAME", "ACTIVATOR", "ID", "CREATION_TIME", "EXPIRY_TIME", "customVarCharField5", "customVarCharField1");
  }
  
  @Override
  public String getColumnLabel(String column) {
    return Ivy.cms().co("/DefaultColumns/" + column);
  }

}
