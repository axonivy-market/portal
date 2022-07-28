package ch.internalsupport;

import static ch.internalsupport.CustomizedTaskLazyDataModel.CUSTOM_CUSTOMER_NAME;

import org.apache.commons.lang.StringUtils;

import ch.ivy.addon.portalkit.taskfilter.TaskFilter;
import ch.ivyteam.ivy.workflow.custom.field.ICustomFieldMeta;
import ch.ivyteam.ivy.workflow.query.TaskQuery;

public class CustomerNameFilter extends TaskFilter {

  private String customerName;

  @Override
  public String label() {
    var customFieldMeta = ICustomFieldMeta.tasks().stream().filter(meta -> meta.name().equals(CUSTOM_CUSTOMER_NAME)).findAny();
    return customFieldMeta.isPresent() ? customFieldMeta.get().label() : CUSTOM_CUSTOMER_NAME;
  }
  
  @Override
  public String value() {
    return StringUtils.isNotBlank(customerName) ? String.format(DOUBLE_QUOTES, customerName) : ALL;
  }

  @Override
  public TaskQuery buildQuery() {
    if (StringUtils.isBlank(customerName)) {
      return null;
    }
    
    String containingKeyword = String.format("%%%s%%", customerName.trim());
    return TaskQuery.create().where().customField().stringField(CUSTOM_CUSTOMER_NAME).isLikeIgnoreCase(containingKeyword);
  }

  @Override
  public void resetValues() {
    customerName = "";
  }

  public String getCustomerName() {
    return customerName;
  }

  public void setCustomerName(String customerName) {
    this.customerName = customerName;
  }

}
