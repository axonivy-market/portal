package com.axonivy.portal.service.complexfilter.operatorpolicy;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;
import com.axonivy.portal.enums.dashboard.filter.FilterOperator;
import com.axonivy.portal.service.filter.operatorpolicy.model.OperatorPolicy;
import com.axonivy.portal.service.filter.operatorpolicy.service.BaselineOperatorService;
import com.axonivy.portal.service.filter.operatorpolicy.service.OperatorPolicyService;

import ch.ivy.addon.portalkit.dto.dashboard.ColumnModel;
import ch.ivy.addon.portalkit.enums.DashboardStandardTaskColumn;
import ch.ivy.addon.portalkit.enums.GlobalVariable;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.environment.IvyTest;

@IvyTest
class TestOperatorPolicyService {

  private static final String OPERATOR_POLICY_KEY = GlobalVariable.FILTER_OPERATOR_POLICY.getKey();

  @AfterEach
  void tearDown() {
    Ivy.var().set(OPERATOR_POLICY_KEY, GlobalVariable.FILTER_OPERATOR_POLICY.getDefaultValue());
  }

  // ── readWidgetPolicy ────────────────────────────────────────────────────────

  @Test
  void readWidgetPolicy_returnsEmpty_whenNoColumnsHaveAllowedOperators() {
    ColumnModel col = OperatorPolicyFixtures.filterableColumn(DashboardStandardTaskColumn.NAME.getField(), null);
    OperatorPolicyService service = serviceWith(List.of(col));

    assertThat(service.readWidgetPolicy().getAllowedOperatorsByField()).isEmpty();
  }

  @Test
  void readWidgetPolicy_reflectsAllowedOperators_fromColumnModel() {
    ColumnModel col = OperatorPolicyFixtures.filterableColumn(DashboardStandardTaskColumn.NAME.getField(),
        List.of(FilterOperator.CONTAINS, FilterOperator.IS_NOT));

    OperatorPolicyService service = serviceWith(List.of(col));

    OperatorPolicy widgetPolicy = service.readWidgetPolicy();
    assertThat(widgetPolicy.hasPolicyForField(DashboardStandardTaskColumn.NAME.getField())).isTrue();
    assertThat(widgetPolicy.getAllowedOperators(DashboardStandardTaskColumn.NAME.getField()))
        .containsExactlyInAnyOrder(FilterOperator.CONTAINS, FilterOperator.IS_NOT);
  }

  // ── keepFiltersAllowedByPolicy ─────────────────────────────────────────────

  @Test
  void keepFiltersAllowedByPolicy_keepsFilter_whenNoWidgetPolicyExistsForField() {
    OperatorPolicyService service = serviceWith(List.of());
    DashboardFilter filter = OperatorPolicyFixtures.filter(DashboardStandardTaskColumn.STATE.getField(), FilterOperator.IS);

    assertThat(service.keepFiltersAllowedByPolicy(List.of(filter))).containsExactly(filter);
  }

  @Test
  void keepFiltersAllowedByPolicy_removesFilter_whenBlockedByGlobalPolicy() {
    OperatorPolicyService service = serviceWith(List.of(), FilterOperator.CONTAINS);
    DashboardFilter filter = OperatorPolicyFixtures.filter(DashboardStandardTaskColumn.NAME.getField(), FilterOperator.CONTAINS);

    assertThat(service.keepFiltersAllowedByPolicy(List.of(filter))).isEmpty();
  }

  @Test
  void keepFiltersAllowedByPolicy_keepsFilter_whenAllowedByWidgetPolicy() {
    ColumnModel col = OperatorPolicyFixtures.filterableColumn(DashboardStandardTaskColumn.NAME.getField(),
        List.of(FilterOperator.CONTAINS));
    OperatorPolicyService service = serviceWith(List.of(col));

    DashboardFilter allowed = OperatorPolicyFixtures.filter(DashboardStandardTaskColumn.NAME.getField(), FilterOperator.CONTAINS);
    DashboardFilter blocked = OperatorPolicyFixtures.filter(DashboardStandardTaskColumn.NAME.getField(), FilterOperator.IS_NOT);

    assertThat(service.keepFiltersAllowedByPolicy(List.of(allowed, blocked)))
        .containsExactly(allowed)
        .doesNotContain(blocked);
  }

