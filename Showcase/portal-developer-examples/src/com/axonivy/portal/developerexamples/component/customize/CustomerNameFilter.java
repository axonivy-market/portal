package com.axonivy.portal.developerexamples.component.customize;

import static ch.ivy.addon.portalkit.constant.CustomFields.CUSTOM_VARCHAR_FIELD5;

import org.apache.commons.lang.StringUtils;

import ch.ivy.addon.portalkit.taskfilter.TaskFilter;
import ch.ivyteam.ivy.workflow.query.TaskQuery;

public class CustomerNameFilter extends TaskFilter {

  private String customerName;

  @Override
  public String label() {
    return "Customer name";
  }
  
  @Override
  public String value() {
    return StringUtils.isNotBlank(customerName) ? String.format(DOUBLE_QUOTES, customerName) : getAllLabel();
  }

  @Override
  public TaskQuery buildQuery() {
    if (StringUtils.isBlank(customerName)) {
      return null;
    }
    
    String containingKeyword = String.format("%%%s%%", customerName.trim());
    return TaskQuery.create().where().customField().stringField(CUSTOM_VARCHAR_FIELD5).isLikeIgnoreCase(containingKeyword);
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
