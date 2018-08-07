package ch.ivy.statUtils;

import static ch.ivy.addon.portalkit.enums.CaseType.SYSTEMIVY;
import static ch.ivyteam.ivy.workflow.CaseProperty.PROCESS_CATEGORY_CODE;
import static ch.ivyteam.ivy.workflow.CaseProperty.START_TIMESTAMP;
import static ch.ivyteam.logicalexpression.RelationalOperator.IS;
import static ch.ivyteam.logicalexpression.RelationalOperator.EQUAL_OR_LARGER;
import static ch.ivyteam.logicalexpression.RelationalOperator.UNEQUAL;

import java.util.Date;
import java.util.List;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.CaseProperty;
import ch.ivyteam.ivy.workflow.CaseState;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.IPropertyFilter;
import ch.ivyteam.logicalexpression.RelationalOperator;

public class CaseStatUtils {
	public static long getAverageProcessTime() {

		List<ICase> caseList = ch.ivy.addon.portalkit.util.CaseUtils
				.findICasesFinished();

		long summedTime = 0;
		if (caseList != null && caseList.size() > 0) {
			for (ICase c : caseList) {
				summedTime += (c.getEndTimestamp().getTime() - c
						.getStartTimestamp().getTime());
			}
			summedTime = summedTime / caseList.size();
		}

		return summedTime;
	}

	public static int getFinishedProcessesCount(Date from) {
		// finished cases
		IPropertyFilter<CaseProperty> filter = Ivy.wf()
				.createCasePropertyFilter(CaseProperty.STATE,
						RelationalOperator.EQUAL, CaseState.DONE.intValue());
		// date filter
		filter = filter.and(Ivy.wf().createCasePropertyFilter(START_TIMESTAMP,
				EQUAL_OR_LARGER, from));
		// System cases
		IPropertyFilter<CaseProperty> categoryIsNull = Ivy.wf()
				.createCasePropertyFilter(PROCESS_CATEGORY_CODE, IS, null);
		IPropertyFilter<CaseProperty> categoryIsNotSystemIvy = Ivy.wf()
				.createCasePropertyFilter(PROCESS_CATEGORY_CODE, UNEQUAL,
						SYSTEMIVY.name());
		IPropertyFilter<CaseProperty> notSystemCase = categoryIsNull
				.or(categoryIsNotSystemIvy);

		List<ICase> caseList = ch.ivy.addon.portalkit.util.CaseUtils
				.findICases(filter.and(notSystemCase));

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
		// System cases
		IPropertyFilter<CaseProperty> categoryIsNull = Ivy.wf()
				.createCasePropertyFilter(PROCESS_CATEGORY_CODE, IS, null);
		IPropertyFilter<CaseProperty> categoryIsNotSystemIvy = Ivy.wf()
				.createCasePropertyFilter(PROCESS_CATEGORY_CODE, UNEQUAL,
						SYSTEMIVY.name());
		IPropertyFilter<CaseProperty> notSystemCase = categoryIsNull
				.or(categoryIsNotSystemIvy);

		List<ICase> caseList = ch.ivy.addon.portalkit.util.CaseUtils
				.findICases(filter.and(notSystemCase));

		return caseList.size();
	}

}
