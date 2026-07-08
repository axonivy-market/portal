package com.axonivy.portal.service.complexfilter.operatorpolicy;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;
import com.axonivy.portal.enums.dashboard.filter.FilterOperator;
import com.axonivy.portal.service.filter.operatorpolicy.service.GlobalOperatorPolicyService;

import ch.ivy.addon.portalkit.dto.dashboard.ColumnModel;
import ch.ivy.addon.portalkit.enums.DashboardStandardTaskColumn;
import ch.ivy.addon.portalkit.enums.GlobalVariable;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.environment.IvyTest;

@IvyTest
class TestGlobalOperatorPolicyService {

  private static final String OPERATOR_POLICY_KEY = GlobalVariable.FILTER_OPERATOR_POLICY.getKey();

  @AfterEach
  void tearDown() {
    Ivy.var().set(OPERATOR_POLICY_KEY, GlobalVariable.FILTER_OPERATOR_POLICY.getDefaultValue());
  }

  // ── isOperatorGloballyEnabled ──────────────────────────────────────────────

  @Test
  void isOperatorGloballyEnabled_returnsTrue_whenNothingIsDisabled() {
    GlobalOperatorPolicyService service = withDisabledOps();

    assertThat(service.isOperatorGloballyEnabled(FilterOperator.CONTAINS)).isTrue();
    assertThat(service.isOperatorGloballyEnabled(FilterOperator.IS)).isTrue();
  }

  @ParameterizedTest(name = "isOperatorGloballyEnabled_returnsFalse_for{0}")
  @EnumSource(value = FilterOperator.class, names = {"CONTAINS", "IS", "BEFORE", "AFTER", "EQUAL"})
  void isOperatorGloballyEnabled_returnsFalse_whenOperatorIsDisabled(FilterOperator disabled) {
    GlobalOperatorPolicyService service = withDisabledOps(disabled);

    assertThat(service.isOperatorGloballyEnabled(disabled)).isFalse();
  }

  @Test
  void isOperatorGloballyEnabled_returnsTrue_forOperatorNotInDisabledSet() {
    GlobalOperatorPolicyService service = withDisabledOps(FilterOperator.BEFORE);

    assertThat(service.isOperatorGloballyEnabled(FilterOperator.AFTER)).isTrue();
  }

  // ── isFilterAllowedByGlobalPolicy ─────────────────────────────────────────

  @Test
  void isFilterAllowedByGlobalPolicy_returnsTrue_whenFilterOperatorIsNotDisabled() {
    GlobalOperatorPolicyService service = withDisabledOps(FilterOperator.BEFORE);
    DashboardFilter filter = OperatorPolicyFixtures.filterWithOperator(FilterOperator.AFTER);

    assertThat(service.isFilterAllowedByGlobalPolicy(filter)).isTrue();
  }

  @Test
  void isFilterAllowedByGlobalPolicy_returnsFalse_whenFilterOperatorIsDisabled() {
    GlobalOperatorPolicyService service = withDisabledOps(FilterOperator.AFTER);
    DashboardFilter filter = OperatorPolicyFixtures.filterWithOperator(FilterOperator.AFTER);

    assertThat(service.isFilterAllowedByGlobalPolicy(filter)).isFalse();
  }

  // ── keepGloballyEnabledOperators ──────────────────────────────────────────

  @Test
  void keepGloballyEnabledOperators_removesDisabledOps() {
    GlobalOperatorPolicyService service = withDisabledOps(FilterOperator.BEFORE, FilterOperator.AFTER);

    List<FilterOperator> result = service.keepGloballyEnabledOperators(
        List.of(FilterOperator.CONTAINS, FilterOperator.BEFORE, FilterOperator.IS, FilterOperator.AFTER));

    assertThat(result)
        .containsExactlyInAnyOrder(FilterOperator.CONTAINS, FilterOperator.IS)
        .doesNotContain(FilterOperator.BEFORE, FilterOperator.AFTER);
  }

  @Test
  void keepGloballyEnabledOperators_returnsAllOps_whenNoneAreDisabled() {
    GlobalOperatorPolicyService service = withDisabledOps();
    List<FilterOperator> input = List.of(FilterOperator.CONTAINS, FilterOperator.IS, FilterOperator.BEFORE);

    assertThat(service.keepGloballyEnabledOperators(input))
        .containsExactlyInAnyOrderElementsOf(input);
  }

  @Test
  void keepGloballyEnabledOperators_returnsEmpty_whenAllInputOpsAreDisabled() {
    GlobalOperatorPolicyService service = withDisabledOps(FilterOperator.CONTAINS, FilterOperator.IS);

    assertThat(service.keepGloballyEnabledOperators(
        List.of(FilterOperator.CONTAINS, FilterOperator.IS))).isEmpty();
  }

  @Test
  void keepGloballyEnabledOperators_preservesInputOrder() {
    GlobalOperatorPolicyService service = withDisabledOps(FilterOperator.BEFORE);
    List<FilterOperator> input = List.of(FilterOperator.IS, FilterOperator.CONTAINS, FilterOperator.AFTER);

    assertThat(service.keepGloballyEnabledOperators(input))
        .containsExactly(FilterOperator.IS, FilterOperator.CONTAINS, FilterOperator.AFTER);
  }

  // ── getColumnsWithGloballyEnabledOperators ─────────────────────────────────

