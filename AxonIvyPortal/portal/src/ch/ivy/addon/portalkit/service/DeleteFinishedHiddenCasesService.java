package ch.ivy.addon.portalkit.service;

import static ch.ivy.addon.portalkit.constant.PortalConstants.PORTAL_LIBRARY_ID;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.enums.AdditionalProperty;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.application.ILibrary;
import ch.ivyteam.ivy.application.IProcessModel;
import ch.ivyteam.ivy.application.IProcessModelVersion;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.exec.Sudo;
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
    IProcessModelVersion portalPMV = findPortalPMVByLibraryId(IApplication.current(), PORTAL_LIBRARY_ID);
    if (portalPMV == null) {
      Ivy.log().warn("Can not retrieve portal PMV");
      return ;
    }
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
      pmvQuery.where().processModelId().isEqual(portalPMV.getId());
      caseQuery.where().and(pmvQuery);
    }
    caseQuery.orderBy().caseId().ascending();
    List<ICase> cases = Ivy.wf().getCaseQueryExecutor().getResults(caseQuery, 0, -1);

    int numOfDeletedCases = 0;
    for (int i = cases.size()-1; i >= 0; i--) {
      ICase iCase = (cases.get(i));
      boolean isHiddenCase = (AdditionalProperty.HIDE.toString()).equals(iCase.customFields().stringField(AdditionalProperty.HIDE.toString()).getOrDefault(StringUtils.EMPTY));
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

  private IProcessModelVersion findPortalPMVByLibraryId(IApplication app, String libraryId) {
    try {
      return Sudo.call(()->{
        List<IProcessModel> pms = app.getProcessModels();
        for (IProcessModel pm : pms) {
          IProcessModelVersion pmv = pm.getReleasedProcessModelVersion();
          if (isPortalPMV(pmv, libraryId)) {
            return pmv;
          }
        }
        return null;
      });
    } catch (Exception e) {
      Ivy.log().error("Error find portal library id: {0}", e, libraryId);
      return null;
    }
  }

  private boolean isPortalPMV(IProcessModelVersion pmv, String libraryId) {
    if (pmv == null) {
      return false;
    }
    ILibrary library = pmv.getLibrary();
    if (library != null) {
      return libraryId.equals(library.getId());
    } else {
      return false;
    }
  }
}
