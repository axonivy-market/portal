package com.axonivy.portal.util.filter.operator.caze.state;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
    if (CollectionUtils.isEmpty(filter.getValues())) {
      return null;
    }

    CaseQuery query = CaseQuery.create();
    IFilterQuery filterQuery = query.where();

    List<CaseBusinessState> states = filter.getValues().stream()
        .map(text -> CaseBusinessState.valueOf(text))
        .filter(Objects::nonNull)
        .collect(Collectors.toList());

    for (CaseBusinessState state : states) {
      filterQuery.or().businessState().isEqual(state);
    }
    return query;
  }
}