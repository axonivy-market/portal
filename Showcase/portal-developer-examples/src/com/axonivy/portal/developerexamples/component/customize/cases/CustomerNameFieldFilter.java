package com.axonivy.portal.developerexamples.component.customize.cases;

import static com.axonivy.portal.developerexamples.component.customize.cases.CustomizedCaseLazyDataModel.CUSTOM_CUSTOMER_NAME;

import org.apache.commons.lang.StringUtils;

import ch.ivy.addon.portalkit.casefilter.CaseFilter;
import ch.ivy.addon.portalkit.util.CaseUtils;
import ch.ivyteam.ivy.workflow.custom.field.ICustomFieldMeta;
import ch.ivyteam.ivy.workflow.query.CaseQuery;

public class CustomerNameFieldFilter extends CaseFilter {
  private String customerNameField;

  @Override
  public String label() {
    var customFieldMeta =
        ICustomFieldMeta.cases().stream().filter(meta -> meta.name().equals(CUSTOM_CUSTOMER_NAME)).findAny();
    return customFieldMeta.isPresent() ? customFieldMeta.get().label() : CUSTOM_CUSTOMER_NAME;
  }

  @Override
  public String value() {
    return StringUtils.isNotBlank(customerNameField) ? String.format(DOUBLE_QUOTES, customerNameField) : ALL;
  }

  @Override
  public CaseQuery buildQuery() {
    if (StringUtils.isBlank(customerNameField)) {
      return null;
    }

    String containingKeyword = String.format("%%%s%%", customerNameField.trim());
    return CaseUtils.createBusinessCaseQuery().where().customField().stringField(CUSTOM_CUSTOMER_NAME)
        .isLikeIgnoreCase(containingKeyword);
  }

  @Override
  public void resetValues() {
    customerNameField = StringUtils.EMPTY;
  }

  public String getCustomerNameField() {
    return customerNameField;
  }

  public void setCustomerNameField(String description) {
    this.customerNameField = description;
  }
}
