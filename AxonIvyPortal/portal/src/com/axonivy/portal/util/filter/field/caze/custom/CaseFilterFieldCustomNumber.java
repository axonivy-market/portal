package com.axonivy.portal.util.filter.field.caze.custom;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;
import com.axonivy.portal.enums.dashboard.filter.FilterOperator;
import com.axonivy.portal.enums.dashboard.filter.FilterFormat;
import com.axonivy.portal.util.filter.field.CustomFilterField;
import com.axonivy.portal.util.filter.operator.caze.customfield.CustomNumberBetweenOperatorHandler;
import com.axonivy.portal.util.filter.operator.caze.customfield.CustomNumberEmptyOperatorHandler;
import com.axonivy.portal.util.filter.operator.caze.customfield.CustomNumberEqualOperatorHandler;
import com.axonivy.portal.util.filter.operator.caze.customfield.CustomNumberGreaterOperatorHandler;
import com.axonivy.portal.util.filter.operator.caze.customfield.CustomNumberLessOperatorHandler;

import ch.ivy.addon.portalkit.enums.DashboardColumnType;
import ch.ivyteam.ivy.workflow.custom.field.ICustomFieldMeta;
import ch.ivyteam.ivy.workflow.query.CaseQuery;

public class CaseFilterFieldCustomNumber extends CustomFilterField {

  private ICustomFieldMeta customField;

  public CaseFilterFieldCustomNumber() {}

  public CaseFilterFieldCustomNumber(ICustomFieldMeta customField) {
    super(customField.name(), FilterFormat.NUMBER);
    this.customField = customField;
  }

  public String getLabel() {
    return customField.label();
  }

  @Override
  public void initFilter(DashboardFilter filter) {
    filter.setFilterField(this);
    filter.setFilterType(DashboardColumnType.CUSTOM);
    filter.setFilterFormat(FilterFormat.NUMBER);
    filter.setField(getName());
  }

  @Override
  public void addNewFilter(DashboardFilter filter) {
    initFilter(filter);
    filter.setOperator(FilterOperator.NOT_EMPTY);
    filter.setFrom(null);
    filter.setTo(null);
    filter.setValue(null);
  }

  @Override
  public CaseQuery generateFilterQuery(DashboardFilter filter) {
    return switch (filter.getOperator()) {
      case EQUAL -> CustomNumberEqualOperatorHandler.getInstance().buildEqualQuery(filter);
      case NOT_EQUAL -> CustomNumberEqualOperatorHandler.getInstance().buildNotEqualQuery(filter);
      case LESS -> CustomNumberLessOperatorHandler.getInstance().buildLessQuery(filter);
      case LESS_OR_EQUAL -> CustomNumberLessOperatorHandler.getInstance().buildLessOrEqualQuery(filter);
      case GREATER -> CustomNumberGreaterOperatorHandler.getInstance().buildGreaterQuery(filter);
      case GREATER_OR_EQUAL -> CustomNumberGreaterOperatorHandler.getInstance().buildGreaterOrEqualQuery(filter);
      case BETWEEN -> CustomNumberBetweenOperatorHandler.getInstance().buildEqualQuery(filter);
      case NOT_BETWEEN -> CustomNumberBetweenOperatorHandler.getInstance().buildNotEqualQuery(filter);
      case EMPTY -> CustomNumberEmptyOperatorHandler.getInstance().buildEmptyQuery(filter);
      case NOT_EMPTY -> CustomNumberEmptyOperatorHandler.getInstance().buildNotEmptyQuery(filter);
      default -> null;
    };
  }
}