  @Test
  void getColumnsWithGloballyEnabledOperators_excludesColumns_whenAllTheirOpsAreDisabled() {
    GlobalOperatorPolicyService service =
        withDisabledOps(FilterOperator.TEXT_OPERATORS.toArray(new FilterOperator[0]));

    ColumnModel nameCol = OperatorPolicyFixtures.filterableColumn(DashboardStandardTaskColumn.NAME.getField());
    ColumnModel stateCol = OperatorPolicyFixtures.filterableColumn(DashboardStandardTaskColumn.STATE.getField());

    assertThat(service.getColumnsWithGloballyEnabledOperators(List.of(nameCol, stateCol)))
        .containsExactly(stateCol)
        .doesNotContain(nameCol);
  }

  @Test
  void getColumnsWithGloballyEnabledOperators_returnsAllColumns_whenNothingIsDisabled() {
    GlobalOperatorPolicyService service = withDisabledOps();
    ColumnModel col1 = OperatorPolicyFixtures.filterableColumn(DashboardStandardTaskColumn.NAME.getField());
    ColumnModel col2 = OperatorPolicyFixtures.filterableColumn(DashboardStandardTaskColumn.STATE.getField());

    assertThat(service.getColumnsWithGloballyEnabledOperators(List.of(col1, col2)))
        .containsExactly(col1, col2);
  }

  @Test
  void getColumnsWithGloballyEnabledOperators_returnsEmpty_forEmptyInput() {
    GlobalOperatorPolicyService service = withDisabledOps();

    assertThat(service.getColumnsWithGloballyEnabledOperators(List.of())).isEmpty();
  }

  // ── resolveEffectiveOperators ──────────────────────────────────────────────

  @Test
  void resolveEffectiveOperators_filtersOutDisabledOps_fromBaselineForNameField() {
    GlobalOperatorPolicyService service = withDisabledOps(FilterOperator.CONTAINS);
    DashboardFilter filter = OperatorPolicyFixtures.filterForField(DashboardStandardTaskColumn.NAME.getField());

    List<FilterOperator> effective = service.resolveEffectiveOperators(filter);

    assertThat(effective).doesNotContain(FilterOperator.CONTAINS);
    assertThat(effective).isNotEmpty();
  }

  @Test
  void resolveEffectiveOperators_returnsEmpty_whenAllBaselineOpsForFieldAreDisabled() {
    GlobalOperatorPolicyService service =
        withDisabledOps(FilterOperator.TEXT_OPERATORS.toArray(new FilterOperator[0]));
    DashboardFilter filter = OperatorPolicyFixtures.filterForField(DashboardStandardTaskColumn.NAME.getField());

    assertThat(service.resolveEffectiveOperators(filter)).isEmpty();
  }

  // ── findFirstEnabledOp ─────────────────────────────────────────────────────

  @Test
  void findFirstEnabledOp_returnsDefaultOp_whenDefaultIsEnabled() {
    GlobalOperatorPolicyService service = withDisabledOps();
    DashboardFilter filter = OperatorPolicyFixtures.filterForField(DashboardStandardTaskColumn.NAME.getField());
    FilterOperator defaultOp = FilterOperator.CONTAINS;

    Optional<FilterOperator> result = service.findFirstEnabledOp(filter, defaultOp);

    assertThat(result).contains(defaultOp);
  }

  @Test
  void findFirstEnabledOp_returnsFallbackOp_whenDefaultIsDisabled() {
    FilterOperator defaultOp = FilterOperator.CONTAINS;
    GlobalOperatorPolicyService service = withDisabledOps(defaultOp);
    DashboardFilter filter = OperatorPolicyFixtures.filterForField(DashboardStandardTaskColumn.NAME.getField());

    Optional<FilterOperator> result = service.findFirstEnabledOp(filter, defaultOp);

    assertThat(result).isPresent().get().isNotEqualTo(defaultOp);
  }

  @Test
  void findFirstEnabledOp_returnsEmpty_whenNoEffectiveOperatorsRemain() {
    GlobalOperatorPolicyService service =
        withDisabledOps(FilterOperator.TEXT_OPERATORS.toArray(new FilterOperator[0]));
    DashboardFilter filter = OperatorPolicyFixtures.filterForField(DashboardStandardTaskColumn.NAME.getField());

    assertThat(service.findFirstEnabledOp(filter, FilterOperator.CONTAINS)).isEmpty();
  }

  // ── getBaselineOperatorService ─────────────────────────────────────────────

  @Test
  void getBaselineOperatorService_returnsNonNull() {
    assertThat(withDisabledOps().getBaselineOperatorService()).isNotNull();
  }

  @Test
  void getBaselineOperatorService_resolvesForColumn_consistentWithDirectBaseline() {
    GlobalOperatorPolicyService service = withDisabledOps();
    ColumnModel col = OperatorPolicyFixtures.filterableColumn(DashboardStandardTaskColumn.NAME.getField());

    assertThat(service.getBaselineOperatorService().resolveForColumn(col))
        .containsExactlyInAnyOrderElementsOf(FilterOperator.TEXT_OPERATORS);
  }

  private static GlobalOperatorPolicyService withDisabledOps(FilterOperator... disabled) {
    Set<FilterOperator> disabledSet = Set.of(disabled);
    String enabledValue = Arrays.stream(FilterOperator.values())
        .filter(op -> !disabledSet.contains(op))
        .map(FilterOperator::name)
        .collect(Collectors.joining(","));

    Ivy.var().set(OPERATOR_POLICY_KEY, enabledValue);
    return new GlobalOperatorPolicyService();
  }
}