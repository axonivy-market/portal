package com.axonivy.portal.components.ivydata.service.impl;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import com.axonivy.portal.components.ivydata.searchcriteria.CaseSearchCriteria;

import ch.ivyteam.ivy.environment.IvyTest;

/**
 * Only admin-query criteria are used here: {@code extendQuery} calls
 * {@code PermissionUtils.checkCaseReadAllOwnRoleInvolvedPermission()} (blocked,
 * needs real security/role setup) unless {@code isAdminQuery} is true, which
 * skips that call entirely. Non-admin queries are not covered for that reason.
 */
@IvyTest
class TestCaseService {

  private final CaseService service = CaseService.newInstance();

  @Test
  void findCasesByCriteria_adminQuery_returnsResultWithoutThrowing() {
    CaseSearchCriteria criteria = new CaseSearchCriteria();
    criteria.setBusinessCase(true);
    criteria.setAdminQuery(true);

    var result = service.findCasesByCriteria(criteria, 0, 10);

    assertThat(result).isNotNull();
    assertThat(result.getCases()).isNotNull();
  }

  @Test
  void countCasesByCriteria_adminQuery_returnsNonNegativeCount() {
    CaseSearchCriteria criteria = new CaseSearchCriteria();
    criteria.setBusinessCase(true);
    criteria.setAdminQuery(true);

    var result = service.countCasesByCriteria(criteria);

    assertThat(result).isNotNull();
    assertThat(result.getTotalCases()).isGreaterThanOrEqualTo(0);
  }
}
