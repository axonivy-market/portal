package ch.internalsupport;

import static ch.ivy.addon.portalkit.constant.CustomFields.CUSTOM_VARCHAR_FIELD1;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import ch.ivy.addon.portalkit.taskfilter.TaskFilter;
import ch.ivyteam.ivy.workflow.query.TaskQuery;

public class CustomerTypeFilter extends TaskFilter {

  private String selectedCustomerType;
  private List<String> customerTypes = Arrays.asList(getAllLabel(), "Exterior", "Interior");

  @Override
  public String label() {
    return "Customer type";
  }

  @Override
  public String value() {
    final String allLabel = getAllLabel();
    if (StringUtils.isBlank(selectedCustomerType)) {
      selectedCustomerType = allLabel;
    }
    
    return !allLabel.equals(selectedCustomerType) ? String.format(
        DOUBLE_QUOTES, selectedCustomerType) : allLabel;
  }

  @Override
  public TaskQuery buildQuery() {
    if (StringUtils.isBlank(selectedCustomerType) || getAllLabel().equals(selectedCustomerType)) {
      return null;
    }

    String containingKeyword = String.format("%%%s%%", selectedCustomerType.trim());
    return TaskQuery.create().where().customField().stringField(CUSTOM_VARCHAR_FIELD1).isLikeIgnoreCase(containingKeyword);
  }

  @Override
  public void resetValues() {
    selectedCustomerType = "";
  }

  public String getSelectedCustomerType() {
    return selectedCustomerType;
  }

  public void setSelectedCustomerType(String selectedCustomerType) {
    this.selectedCustomerType = selectedCustomerType;
  }

  public List<String> getCustomerTypes() {
    return customerTypes;
  }

  public void setCustomerTypes(List<String> customerTypes) {
    this.customerTypes = customerTypes;
  }

}
