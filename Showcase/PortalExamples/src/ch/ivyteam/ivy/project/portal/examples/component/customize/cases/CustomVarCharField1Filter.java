package ch.ivyteam.ivy.project.portal.examples.component.customize.cases;

import org.apache.commons.lang.StringUtils;

import ch.ivy.addon.portalkit.casefilter.CaseFilter;
import ch.ivyteam.ivy.workflow.query.CaseQuery;

public class CustomVarCharField1Filter extends CaseFilter {
	private String customVarCharField1;

	@Override
	public String label() {
		return "customVarCharField1";
	}

	@Override
	public String value() {
		return StringUtils.isNotBlank(customVarCharField1) ? String.format(DOUBLE_QUOTES, customVarCharField1) : ALL;
	}

	@Override
	public CaseQuery buildQuery() {
		if (StringUtils.isBlank(customVarCharField1)) {
			return null;
		}

		String containingKeyword = String.format("%%%s%%", customVarCharField1.trim());
		return CaseQuery.create().where().customVarCharField1().isLikeIgnoreCase(containingKeyword);
	}

	@Override
	public void resetValues() {
		customVarCharField1 = StringUtils.EMPTY;
	}

	public String getCustomVarCharField1() {
		return customVarCharField1;
	}

	public void setcustomVarCharField1(String description) {
		this.customVarCharField1 = description;
	}
}
