package com.axonivy.portal.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import com.axonivy.portal.enums.SearchScopeCaseField;
import com.axonivy.portal.enums.SearchScopeTaskField;

import ch.ivy.addon.portalkit.enums.GlobalVariable;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.environment.IvyTest;

@IvyTest
class TestSearchScopeUtils {

  private static final String TASK_FIELDS_KEY = GlobalVariable.SEARCH_SCOPE_BY_TASK_FIELDS.getKey();
  private static final String CASE_FIELDS_KEY = GlobalVariable.SEARCH_SCOPE_BY_CASE_FIELDS.getKey();

  @AfterEach
  void tearDown() {
    Ivy.var().set(TASK_FIELDS_KEY, GlobalVariable.SEARCH_SCOPE_BY_TASK_FIELDS.getDefaultValue());
    Ivy.var().set(CASE_FIELDS_KEY, GlobalVariable.SEARCH_SCOPE_BY_CASE_FIELDS.getDefaultValue());
  }

  @Test
  void getSearchScopeTaskFields_returnsConfiguredFields() {
    Ivy.var().set(TASK_FIELDS_KEY, "NAME,DESCRIPTION");

    assertThat(SearchScopeUtils.getSearchScopeTaskFields())
        .containsExactly(SearchScopeTaskField.NAME, SearchScopeTaskField.DESCRIPTION);
  }

  @Test
  void getSearchScopeTaskFields_emptyScope_returnsEmptyListInsteadOfDefault() {
    Ivy.var().set(TASK_FIELDS_KEY, "");

    assertThat(SearchScopeUtils.getSearchScopeTaskFields()).isEmpty();
  }

  @Test
  void getSearchScopeTaskFields_blankScope_returnsEmptyList() {
    Ivy.var().set(TASK_FIELDS_KEY, "   ");

    assertThat(SearchScopeUtils.getSearchScopeTaskFields()).isEmpty();
  }

  @Test
  void getSearchScopeTaskFields_toleratesLowerCaseAndSurroundingSpaces() {
    Ivy.var().set(TASK_FIELDS_KEY, " name , description ");

    assertThat(SearchScopeUtils.getSearchScopeTaskFields())
        .containsExactly(SearchScopeTaskField.NAME, SearchScopeTaskField.DESCRIPTION);
  }

  @Test
  void getSearchScopeTaskFields_skipsEmptySegments() {
    Ivy.var().set(TASK_FIELDS_KEY, "NAME,,DESCRIPTION,");

    assertThat(SearchScopeUtils.getSearchScopeTaskFields())
        .containsExactly(SearchScopeTaskField.NAME, SearchScopeTaskField.DESCRIPTION);
  }

  @Test
  void getSearchScopeTaskFields_keepsConfiguredOrder() {
    Ivy.var().set(TASK_FIELDS_KEY, "DESCRIPTION,NAME");

    assertThat(SearchScopeUtils.getSearchScopeTaskFields())
        .containsExactly(SearchScopeTaskField.DESCRIPTION, SearchScopeTaskField.NAME);
  }

  @Test
  void getSearchScopeTaskFields_unknownField_throws() {
    Ivy.var().set(TASK_FIELDS_KEY, "NAME,NOT_A_FIELD");

    assertThatThrownBy(SearchScopeUtils::getSearchScopeTaskFields)
        .isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  void getSearchScopeCaseFields_returnsConfiguredFields() {
    Ivy.var().set(CASE_FIELDS_KEY, "NAME,DESCRIPTION,CUSTOM");

    assertThat(SearchScopeUtils.getSearchScopeCaseFields())
        .containsExactly(SearchScopeCaseField.NAME, SearchScopeCaseField.DESCRIPTION,
            SearchScopeCaseField.CUSTOM);
  }

  @Test
  void getSearchScopeCaseFields_emptyScope_returnsEmptyListInsteadOfDefault() {
    Ivy.var().set(CASE_FIELDS_KEY, "");

    assertThat(SearchScopeUtils.getSearchScopeCaseFields()).isEmpty();
  }

  @Test
  void getSearchScopeCaseFields_toleratesLowerCaseAndSurroundingSpaces() {
    Ivy.var().set(CASE_FIELDS_KEY, " custom , name ");

    assertThat(SearchScopeUtils.getSearchScopeCaseFields())
        .containsExactly(SearchScopeCaseField.CUSTOM, SearchScopeCaseField.NAME);
  }

  @Test
  void getSearchScopeCaseFields_unknownField_throws() {
    Ivy.var().set(CASE_FIELDS_KEY, "CUSTOM,NOT_A_FIELD");

    assertThatThrownBy(SearchScopeUtils::getSearchScopeCaseFields)
        .isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  void taskAndCaseScopes_areResolvedIndependently() {
    Ivy.var().set(TASK_FIELDS_KEY, "");
    Ivy.var().set(CASE_FIELDS_KEY, "NAME");

    assertThat(SearchScopeUtils.getSearchScopeTaskFields()).isEmpty();
    assertThat(SearchScopeUtils.getSearchScopeCaseFields())
        .containsExactly(SearchScopeCaseField.NAME);
  }
}