  @Test
  void keepFiltersAllowedByPolicy_removesFilter_whenBlockedByWidgetPolicy_evenIfGloballyAllowed() {
    ColumnModel col = OperatorPolicyFixtures.filterableColumn(DashboardStandardTaskColumn.NAME.getField(),
        List.of(FilterOperator.CONTAINS));
    OperatorPolicyService service = serviceWith(List.of(col));

    DashboardFilter filter = OperatorPolicyFixtures.filter(DashboardStandardTaskColumn.NAME.getField(), FilterOperator.IS_NOT);

    assertThat(service.keepFiltersAllowedByPolicy(List.of(filter))).isEmpty();
  }

  @Test
  void keepFiltersAllowedByPolicy_removesFilter_whenBlockedByBothPolicies() {
    ColumnModel col = OperatorPolicyFixtures.filterableColumn(DashboardStandardTaskColumn.NAME.getField(),
        List.of(FilterOperator.CONTAINS, FilterOperator.IS_NOT));
    OperatorPolicyService service = serviceWith(List.of(col), FilterOperator.IS_NOT);

    DashboardFilter filter = OperatorPolicyFixtures.filter(DashboardStandardTaskColumn.NAME.getField(), FilterOperator.IS_NOT);

    assertThat(service.keepFiltersAllowedByPolicy(List.of(filter))).isEmpty();
  }

  @Test
  void keepFiltersAllowedByPolicy_returnsEmpty_forEmptyInput() {
    OperatorPolicyService service = serviceWith(List.of());

    assertThat(service.keepFiltersAllowedByPolicy(List.of())).isEmpty();
  }

  @Test
  void keepFiltersAllowedByPolicy_filtersAcrossMultipleFields() {
    ColumnModel stateCol = OperatorPolicyFixtures.filterableColumn(DashboardStandardTaskColumn.STATE.getField(), List.of(FilterOperator.IS));
    ColumnModel nameCol = OperatorPolicyFixtures.filterableColumn(DashboardStandardTaskColumn.NAME.getField(), List.of(FilterOperator.CONTAINS));
    OperatorPolicyService service = serviceWith(List.of(stateCol, nameCol));

    DashboardFilter validState = OperatorPolicyFixtures.filter(DashboardStandardTaskColumn.STATE.getField(), FilterOperator.IS);
    DashboardFilter invalidState = OperatorPolicyFixtures.filter(DashboardStandardTaskColumn.STATE.getField(), FilterOperator.IS_NOT);
    DashboardFilter validName = OperatorPolicyFixtures.filter(DashboardStandardTaskColumn.NAME.getField(), FilterOperator.CONTAINS);

    assertThat(service.keepFiltersAllowedByPolicy(List.of(validState, invalidState, validName)))
        .containsExactlyInAnyOrder(validState, validName)
        .doesNotContain(invalidState);
  }

  // ── resolveEffectiveOperators (override) ───────────────────────────────────

  @Test
  void resolveEffectiveOperators_returnsGlobalEffective_whenNoWidgetPolicyExistsForField() {
    OperatorPolicyService service = serviceWith(List.of());
    DashboardFilter filter = OperatorPolicyFixtures.filterForField(DashboardStandardTaskColumn.NAME.getField());

    assertThat(service.resolveEffectiveOperators(filter))
        .containsExactlyInAnyOrderElementsOf(FilterOperator.TEXT_OPERATORS);
  }

  @Test
  void resolveEffectiveOperators_intersectsGlobalAndWidgetPolicy() {
    // Widget allows CONTAINS + IS_NOT; global disables IS_NOT -> result: only CONTAINS
    ColumnModel col = OperatorPolicyFixtures.filterableColumn(DashboardStandardTaskColumn.NAME.getField(),
        List.of(FilterOperator.CONTAINS, FilterOperator.IS_NOT));
    OperatorPolicyService service = serviceWith(List.of(col), FilterOperator.IS_NOT);

    DashboardFilter filter = OperatorPolicyFixtures.filterForField(DashboardStandardTaskColumn.NAME.getField());

    assertThat(service.resolveEffectiveOperators(filter))
        .containsExactly(FilterOperator.CONTAINS);
  }

  @Test
  void resolveEffectiveOperators_returnsEmpty_whenGlobalBlocksAllWidgetOps() {
    ColumnModel col = OperatorPolicyFixtures.filterableColumn(DashboardStandardTaskColumn.NAME.getField(),
        List.of(FilterOperator.CONTAINS));
    OperatorPolicyService service = serviceWith(List.of(col), FilterOperator.CONTAINS);

    DashboardFilter filter = OperatorPolicyFixtures.filterForField(DashboardStandardTaskColumn.NAME.getField());

    assertThat(service.resolveEffectiveOperators(filter)).isEmpty();
  }

