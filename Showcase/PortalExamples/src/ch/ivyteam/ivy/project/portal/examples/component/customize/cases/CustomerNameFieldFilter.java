package ch.ivyteam.ivy.project.portal.examples.component.customize.cases;

import static ch.ivy.addon.portalkit.constant.CustomFields.CUSTOM_VARCHAR_FIELD1;

import org.apache.commons.lang.StringUtils;

import ch.ivy.addon.portalkit.casefilter.CaseFilter;
import ch.ivy.addon.portalkit.util.CaseUtils;
import ch.ivyteam.ivy.workflow.query.CaseQuery;

public class CustomerNameFieldFilter extends CaseFilter {
	private String customerNameField;

	@Override
	public String label() {
		return "Customer Name";
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
		return CaseUtils.createBusinessCaseQuery().where().customField().stringField(CUSTOM_VARCHAR_FIELD1).isLikeIgnoreCase(containingKeyword);
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
