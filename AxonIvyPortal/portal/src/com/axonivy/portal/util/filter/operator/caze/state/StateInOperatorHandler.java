package com.axonivy.portal.util.filter.operator.caze.state;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;

import ch.ivyteam.ivy.workflow.caze.CaseBusinessState;
import ch.ivyteam.ivy.workflow.query.CaseQuery;
import ch.ivyteam.ivy.workflow.query.CaseQuery.IFilterQuery;

public class StateInOperatorHandler {
  
  private static StateInOperatorHandler instance;
  
  public static StateInOperatorHandler getInstance() {
    if(instance == null) {
      instance = new StateInOperatorHandler();
    }

    return instance;
  }
  
  public CaseQuery buildStateInQuery(DashboardFilter filter) {
    if (CollectionUtils.isEmpty(filter.getTexts())) {
      return null;
    }
    
    CaseQuery query = CaseQuery.create();
    CaseQuery subQuery = CaseQuery.create();
    IFilterQuery filterQuery = subQuery.where();
    List<CaseBusinessState> searchState = new ArrayList<>();
    filter.getTexts().forEach(text -> {
       switch (text) {
        case "OPEN" -> searchState.add(CaseBusinessState.OPEN);
        case "DONE" -> searchState.add(CaseBusinessState.DONE);
        case "DESTROYED" -> searchState.add(CaseBusinessState.DESTROYED);
        default -> {}
      };
    });
    for (CaseBusinessState state : searchState) {
      filterQuery.or().businessState().isEqual(state);
    }
    query.where().and(subQuery);
    return query;
  }
  

}
