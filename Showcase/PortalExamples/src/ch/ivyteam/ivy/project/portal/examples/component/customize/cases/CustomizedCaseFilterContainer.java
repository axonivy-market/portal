package ch.ivyteam.ivy.project.portal.examples.component.customize.cases;

import java.util.Collections;

import ch.ivy.addon.portalkit.casefilter.DefaultCaseFilterContainer;
import ch.ivy.addon.portalkit.comparator.CaseFilterComparator;

public class CustomizedCaseFilterContainer extends DefaultCaseFilterContainer {

	private CustomVarCharField1Filter customVarCharField1Filter = new CustomVarCharField1Filter();

	public CustomizedCaseFilterContainer() {
		super();
		filters.add(customVarCharField1Filter);
		Collections.sort(filters, new CaseFilterComparator());
	}

	public CustomVarCharField1Filter getCustomVarCharField1Filter() {
		return customVarCharField1Filter;
	}

	public void setCustomVarCharField1Filter(CustomVarCharField1Filter customVarCharField1Filter) {
		this.customVarCharField1Filter = customVarCharField1Filter;
	}
}
