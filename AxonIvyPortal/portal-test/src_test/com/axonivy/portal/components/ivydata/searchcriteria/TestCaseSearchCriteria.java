package com.axonivy.portal.components.ivydata.searchcriteria;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import ch.ivyteam.ivy.environment.IvyTest;
import ch.ivyteam.ivy.workflow.CaseState;
import ch.ivyteam.ivy.workflow.query.CaseQuery;

@IvyTest
class TestCaseSearchCriteria {

  @Test
  void hasIncludedStates_nullOrEmpty_returnsFalse() {
    CaseSearchCriteria criteria = new CaseSearchCriteria();
    assertThat(criteria.hasIncludedStates()).isFalse();

    criteria.setIncludedStates(List.of());
    assertThat(criteria.hasIncludedStates()).isFalse();
  }

  @Test
  void hasIncludedStates_nonEmpty_returnsTrue() {
    CaseSearchCriteria criteria = new CaseSearchCriteria();
    criteria.setIncludedStates(List.of(CaseState.RUNNING));
    assertThat(criteria.hasIncludedStates()).isTrue();
  }

  @Test
  void hasApps_nullOrEmpty_returnsFalse() {
    CaseSearchCriteria criteria = new CaseSearchCriteria();
    assertThat(criteria.hasApps()).isFalse();

    criteria.setApps(List.of());
    assertThat(criteria.hasApps()).isFalse();
  }

  @Test
  void hasApps_nonEmpty_returnsTrue() {
    CaseSearchCriteria criteria = new CaseSearchCriteria();
    criteria.setApps(List.of("portal"));
    assertThat(criteria.hasApps()).isTrue();
  }

  @Test
  void hasKeyword_blank_returnsFalse() {
    CaseSearchCriteria criteria = new CaseSearchCriteria();
    assertThat(criteria.hasKeyword()).isFalse();

    criteria.setKeyword("");
    assertThat(criteria.hasKeyword()).isFalse();
  }

  @Test
  void hasKeyword_nonEmpty_returnsTrue() {
    CaseSearchCriteria criteria = new CaseSearchCriteria();
    criteria.setKeyword("foo");
    assertThat(criteria.hasKeyword()).isTrue();
  }

  @Test
  void hasCaseId_nullOrZero_returnsFalse() {
    CaseSearchCriteria criteria = new CaseSearchCriteria();
    assertThat(criteria.hasCaseId()).isFalse();

    criteria.setCaseId(0L);
    assertThat(criteria.hasCaseId()).isFalse();
  }

  @Test
  void hasCaseId_nonZero_returnsTrue() {
    CaseSearchCriteria criteria = new CaseSearchCriteria();
    criteria.setCaseId(42L);
    assertThat(criteria.hasCaseId()).isTrue();
  }

  @Test
  void hasCategory_blank_returnsFalse() {
    CaseSearchCriteria criteria = new CaseSearchCriteria();
    assertThat(criteria.hasCategory()).isFalse();

    criteria.setCategory("   ");
    assertThat(criteria.hasCategory()).isFalse();
  }

  @Test
  void hasCategory_nonBlank_returnsTrue() {
    CaseSearchCriteria criteria = new CaseSearchCriteria();
    criteria.setCategory("finance");
    assertThat(criteria.hasCategory()).isTrue();
  }

  @Test
  void hasInvolvedUsername_blank_returnsFalse() {
    CaseSearchCriteria criteria = new CaseSearchCriteria();
    assertThat(criteria.hasInvolvedUsername()).isFalse();
  }

  @Test
  void hasInvolvedUsername_nonBlank_returnsTrue() {
    CaseSearchCriteria criteria = new CaseSearchCriteria();
    criteria.setInvolvedUsername("john");
    assertThat(criteria.hasInvolvedUsername()).isTrue();
  }

  @Test
  void extendStatesQueryByPermission_noAdminPermission_onlySetsAdminQueryFlag() {
    CaseSearchCriteria criteria = new CaseSearchCriteria();
    criteria.setIncludedStates(List.of(CaseState.RUNNING));

    criteria.extendStatesQueryByPermission(false);

    assertThat(criteria.isAdminQuery()).isFalse();
    assertThat(criteria.getIncludedStates()).containsExactly(CaseState.RUNNING);
  }

  @Test
  void extendStatesQueryByPermission_adminPermission_addsMissingAdvanceStates() {
    CaseSearchCriteria criteria = new CaseSearchCriteria();
    criteria.setIncludedStates(new ArrayList<>(List.of(CaseState.RUNNING)));

    criteria.extendStatesQueryByPermission(true);

    assertThat(criteria.isAdminQuery()).isTrue();
    assertThat(criteria.getIncludedStates())
        .contains(CaseState.RUNNING, CaseState.DONE, CaseState.DESTROYED);
  }

  @Test
  void extendStatesQueryByPermission_adminPermissionAllStatesAlreadyIncluded_doesNotDuplicate() {
    CaseSearchCriteria criteria = new CaseSearchCriteria();
    criteria.setIncludedStates(new ArrayList<>(List.of(CaseState.RUNNING, CaseState.DONE, CaseState.DESTROYED)));

    criteria.extendStatesQueryByPermission(true);

    assertThat(criteria.getIncludedStates()).containsExactly(CaseState.RUNNING, CaseState.DONE, CaseState.DESTROYED);
  }

  @Test
  void createQuery_default_returnsNonNullQuery() {
    CaseSearchCriteria criteria = new CaseSearchCriteria();
    CaseQuery query = criteria.createQuery();
    assertThat(query).isNotNull();
  }

  @Test
  void createQuery_businessCase_returnsNonNullQuery() {
    CaseSearchCriteria criteria = new CaseSearchCriteria();
    criteria.setBusinessCase(true);
    CaseQuery query = criteria.createQuery();
    assertThat(query).isNotNull();
  }

  @Test
  void createQuery_technicalCase_returnsNonNullQuery() {
    CaseSearchCriteria criteria = new CaseSearchCriteria();
    criteria.setTechnicalCase(true);
    criteria.setBusinessCaseId(1L);
    CaseQuery query = criteria.createQuery();
    assertThat(query).isNotNull();
  }

  @Test
  void createQuery_withCaseIdKeywordCategoryAndStates_returnsNonNullQuery() {
    CaseSearchCriteria criteria = new CaseSearchCriteria();
    criteria.setCaseId(5L);
    criteria.setKeyword("test");
    criteria.setCategory("finance");
    criteria.setIncludedStates(List.of(CaseState.RUNNING));

    CaseQuery query = criteria.createQuery();
    assertThat(query).isNotNull();
  }

  @Test
  void createQuery_sortByEachField_returnsNonNullQuery() {
    for (String field : List.of("NAME", "ID", "CREATION_TIME", "FINISHED_TIME", "CREATOR", "STATE")) {
      CaseSearchCriteria criteria = new CaseSearchCriteria();
      criteria.setSortField(field);
      criteria.setSortDescending(true);
      assertThat(criteria.createQuery()).isNotNull();
    }
  }

  @Test
  void createQuery_notSorted_returnsNonNullQuery() {
    CaseSearchCriteria criteria = new CaseSearchCriteria();
    criteria.setSorted(false);
    assertThat(criteria.createQuery()).isNotNull();
  }
}
