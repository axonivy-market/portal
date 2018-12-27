package ch.ivy.addon.portalkit.service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import ch.ivy.addon.portalkit.enums.PortalLibrary;
import ch.ivyteam.ivy.application.IProcessModelVersion;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.CaseState;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.query.CaseQuery;
import ch.ivyteam.ivy.workflow.query.CaseQuery.IFilterQuery;

public class DeleteFinishedHiddenCasesService {

  private static final String DELETE_ALL_FINISHED_HIDDEN_CASES = "PortalDeleteAllFinishedHiddenCases";

  public void deleteFinishedHiddenCases() {
	    Date currentDate = new java.util.Date();
	    Ivy.log().info("***Job for deleting finished hidden system cases started at: " + currentDate + " by user: " + Ivy.session().getSessionUserName());

	    boolean shouldDeleteAllCases = Boolean.parseBoolean(Ivy.var().get(DELETE_ALL_FINISHED_HIDDEN_CASES));
	    PortalConnectorDetector detector = new PortalConnectorDetector();
	    IProcessModelVersion portalKitPMV = detector.findPortalPMVByLibraryId(Ivy.wf().getApplication(), PortalLibrary.PORTAL_KIT.getValue());
	    CaseQuery caseQuery = CaseQuery.create();
	    List<CaseState> filteredStates = Arrays.asList(CaseState.DONE, CaseState.DESTROYED);
	    CaseQuery stateFieldQuery = CaseQuery.create();
	    IFilterQuery stateFilteredQuery = stateFieldQuery.where();
	    for (CaseState state : filteredStates) {
	    	stateFilteredQuery.or().state().isEqual(state);
	    }
	    caseQuery.where().and(stateFieldQuery);
	    if (!shouldDeleteAllCases) {
	    	//filter only cases generated by Portal
	    	CaseQuery pmvQuery = CaseQuery.create();
	    	pmvQuery.where().processModelId().isEqual(portalKitPMV.getId());
	    	caseQuery.where().and(pmvQuery);
	    }
	    caseQuery.orderBy().caseId().ascending();
	    List<ICase> cases = Ivy.wf().getCaseQueryExecutor().getResults(caseQuery, 0, -1);

	    int numOfDeletedCases = 0;
	    for (int i = cases.size()-1; i >= 0; i--) {
	      ICase iCase = (cases.get(i));
	      boolean isHiddenCase = ("HIDE").equals(iCase.getAdditionalProperty("HIDE"));
	      if (isHiddenCase) {
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
	    Ivy.log().info("***Job for deleting finished hidden cases (deleted " + numOfDeletedCases + " cases) has ended at: " + currentDate);
  }

}
