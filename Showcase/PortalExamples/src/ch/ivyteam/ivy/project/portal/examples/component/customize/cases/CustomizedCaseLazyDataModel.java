package ch.ivyteam.ivy.project.portal.examples.component.customize.cases;

import ch.ivy.addon.portalkit.bo.RemoteCase;
import ch.ivy.addon.portalkit.comparator.RemoteCaseComparator;
import ch.ivy.addon.portalkit.datamodel.CaseLazyDataModel;
import ch.ivyteam.ivy.workflow.query.CaseQuery;

public class CustomizedCaseLazyDataModel extends CaseLazyDataModel {
	private static final String CUSTOM_TIMESTAMP_FIELD1 = "customTimestampField1";
	private static final String CUSTOM_VARCHAR_FIELD5 = "customVarcharField5";

	public CustomizedCaseLazyDataModel() {
		super();
	}

	public CustomizedCaseLazyDataModel(String caseWidgetComponentId) {
		super(caseWidgetComponentId);
	}

	// ===================Basic customization, extend columns====================

	// Extend sort fields, include 1 text field and 1 date time field
	@Override
	public void extendSort(CaseQuery caseQuery) {
		if (CUSTOM_VARCHAR_FIELD5.equalsIgnoreCase(queryCriteria.getSortField())) {
			if (queryCriteria.isSortDescending()) {
				caseQuery.orderBy().customVarCharField5().descending();
			} else {
				caseQuery.orderBy().customVarCharField5();
			}
		} else if (CUSTOM_TIMESTAMP_FIELD1.equalsIgnoreCase(queryCriteria.getSortField())) {
			if (queryCriteria.isSortDescending()) {
				caseQuery.orderBy().customTimestampField1().descending();
			} else {
				caseQuery.orderBy().customTimestampField1();
			}
		}
	}

	@Override
	public void extendSortTasksInNotDisplayedTaskMap() {
		if (CUSTOM_VARCHAR_FIELD5.equalsIgnoreCase(queryCriteria.getSortField())) {
			comparator = RemoteCaseComparator.comparatorString(RemoteCase::getCustomVarCharField5);
		} else if (CUSTOM_TIMESTAMP_FIELD1.equalsIgnoreCase(queryCriteria.getSortField())) {
			comparator = RemoteCaseComparator.naturalOrderNullsFirst(RemoteCase::getCustomTimestampField1);
		}
	}
}
