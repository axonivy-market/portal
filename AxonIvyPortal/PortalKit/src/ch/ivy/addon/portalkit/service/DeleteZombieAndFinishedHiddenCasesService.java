package ch.ivy.addon.portalkit.service;

import java.util.Date;
import java.util.List;

import ch.ivy.addon.portalkit.persistence.variable.GlobalVariable;
import ch.ivy.addon.portalkit.support.DataCache;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.persistence.OrderDirection;
import ch.ivyteam.ivy.workflow.CaseProperty;
import ch.ivyteam.ivy.workflow.CaseState;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.IPropertyFilter;
import ch.ivyteam.ivy.workflow.PropertyOrder;
import ch.ivyteam.logicalexpression.RelationalOperator;

public class DeleteZombieAndFinishedHiddenCasesService {

  public void deleteZombieAndFinishedHiddenCases() {
	    Date currentDate = new java.util.Date();
	    Ivy.log().info("***Job for deleting zombie and finished hidden system cases started at: " + currentDate + " by user: " + Ivy.session().getSessionUserName());

	    IPropertyFilter<CaseProperty> zombieCaseFilter = Ivy.wf().createCasePropertyFilter(CaseProperty.STATE, RelationalOperator.EQUAL, CaseState.ZOMBIE);
	    IPropertyFilter<CaseProperty> doneCaseFilter = Ivy.wf().createCasePropertyFilter(CaseProperty.STATE, RelationalOperator.EQUAL, CaseState.DONE);
	    IPropertyFilter<CaseProperty> destroyedCaseFilter = Ivy.wf().createCasePropertyFilter(CaseProperty.STATE, RelationalOperator.EQUAL, CaseState.DESTROYED);

	    IPropertyFilter<CaseProperty> caseFilter = zombieCaseFilter.or(doneCaseFilter).or(destroyedCaseFilter);

	    // create order
	    List<PropertyOrder<CaseProperty>> propertyOrder = PropertyOrder.create(CaseProperty.ID, OrderDirection.ASCENDING);

	    List<ICase> cases = Ivy.wf().findCases(caseFilter, propertyOrder, 0, -1, false).getResultList();

	    int numOfDeletedCases = 0;
	    boolean shouldDeleteCasesInAllApps = Boolean.parseBoolean((String)DataCache.getGlobalSettingValue(GlobalVariable.DELETE_ZOMBIE_AND_FINISHED_HIDDEN_CASES_IN_ALL_APPS));
	    for (int i = cases.size()-1; i >= 0; i--) {
	      ICase iCase = (cases.get(i));
	      boolean isHiddenCase = ("HIDE").equals(iCase.getAdditionalProperty("HIDE"));
	      boolean isZombieCase = CaseState.ZOMBIE.equals(iCase.getState());
	      boolean isApplicationNamePortal = IApplication.PORTAL_APPLICATION_NAME.equals(iCase.getApplication().getName());
	      if ((isZombieCase || isHiddenCase) && (shouldDeleteCasesInAllApps ? true : isApplicationNamePortal)) {
	        try {
	          Ivy.wf().deleteCompletedCase(iCase);
	          Ivy.log().info("Case was deleted with id: " + iCase.getId());
	          numOfDeletedCases++;
	        } catch (Exception e) {
	          Ivy.log().error("Cannot delete case: " + iCase.getId(), e);
	        }
	      }
	    }

	    currentDate = new java.util.Date();
	    Ivy.log().info("***Job for deleting zombie and finished hidden cases (deleted " + numOfDeletedCases + " cases) has ended at: " + currentDate);
	  }

}
