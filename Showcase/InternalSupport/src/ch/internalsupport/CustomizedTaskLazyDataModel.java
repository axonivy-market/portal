package ch.internalsupport;

import java.util.Arrays;
import java.util.List;

import ch.ivy.addon.portalkit.datamodel.TaskLazyDataModel;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.query.TaskQuery;

public class CustomizedTaskLazyDataModel extends TaskLazyDataModel {

  private static final long serialVersionUID = 7996851327481047161L;
  public static final String CUSTOM_CUSTOMER_TYPE = "CustomerType";
  public static final String CUSTOM_CUSTOMER_NAME = "CustomerName";

  public CustomizedTaskLazyDataModel() {
    super();
  }

  @Override
  public void initFilterContainer() {
    filterContainer = new CustomizedTaskFilterContainer();
  }

  @Override
  public void extendSort(TaskQuery taskQuery) {
    if (CUSTOM_CUSTOMER_NAME.equalsIgnoreCase(criteria.getSortField())) {
      if (criteria.isSortDescending()) {
        taskQuery.orderBy().customField().stringField(CUSTOM_CUSTOMER_NAME).descending();
      } else {
        taskQuery.orderBy().customField().stringField(CUSTOM_CUSTOMER_NAME);
      }
    } else if (CUSTOM_CUSTOMER_TYPE.equalsIgnoreCase(criteria.getSortField())) {
      if (criteria.isSortDescending()) {
        taskQuery.orderBy().customField().stringField(CUSTOM_CUSTOMER_TYPE).descending();
      } else {
        taskQuery.orderBy().customField().stringField(CUSTOM_CUSTOMER_TYPE);
      }
    }
  }

  @Override
  public List<String> getDefaultColumns() {
    return Arrays.asList("PRIORITY", "NAME", "ACTIVATOR", "ID", "CREATION_TIME", "EXPIRY_TIME", CUSTOM_CUSTOMER_NAME, CUSTOM_CUSTOMER_TYPE);
  }
  
  @Override
  public String getColumnLabel(String column) {
    return Ivy.cms().co("/DefaultColumns/" + column);
  }

}
