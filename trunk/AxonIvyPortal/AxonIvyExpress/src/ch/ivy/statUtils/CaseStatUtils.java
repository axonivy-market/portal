package ch.ivy.statUtils;

import static ch.ivyteam.ivy.workflow.CaseProperty.START_TIMESTAMP;
import static ch.ivyteam.logicalexpression.RelationalOperator.EQUAL_OR_LARGER;

import java.util.Date;
import java.util.List;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.CaseProperty;
import ch.ivyteam.ivy.workflow.CaseState;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.IPropertyFilter;
import ch.ivyteam.logicalexpression.RelationalOperator;

public class CaseStatUtils {

	public static int getFinishedProcessesCount(Date from) {
		// finished cases
		IPropertyFilter<CaseProperty> filter = Ivy.wf()
				.createCasePropertyFilter(CaseProperty.STATE,
						RelationalOperator.EQUAL, CaseState.DONE.intValue());
		// date filter
		filter = filter.and(Ivy.wf().createCasePropertyFilter(START_TIMESTAMP,
				EQUAL_OR_LARGER, from));

		List<ICase> caseList = ch.ivy.addon.portalkit.util.CaseUtils
				.findICases(filter);

		return caseList.size();
	}

	public static int getRunningProcessesCount(Date from) {
		// running cases
		IPropertyFilter<CaseProperty> filter = Ivy.wf()
				.createCasePropertyFilter(CaseProperty.STATE,
						RelationalOperator.EQUAL, CaseState.RUNNING.intValue());
		filter = filter.or(CaseProperty.STATE, RelationalOperator.EQUAL,
				CaseState.CREATED.intValue());
		// date filter
		filter = filter.and(Ivy.wf().createCasePropertyFilter(START_TIMESTAMP,
				EQUAL_OR_LARGER, from));

		List<ICase> caseList = ch.ivy.addon.portalkit.util.CaseUtils
				.findICases(filter);

		return caseList.size();
	}

}
