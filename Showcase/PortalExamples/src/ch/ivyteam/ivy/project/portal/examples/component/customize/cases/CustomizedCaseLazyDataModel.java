package ch.ivyteam.ivy.project.portal.examples.component.customize.cases;

import java.util.Arrays;
import java.util.List;

import ch.ivy.addon.portalkit.bo.RemoteCase;
import ch.ivy.addon.portalkit.comparator.RemoteCaseComparator;
import ch.ivy.addon.portalkit.datamodel.CaseLazyDataModel;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.query.CaseQuery;

public class CustomizedCaseLazyDataModel extends CaseLazyDataModel {
	private static final String CUSTOM_TIMESTAMP_FIELD1 = "customTimestampField1";
	private static final String CUSTOM_VARCHAR_FIELD1 = "customVarcharField1";

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
		if (CUSTOM_VARCHAR_FIELD1.equalsIgnoreCase(queryCriteria.getSortField())) {
			if (queryCriteria.isSortDescending()) {
				caseQuery.orderBy().customVarCharField1().descending();
			} else {
				caseQuery.orderBy().customVarCharField1();
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
	public void extendSortCasesInNotDisplayedCaseMap() {
		if (CUSTOM_VARCHAR_FIELD1.equalsIgnoreCase(queryCriteria.getSortField())) {
			comparator = RemoteCaseComparator.comparatorString(RemoteCase::getCustomVarCharField1);
		} else if (CUSTOM_TIMESTAMP_FIELD1.equalsIgnoreCase(queryCriteria.getSortField())) {
			comparator = RemoteCaseComparator.naturalOrderNullsFirst(RemoteCase::getCustomTimestampField1);
		}
	}
	
	@Override
	protected List<String> getDefaultColumns() {
	  return Arrays.asList("NAME", "ID", "CREATOR", "CREATION_TIME", "EXPIRY_TIME", "STATE", CUSTOM_VARCHAR_FIELD1, CUSTOM_TIMESTAMP_FIELD1);
	}
	
	@Override
  public String getColumnLabel(String column) {
    return Ivy.cms().co("/DefaultColumns/caseList/" + column);
  }

	@Override
	protected void initFilterContainer() {
		filterContainer = new CustomizedCaseFilterContainer();
	}
}