  @Test
  void resolveEffectiveOperators_returnsEmpty_whenWidgetOpsHaveNoOverlapWithGlobalBaseline() {
    // Baseline for NAME is TEXT_OPERATORS; widget only allows EQUAL (a number operator)
    ColumnModel col = OperatorPolicyFixtures.filterableColumn(DashboardStandardTaskColumn.NAME.getField(),
        List.of(FilterOperator.EQUAL));
    OperatorPolicyService service = serviceWith(List.of(col));

    DashboardFilter filter = OperatorPolicyFixtures.filterForField(DashboardStandardTaskColumn.NAME.getField());

    assertThat(service.resolveEffectiveOperators(filter)).isEmpty();
  }

  @ParameterizedTest(name = "resolveEffectiveOperators_returnsNonEmpty_for{0}FieldWithPermissiveWidgetPolicy")
  @EnumSource(value = DashboardStandardTaskColumn.class, names = {"STATE", "PRIORITY", "CREATED"})
  void resolveEffectiveOperators_returnsNonEmpty_forWellKnownFieldsWithPermissiveWidgetPolicy(DashboardStandardTaskColumn taskColumn) {

    List<FilterOperator> baselineOps =
        new BaselineOperatorService().resolveByField(taskColumn.getField());
    ColumnModel col = OperatorPolicyFixtures.filterableColumn(taskColumn.getField(), baselineOps);
    OperatorPolicyService service = serviceWith(List.of(col));

    DashboardFilter filter = OperatorPolicyFixtures.filterForField(taskColumn.getField());

    assertThat(service.resolveEffectiveOperators(filter)).isNotEmpty();
  }

  // ── findFirstEnabledOp: onSelectFilter scenario ────────────────────────────

  @Test
  void findFirstEnabledOp_returnsDefaultOp_whenDefaultIsAllowedByBothPolicies() {
    ColumnModel col = OperatorPolicyFixtures.filterableColumn(DashboardStandardTaskColumn.NAME.getField(),
        List.of(FilterOperator.CONTAINS, FilterOperator.IS_NOT));
    OperatorPolicyService service = serviceWith(List.of(col));

    DashboardFilter filter = OperatorPolicyFixtures.filterForField(DashboardStandardTaskColumn.NAME.getField());

    assertThat(service.findFirstEnabledOp(filter, FilterOperator.CONTAINS))
        .contains(FilterOperator.CONTAINS);
  }

  @Test
  void findFirstEnabledOp_returnsFallback_whenDefaultIsBlockedByWidgetPolicy() {
    ColumnModel col = OperatorPolicyFixtures.filterableColumn(DashboardStandardTaskColumn.NAME.getField(),
        List.of(FilterOperator.IS_NOT));
    OperatorPolicyService service = serviceWith(List.of(col));

    DashboardFilter filter = OperatorPolicyFixtures.filterForField(DashboardStandardTaskColumn.NAME.getField());

    assertThat(service.findFirstEnabledOp(filter, FilterOperator.CONTAINS))
        .contains(FilterOperator.IS_NOT);
  }

  @Test
  void findFirstEnabledOp_returnsEmpty_whenNoPolicyOverlapExists() {
    // Widget allows only CONTAINS; global blocks CONTAINS
    ColumnModel col = OperatorPolicyFixtures.filterableColumn(DashboardStandardTaskColumn.NAME.getField(),
        List.of(FilterOperator.CONTAINS));
    OperatorPolicyService service = serviceWith(List.of(col), FilterOperator.CONTAINS);

    DashboardFilter filter = OperatorPolicyFixtures.filterForField(DashboardStandardTaskColumn.NAME.getField());

    assertThat(service.findFirstEnabledOp(filter, FilterOperator.CONTAINS)).isEmpty();
  }

  private static OperatorPolicyService serviceWith(List<ColumnModel> columns, FilterOperator... disabled) {
    Set<FilterOperator> disabledSet = Set.of(disabled);
    String enabledValue = Arrays.stream(FilterOperator.values())
        .filter(op -> !disabledSet.contains(op))
        .map(FilterOperator::name)
        .collect(Collectors.joining(","));

    Ivy.var().set(OPERATOR_POLICY_KEY, enabledValue);
    return new OperatorPolicyService(columns);
  }
}